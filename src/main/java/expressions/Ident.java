package expressions;

import compiler.*;
import functions.FunctionType;
import newclass.ClassOp;
import types.*;

import java.util.HashMap;
import java.util.List;

public class Ident extends Expr implements AssignLHS, ClassOp {

  private final String name;
  private final int lineNum;
  private final int linePos;
  private Type type = null;

  public Ident(String name, int lineNum, int linePos) {
    this.name = name;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public String getString() {
    return name;
  }

  public int getLinePos() {
    return linePos;
  }

  public int getLineNum() {
    return lineNum;
  }

  @Override
  public Type getType(SemanticCheckData data) {
    Type t = data.getCurrScope().getType(name, data.getErrorLog(), lineNum, linePos);
    type = t;
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

  public void setType(Type type) {
    this.type = type;
  }
}
