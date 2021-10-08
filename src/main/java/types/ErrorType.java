package types;

import types.Type;

public class ErrorType implements Type {
  @Override
  public boolean match(Type otherType) {
    return (otherType instanceof ErrorType);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean declared() {
    return false;
  }

  @Override
  public void declare() {

  }

  @Override
  public boolean isParam() {
    return false;
  }

  @Override
  public void setToParam() {
  }

  @Override
  public boolean isAttribute() {
    return false;
  }

  @Override
  public void setToAttribute() {

  }

  @Override
  public String toString() {
    return "ERROR TYPE";
  }
}
