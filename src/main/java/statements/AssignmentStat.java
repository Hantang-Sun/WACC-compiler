package statements;

import expressions.AssignLHS;
import expressions.AssignRHS;

public class AssignmentStat implements Stat {

  private final AssignLHS lhs;
  private final AssignRHS rhs;
  private final int lineNum;
  private final int linePos;

  public AssignmentStat(AssignLHS lhs, AssignRHS rhs, int lineNum,
                        int linePos) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public AssignLHS getLhs() {
    return lhs;
  }

  public AssignRHS getRhs() {
    return rhs;
  }

  @Override
  public StatType getStatType() {
    return StatType.ASSIGNMENT;
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
