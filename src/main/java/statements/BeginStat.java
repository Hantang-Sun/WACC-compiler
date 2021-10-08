package statements;

import java.util.List;

public class BeginStat implements Stat {

  private final List<Stat> stats;
  private final int lineNum;
  private final int linePos;

  public BeginStat(List<Stat> stats, int lineNum, int linePos) {
    this.stats = stats;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public List<Stat> getStats() {
    return stats;
  }

  @Override
  public StatType getStatType() {
    return StatType.BEGIN;
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