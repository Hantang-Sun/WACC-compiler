package compiler;

import types.ErrorType;
import types.Type;

import java.util.ArrayList;
import java.util.List;

public class Scope {

  private final Scope parent;
  private final List<Pair<String, Type>> vars;
  private final List<Scope> children;
  private String funcName = "";

  public Scope(Scope parent) {
    this.parent = parent;
    this.vars = new ArrayList<>();
    this.children = new ArrayList<>();
  }

  public Scope(Scope parent,String funcName) {
    this(parent);
    this.funcName = funcName;
  }

  public Scope getParent() {
    return parent;
  }

  public Scope getChild(int i) {
    return children.get(i);
  }

  public List<Pair<String, Type>> getVars() {
    return vars;
  }

  public void addChild(Scope child) {
    children.add(child);
  }

  public void addNewVar(String varName, Type type) {
    vars.add(new Pair<>(varName, type));
  }

  public boolean contains(String varName) {
    for (Pair<String, Type> var : vars) {
      if (var.getKey().equals(varName)) {
        return true;
      }
    }
    return false;
  }

  public Type getType(String varName, SemanticErrorLog errorLog,
                      int lineNum, int linePos) {
    //try to find var in my scope
    for (Pair<String, Type> var : vars) {
      if (var.getKey().equals(varName)) {
        return var.getValue();
      }
    }
    //try to find in an ancestor's scope
    if (parent != null) {
      return parent.getType(varName, errorLog, lineNum, linePos);
    } else {
      //var not declared
      errorLog.error("Error in line " + lineNum + ":" + linePos + "\n" +
              "Variable " + varName + " used before declaration");
      return new ErrorType();
    }
  }

  public String getFuncName() {
    return funcName;
  }

  public int getChildNum(){
    return children.size();
  }
}

