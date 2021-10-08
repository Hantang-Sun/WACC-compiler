package newclass;

import compiler.SemanticCheckData;
import compiler.SemanticErrorLog;
import expressions.AssignLHS;
import expressions.AssignRHS;
import expressions.Expr;
import expressions.Ident;
import functions.FunctionType;
import newclass.ClassOp;
import statements.Stat;
import statements.StatType;
import types.ClassType;
import types.ErrorType;
import types.Type;

import java.util.HashMap;
import java.util.List;

public class ClassCall implements Stat, AssignRHS, AssignLHS {

  private final Ident varName;
  private final List<ClassOp> classOps;
  private Type type;
  private final boolean isSelf;

  private final int lineNum;
  private final int linePos;

  public ClassCall(Ident varName, List<ClassOp> classOps, boolean isSelf, int lineNum, int linePos) {
    this.varName = varName;
    this.classOps = classOps;
    this.isSelf = isSelf;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  @Override
  public Type getType(SemanticCheckData semanticCheckData) {
    Type ERROR_TYPE = new ErrorType();
    SemanticErrorLog errorLog = semanticCheckData.getErrorLog();
    String insideClass = semanticCheckData.getInsideClass();
    HashMap<String, Newclass> classNames = semanticCheckData.getClassNames();
    boolean finalClassOpIsAttributeAccess = false;

    Type callerType;
    if (isSelf) {
      if (insideClass == null) {
        errorLog.error("Error inl line " + lineNum + ":" + linePos + "\n" +
                "Tried to use self outside of a class context");
        return ERROR_TYPE;
      }
      callerType = new ClassType(insideClass);
      varName.setType(callerType);
    } else {
      callerType = varName.getType(semanticCheckData);
    }

    for (ClassOp classOp : classOps) {

      //case where method call was used on something that's not an object
      if (!(callerType instanceof ClassType)) {
        errorLog.error("Error in line " + lineNum + ":" +
                linePos + "\n" +
                "Expected an object but was given type " + callerType.toString());
        return ERROR_TYPE;
      }

      ClassType classType = (ClassType) callerType;
      String callerClassName = classType.getClassName();
      Newclass callerClass = classNames.get(callerClassName);

      //case where we are accessing a field of the class
      if (classOp instanceof Ident) {
        finalClassOpIsAttributeAccess = true;
        Ident attribute = (Ident) classOp;
        boolean isContinue = false;

        //find attribute object
        for (ClassAttribute classAttribute : callerClass.getAttributes()) {
          if (classAttribute.getName().getString().equals(attribute.getString())) {

            //check that it is public or we are inside the class of the attribute
            if (callerClassName.equals(insideClass) || classAttribute.isPublic()) {
              callerType = classAttribute.getType();
              attribute.setType(callerType);
              isContinue = true;
              break;
            }
            //attribute must be private and outside class
            errorLog.error("Error in line " + lineNum + ":" +
                    linePos + "\n" +
                    "Tried to access private attribute " + attribute.getString() +
                    " outside class " + callerClassName);
            return ERROR_TYPE;
          }
        }
        if (isContinue) {
          continue;
        }

        //cannot find attribute
        errorLog.error("Error in line " + lineNum + ":" +
                linePos + "\n" +
                "Attribute " + attribute.getString() + " is not a field of" +
                " class " + callerClassName);
        return ERROR_TYPE;

        //case where we are doing a method call
      } else {
        finalClassOpIsAttributeAccess = false;
        MethodCall methodCall = (MethodCall) classOp;
        List<Expr> exprs = methodCall.getExprs();
        String methodName = methodCall.getMethodName().getString();

        HashMap<String, List<FunctionType>> methodType = semanticCheckData.getMethodTypes().get(callerClassName);
        List<FunctionType> methodSignatures = methodType.get(methodName);

        boolean isContinue = false;

        //case where the calling class doesn't have a method with that name
        if (methodSignatures == null) {
          if (methodName.equals(callerClassName)) {
            errorLog.error("Error in line " + lineNum + ":" +  linePos + "\n" +
                    "Constructor " + methodName + " is not callable");
            return ERROR_TYPE;
          }
          errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
                  "Method " + methodName + " does not exist in class " +
                  callerClassName);
          return ERROR_TYPE;

        //case where the calling class has one method with that name
        } else if (methodSignatures.size() == 1) {

          if (!methodSignatures.get(0).isPublic() &&
                  !callerClassName.equals(insideClass)) {
            errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
                    "Method " + methodName + " is a private method, " +
                    "cannot be called outside of class " + callerClassName);
          }
          //param count matching
          List<Type> argTypes = methodSignatures.get(0).getInputType();
          if (argTypes.size() != exprs.size()) {
            errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
                    "Method " + methodName + " in class " + callerClassName +
                    " takes " + argTypes.size() + " parameters but was given " +
                    exprs.size() + " parameters");
            return ERROR_TYPE;
          }
          //param types matching
          for (Expr expr : exprs) {
            Type exprType = expr.getType(semanticCheckData);
            if (ERROR_TYPE.match(exprType)) {
              return ERROR_TYPE;
            }
          }
          boolean sameParams = true;
          for (int i = 0; i < exprs.size(); i++) {
            Type paramType = exprs.get(i).getType();
            if (!paramType.match(argTypes.get(i))) {
              sameParams = false;
              break;
            }
          }
          if (sameParams) {
            callerType = methodSignatures.get(0).getReturnType();
            methodCall.setType(callerType);
            methodCall.setMethodName(new Ident(methodSignatures.get(0).getFuncName(), 0, 0));
            continue;
          }
          String methodError = "Method " + methodName + " in class " +
                  callerClass.getName() + " takes types ";
          for (Type argType : argTypes) {
            methodError += argType + " ";
          }
          methodError += "as parameters but was given types ";
          for (Expr expr : exprs) {
            methodError += expr.getType() + " ";
          }
          methodError += "as arguments";
          errorLog.error("Error in line " + lineNum + ":" +
                  linePos + "\n" + methodError);
          return ERROR_TYPE;
        }

        //case where method is overloaded
        for (Expr expr : exprs) {
          Type exprType = expr.getType(semanticCheckData);
          if (ERROR_TYPE.match(exprType)) {
            return ERROR_TYPE;
          }
        }

        for (FunctionType methodSignature : methodSignatures) {
          boolean sameParams = true;
          List<Type> argTypes = methodSignature.getInputType();

          //check arg no
          if (argTypes.size() == exprs.size()) {

            //check param types
            for (int i = 0; i < exprs.size(); i++) {
              Type exprType = exprs.get(i).getType();

              if (!argTypes.get(i).match(exprType)) {
                sameParams = false;
                break;
              }
            }

            if (sameParams) {
              if (methodSignature.isPublic() ||
                      callerClassName.equals(insideClass)) {
                callerType = methodSignature.getReturnType();
                methodCall.setType(callerType);
                methodCall.setMethodName(new Ident(methodSignature.getFuncName(), 0, 0));
                isContinue = true;
                break;
              } else {
                //private method access outside of class
                errorLog.error("Error in line " + lineNum + ":" + linePos + "\n"
                        + "Tried to access private method " + methodName +
                        " outside class " + callerClassName);
                return ERROR_TYPE;
              }
            }
          }
        }

        if (isContinue) {
          continue;
        }

        //cannot find method
        String methodError = "Class " + callerClassName + " has no method " +
                methodName + " that takes types ";
        for (Expr expr : exprs) {
          methodError += expr.getType().toString() + " ";
        }
        methodError += "as parameters";
        errorLog.error("Error in line " + lineNum + ":" +
                linePos + "\n" + methodError);
        return ERROR_TYPE;
      }
    }
    //Class call on the lhs of an assignment must be an attribute access
    if (semanticCheckData.isAssignLhs()) {
      if (!finalClassOpIsAttributeAccess) {
        errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
                "Cannot have a method call on the left hand side of an " +
                "assignment");
        return ERROR_TYPE;
      }
    }

    this.type = callerType;
    return this.type;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public StatType getStatType() {
    return StatType.CLASS_CALL;
  }

  @Override
  public int getLineNum() {
    return lineNum;
  }

  @Override
  public int getLinePos() {
    return linePos;
  }

  public Ident getVarName() {
    return varName;
  }

  public List<ClassOp> getClassOps() {
    return classOps;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
