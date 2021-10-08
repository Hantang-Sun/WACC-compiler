package expressions;

import compiler.*;
import types.*;

import java.util.List;

public class ArrayLiter implements AssignRHS {

  private final List<Expr> exprs;
  private final int lineNum;
  private final int linePos;
  private Type type = null;

  public ArrayLiter(List<Expr> exprs, int lineNum, int linePos) {
    this.exprs = exprs;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public List<Expr> getExprs() {
    return exprs;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    if (exprs.isEmpty()) {
      this.type = new ArrayType(new AnyType());
      return new ArrayType(new AnyType());
    }

    Type type = exprs.get(0).getType(data);
    for (int i = 0; i < exprs.size(); i++) {
      Type exprType = exprs.get(i).getType(data);

      if (!type.match(exprType)) {
        data.getErrorLog().error("Error in line " + lineNum + ":" + linePos +
                "\nArray literal conflicting types at indices 0 and " + i +
                ": " + type.toString() + " and " + exprType.toString());
        return new ErrorType();
      }
    }
    this.type = new ArrayType(type);
    return new ArrayType(type);
  }

  @Override
  public Type getType() {
    if (this.type == null) {
      System.out.println("something isn't right");
      return null;
    }
    return this.type;
  }
}