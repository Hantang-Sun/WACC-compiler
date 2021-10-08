package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class CharLiter extends Expr {

  private final char c;

  public CharLiter(char c) {
    this.c = c;
  }

  public char getChar() {
    return c;
  }


  @Override
  public Type getType(SemanticCheckData data) {
    return new BaseType(BaseTypeEnum.CHAR);
  }

  @Override
  public Type getType() {
    return new BaseType(BaseTypeEnum.CHAR);
  }
}
