package codeGen.operands;


public class Immediate implements Operand {
  private int num;
  private char aChar;

  public Immediate(int num) {
    this.num = num;
  }

  public Immediate(char aChar){
    this.aChar = aChar;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public char getChar() {
    return aChar;
  }
}
