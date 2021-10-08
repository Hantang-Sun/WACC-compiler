package codeGen.operands;

public class ASR implements Operand {
  private int value;

  public ASR(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
