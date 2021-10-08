package codeGen.operands;

public class LeftShift implements Operand{
  private final int shiftValue;

  public LeftShift(int shiftValue) {
    this.shiftValue = shiftValue;
  }

  public int getShiftValue() {
    return shiftValue;
  }
}
