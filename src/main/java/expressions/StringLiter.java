package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class StringLiter extends Expr {

  private final String s;

  public StringLiter(String s) {
    this.s = s;
  }

  public String getString() {
    return s;
  }


  @Override
  public Type getType(SemanticCheckData data) {
    return new BaseType(BaseTypeEnum.STRING);
  }

  @Override
  public Type getType() {
    return new BaseType(BaseTypeEnum.STRING);
  }
}
