package types;

public class AnyType implements Type {

  private boolean declared = false;
  private boolean isParam = false;
  private boolean isAttribute = false;

  @Override
  public boolean match(Type otherType) {
    return true;
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
}
