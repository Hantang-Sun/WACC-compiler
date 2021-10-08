package newclass;

import types.ErrorType;
import compiler.SemanticCheckData;
import expressions.AssignRHS;
import expressions.Expr;
import expressions.Ident;
import functions.FunctionType;
import types.ClassType;
import types.Type;

import java.util.List;

public class NewObject implements AssignRHS {

  private Ident constructorName;
  private final List<Expr> exprs;
  private final int lineNum;
  private final int linePos;
  private Type type;

  public NewObject(Ident constructorName,
                   List<Expr> exprs, int lineNum, int linePos) {
    this.constructorName = constructorName;
    this.exprs = exprs;
    this.lineNum = lineNum;
    this.linePos = linePos;
    type = new ClassType(constructorName.getString());
  }

  public List<Expr> getExprs() {
    return exprs;
  }

  public Ident getConstructorName() {
    return constructorName;
  }

  @Override
  public Type getType(SemanticCheckData semanticCheckData) {
    Type ERROR_TYPE = new ErrorType();

    //check that class exists
    Newclass newclass = semanticCheckData.getClassNames().get(constructorName.getString());
    if (newclass == null) {
      semanticCheckData.getErrorLog().error("Error in line " + lineNum + ":" +
                    linePos + "\n" + "Class " + constructorName.getString() +
                    " does not exist.");
      return ERROR_TYPE;
    }

    //setup variables used type checking
    List<FunctionType> construcArgs = semanticCheckData.getConstructorTypes()
            .get(constructorName.getString());
    int paramNum = exprs.size();


    //case where there are no constructors
    if (construcArgs == null) {
      semanticCheckData.getErrorLog().error("Error in line " + lineNum + ":" +
              linePos + "\n" + "Class " + constructorName.getString() +
              " has no constructors");
      return ERROR_TYPE;

    //case where there is one constructor
    } else if (construcArgs.size() == 1) {
      List<Type> args = construcArgs.get(0).getInputType();
      if (args.size() != paramNum) {
        semanticCheckData.getErrorLog().error("Error in line " + lineNum + ":" +
                linePos + "\n" + "Class " + constructorName.getString() +
                "'s constructor takes " + args.size() + " parameters but was" +
                " given " + paramNum + " parameters");
        return ERROR_TYPE;
      }


      for (Expr expr : exprs) {
        Type exprType = expr.getType(semanticCheckData);
        if (ERROR_TYPE.match(exprType)) {
          return ERROR_TYPE;
        }
      }
      boolean sameParams = true;
      for (int i = 0; i < paramNum; i++) {
        Type exprType = exprs.get(i).getType();
        if (!exprType.match(args.get(i))) {
          sameParams = false;
          break;
        }
      }
      if (sameParams) {
        constructorName = new Ident(construcArgs.get(0).getFuncName(), 0, 0);
        return type;
      }

      //call does not match constructor
      String constructorError = "Class " + constructorName.getString() +
              "'s constructor takes types ";
      for (Type arg : args) {
        constructorError += arg.toString() + " ";
      }
      constructorError += "but was given types ";
      for (Expr expr : exprs) {
        constructorError += expr.getType() + " ";
      }
      constructorError += "as arguments";
      semanticCheckData.getErrorLog().error("Error in line " + lineNum + ":" +
              linePos + "\n" + constructorError);
      return new ErrorType();
    }

    //case where there are multiple constructors
    for (Expr expr : exprs) {
      Type exprType = expr.getType(semanticCheckData);
      if (ERROR_TYPE.match(exprType)) {
        return ERROR_TYPE;
      }
    }
    for (FunctionType construcArg : construcArgs) {
      boolean sameParams = false;
      List<Type> args = construcArg.getInputType();
      if (paramNum == args.size()) {
        sameParams = true;
        for (int i = 0; i < paramNum; i++) {
          Type exprType = exprs.get(i).getType();
          if (!exprType.match(args.get(i))) {
            sameParams = false;
            break;
          }
        }
      }
      if (sameParams) {
        constructorName = new Ident(construcArg.getFuncName(), 0, 0);
        return type;
      }
    }

    //Case where none of the constructors match
    String constructorError = "Class " + constructorName.getString() + " has "
            + "no constructor that takes types ";
    for (Expr expr : exprs) {
      constructorError += expr.getType().toString() + " ";
    }
    constructorError += "as parameters";
    semanticCheckData.getErrorLog().error("Error in line " + lineNum + ":" +
              linePos + "\n" + constructorError);
    return new ErrorType();
  }

  @Override
  public Type getType() {
    return type;
  }
}
