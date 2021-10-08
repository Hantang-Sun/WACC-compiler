package codeGen;

import codeGen.operands.Operand;

import java.util.ArrayList;
import java.util.List;

public class Instruction {

  private final Opcode type;
  private final List<Operand> operands;

  public Instruction(Opcode type, Operand... operands) {
    this.type = type;
    this.operands = new ArrayList<>();
    for (Operand operand : operands) {
      this.operands.add(operand);
    }
  }

  public Opcode getType() {
    return type;
  }

  public List<Operand> getOperands() {
    return operands;
  }

  public Operand getOperand(int x) {
    return operands.get(x);
  }

  public int getOperandNum() {
    return operands.size();
  }
}
