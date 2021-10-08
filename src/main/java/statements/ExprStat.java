package statements;

import expressions.Expr;

public class ExprStat implements Stat {

  private final StatType statType;
  private final Expr expr;
  private final int lineNum;
  private final int linePos;

  public ExprStat(StatType statType, Expr expr, int lineNum,
                  int linePos) {
    this.statType = statType;
    this.expr = expr;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public StatType getStatType() {
    return statType;
  }

  @Override
  public int getLineNum() {
    return lineNum;
  }

  @Override
  public int getLinePos() {
    return linePos;
  }

  public Expr getExpr() {
    return expr;
  }
}
