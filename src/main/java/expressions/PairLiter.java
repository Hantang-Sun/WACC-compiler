package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class PairLiter extends Expr {

  public PairLiter() {}

  @Override
  public Type getType(SemanticCheckData data) {
    return new PairType();
  }

  @Override
  public Type getType() {
    return new PairType();
  }

}