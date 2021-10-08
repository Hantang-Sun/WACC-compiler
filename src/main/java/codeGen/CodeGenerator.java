package codeGen;

import codeGen.operands.*;
import compiler.Pair;
import compiler.Scope;
import expressions.*;
import functions.Function;
import functions.Param;
import functions.Program;
import newclass.*;
import statements.*;
import types.*;

import java.lang.reflect.Constructor;
import java.util.*;

public class CodeGenerator {

  public static final String SELF = "self";
  //variables
  private final Program ASTroot;
  private final Scope symbolTable;
  private final HashMap<String, Newclass> newclasses;

  //holds the .data instructions
  private List<DataLabel> dataLabels = new ArrayList<>();

  private String className = "";

  //counter to assign next free label
  private static int labelNum = 0;
  private static int msgNum = 100; //0-99 are reserved


  private int funcAddSp = 0;
  private static int stackOffset = 0;
  private final int MAX_SP_CHANGE = 1024;


  private Stack<String> breakLbls = new Stack<>();
  private Stack<String> continueLbls = new Stack<>();


  //allows traversal of symbol table to find correct child scope
  private final Stack<Integer> scopeIndex = new Stack<>();
  private Stack<Integer> funcScopeIndex = new Stack<>();

  private Stack<Integer> classScopeIndex = new Stack<>();

  //flag stating if what we are transuating lies in a function
  private boolean isInFunc = false;
  private boolean isInClass = false;
  private boolean isAttribute = false;


  //flags for whether we need to include read/print assembly for different types
  private boolean read_int = false;
  private boolean read_chr = false;
  private boolean prt_int = false;
  private boolean prt_str = false;
  private boolean prt_ln = false;
  private boolean prt_bool = false;
  private boolean prt_ref = false;


  //flags for run time errors
  private boolean overflow = false;
  private boolean run_time_error = false;
  private boolean divide_by_zero = false;
  private boolean array_bounds_check = false;
  private boolean null_pointer_check = false;
  private boolean free_pair = false;


  //Special labels
  private final String OVERFLOW = "p_throw_overflow_error";
  private final String READ_INT = "p_read_int";
  private final String READ_CHR = "p_read_char";
  private final String PRT_INT = "p_print_int";
  private final String PRT_STR = "p_print_string";
  private final String PRT_LN = "p_print_ln";
  private final String PRT_BOOL = "p_print_bool";
  private final String PRT_REF = "p_print_reference";
  private final String PRT_CHR = "putchar";
  private final String LBL_START = "L";
  private final String LBL_END = ":";
  private final String FUNC_LBL = "f_";
  private final String MSG_LBL = "msg_";


  //Special Register
  private final Register R0 = new Register(0);
  private final Register R1 = new Register(1);
  private final Register ACC = new Register(4);
  private final Register TMP = new Register(5);
  private final Register SP = new Register(13);
  private final Register LR = new Register(14);
  private final Register PC = new Register(15);


