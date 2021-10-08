package expressions;

import compiler.*;
import types.*;

public class UnOpExpr extends Expr {

  private UnOp op;
  private Expr expr;
  private final int lineNum;
  private final int linePos;
  private Type type = null;

  public UnOpExpr(UnOp op, Expr expr, int lineNum, int linePos) {
    this.lineNum = lineNum;
    this.linePos = linePos;
    this.op = op;
    this.expr = expr;
  }

  public UnOp getOp() {
    return op;
  }

  public Expr getExpr() {
    return expr;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    SemanticErrorLog errorLog = data.getErrorLog();

    Type type = expr.getType(data);
    if (op == UnOp.NOT) {
      if (type.match(new BaseType(BaseTypeEnum.BOOL))) {
        this.type = new BaseType(BaseTypeEnum.BOOL);
        return new BaseType(BaseTypeEnum.BOOL);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected BOOL, but was given " +
              type.toString());
      return new ErrorType();
    }

    if (op == UnOp.NEGATE) {
      if (type.match(new BaseType(BaseTypeEnum.INT))) {
        this.type = new BaseType(BaseTypeEnum.INT);
        return new BaseType(BaseTypeEnum.INT);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected INT, but was given " +
              type.toString());
      return new ErrorType();
    }

    if (op == UnOp.LEN) {
      if (type instanceof ArrayType) {
        this.type = new BaseType(BaseTypeEnum.INT);
        return new BaseType(BaseTypeEnum.INT);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected an ARRAY, but was given " +
              type.toString());
      return new ErrorType();
    }

    if (op == UnOp.ORD) {
      if (type.match(new BaseType(BaseTypeEnum.CHAR))) {
        this.type = new BaseType(BaseTypeEnum.INT);
        return new BaseType(BaseTypeEnum.INT);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected CHAR, but was given " +
              type.toString());
      return new ErrorType();
    }

    if (op == UnOp.CHR) {
      if (type.match(new BaseType(BaseTypeEnum.INT))) {
        this.type = new BaseType(BaseTypeEnum.CHAR);
        return new BaseType(BaseTypeEnum.CHAR);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected INT, but was given " +
              type.toString());
      return new ErrorType();
    }
    if (op == UnOp.COMPLEMENT){
      if (type.match(new BaseType(BaseTypeEnum.INT))){
        this.type = new BaseType(BaseTypeEnum.CHAR);
        return new BaseType(BaseTypeEnum.INT);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
          op.toString() + " operator expected INT, but was given " +
          type.toString());
    }
    errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
            op.toString() + " is not a UnOp");
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
