package newclass;

import compiler.SemanticCheckData;
import expressions.Expr;
import expressions.Ident;
import types.Type;

import java.util.List;

public class MethodCall implements ClassOp {

  private Ident methodName;
  private final List<Expr> exprs;
  private Type returnType;

  public MethodCall(Ident methodName, List<Expr> exprs) {
    this.methodName = methodName;
    this.exprs = exprs;
  }

  public Type getReturnType() {
    return returnType;
  }

  public Ident getMethodName() {
    return methodName;
  }

  public void setMethodName(Ident methodName) {
    this.methodName = methodName;
  }

  public List<Expr> getExprs() {
    return exprs;
  }

  public void setType(Type returnType) {
    this.returnType = returnType;
  }

  @Override
  public int getLinePos() {
    return methodName.getLinePos();
  }

  @Override
  public int getLineNum() {
    return methodName.getLinePos();
  }
}
