package expressions;

import functions.FunctionType;
import compiler.*;
import statements.Stat;
import statements.StatType;
import types.*;

import java.util.List;

public class Call implements AssignRHS, Stat {

  private Ident funcName;
  private final List<Expr> exprs;
  private int lineNum;
  private int linePos;
  private Type type;

  public Call(Ident funcName, List<Expr> exprs, int lineNum, int linePos) {
    this.lineNum = lineNum;
    this.linePos = linePos;
    this.funcName = funcName;
    this.exprs = exprs;
  }

  public Ident getFuncName() {
    return funcName;
  }

  public List<Expr> getExprs() {
    return exprs;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    SemanticErrorLog errorLog = data.getErrorLog();
    Type ERROR_TYPE = new ErrorType();

    final String FUNC_NAME = funcName.getString();
    List<FunctionType> functionTypes = data.getFuncTypes().get(FUNC_NAME);

    //case where invalid function is given
    if (functionTypes == null) {
      data.getErrorLog().error("Error in line " + lineNum + ":" + linePos +
              "\nFunction " + FUNC_NAME + " has not been defined");
      return ERROR_TYPE;

      //In the case of one function we can give specific error message
    } else if (functionTypes.size() == 1) {
      List<Type> argsType = functionTypes.get(0).getInputType();
      if (argsType.size() != exprs.size()) {
        errorLog.error("Error in line " + lineNum + ":" + linePos +
                "\nIncorrect arg number, expected " + argsType.size() +
                ", but was given " + exprs.size());
        return ERROR_TYPE;
      }
      for (Expr expr : exprs) {
        Type exprType = expr.getType(data);
        if (ERROR_TYPE.match(exprType)) {
          return ERROR_TYPE;
        }
      }
      boolean sameParams = true;
      for (int i = 0; i < exprs.size(); i++) {
        Type exprType = exprs.get(i).getType();
        if (!exprType.match(argsType.get(i))) {
          sameParams = false;
          break;
        }
      }
      if (sameParams) {
        funcName = new Ident(functionTypes.get(0).getFuncName(), 0, 0);
        type = functionTypes.get(0).getReturnType();
        return type;
      }
      String callError = "Function " + FUNC_NAME + "takes types ";
      for (Type type : argsType) {
        callError += type.toString() + " ";
      }
      callError += "as parameters but was give types ";
      for (Expr expr : exprs) {
        callError += expr.getType().toString() + " ";
      }
      callError += "as arguments";
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              callError);
      return ERROR_TYPE;
    }

    for (int j = 0; j < functionTypes.size(); j++) {
      FunctionType funcType = functionTypes.get(j);

      //make sure return type matches
      if (data.getCallType() != null) {
        if (!data.getCallType().match(funcType.getReturnType())) {
          continue;
        }
      }

      List<Type> argsType = funcType.getInputType();
      //make sure number of parameters matches
      if (exprs.size() != argsType.size()) {
        continue;
      }
      //make sure types of parameters matches
      Type exprType;
      for (int i = 0; i < exprs.size(); i++) {
        exprType = exprs.get(i).getType(data);
        if (!exprType.match(argsType.get(i))) {
          continue;
        }
      }

      //this is all good
      this.funcName = new Ident(funcType.getFuncName(), 0, 0);
      this.type = funcType.getReturnType();
      return funcType.getReturnType();
    }

    errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
            "Call parameters don't match expected parameters for any function");

    return ERROR_TYPE;
  }

  @Override
  public Type getType() {
    if (this.type == null) {
      System.out.println("something isn't right");
      return null;
    }
    return this.type;
  }

  @Override
  public StatType getStatType() {
    return StatType.CALL;
  }

  @Override
  public int getLineNum() {
    return lineNum;
  }

  @Override
  public int getLinePos() {
    return linePos;
  }
}
