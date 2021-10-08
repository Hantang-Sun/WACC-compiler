package expressions;

import compiler.*;
import types.*;

public class PairElem implements AssignLHS, AssignRHS {

  private final Expr expr;
  private final boolean isFst;
  private final int lineNum;
  private final int linePos;
  private Type type = null;

  public PairElem(Expr expr, boolean isFst, int lineNum, int linePos) {
    this.expr = expr;
    this.isFst = isFst;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Expr getExpr() {
    return expr;
  }

  public boolean isFst() {
    return isFst;
  }

  public int getLineNum() {
    return lineNum;
  }

  public int getLinePos() {
    return linePos;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    Type type = expr.getType(data);

    if (type instanceof PairType) {
      if (!(expr instanceof PairLiter)) {
        if (isFst) {
          this.type = ((PairType) type).getFstType();
          return ((PairType) type).getFstType();
        } else {
          this.type = ((PairType) type).getSndType();
          return ((PairType) type).getSndType();
        }
      }
    }
    String errorMessage = " expected PAIR, but was given " + type.toString();
    if (isFst) {
      errorMessage = "FST" + errorMessage;
    } else {
      errorMessage = "SND" + errorMessage;
    }
    data.getErrorLog().error("Error in line " + lineNum + ":" + linePos + "\n"
            + errorMessage);
    return new ErrorType();
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
