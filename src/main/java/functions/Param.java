package functions;

import types.Type;

public class Param {

  private Type type;
  private String name;

  public Param(Type type, String name){
    this.type = type;
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public String getName() {
    return name;
  }
}
