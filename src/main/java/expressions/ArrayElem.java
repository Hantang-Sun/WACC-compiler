package expressions;

import compiler.*;
import types.*;

import java.util.List;

public class ArrayElem extends Expr implements AssignLHS {

  private String name;
  private List<Expr> exprs;
  private int lineNum;
  private int linePos;
  private Type type = null;

  public ArrayElem(String name, List<Expr> exprs, int lineNum,
                   int linePos) {
    this.lineNum = lineNum;
    this.linePos = linePos;
    this.name = name;
    this.exprs = exprs;
  }

  public String getName() {
    return name;
  }

  public List<Expr> getExprs() {
    return exprs;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    SemanticErrorLog errorLog = data.getErrorLog();
    Scope scope = data.getCurrScope();

    Type type = scope.getType(name, errorLog, lineNum, linePos);

    for (Expr expr : exprs) {
      Type exprType = expr.getType(data);

      if (!(type instanceof ArrayType)) {
        errorLog.error("Error in line " + lineNum + ":" + linePos +
                "\nArray expected type ARRAY, but elem was called on " +
                type.toString() + ", maybe dimensions are wrong");
        return new ErrorType();

      } else if (!exprType.match(new BaseType(BaseTypeEnum.INT))) {
        errorLog.error("Error in line " + lineNum + ":" + linePos +
                "\nArray elem expected type INT, but was given " +
                exprType.toString());
        return new ErrorType();

      } else {
        type = ((ArrayType)type).getType();
      }
    }
    this.type = type;
    return type;
  }

  @Override
  public Type getType() {
    if (this.type == null) {
      System.out.println("ArrayElem: something isn't right");
      return null;
    }
    return this.type;
  }
}
