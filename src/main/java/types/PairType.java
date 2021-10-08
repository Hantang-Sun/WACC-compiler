package types;

public class PairType implements Type {

    private final boolean anyType;
    private Type fstType;
    private Type sndType;

    private boolean declared = false;
    private boolean isParam = false;
    private boolean isAttribute = false;

    public PairType(Type fstType, Type sndType) {
      this.anyType = false;
      this.fstType = fstType;
      this.sndType = sndType;
    }

    public PairType() {
      this.anyType = true;
    }

    public Type getFstType() {
      if (anyType) {
        return new AnyType();
      }
      return fstType;
    }

    public Type getSndType() {
      if (anyType) {
        return new AnyType();
      }
      return sndType;
    }

  @Override
  public boolean match(Type otherType) {
    if (otherType instanceof AnyType|| otherType instanceof ErrorType){
      return true;
    }
    if (otherType instanceof PairType) {
      PairType otherPair = (PairType)otherType;
      if (anyType || otherPair.anyType){
        return true;
      }
      return fstType.match(otherPair.fstType)
             && sndType.match(otherPair.sndType);
    }
    return false;
  }

  @Override
  public int size() {
    return 4;
  }

  @Override
  public String toString() {
      if (anyType) {
        return "NULL";
      } else {
        return "PAIR(" + fstType.toString() + ", " + sndType.toString() + ")";
      }
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