package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public class NewPair implements AssignRHS {

  private final Expr fst;
  private final Expr snd;
  private Type type = null;

  public NewPair(Expr fst, Expr snd) {
    this.fst = fst;
    this.snd = snd;
  }

  public Expr getFst() {
    return fst;
  }

  public Expr getSnd() {
    return snd;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    Type t = new PairType(fst.getType(data), snd.getType(data));
    this.type = t;
    return t;
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
