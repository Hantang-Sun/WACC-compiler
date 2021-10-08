package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class IntLiter extends Expr {

  private int i;

  public IntLiter(String n, boolean minus) {
    if (minus) {
      n = "-" + n;
    }
    try {
      i = Integer.parseInt(n);
    } catch (Exception e) {
      System.out.println("Integer overflow: INT too large");
      System.out.println("#syntax_error#");
      System.exit(100);
    }
  }

  public int getInt() {
    return i;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    return new BaseType(BaseTypeEnum.INT);
  }

  @Override
  public Type getType() {
    return new BaseType(BaseTypeEnum.INT);
  }
}
