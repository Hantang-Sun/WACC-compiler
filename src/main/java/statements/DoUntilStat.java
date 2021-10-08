package statements;

import expressions.Expr;

import java.util.List;

public class DoUntilStat implements Stat{
  private final Expr condition;
  private final List<Stat> stats;

  private final int lineNum;
  private final int linePos;

  public DoUntilStat(Expr condition, List<Stat> stats, int lineNum, int linePos) {
    this.condition = condition;
    this.stats = stats;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Expr getCondition() {
    return condition;
  }

  public List<Stat> getStats() {
    return stats;
  }

  @Override
  public StatType getStatType() {
    return StatType.DO_UNTIL;
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
