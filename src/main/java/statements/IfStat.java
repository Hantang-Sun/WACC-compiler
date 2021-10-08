package statements;

import expressions.Expr;

import java.util.List;

public class IfStat implements Stat {

  private final Expr expr;
  private final List<Stat> thenStats;
  private final List<Stat> elseStats;
  private final int lineNum;
  private final int linePos;

  public IfStat(Expr expr, List<Stat> thenStats, List<Stat> elseStats,
                int lineNum, int linePos) {
    this.expr = expr;
    this.thenStats = thenStats;
    this.elseStats = elseStats;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public Expr getExpr() {
    return expr;
  }

  public List<Stat> getThenStats() {
    return thenStats;
  }

  public List<Stat> getElseStats() {
    return elseStats;
  }

  @Override
  public StatType getStatType() {
    return StatType.IF;
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
