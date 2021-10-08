package statements;

import expressions.AssignLHS;

public class ReadStat implements Stat {

  private final AssignLHS lhs;
  private final int lineNum;
  private final int linePos;

  public ReadStat(AssignLHS lhs, int lineNum, int linePos) {
    this.lhs = lhs;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public AssignLHS getLhs() {
    return lhs;
  }

  @Override
  public StatType getStatType() {
    return StatType.READ;
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