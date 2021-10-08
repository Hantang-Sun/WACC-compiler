package statements;

public class LoopControlStat implements Stat {

  private final boolean isBreak;

  private final int lineNum;
  private final int linePos;

  public LoopControlStat(boolean isBreak, int lineNum, int linePos) {
    this.isBreak = isBreak;
    this.lineNum = lineNum;
    this.linePos = linePos;
  }

  public boolean isBreak() {
    return isBreak;
  }

  @Override
  public StatType getStatType() {
    if (isBreak) {
      return StatType.BREAK;
    }
    return StatType.CONTINUE;
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
