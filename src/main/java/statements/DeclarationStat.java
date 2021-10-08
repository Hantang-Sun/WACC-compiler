package statements;

import expressions.AssignRHS;
import expressions.Ident;
import types.Type;

public class DeclarationStat implements Stat {

  private final Type type;
  private final Ident name;
  private final AssignRHS rhs;
  private final int lineNum;
  private final int linePos;

  public DeclarationStat(Type type, Ident name, AssignRHS rhs, int lineNum,
                         int linePos) {
    this.type = type;
    this.name = name;
    this.rhs = rhs;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Type getType() {
    return type;
  }

  public Ident getName() {
    return name;
  }

  public AssignRHS getRHS() {
    return rhs;
  }

  @Override
  public StatType getStatType() {
    return StatType.DECLARATION;
  }

  @Override
  public int getLineNum() {
    return lineNum;
  }

  @Override
  public int getLinePos() {
    return linePos;
  }
}
