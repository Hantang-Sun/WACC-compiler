package types;

import compiler.Pair;

import java.util.List;

public class ClassType implements Type {

  private final String className ;

  private boolean declared = false;
  private boolean isParam = false;
  private boolean isAttribute = false;

  public ClassType(String className) {
    this.className = className;
  }

  @Override
  public boolean match(Type otherType) {
    if (otherType instanceof ClassType) {
      ClassType ct = ((ClassType) otherType);
      return ct.getClassName().equals(className);
    }
    return false;
  }

  @Override
  public int size() {
    return 4;
  }

  @Override
  public boolean declared() {
    return declared;
  }

  @Override
  public void declare() {
    declared = true;
  }

  @Override
  public boolean isParam() {
    return isParam;
  }

  @Override
  public void setToParam() {
    isParam = true;
  }

  @Override
  public boolean isAttribute() {
    return isAttribute;
  }

  @Override
  public void setToAttribute() {
    isAttribute = true;
  }

  public String getClassName() {
    return className;
  }

  @Override
  public String toString() {
    return className;
  }
}
