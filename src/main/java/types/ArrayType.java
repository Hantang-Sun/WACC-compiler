package types;

public class ArrayType implements Type {

  private final Type type;

  private boolean declared = false;
  private boolean isParam = false;
  private boolean isAttribute = false;

  public ArrayType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  @Override
  public boolean match(Type otherType) {
    if (otherType instanceof AnyType || otherType instanceof ErrorType) {
      return true;
    }

    if (otherType instanceof ArrayType) {
      ArrayType otherArray = (ArrayType) otherType;
      return (type.match(otherArray.type));
    }

    return false;
  }

  @Override
  public int size() {
    return 4;
  }

  @Override
  public String toString() {
    return type.toString() + "[]";
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