  //Special Data Labels
  private final DataLabel DATA_PRT_REF = new DataLabel(8, "\"%p\\0\"");
  private final DataLabel DATA_PRT_STR = new DataLabel(0, "\"%.*s\\0\"");
  private final DataLabel DATA_PRT_TRUE = new DataLabel(4, "\"true\\0\"");
  private final DataLabel DATA_PRT_FALSE = new DataLabel(5, "\"false\\0\"");
  private final DataLabel DATA_PRT_INT = new DataLabel(2, "\"%d\\0\"");
  private final DataLabel DATA_NEG_ARRAY_INDEX = new DataLabel(9,
          "\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"");
  private final DataLabel DATA_BIG_ARRAY_INDEX = new DataLabel(10,
          "\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"");
  private final DataLabel DATA_NULL_REF = new DataLabel(11,
          "\"NullReferenceError: dereference a null reference\\n\\0\"");
  private final DataLabel DATA_OVERFLOW = new DataLabel(6,
          "\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\"");


  //Instruction
  private final Instruction TEXT
          = new Instruction(Opcode.LABEL, new Label(".text"));
  private final Instruction GLOBAL_MAIN
          = new Instruction(Opcode.LABEL, new Label(".global main"));
  private final Instruction MAIN_LBL
          = new Instruction(Opcode.LABEL, new Label("main:"));
  private final Instruction FUNC_END
          = new Instruction(Opcode.LABEL, new Label("\t.ltorg"));
  private final Instruction MALLOC
          = new Instruction(Opcode.BL, new Label("malloc"));
  private final Instruction CHECK_ARRAY_BOUNDS
          = new Instruction(Opcode.BL, new Label("p_check_array_bounds"));
  private final Instruction CHECK_NULL_POINTER
          = new Instruction(Opcode.BL, new Label("p_check_null_pointer"));
  private final Instruction FREE_PAIR
          = new Instruction(Opcode.BL, new Label("p_free_pair"));


  //generates assembly using accumulator method
  public CodeGenerator(Program ASTroot, Scope symbolTable, HashMap<String, Newclass> newclasses) {
    this.ASTroot = ASTroot;
    this.symbolTable = symbolTable;
    this.newclasses = newclasses;
    scopeIndex.push(0);
  }


  public boolean isRead_int() {
    return read_int;
  }

  public boolean isRead_chr() {
    return read_chr;
  }

  public boolean isPrt_int() {
    return prt_int;
  }

  public boolean isPrt_str() {
    return prt_str;
  }

  public boolean isPrt_ln() {
    return prt_ln;
  }

  public boolean isPrt_bool() {
    return prt_bool;
  }

  public boolean isPrt_ref() {
    return prt_ref;
  }

  public boolean isOverflow() {
    return overflow;
  }

  public boolean isRun_time_error() {
    return run_time_error;
  }

  public boolean isDivide_by_zero() {
    return divide_by_zero;
  }

  public boolean isArray_bounds_check() {
    return array_bounds_check;
  }

  public boolean isNull_pointer_check() {
    return null_pointer_check;
  }

  public boolean isFree_pair() {
    return free_pair;
  }


  private void setRunTimeError() {
    run_time_error = true;
    prt_str = true;
    dataLabels.add(DATA_PRT_STR);
  }


  //uses the global integer stacks to find the current scope the program is in
  public Scope getScope() {
    Scope currScope = symbolTable;
    Stack<Integer> indexes;

    //determines which integer stack to use, based on isInFunc bool
    if (isInClass) {
      indexes = classScopeIndex;
    } else if (isInFunc) {
      indexes = funcScopeIndex;
    } else {
      indexes = scopeIndex;
    }

    //traverses the symbol table based on the integer stack
    for (int i = 0; i < indexes.size() - 1; i++) {
      currScope = currScope.getChild(indexes.get(i));
    }
    return currScope;
  }

  //compute the offset value that needs to be applied to the stack pointer to find the given variable
  private int getVarStackAddr(String varName, Scope currScope,
                              boolean isDeclaration) {
    if (varName.equals("ys")){
      System.out.println(currScope);
    }
    if (varName.equals(SELF)) {
      int size = stackOffset + 4;
      for (Pair<String, Type> var : currScope.getVars()) {
        size += var.getValue().size();
      }
      return size;
    }

    isAttribute = false;
    int offset = getVarStackAddrHelper(varName, currScope, isDeclaration);
    if (isAttribute) {
      return -1;
    }
    return offset;
  }

  private int getVarStackAddrHelper(String varName, Scope currScope,
                                    boolean isDeclaration) {

    int addr = 0;

    /*try to find the variable in the current scope
      address will equal the sum of the sizes' of all the vars before one we are
      looking for.
              var_n
              var_n-1
               ...
              var_0  <- first elem in vars list
              -----  <- stack pointer
    */

    //get the size of the variables in the current scope
    int size = 0;
    for (Pair<String, Type> var : currScope.getVars()) {
      size += var.getValue().size();
    }

    for (Pair<String, Type> var : currScope.getVars()) {
      if (var.getKey().equals(varName) && (isDeclaration || var.getValue().declared())) {
        if (isDeclaration) {
          var.getValue().declare();
        }
        if (var.getValue().isAttribute()) {
          isAttribute = true;
          return -1;
        }
        int paramOffset = var.getValue().isParam() ? 4 : 0;
        if ((funcScopeIndex.size() >= 2 && isInFunc) || (isInClass && classScopeIndex.size()>= 3)) {
          return (size - addr - var.getValue().size()) + stackOffset + paramOffset;
        }
        return addr + stackOffset + paramOffset;
      }

      addr += var.getValue().size();
    }

    //if var not in current scope, then it is in some parent scope
    return addr + getVarStackAddrHelper(varName, currScope.getParent(), isDeclaration);
  }

  // called when a new scope is entered. The function alters the scope index, adds the instruction
  // that moves the stack pointer and returns the total size of variables declared in the new scope
  private int scopeEnter(List<Instruction> instrs) {
    Stack<Integer> indexes;

    //determines which integer stack to use, based on isInFunc bool
    if (isInFunc) {
      indexes = funcScopeIndex;
    } else {
      indexes = scopeIndex;
    }

    //entering a child scope so push a zero
    indexes.push(0);
    Scope currScope = getScope();

    //get the size of all the variables in the current scope
    int size = 0;
    for (Pair<String, Type> var : currScope.getVars()) {
      size += var.getValue().size();
    }

    int size_cpy = size;
    funcAddSp += size_cpy;

    //decrement stack pointer
    while (size > 0) {
      if (size <= MAX_SP_CHANGE) {
        instrs.add(new Instruction(Opcode.SUB, SP, SP,
                new Immediate(size)));
        size = 0;
      } else {
        instrs.add(new Instruction(Opcode.SUB, SP, SP,
                new Immediate(MAX_SP_CHANGE)));
        size -= MAX_SP_CHANGE;
      }
    }


    return size_cpy;
  }

  //alter scope index and adds the instruction that move stack pointer back
  private void scopeExit(List<Instruction> instrs, int size) {
    Stack<Integer> indexes;

    //determines which integer stack to use, based on isInFunc bool
    if (isInFunc) {
      indexes = funcScopeIndex;
    } else {
      indexes = scopeIndex;
    }

    //leaving a scope so pop an int
    indexes.pop();

    //increment the top int by 1
    indexes.push(indexes.pop() + 1);

    funcAddSp -= size;

    //increment the stack pointer
    while (size > 0) {
      if (size <= MAX_SP_CHANGE) {
        instrs.add(new Instruction(Opcode.ADD, SP, SP,
                new Immediate(size)));
        size = 0;
      } else {
        instrs.add(new Instruction(Opcode.ADD, SP, SP,
                new Immediate(MAX_SP_CHANGE)));
        size -= MAX_SP_CHANGE;
      }
    }
  }

  //clears function scope index and set isInFunc flag to false
  //this function is called after the translation of every call command
  private void scopeExitFunction() {
    funcScopeIndex = new Stack<>();
  }

  //called when a call expressions needs to be translated
  private void scopeEnterFunction(String funcName) {
    int index = -1;

    for (int i = 0; i < symbolTable.getChildNum(); i++) {
      if (symbolTable.getChild(i).getFuncName().equals(funcName)) {
        index = i;
        break;
      }
    }
//    Scope classes = symbolTable.getChild(symbolTable.getChildNum()-1);
//    for (int i = 0; i < classes.getChildNum(); i++) {
//      Scope aclass = classes.getChild(i)
//      for (int j = 0 ; j < aclass.getChildNum(); j++){
//        if (aclass.getChild(j).getFuncName().equals(funcName)){
//
//        }
//      }
//    }

    if (index == -1) {
      System.out.println("Scope not found");
    }

    funcScopeIndex.push(index);
    funcScopeIndex.push(0);
  }


  //allocate label for if or while instructions
  private String getNextLabel() {
    return LBL_START + (labelNum++) + LBL_END;
  }

  private String removeLabelEnd(String label) {
    return label.substring(0, label.length() - 1);
  }

  //returns the label given a function
  private String getFuncLabel(Function func) {
    return FUNC_LBL + func.getFuncName() + LBL_END;
  }


  private Opcode getStoreOp(Type t) {
    if (t.size() == 1) {
      return Opcode.STRB;
    }
    if (t.size() == 4) {
      return Opcode.STR;
    }
    return Opcode.STR;
  }

  private Opcode getLoadOp(Type t) {
    if (t.size() == 1) {
      return Opcode.LDRSB;
    }
    if (t.size() == 4) {
      return Opcode.LDR;
    }
    return Opcode.LDR;
  }


  private void pushLoopControlLabels(String breakLbl, String continueLbl) {
    breakLbls.push(breakLbl);
    continueLbls.push(continueLbl);
  }

  private void popLoopControlLabels() {
    breakLbls.pop();
    continueLbls.pop();
  }


  //generate the internal assembly representation from the AST
  public List<Instruction> generateInstructions() {
    List<Instruction> instrs = new ArrayList<>();

    instrs.add(TEXT);
    instrs.add(GLOBAL_MAIN);

    List<Newclass> newclasses = ASTroot.getClasses();

    classScopeIndex.add(symbolTable.getChildNum() - 1);
    classScopeIndex.add(0);

    isInClass = true;

    for (Newclass newclass : newclasses) {
      scopeEnterConstructor();
      className = newclass.getName().getClassName();
      List<Instruction> classInstrs = transClass(newclass);
      className = "";
      instrs.addAll(classInstrs);
      scopeExitConstructor();
    }

    isInClass = false;
    classScopeIndex = new Stack<>();

    //generate assembly for all the functions
    List<Function> funcs = ASTroot.getFunctions();

    isInFunc = true;
    for (Function func : funcs) {
      List<Instruction> funcInstrs = transFunc(func);
      instrs.addAll(funcInstrs);
    }
    isInFunc = false;


    instrs.add(MAIN_LBL);
    instrs.add(new Instruction(Opcode.PUSH, LR));

    //entering main scope
    scopeIndex.push(0);
    Scope currScope = getScope();

    //get the size of all the variables in the current scope
    int size = 0;
    for (Pair<String, Type> var : currScope.getVars()) {
      size += var.getValue().size();
    }

    int temp_size = size;

    //decrement stack pointer
    while (temp_size > 0) {
      if (temp_size <= MAX_SP_CHANGE) {
        instrs.add(new Instruction(Opcode.SUB, SP, SP,
                new Immediate(temp_size)));
        temp_size = 0;

      } else {
        instrs.add(new Instruction(Opcode.SUB, SP, SP,
                new Immediate(MAX_SP_CHANGE)));
        temp_size -= MAX_SP_CHANGE;
      }
    }

    //generate assembly for the main function
    List<Instruction> mainInstrs = transStats(ASTroot.getStatements());
    instrs.addAll(mainInstrs);

    //increment stack pointer
    while (size > 0) {
      if (size <= MAX_SP_CHANGE) {
        instrs.add(new Instruction(Opcode.ADD, SP, SP,
                new Immediate(size)));
        size = 0;

      } else {
        instrs.add(new Instruction(Opcode.ADD, SP, SP,
                new Immediate(MAX_SP_CHANGE)));
        size -= MAX_SP_CHANGE;
      }
    }

    //leaving main scope
    scopeIndex.pop();

    //generate remaining instructions
    instrs.add(new Instruction(Opcode.MOV, R0, new Immediate(0)));
    instrs.add(new Instruction(Opcode.POP, PC));
    instrs.add(FUNC_END);

    List<Instruction> data = generateDataLabels();
    data.addAll(instrs);
    instrs = data;

    return instrs;
  }

  private List<Instruction> transClass(Newclass newclass) {
    List<Instruction> instrs = new ArrayList<>();
    List<Function> classConstructors = newclass.getConstructors();
    List<Function> functions = newclass.getMethods();

    for (Function constructor : classConstructors) {
      instrs.addAll(transConstructor(constructor));
    }

    for (Function function : functions) {
      instrs.addAll(transFunc(function));
    }

    return instrs;
  }

  private List<Instruction> transConstructor(Function constructor) {
    List<Instruction> instrs = new ArrayList<>();

    List<Stat> statements = constructor.getStatements();

    Newclass newclass = newclasses.get(constructor.getName().getString());
    int class_size = 0;
    for (ClassAttribute attribute : newclass.getAttributes()) {
      class_size += attribute.getType().size();
    }

    scopeEnterConstructor();

    int spChange = 0;
    Scope scope = getScope();

    //calculate size for vars + params declared in function scope
    for (Pair<String, Type> var : scope.getVars()) {
      spChange += var.getValue().size();
    }
    //calculate size for function parameters
    List<Param> ps = constructor.getParams();
    for (Param p : ps) {
      spChange -= p.getType().size();
    }

    //allocate and zero out memory for the object
    instrs.add(new Instruction(Opcode.LABEL, new Label(getFuncLabel(constructor))));
    instrs.add(new Instruction(Opcode.PUSH, LR));
    instrs.add(new Instruction(Opcode.MOV, R0, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOV, R1, new Immediate(class_size)));
    instrs.add(new Instruction(Opcode.BL, new Label("calloc")));
    int selfOffset = 4;
    for (Pair<String, Type> var : getScope().getVars()) {
      if (var.getValue().isParam()) {
        selfOffset += var.getValue().size();
      }
    }
    instrs.add(new Instruction(Opcode.STR, R0, new Address(SP, new Immediate(selfOffset))));
    instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(spChange)));

    //translate constructor stats
    instrs.addAll(transStats(statements));

    scopeExitConstructor();

    //return pointer to object
    instrs.add(new Instruction(Opcode.ADD, SP, SP, new Immediate(spChange)));
    instrs.add(new Instruction(Opcode.LDR, R0, new Address(SP, new Immediate(selfOffset))));
    instrs.add(new Instruction(Opcode.POP, PC));
    instrs.add(FUNC_END);

    return instrs;
  }

