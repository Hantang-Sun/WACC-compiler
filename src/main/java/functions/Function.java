package functions;

import expressions.Ident;
import statements.Stat;
import types.Type;

import java.util.List;

public class Function {

  private final Type returnType;
  private final Ident name;
  private final List<Param> params;
  private final List<Stat> statements;
  private int overloadingNum;
  private boolean isPublic;

  private final int lineNum;
  private final int linePos;


  public Function(Type returnType, Ident name, List<Param> params,
                  List<Stat> statements, int lineNum, int linePos) {
    this(returnType, name, params, statements, lineNum, linePos, true);
  }

  public Function(Type returnType, Ident name, List<Param> params,
                  List<Stat> statements, int lineNum, int linePos,
                  boolean isPublic) {
    this.returnType = returnType;
    this.name = name;
    this.params = params;
    this.statements = statements;
    this.lineNum = lineNum;
    this.linePos = linePos;
    this.isPublic = isPublic;
    overloadingNum = 0;
  }

  public void setOverloadingNum(int overloadingNum) {
    this.overloadingNum = overloadingNum;
  }

  public String getFuncName() {
    return name.getString() + "_" + overloadingNum;
  }

  public Type getReturnType() {
    return returnType;
  }

  public Ident getName() {
    return name;
  }

  public List<Stat> getStatements() {
    return statements;
  }

  public List<Param> getParams() {
    return params;
  }

  public int getLineNum() {
    return lineNum;
  }

  public int getLinePos() {
    return linePos;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setToPrivate() {
    isPublic = false;
  }
}
