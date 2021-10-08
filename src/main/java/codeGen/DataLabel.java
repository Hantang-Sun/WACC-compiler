package codeGen;

import java.util.Objects;

public class  DataLabel {

  private int msgNum;
  private String string;

  public DataLabel(int msgNum, String string){
    this.msgNum = msgNum;
    this.string = string;
  }

  public int getMsgNum() {
    return msgNum;
  }

  public String getString() {
    return string;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataLabel dataLabel = (DataLabel) o;
    return msgNum == dataLabel.msgNum;
  }

  @Override
  public int hashCode() {
    return msgNum;
  }
}


