package expressions;

import compiler.*;
import types.*;

public class BinOpExpr extends Expr {

  private BinOp op;
  private Expr expr1;
  private Expr expr2;
  private int lineNum;
  private int linePos;
  private Type type;

  public BinOpExpr(BinOp op, Expr expr1, Expr expr2, int lineNum, int linePos) {
    this.lineNum = lineNum;
    this.linePos = linePos;
    this.op = op;
    this.expr1 = expr1;
    this.expr2 = expr2;
  }

  public BinOp getOp() {
    return op;
  }

  public Expr getExpr1() {
    return expr1;
  }

  public Expr getExpr2() {
    return expr2;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    SemanticErrorLog errorLog = data.getErrorLog();

    Type type1 = expr1.getType(data);
    Type type2 = expr2.getType(data);

    if (op == BinOp.ADD || op == BinOp.SUB || op == BinOp.MULT
            || op == BinOp.DIVIDE || op == BinOp.MOD || op == BinOp.BITWISEAND
            || op == BinOp.BITWISEOR || op == BinOp.BITWISEXOR
            || op == BinOp.SHIFTLEFT || op == BinOp.SHIFTRIGHT) {

      if (type1.match(new BaseType(BaseTypeEnum.INT))
              && type2.match(new BaseType(BaseTypeEnum.INT))) {
        this.type = new BaseType(BaseTypeEnum.INT);
        return new BaseType(BaseTypeEnum.INT);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected INT and INT, but was given " +
              type1.toString() + " and " + type2.toString());
      return new ErrorType();
    }

    if (op == BinOp.EQUALS || op == BinOp.NOTEQUAL) {
      if (type1.match(type2)) {
        this.type = new BaseType(BaseTypeEnum.BOOL);
        return new BaseType(BaseTypeEnum.BOOL);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected matching type, but was given "
              + type1.toString() + " and " + type2.toString());
      return new ErrorType();
    }

    if (op == BinOp.AND || op == BinOp.OR) {
      if (type1.match(new BaseType(BaseTypeEnum.BOOL))
              && type2.match(new BaseType(BaseTypeEnum.BOOL))) {
        this.type = new BaseType(BaseTypeEnum.BOOL);
        return new BaseType(BaseTypeEnum.BOOL);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() + " operator expected BOOL and BOOL, but was given "
              + type1.toString() + " and " + type2.toString());
      return new ErrorType();
    }

    if (op == BinOp.GREATER || op == BinOp.GREATEREQ ||
            op == BinOp.LESS || op == BinOp.LESSEQ) {
      if (type1.match(new BaseType(BaseTypeEnum.INT)) &&
              type2.match(new BaseType(BaseTypeEnum.INT))) {
        this.type = new BaseType(BaseTypeEnum.BOOL);
        return new BaseType(BaseTypeEnum.BOOL);
      }
      if (type1.match(new BaseType(BaseTypeEnum.CHAR)) &&
              type2.match(new BaseType(BaseTypeEnum.CHAR))) {
        this.type = new BaseType(BaseTypeEnum.BOOL);
        return new BaseType(BaseTypeEnum.BOOL);
      }
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              op.toString() +
              " operator expected INT and INT or CHAR and CHAR, but was given "
              + type1.toString() + " and " + type2.toString());
      return new ErrorType();
    }

    errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
            "Unknown syntax error, " + op.toString() + " does not exist");
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
