package statements;

import expressions.Expr;

import java.util.List;

public class WhileStat implements Stat {

  private final Expr expr;
  private final List<Stat> stats;
  private final int lineNum;
  private final int linePos;

  public WhileStat(Expr expr, List<Stat> stats, int lineNum,
                   int linePos) {
    this.expr = expr;
    this.stats = stats;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Expr getExpr() {
    return expr;
  }

  public List<Stat> getStats() {
    return stats;
  }

  @Override
  public StatType getStatType() {
    return StatType.WHILE;
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
