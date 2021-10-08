package types;

public class BaseType implements Type {

  private final BaseTypeEnum type;

  private boolean declared = false;
  private boolean isParam = false;
  private boolean isAttribute = false;

  public BaseType(BaseTypeEnum type) {
    this.type = type;
  }

  public BaseTypeEnum getType() {
    return type;
  }

  @Override
  public boolean match(Type otherType) {
    if (otherType instanceof AnyType || otherType instanceof ErrorType) {
      return true;
    }

    if (otherType instanceof BaseType) {
      BaseType otherBase = (BaseType) otherType;
      return (otherBase.type == this.type);
    }

    return false;
  }

  @Override
  public int size() {
    switch (type) {
      case BOOL:
      case CHAR:
        return 1;
      case STRING:
      case INT:
        return 4;
      default:
        return 0;
    }
  }

  @Override
  public String toString() {
    return type.toString();
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