package statements;

import expressions.Expr;

import java.util.List;

public class ForLoopStat implements Stat {

  private final Stat init;
  private final Expr condition;
  private final Stat increment;
  private final List<Stat> stats;

  private final int lineNum;
  private final int linePos;

  public ForLoopStat(Stat init, Expr condition, Stat increment,
                     List<Stat> stats, int lineNum, int linePos) {
    this.init = init;
    this.condition = condition;
    this.increment = increment;
    this.stats = stats;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Stat getInit() {
    return init;
  }

  public Expr getCondition() {
    return condition;
  }

  public Stat getIncrement() {
    return increment;
  }

  public List<Stat> getStats() {
    return stats;
  }

  @Override
  public StatType getStatType() {
    return StatType.FOR;
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
