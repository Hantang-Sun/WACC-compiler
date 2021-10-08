package codeGen.operands;


public class Label implements Operand{
  private String label;

  public Label(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
