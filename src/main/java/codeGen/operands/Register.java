package codeGen.operands;


public class Register implements Operand {
  private int reg;
  boolean indirect = false;
  //if this flag is set to true, r1 becomes [r1]

  public Register(int reg) {
    this.reg = reg;
  }

  public Register(int reg, boolean indirect){
    this.reg = reg;
    this.reg = reg;
    this.indirect = indirect;
  }

  public int getReg() {
    return reg;
  }

  public boolean isIndirect() {return indirect;}

  public void setReg(int regNum) {
    this.reg = regNum;
  }
}
