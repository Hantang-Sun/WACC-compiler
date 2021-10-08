package armPrinter;

import codeGen.CodeGenerator;
import codeGen.Instruction;
import codeGen.Opcode;
import codeGen.operands.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;



public class AssemblyFileGenerator {

  //variables
  private CodeGenerator codeGenerator;

  private List<Instruction> instrs;

  public AssemblyFileGenerator(CodeGenerator codeGenerator) {
    this.codeGenerator = codeGenerator;
  }

  public void generateAssemblyFile(String filename) throws IOException {
    //generate internal representation
    instrs = codeGenerator.generateInstructions();
    File file = new File(filename);
    FileWriter fileWriter = new FileWriter(file);

    //generate assembly
    for (Instruction instr : instrs) {
      String assemblyInstr = generateInstruction(instr);
      fileWriter.write(assemblyInstr);
    }

    IOFunctionTranslator handCraftedAssembly = new IOFunctionTranslator();

    if (codeGenerator.isPrt_bool()) {
      fileWriter.write(handCraftedAssembly.getPrintBool());
    }
    if (codeGenerator.isPrt_int()) {
      fileWriter.write(handCraftedAssembly.getPrintInt());
    }
    if (codeGenerator.isPrt_ln()) {
      fileWriter.write(handCraftedAssembly.getPrintNewLine());
    }
    if (codeGenerator.isPrt_str()) {
      fileWriter.write(handCraftedAssembly.getPrintString());
    }
    if (codeGenerator.isPrt_ref()) {
      fileWriter.write(handCraftedAssembly.getPrintReference());
    }
    if (codeGenerator.isRead_chr()) {
      fileWriter.write(handCraftedAssembly.getReadChar());
    }
    if (codeGenerator.isRead_int()) {
      fileWriter.write(handCraftedAssembly.getReadInt());
    }
    if (codeGenerator.isRun_time_error()) {
      fileWriter.write(handCraftedAssembly.getRuntimeError());
    }
    if (codeGenerator.isOverflow()) {
      fileWriter.write(handCraftedAssembly.getOverflowError());
    }
    if (codeGenerator.isDivide_by_zero()) {
      fileWriter.write(handCraftedAssembly.getDivisionByZeroError());
    }
    if (codeGenerator.isArray_bounds_check()) {
      fileWriter.write(handCraftedAssembly.getArrayBoundsCheck());
    }
    if (codeGenerator.isNull_pointer_check()) {
      fileWriter.write(handCraftedAssembly.getNullPointerCheck());
    }
    if (codeGenerator.isFree_pair()) {
      fileWriter.write(handCraftedAssembly.getFreePair());
    }
    fileWriter.close();
  }

  private String generateInstruction(Instruction instr) {
    String assemblyInstr = generateOpcode(instr.getType()) + " ";

    Opcode opcode = instr.getType();

    for (int x = 0; x < instr.getOperandNum(); x++) {
      Operand operand = instr.getOperand(x);

      if ((opcode == Opcode.POP || opcode == Opcode.PUSH)
              && operand instanceof Register ) {
        assemblyInstr += "{" + generateOperand(operand) + "}";
      } else {
        assemblyInstr += generateOperand(operand);
      }
      if (x != instr.getOperandNum() - 1) {
        assemblyInstr += (", ");
      }
    }

    return assemblyInstr + "\n";
  }

  private String generateOpcode(Opcode opcode) {
    if (opcode == Opcode.LABEL) {
      return "";
    } else {
      return "\t" + opcode.toString();
    }
  }

  private String generateOperand(Operand operand) {
    if (operand instanceof Address) {
      return generateAddress((Address) operand);
    } else if (operand instanceof Immediate) {
      return generateImmediate((Immediate) operand);
    } else if (operand instanceof Label) {
      return generateLabel((Label) operand);
    } else if (operand instanceof Register) {
      return generateRegister((Register) operand);
    } else if (operand instanceof LeftShift) {
      return generateLeftShift((LeftShift) operand);
    } else if (operand instanceof ASR) {
      return generateASR((ASR) operand);
    }
    return "";
  }

  private String generateAddress(Address address) {
    String s = "[" + generateRegister(address.getBase());

    if (address.hasRegOffset()) {
      s += ", " + generateRegister(address.getRegOffset());
    } else if (address.hasNumOffset() && address.getNumOffset().getNum() != 0) {
      s += ", " + generateImmediate(address.getNumOffset());
    }

    return s + ']';
  }

  private String generateImmediate(Immediate immediate) {
    if (immediate.getChar() != '\u0000') {
      return "#" + "'" + immediate.getChar() + "'";
    } else if (Math.abs(immediate.getNum()) > 1024) {
      return "=" + immediate.getNum();
    }
    return "#" + immediate.getNum();
  }

  private String generateLabel(Label label) {
    return label.getLabel();
  }

  private String generateRegister(Register register) {
    switch (register.getReg()) {
      case 13:
        return "sp";
      case 14:
        return "lr";
      case 15:
        return "pc";
      default:
        return "r" + register.getReg();
    }
  }

  private String generateLeftShift(LeftShift leftShift) {
    return "LSL #" + leftShift.getShiftValue();
  }

  private String generateASR(ASR asr) {
    return "ASR #" + asr.getValue();
  }

}
