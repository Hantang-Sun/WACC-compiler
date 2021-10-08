package functions;

import types.Type;

import java.util.List;

public class FunctionType {

  private final List<Type> inputType;
  private final Type returnType;
  private final String funcName;
  private final boolean isPublic;

  public FunctionType(List<Type> inputType, Type returnType, String funcName, boolean isPublic) {
    this.inputType = inputType;
    this.returnType = returnType;
    this.funcName = funcName;
    this.isPublic = isPublic;
  }

  public List<Type> getInputType() {
    return inputType;
  }

  public Type getReturnType() {
    return returnType;
  }

  public String getFuncName() {
    return funcName;
  }

  public boolean isPublic() {
    return isPublic;
  }
}
