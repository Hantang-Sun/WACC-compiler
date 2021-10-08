package types;

public interface Type {

  boolean match(Type otherType);

  int size();

  @Override
  String toString();

  boolean declared();

  void declare();

  boolean isParam();

  void setToParam();

  boolean isAttribute();

  void setToAttribute();
}
