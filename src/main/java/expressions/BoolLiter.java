package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class BoolLiter extends Expr {

  private boolean b;

  public BoolLiter(boolean b) {
    this.b = b;
  }

  public boolean getBool() {
    return b;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    return new BaseType(BaseTypeEnum.BOOL);
  }

  @Override
  public Type getType() {
    return new BaseType(BaseTypeEnum.BOOL);
  }
}
