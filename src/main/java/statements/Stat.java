package statements;

public interface Stat {
  
  StatType getStatType();

  int getLineNum();

  int getLinePos();
}