  private void scopeExitConstructor() {
    classScopeIndex.pop();
    classScopeIndex.push(classScopeIndex.pop() + 1);
  }

  private void scopeEnterConstructor() {
    classScopeIndex.push(0);
  }

  //generate data labels for string constants
  public List<Instruction> generateDataLabels() {
    List<Instruction> data = new ArrayList<>();

    data.add(new Instruction(Opcode.LABEL, new Label(".data")));

    List<DataLabel> tempLabel = new ArrayList<>(new HashSet<>(dataLabels));

    dataLabels = tempLabel;

    for (int i = 0; i < dataLabels.size(); i++) {
      data.add(new Instruction(Opcode.LABEL,
              new Label(MSG_LBL + dataLabels.get(i).getMsgNum() + LBL_END)));

      String s = dataLabels.get(i).getString();

      int len = s.length() - 2;

      for (int j = 0; j < s.length(); j++) {
        if (s.charAt(j) == '\\') {
          len--;
          j += 1;
        }
      }

      data.add(new Instruction(Opcode.LABEL, new Label(".word " + len)));
      data.add(new Instruction(Opcode.LABEL, new Label(".ascii " +
              dataLabels.get(i).getString())));
    }

    return data;
  }

  //generate assembly for a function
  private List<Instruction> transFunc(Function func) {
    List<Instruction> instrs = new ArrayList<>();
    List<Stat> stats = func.getStatements();
    String funcLbl = getFuncLabel(func);

    //set up function
    instrs.add(new Instruction(Opcode.LABEL, new Label(funcLbl)));
    instrs.add(new Instruction(Opcode.PUSH, LR));
    if (isInClass) {
      scopeEnterConstructor();
    } else {
      scopeEnterFunction(func.getFuncName());
    }

    int spChange = 0;
    Scope scope = getScope();

    //calculate size for vars + params declared in function scope
    for (Pair<String, Type> var : scope.getVars()) {
      spChange += var.getValue().size();
    }

    //calculate size for function parameters
    List<Param> ps = func.getParams();
    for (Param p : ps) {
      spChange -= p.getType().size();
    }

    instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(spChange)));

    funcAddSp = spChange;

    //translate function
    instrs.addAll(transStats(stats));

    //exit the function scope
    if (isInClass) {
      scopeExitConstructor();
    } else {
      scopeExitFunction();
    }
    funcAddSp = 0;

    instrs.add(new Instruction(Opcode.POP, PC));
    instrs.add(FUNC_END);

    return instrs;
  }

  //generate assembly for statements
  private List<Instruction> transStats(List<Stat> stats) {
    List<Instruction> instrs = new ArrayList<>();

    //translate all statements in a scope
    for (Stat stat : stats) {
      instrs.addAll(transStat(stat));

      //stop translating the statements after exit in a scope
      if (stat instanceof ExprStat) {
        ExprStat exprStat = (ExprStat) stat;
        if (exprStat.getStatType() == StatType.EXIT) {
          break;
        }
      }
    }

    return instrs;
  }

  //generate assembly for a statement
  private List<Instruction> transStat(Stat stat) {
    if (stat instanceof AssignmentStat) {
      return transAssignmentStats((AssignmentStat) stat);
    }
    if (stat instanceof BeginStat) {
      return transBeginStat((BeginStat) stat);
    }
    if (stat instanceof DeclarationStat) {
      return transDeclarationStat((DeclarationStat) stat);
    }
    if (stat instanceof ExprStat) {
      return transExprStat((ExprStat) stat);
    }
    if (stat instanceof IfStat) {
      return transIfStat((IfStat) stat);
    }
    if (stat instanceof ReadStat) {
      return transReadStat((ReadStat) stat);
    }
    if (stat instanceof WhileStat) {
      return transWhileStat((WhileStat) stat);
    }
    if (stat instanceof ForLoopStat) {
      return transForLoopStat((ForLoopStat) stat);
    }
    if (stat instanceof DoUntilStat) {
      return transDoUntilStat((DoUntilStat) stat);
    }
    if (stat instanceof LoopControlStat) {
      return transLoopControlStat((LoopControlStat) stat);
    }
    if (stat instanceof Call) {
      return transCall((Call) stat);
    }
    if (stat instanceof ClassCall) {
      return transClassCall((ClassCall) stat, true);
    }

    System.out.println("invalid type of stat");
    return new ArrayList<>();
  }

  private List<Instruction> transLoopControlStat(LoopControlStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    if (stat.isBreak()) {
      instrs.add(new Instruction(Opcode.B, new Label(breakLbls.peek())));
    } else {
      instrs.add(new Instruction(Opcode.B, new Label(continueLbls.peek())));
    }

    return instrs;
  }

  private List<Instruction> transForLoopStat(ForLoopStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    final String CONDITION = getNextLabel();
    final String DONE = getNextLabel();
    pushLoopControlLabels(removeLabelEnd(DONE), removeLabelEnd(CONDITION));

    //scope of For Loop is the entire body + the 3 params
    //semantic check makes sure that the 3 params don't contain any variables
    //that are defined within the loop body

    //initialise loop counter
    int size = scopeEnter(instrs);
    instrs.addAll(transStat(stat.getInit()));


    //evaluate condition
    instrs.add(new Instruction(Opcode.LABEL, new Label(CONDITION)));
    instrs.addAll(transExpression(stat.getCondition()));
    instrs.add(new Instruction(Opcode.CMP, ACC, new Immediate(0)));
    instrs.add(new Instruction(Opcode.BEQ, new Label(removeLabelEnd(DONE))));


    //get for body
    instrs.addAll(transStats(stat.getStats()));


    //evaluate increment
    instrs.addAll(transStat(stat.getIncrement()));
    instrs.add(new Instruction(Opcode.B, new Label(removeLabelEnd(CONDITION))));
    instrs.add(new Instruction(Opcode.LABEL, new Label(DONE)));
    scopeExit(instrs, size);


    popLoopControlLabels();
    return instrs;
  }

  private List<Instruction> transDoUntilStat(DoUntilStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    final String LOOP = getNextLabel();
    final String DONE = getNextLabel();
    final String CONDITION = getNextLabel();
    pushLoopControlLabels(removeLabelEnd(DONE), removeLabelEnd(CONDITION));


    //Start of loop
    instrs.add(new Instruction(Opcode.LABEL, new Label(LOOP)));


    //Loop body
    int size = scopeEnter(instrs);
    instrs.addAll(transStats(stat.getStats()));
    scopeExit(instrs, size);


    //Checking condition for iteration
    instrs.add(new Instruction(Opcode.LABEL, new Label(CONDITION)));
    instrs.addAll(transExpression(stat.getCondition()));
    instrs.add(new Instruction(Opcode.CMP, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.BNE, new Label(removeLabelEnd(LOOP))));
    instrs.add(new Instruction(Opcode.LABEL, new Label(DONE)));


    popLoopControlLabels();
    return instrs;
  }

  //generate assembly for a statement containing an expression
  private List<Instruction> transExprStat(ExprStat stat) {

    StatType statType = stat.getStatType();
    switch (statType) {
      case FREE:
        return transFreeStat(stat);
      case EXIT:
        return transExitStat(stat);
      case RETURN:
        return transReturnStat(stat);
      case PRINT:
        return transPrintStat(stat);
      case PRINTLN:
        return transPrintlnStat(stat);
      default:
        return new ArrayList<>();
    }
  }

  //generate assembly for a println statement
  private List<Instruction> transPrintlnStat(ExprStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //translate the statement as a regular print
    instrs.addAll(transPrintStat(stat));
    instrs.add(new Instruction(Opcode.BL, new Label(PRT_LN)));

    //extra assembly
    dataLabels.add(new DataLabel(1, "\"\\0\""));
    prt_ln = true;

    return instrs;
  }

  //generate assembly for a print statement
  private List<Instruction> transPrintStat(ExprStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //Possible printable types
    final Type CHAR_ARRAY_T = new ArrayType(new BaseType(BaseTypeEnum.CHAR));
    final Type INT_T = new BaseType(BaseTypeEnum.INT);
    final Type BOOL_T = new BaseType(BaseTypeEnum.BOOL);
    final Type CHAR_T = new BaseType(BaseTypeEnum.CHAR);
    final Type STRING_T = new BaseType(BaseTypeEnum.STRING);

    Type t = stat.getExpr().getType();

    //check the type of the print
    if (!t.match(CHAR_ARRAY_T)) {
      instrs.addAll(transExpression(stat.getExpr()));
      instrs.add(new Instruction(Opcode.MOV, R0, ACC));
    }

    if (t.match(INT_T)) {
      instrs.add(new Instruction(Opcode.BL, new Label(PRT_INT)));
      dataLabels.add(DATA_PRT_INT);
      prt_int = true;

    } else if (t.match(CHAR_T)) {
      instrs.add(new Instruction(Opcode.BL, new Label(PRT_CHR)));

    } else if (t.match(STRING_T)) {
      instrs.add(new Instruction(Opcode.BL, new Label(PRT_STR)));
      dataLabels.add(DATA_PRT_STR);
      prt_str = true;

    } else if (t.match(BOOL_T)) {
      instrs.add(new Instruction(Opcode.BL, new Label(PRT_BOOL)));
      dataLabels.add(DATA_PRT_TRUE);
      dataLabels.add(DATA_PRT_FALSE);
      prt_bool = true;

    } else if (t.match(CHAR_ARRAY_T)) {

      if (stat.getExpr() instanceof Ident) {
        //TODO:
        instrs.addAll(transIdent((Ident) stat.getExpr(), true));
        instrs.add(new Instruction(Opcode.MOV, R0, ACC));
        //instrs.add(new Instruction(Opcode.LDR, R0, new Address(SP, new Immediate(stackAddr))));
        instrs.add(new Instruction(Opcode.BL, new Label(PRT_STR)));
        dataLabels.add(DATA_PRT_STR);
        prt_str = true;

      } else {
        List<Expr> exprs = ((ArrayLiter) (AssignRHS) stat.getExpr()).getExprs();

        for (Expr expr : exprs) {
          instrs.addAll(transExpression(expr));
          instrs.add(new Instruction(Opcode.BL, new Label(PRT_CHR)));
        }
      }
    } else {
      instrs.add(new Instruction(Opcode.BL, new Label(PRT_REF)));
      dataLabels.add(DATA_PRT_REF);
      prt_ref = true;
    }
    return instrs;
  }

  //generate assembly for a return statement
  private List<Instruction> transReturnStat(ExprStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //get return value
    instrs.addAll(transExpression(stat.getExpr()));
    instrs.add(new Instruction(Opcode.MOV, R0, ACC));

    //reset stack
    instrs.add(new Instruction(Opcode.ADD, SP, SP, new Immediate(funcAddSp)));

    //leave function
    instrs.add(new Instruction(Opcode.POP, PC));

    return instrs;
  }

  //generate assembly for an exit statement
  private List<Instruction> transExitStat(ExprStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //generate assembly for the exit expression
    instrs.addAll(transExpression(stat.getExpr()));
    instrs.add(new Instruction(Opcode.MOV, R0, ACC));
    instrs.add(new Instruction(Opcode.BL, new Label("exit")));

    return instrs;
  }

  //generate assembly for a free statement
  private List<Instruction> transFreeStat(ExprStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //generate assembly for the free expression
    instrs.addAll(transExpression(stat.getExpr()));
    instrs.add(new Instruction(Opcode.MOV, R0, ACC));
    instrs.add(FREE_PAIR);

    free_pair = true;
    dataLabels.add(DATA_NULL_REF);
    setRunTimeError();

    return instrs;
  }

  //generate assembly for an if statement
  private List<Instruction> transIfStat(IfStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //get labels
    String elseLabel = getNextLabel();
    String endLabel = getNextLabel();


    //generate condition evaluation code
    instrs.addAll(transExpression(stat.getExpr()));
    instrs.add(new Instruction(Opcode.CMP, ACC, new Immediate(0)));
    instrs.add(new Instruction(Opcode.BEQ, new Label(removeLabelEnd(elseLabel))));


    //generate If clause
    int size = scopeEnter(instrs);
    instrs.addAll(transStats(stat.getThenStats()));
    scopeExit(instrs, size);
    instrs.add(new Instruction(Opcode.B, new Label(removeLabelEnd(endLabel))));



    //generate Else clause
    instrs.add(new Instruction(Opcode.LABEL, new Label(elseLabel)));
    size = scopeEnter(instrs);
    instrs.addAll(transStats(stat.getElseStats()));
    scopeExit(instrs, size);


    //label for end jump
    instrs.add(new Instruction(Opcode.LABEL, new Label(endLabel)));

    return instrs;
  }

  //generate code for while statement
  private List<Instruction> transWhileStat(WhileStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //get labels
    final String CONDITION = getNextLabel();
    final String DONE = getNextLabel();
    pushLoopControlLabels(removeLabelEnd(DONE), removeLabelEnd(CONDITION));


    //generate condition evaluation code
    instrs.add(new Instruction(Opcode.LABEL, new Label(CONDITION)));
    instrs.addAll(transExpression(stat.getExpr()));
    instrs.add(new Instruction(Opcode.CMP, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.BNE, new Label(removeLabelEnd(DONE))));


    //generate code for statements in loop
    int size = scopeEnter(instrs);
    instrs.addAll(transStats(stat.getStats()));
    scopeExit(instrs, size);


    //jump to condition code
    instrs.add(new Instruction(Opcode.B, new Label(removeLabelEnd(CONDITION))));
    instrs.add(new Instruction(Opcode.LABEL, new Label(DONE)));


    popLoopControlLabels();
    return instrs;
  }

  //generate code for a begin statement
  private List<Instruction> transBeginStat(BeginStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    //enter begin scope
    int size = scopeEnter(instrs);

    //generate code for statements in begin scope
    instrs.addAll(transStats(stat.getStats()));

    //exit begin scope
    scopeExit(instrs, size);

    return instrs;
  }

  //generate code for a read statement
  private List<Instruction> transReadStat(ReadStat stat) {
    List<Instruction> instrs = new ArrayList<>();
    Type t = stat.getLhs().getType();

    //check lhs
    instrs.addAll(transAssignLhs(stat.getLhs()));
    instrs.add(new Instruction(Opcode.MOV, R0, ACC));
//    if (stat.getLhs() instanceof Ident) {
//      String varName = ((Ident) stat.getLhs()).getString();
//      int bufferStackAddr = getVarStackAddr(varName, getScope(), false);
//      //TODO:
//      instrs.addAll(transIdent((Ident) stat.getLhs(), false));
//      instrs.add(new Instruction(Opcode.MOV, R0, ACC));
//      //instrs.add(new Instruction(Opcode.ADD, R0, SP, new Immediate(bufferStackAddr)));
//
//    } else if (stat.getLhs() instanceof PairElem) {
//      instrs.addAll(transPairElem((PairElem) stat.getLhs(), false));
//      instrs.add(new Instruction(Opcode.MOV, R0, ACC));
//
//    } else {
//      instrs.addAll(transArrayElem((ArrayElem) stat.getLhs(), false));
//      instrs.add(new Instruction(Opcode.MOV, R0, ACC));
//    }

    //check expression type
    if (t.match(new BaseType(BaseTypeEnum.INT))) {
      instrs.add(new Instruction(Opcode.BL, new Label(READ_INT)));
      read_int = true;
      dataLabels.add(new DataLabel(2, "\"%d\\0\""));

    } else if (t.match(new BaseType(BaseTypeEnum.CHAR))) {
      instrs.add(new Instruction(Opcode.BL, new Label(READ_CHR)));
      read_chr = true;
      dataLabels.add(new DataLabel(3, "\" %c\\0\""));
    }

    return instrs;
  }

  private List<Instruction> transDeclarationStat(DeclarationStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    int size = stat.getRHS().getType().size();
    int addr = getVarStackAddr(stat.getName().getString(), getScope(), true);

    Opcode store = getStoreOp(stat.getRHS().getType());
    //TODO:
    instrs.addAll(transRhs(stat.getRHS()));
    instrs.add(push(ACC));
    instrs.addAll(transIdent(stat.getName(), false));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(store, TMP, new Address(ACC)));
    //instrs.add(new Instruction(op, ACC, new Address(SP, new Immediate(addr))));

    return instrs;
  }

  private List<Instruction> transAssignmentStats(AssignmentStat stat) {
    List<Instruction> instrs = new ArrayList<>();

    Opcode store = getStoreOp(stat.getRhs().getType());
    instrs.addAll(transRhs(stat.getRhs()));
    instrs.add(push(ACC));
    instrs.addAll(transAssignLhs(stat.getLhs()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(store, TMP, new Address(ACC)));

    return instrs;
  }

  private List<Instruction> transAssignLhs(AssignLHS lhs) {
    if (lhs instanceof Ident) {
      return transIdent((Ident) lhs, false);
    }
    if (lhs instanceof ArrayElem) {
      return transArrayElem((ArrayElem) lhs, false);
    }
    if (lhs instanceof PairElem) {
      return transPairElem((PairElem) lhs, false);
    }
    if (lhs instanceof ClassCall) {
      return transClassCall((ClassCall) lhs, false);
    }
    System.out.println("ERROR 1102: transLhs failed");
    return new ArrayList<>();
  }

  private List<Instruction> transExpression(Expr expr) {
    List<Instruction> instrs = new ArrayList<>();

    if (expr instanceof BinOpExpr) {
      return transBinOp((BinOpExpr) expr);
    }
    if (expr instanceof UnOpExpr) {
      return transUnOp((UnOpExpr) expr);
    }
    if (expr instanceof IntLiter) {
      return transIntLiter((IntLiter) expr);
    }
    if (expr instanceof StringLiter) {
      return transStringLiter((StringLiter) expr);
    }
    if (expr instanceof CharLiter) {
      return transCharLiter((CharLiter) expr);
    }
    if (expr instanceof BoolLiter) {
      return transBoolLiter((BoolLiter) expr);
    }
    if (expr instanceof PairLiter) {
      return transPairLiter();
    }
    if (expr instanceof Ident) {
      return transIdent((Ident) expr, true);
    }
    if (expr instanceof ArrayElem) {
      return transArrayElem((ArrayElem) expr, true);
    }
    return instrs;
  }

  private List<Instruction> transUnOp(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    switch (expr.getOp()) {
      case CHR:
        return transChr(expr);
      case LEN:
        return tranLen(expr);
      case NOT:
        return transNot(expr);
      case ORD:
        return tranOrd(expr);
      case NEGATE:
        return transNegate(expr);
      default:
        return instrs;

    }
  }

  private List<Instruction> transNegate(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(expr.getExpr()));

    instrs.add(new Instruction(Opcode.RSBS, ACC, ACC, new Immediate(0)));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));

    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> tranOrd(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(expr.getExpr()));

    return instrs;
  }

  private List<Instruction> transNot(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(expr.getExpr()));
    instrs.add(new Instruction(Opcode.EOR, ACC, ACC, new Immediate(1)));

    return instrs;
  }

  private List<Instruction> tranLen(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    /*arrays are formatted:
      arrayLength 4 bytes
    */

    String arrayName = ((Ident) expr.getExpr()).getString();
    int arrayStackAddr = getVarStackAddr(arrayName, getScope(), false);

    //TODO:
    instrs.addAll(transIdent((Ident) expr.getExpr(), true));
    //instrs.add(new Instruction(Opcode.LDR, ACC, new Address(SP, new Immediate(arrayStackAddr))));
    instrs.add(new Instruction(Opcode.LDR, ACC, new Address(ACC)));

    return instrs;
  }

  private List<Instruction> transChr(UnOpExpr expr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(expr.getExpr()));

    return instrs;
  }

  private List<Instruction> transBoolLiter(BoolLiter expr) {
    List<Instruction> instrs = new ArrayList<>();

    if (expr.getBool()) {
      instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(1)));
    } else {
      instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(0)));
    }
    return instrs;
  }

  private List<Instruction> transIdent(Ident expr, boolean getElem) {
    List<Instruction> instrs = new ArrayList<>();
    String varName = expr.getString();
    int stackAddr = getVarStackAddr(varName, getScope(), false);

    if (stackAddr == -1) {
      int offset = stackOffset + 4;
      for (Pair<String, Type> var : getScope().getVars()) {
        offset += var.getValue().size();
      }
      instrs.add(new Instruction(Opcode.LDR, ACC, new Address(SP, new Immediate(offset))));
      Opcode load = getLoadOp(expr.getType());
      offset = getAttributeOffset(newclasses.get(className).getName(), expr);
      if (getElem) {
        instrs.add(new Instruction(load, ACC, new Address(ACC, new Immediate(offset))));
      } else {
        instrs.add(new Instruction(Opcode.ADD, ACC, ACC, new Immediate(offset)));
      }
    } else {
      if (getElem) {
        Type t = expr.getType();
        Opcode op = getLoadOp(t);
        if (stackAddr > MAX_SP_CHANGE) {
          instrs.add(new Instruction(Opcode.LDR, TMP, new Immediate(stackAddr)));
          instrs.add(new Instruction(op, ACC, new Address(SP, TMP)));
        } else {
          instrs.add(new Instruction(op, ACC, new Address(SP, new Immediate(stackAddr))));
        }
      } else {
        if (stackAddr > MAX_SP_CHANGE) {
          instrs.add(new Instruction(Opcode.LDR, TMP, new Immediate(stackAddr)));
          instrs.add(new Instruction(Opcode.ADD, ACC, SP, TMP));
        } else {
          instrs.add(new Instruction(Opcode.ADD, ACC, SP, new Immediate(stackAddr)));
        }
      }
    }
    return instrs;
  }

  private List<Instruction> transCharLiter(CharLiter expr) {
    List<Instruction> instrs = new ArrayList<>();
    if (expr.getChar() <= 10) {
      instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate((int) expr.getChar())));
    } else {
      instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(expr.getChar())));
    }

    return instrs;
  }

  private List<Instruction> transStringLiter(StringLiter expr) {
    List<Instruction> instrs = new ArrayList<>();
    int msgNumber = msgNum++;
    this.dataLabels.add(new DataLabel(msgNumber, expr.getString()));
    instrs.add(new Instruction(Opcode.LDR, ACC, new Label("=msg_" + msgNumber)));
    return instrs;
  }

  private List<Instruction> transIntLiter(IntLiter expr) {
    List<Instruction> instrs = new ArrayList<>();
    if (Math.abs(expr.getInt()) > 1024) {
      instrs.add(new Instruction(Opcode.LDR, ACC, new Immediate(expr.getInt())));
    } else {
      instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(expr.getInt())));
    }
    return instrs;
  }

  private List<Instruction> transRhs(AssignRHS rhs) {
    if (rhs instanceof Call) {
      return transCall((Call) rhs);
    }
    if (rhs instanceof ArrayLiter) {
      return transArrayLiter((ArrayLiter) rhs);
    }
    if (rhs instanceof NewPair) {
      return transNewPair((NewPair) rhs);
    }
    if (rhs instanceof PairElem) {
      return transPairElem((PairElem) rhs, true);
    }
    if (rhs instanceof ArrayElem) {
      return transArrayElem((ArrayElem) rhs, true);
    }
    if (rhs instanceof ClassCall) {
      return transClassCall((ClassCall) rhs, true);
    }
    if (rhs instanceof NewObject) {
      return transNewObject((NewObject) rhs);
    }

    return transExpression((Expr) rhs);
  }

  private List<Instruction> transNewObject(NewObject rhs) {
    List<Instruction> instrs = new ArrayList<>();

    List<Expr> exprs = rhs.getExprs();
    String funcName = rhs.getConstructorName().getString();

    //size of a pointer
    int spChange = 4;
    stackOffset += 4;
    for (Expr expr : exprs) {
      spChange += expr.getType().size();
    }

    //make space for object pointer
    instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(4)));

    for (int i = 0; i < exprs.size(); i++) {
      Type type = exprs.get(i).getType();

      instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(type.size())));

      stackOffset += type.size();

      instrs.addAll(transExpression(exprs.get(i)));
      Opcode store = getStoreOp(type);
      instrs.add(new Instruction(store, ACC, new Address(SP)));
    }

    stackOffset -= spChange;

    instrs.add(new Instruction(Opcode.BL, new Label(FUNC_LBL + funcName)));

    instrs.add(new Instruction(Opcode.ADD, SP, SP, new Immediate(spChange)));

    instrs.add(new Instruction(Opcode.MOV, ACC, R0));

    return instrs;
  }

  private List<Instruction> transClassCall(ClassCall rhs, boolean getElem) {
    List<Instruction> instrs = new ArrayList<>();

    Ident varName = rhs.getVarName();
    instrs.addAll(transIdent(varName, true));
    Type t = rhs.getVarName().getType();
    int lastClassOp = rhs.getClassOps().size() - 1;
    for (int i = 0; i < rhs.getClassOps().size(); i++) {
      ClassOp classOp = rhs.getClassOps().get(i);
      if (classOp instanceof Ident) {
        Ident attributeName = (Ident) classOp;
        int attributeOffset = getAttributeOffset((ClassType) t, attributeName);
        if (i == lastClassOp && !getElem) {
          instrs.add(new Instruction(Opcode.ADD, ACC, ACC, new Immediate(attributeOffset)));
        } else {
          instrs.add(new Instruction(Opcode.LDR, ACC, new Address(ACC, new Immediate(attributeOffset))));
        }
        t = attributeName.getType();
      } else if (classOp instanceof MethodCall) {
        MethodCall methodCall = (MethodCall) classOp;
        List<Expr> exprs = methodCall.getExprs();

        //push object
        int spChange = 4;
        stackOffset += 4;
        instrs.add(new Instruction(Opcode.PUSH, ACC));
        //push params
        for (Expr expr : exprs) {
          Type type = expr.getType();
          spChange += type.size();
          stackOffset += type.size();
          Opcode store = getStoreOp(type);

          instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(type.size())));
          instrs.addAll(transExpression(expr));
          instrs.add(new Instruction(store, ACC, new Address(SP)));
        }
        stackOffset -= spChange;
        //call method
        instrs.add(new Instruction(Opcode.BL, new Label(FUNC_LBL + methodCall.getMethodName().getString())));
        instrs.add(new Instruction(Opcode.ADD, SP, SP, new Immediate(spChange)));
        instrs.add(new Instruction(Opcode.MOV, ACC, R0));

      } else {
        System.out.println("This isnt good");
      }
    }


    return instrs;
  }

  private int getAttributeOffset(ClassType t, Ident name) {
    Newclass newclass = newclasses.get(t.getClassName());
    List<ClassAttribute> attributes = newclass.getAttributes();
    int offset = 0;
    for (ClassAttribute attribute : attributes) {
      if (attribute.getName().getString().equals(name.getString())) {
        return offset;
      } else {
        offset += attribute.getType().size();
      }
    }
    System.out.println("invalid attribute");
    return 0;
  }

  private List<Instruction> transPairLiter() {
    List<Instruction> instrs = new ArrayList<>();

    instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transPairElem(PairElem rhs, boolean getElem) {
    List<Instruction> instrs = new ArrayList<>();

    //get pair address
    instrs.addAll(transExpression(rhs.getExpr()));

    //checks null pointer dereference
    instrs.add(new Instruction(Opcode.MOV, R0, ACC));
    instrs.add(CHECK_NULL_POINTER);

    //get fst or snd elem address
    if (rhs.isFst()) {
      instrs.add(new Instruction(Opcode.LDR, ACC, new Address(ACC)));
    } else {
      instrs.add(new Instruction(Opcode.LDR, ACC, new Address(ACC, new Immediate(4))));
    }

    Opcode op = getLoadOp(rhs.getType());

    //get fst or snd elem
    if (getElem) {
      instrs.add(new Instruction(op, ACC, new Address(ACC)));
    }

    null_pointer_check = true;
    dataLabels.add(DATA_NULL_REF);
    setRunTimeError();
    return instrs;
  }

  private List<Instruction> transNewPair(NewPair rhs) {
    List<Instruction> instrs = new ArrayList<>();

    //make space for two pointers
    instrs.add(new Instruction(Opcode.MOV, R0, new Immediate(8)));
    instrs.add(MALLOC);
    instrs.add(push(R0));

    //make fst pointer and store expr in it.
    instrs.add(new Instruction(Opcode.MOV, R0, new Immediate(rhs.getFst().getType().size())));
    instrs.add(MALLOC);
    instrs.add(pop(ACC));
    instrs.add(new Instruction(Opcode.STR, R0, new Address(ACC)));
    instrs.add(push(ACC));
    instrs.add(push(R0));
    instrs.addAll(transExpression(rhs.getFst()));
    Opcode op = getStoreOp(rhs.getFst().getType());
    instrs.add(pop(TMP));
    instrs.add(new Instruction(op, ACC, new Address(TMP)));

    //make snd pointer and store expr in it.
    instrs.add(new Instruction(Opcode.MOV, R0, new Immediate(rhs.getSnd().getType().size())));
    instrs.add(MALLOC);
    instrs.add(pop(ACC));
    instrs.add(new Instruction(Opcode.STR, R0, new Address(ACC, new Immediate(4))));
    instrs.add(push(ACC));
    instrs.add(push(R0));
    instrs.addAll(transExpression(rhs.getSnd()));
    op = getStoreOp(rhs.getSnd().getType());
    instrs.add(pop(TMP));
    instrs.add(new Instruction(op, ACC, new Address(TMP)));

    //put pair address in accumulator
    instrs.add(pop(ACC));

    return instrs;
  }

  private List<Instruction> transArrayLiter(ArrayLiter rhs) {
    List<Instruction> instrs = new ArrayList<>();

    final int BYTES_TO_STORE_ARRAY_LEN = 4;
    final int ELEM_SIZE = ((ArrayType) rhs.getType()).getType().size();
    final int NUM_ELEMS = rhs.getExprs().size();
    final Immediate arraySize
            = new Immediate(BYTES_TO_STORE_ARRAY_LEN + ELEM_SIZE * NUM_ELEMS);

    instrs.add(new Instruction(Opcode.MOV, R0, arraySize));
    instrs.add(MALLOC);

    int stackAddr = BYTES_TO_STORE_ARRAY_LEN;
    Opcode op = getStoreOp(((ArrayType) rhs.getType()).getType());

    for (Expr expr : rhs.getExprs()) {
      instrs.addAll(transExpression(expr));
      instrs.add(new Instruction(op, ACC, new Address(R0, new Immediate(stackAddr))));
      stackAddr += ELEM_SIZE;
    }

    instrs.add(new Instruction(Opcode.MOV, ACC, new Immediate(NUM_ELEMS)));
    instrs.add(new Instruction(Opcode.STR, ACC, new Address(R0)));
    instrs.add(new Instruction(Opcode.MOV, ACC, R0));

    return instrs;
  }

  private List<Instruction> transArrayElem(ArrayElem rhs, boolean getElem) {
    List<Instruction> instrs = new ArrayList<>();
    final Immediate SIZE_OF_PTR = new Immediate(4);
    final int PTR_SHIFT = 2;

    List<Expr> exprs = rhs.getExprs();
    //get array addr
    Immediate arrayAddr
            = new Immediate(getVarStackAddr(rhs.getName(), getScope(), false));

    if (exprs.size() == 0) {
      //TODO:
      Ident arrayName = new Ident(rhs.getName(), 0, 0);
      arrayName.setType(new ArrayType(rhs.getType()));
      instrs.addAll(transIdent(arrayName, true));
      //instrs.add(new Instruction(Opcode.LDR, ACC, new Address(SP, arrayAddr)));
    } else {

      instrs.addAll(transExpression(exprs.get(0)));
      instrs.add(new Instruction(Opcode.MOV, TMP, ACC));
      //TODO:
      Ident arrayName = new Ident(rhs.getName(), 0, 0);
      arrayName.setType(new ArrayType(rhs.getType()));
      instrs.addAll(transIdent(arrayName, true));
      //instrs.add(new Instruction(Opcode.LDR, ACC, new Address(SP, arrayAddr)));


      //check array bounds
      final Instruction setUpRO = new Instruction(Opcode.MOV, R0, TMP);
      final Instruction setUpR1 = new Instruction(Opcode.MOV, R1, ACC);
      instrs.add(setUpRO);
      instrs.add(setUpR1);
      instrs.add(CHECK_ARRAY_BOUNDS);

      //get the shift amount
      Type type = rhs.getType();
      int shiftValue;
      if (exprs.size() == 1) {
        shiftValue = getShiftValue(type.size());
      } else {
        shiftValue = PTR_SHIFT;
      }

      //get elem
      Instruction getZeroElem = new Instruction(Opcode.ADD, ACC, ACC, SIZE_OF_PTR);
      instrs.add(getZeroElem);
      instrs.add(getShiftInstruction(shiftValue));

      for (int i = 1; i < exprs.size(); i++) {
        //push sub-array address pointer
        instrs.add(push(ACC));
        //get sub-array index
        instrs.addAll(transExpression(exprs.get(i)));
        instrs.add(new Instruction(Opcode.MOV, TMP, ACC));
        //pop sub-array address pointer
        instrs.add(pop(ACC));
        //get sub-array address
        instrs.add(new Instruction(Opcode.LDR, ACC, new Address(ACC)));
        //check whether index in bound
        instrs.add(setUpRO);
        instrs.add(setUpR1);
        instrs.add(CHECK_ARRAY_BOUNDS);
        //get address of elem zero in sub-array
        instrs.add(new Instruction(Opcode.ADD, ACC, ACC, SIZE_OF_PTR));
        if (i == exprs.size() - 1) {
          shiftValue = getShiftValue(type.size());
        } else {
          shiftValue = PTR_SHIFT;
        }
        //get address of elem in sub array
        instrs.add(getShiftInstruction(shiftValue));
      }

      //storing the elem in the accumulator
      if (getElem) {
        Opcode op = getLoadOp(rhs.getType());
        instrs.add(new Instruction(op, ACC, new Address(ACC)));
      }
    }

    dataLabels.add(DATA_NEG_ARRAY_INDEX);
    dataLabels.add(DATA_BIG_ARRAY_INDEX);
    array_bounds_check = true;
    setRunTimeError();

    return instrs;
  }

  private int getShiftValue(int size) {
    switch (size) {
      case 1:
        return 0;
      case 4:
        return 2;
      default:
        return -1;
    }
  }

  private Instruction getShiftInstruction(int shiftValue) {
    if (shiftValue == 0) {
      return new Instruction(Opcode.ADD, ACC, ACC, TMP);
    } else {
      return new Instruction(Opcode.ADD, ACC, ACC, TMP, new LeftShift(shiftValue));
    }
  }

  private List<Instruction> transCall(Call rhs) {
    List<Instruction> instrs = new ArrayList<>();

    List<Expr> exprs = rhs.getExprs();
    String funcName = rhs.getFuncName().getString();

    int spChange = 0;

    for (int i = 0; i < exprs.size(); i++) {

      Type type = exprs.get(i).getType();

      instrs.add(new Instruction(Opcode.SUB, SP, SP, new Immediate(type.size())));

      spChange += type.size();
      stackOffset += type.size();

      instrs.addAll(transExpression(exprs.get(i)));
      Opcode store = getStoreOp(type);
      instrs.add(new Instruction(store, ACC, new Address(SP)));
    }

    stackOffset -= spChange;

    instrs.add(new Instruction(Opcode.BL, new Label(FUNC_LBL + funcName)));
    instrs.add(new Instruction(Opcode.ADD, SP, SP, new Immediate(spChange)));
    instrs.add(new Instruction(Opcode.MOV, ACC, R0));

    return instrs;
  }

  private List<Instruction> transBinOp(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    switch (binExpr.getOp()) {
      case ADD:
        return transAddExpr(binExpr);
      case MULT:
        return transMultExpr(binExpr);
      case DIVIDE:
        return transDivideExpr(binExpr);
      case MOD:
        return transModExpr(binExpr);
      case SUB:
        return transSubExpr(binExpr);
      case GREATER:
        return transGreaterExpr(binExpr);
      case GREATEREQ:
        return transGreatereqExpr(binExpr);
      case LESS:
        return transLessExpr(binExpr);
      case LESSEQ:
        return transLesseqExpr(binExpr);
      case EQUALS:
        return transEqualsExpr(binExpr);
      case NOTEQUAL:
        return transNotequalExpr(binExpr);
      case AND:
        return transAndExpr(binExpr);
      case OR:
        return transOrExpr(binExpr);
      case BITWISEOR:
        return transBitwiseOR(binExpr);
      case BITWISEAND:
        return transBitwiseAnd(binExpr);
      case BITWISEXOR:
        return transBitwiseXor(binExpr);
      case SHIFTLEFT:
        return transShiftLeft(binExpr);
      case SHIFTRIGHT:
        return tranShiftRight(binExpr);
    }

    return instrs;
  }

  private List<Instruction> tranShiftRight(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.LSR, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transShiftLeft(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.LSL, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transBitwiseXor(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.EOR, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transBitwiseAnd(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.AND, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transBitwiseOR(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));


    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.ORR, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transOrExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.ORR, ACC, ACC, TMP));

    return instrs;
  }

  private List<Instruction> transAndExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.AND, ACC, ACC, TMP));

    return instrs;
  }

  private List<Instruction> transNotequalExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVNE, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVEQ, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transLesseqExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));

    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVLE, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVGT, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transLessExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));

    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVLT, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVGE, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transGreatereqExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVGE, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVLT, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transGreaterExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVGT, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVLE, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transSubExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();


    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));


    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.SUBS, ACC, TMP, ACC));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transModExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.BL, new Label("p_check_divide_by_zero")));
    instrs.add(new Instruction(Opcode.BL, new Label("__aeabi_idivmod")));
    instrs.add(new Instruction(Opcode.MOV, ACC, new Register(1)));
    dataLabels.add(new DataLabel(7, "\"DivideByZeroError: divide or modulo by zero\\n\\0\""));
    dataLabels.add(new DataLabel(0, "\"%.*s\\0\""));

    divide_by_zero = true;
    run_time_error = true;
    prt_str = true;

    return instrs;
  }

  private List<Instruction> transDivideExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.MOV, R0, TMP));
    instrs.add(new Instruction(Opcode.MOV, new Register(1), new Register(4)));

    instrs.add(new Instruction(Opcode.BL, new Label("p_check_divide_by_zero")));
    instrs.add(new Instruction(Opcode.BL, new Label("__aeabi_idiv")));
    instrs.add(new Instruction(Opcode.MOV, ACC, R0));
    dataLabels.add(new DataLabel(7, "\"DivideByZeroError: divide or modulo by zero\\n\\0\""));
    dataLabels.add(new DataLabel(0, "\"%.*s\\0\""));

    divide_by_zero = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transMultExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));

    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.SMULL, ACC, TMP, ACC, TMP));
    instrs.add(new Instruction(Opcode.LABEL, new Label("\tCMP r5, r4, ASR #31")));
    instrs.add(new Instruction(Opcode.BLNE, new Label(OVERFLOW)));


    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private List<Instruction> transEqualsExpr(BinOpExpr binExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(binExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(binExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.CMP, TMP, ACC));
    instrs.add(new Instruction(Opcode.MOVEQ, ACC, new Immediate(1)));
    instrs.add(new Instruction(Opcode.MOVNE, ACC, new Immediate(0)));

    return instrs;
  }

  private List<Instruction> transAddExpr(BinOpExpr addExpr) {
    List<Instruction> instrs = new ArrayList<>();

    instrs.addAll(transExpression(addExpr.getExpr1()));
    instrs.add(push(ACC));
    instrs.addAll(transExpression(addExpr.getExpr2()));
    instrs.add(pop(TMP));
    instrs.add(new Instruction(Opcode.ADDS, ACC, ACC, TMP));
    instrs.add(new Instruction(Opcode.BLVS, new Label(OVERFLOW)));

    dataLabels.add(DATA_OVERFLOW);
    overflow = true;
    setRunTimeError();

    return instrs;
  }

  private Instruction push(Register reg) {
    stackOffset += 4;
    return new Instruction(Opcode.PUSH, reg);
  }

  private Instruction pop(Register reg) {
    stackOffset -= 4;
    return new Instruction(Opcode.POP, reg);
  }
}

