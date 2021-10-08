package codeGen.operands;


public class Address implements Operand {

  private Register base;
  private Register regOffset;
  private Immediate numOffset;

  //[r0]
  public Address(Register base) {
    this(base, null, null);
  }

  //[r0, r1]
  public Address(Register base, Register regOffset) {
    this(base, regOffset, null);
  }

  //[r0, #0]
  public Address(Register base, Immediate numOffset) {
    this(base, null, numOffset);
  }

  //[r0, r1, #0]
  public Address(Register base, Register regOffset, Immediate numOffset) {
    this.base = base;
    this.regOffset = regOffset;
    this.numOffset = numOffset;
  }

  public Register getBase() {
    return base;
  }

  public Register getRegOffset() {
    return regOffset;
  }

  public Immediate getNumOffset() {
    return numOffset;
  }

  public void setBase(Register base) {
    this.base = base;
  }

  public void setRegOffset(Register regOffset) {
    this.regOffset = regOffset;
  }

  public void setNumOffset(Immediate numOffset) {
    this.numOffset = numOffset;
  }

  public boolean hasRegOffset() {
    return regOffset != null;
  }

  public boolean hasNumOffset() {
    return numOffset != null;
  }

}
