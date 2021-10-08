package compiler;

import expressions.Call;
import expressions.Expr;
import expressions.Ident;
import functions.*;
import newclass.*;
import statements.*;
import types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SemanticChecker {

  private final Scope globalScope;
  private final SemanticErrorLog errorLog;
  private final HashMap<String, List<FunctionType>> funcTypes;
  private final HashMap<String, Newclass> classNames;
  private final HashMap<String, HashMap<String, List<FunctionType>>> methodTypes;
  private final HashMap<String, List<FunctionType>> constructorTypes;
  private final SemanticCheckData semanticData;


  private final Type BOOL_TYPE = new BaseType(BaseTypeEnum.BOOL);
  private final Type INT_TYPE = new BaseType(BaseTypeEnum.INT);
  private final Type ERROR_TYPE = new ErrorType();
  private boolean insideLoop = false;
  private String insideClass = null;


  public SemanticChecker() {
    globalScope = new Scope(null);
    errorLog = new SemanticErrorLog();
    funcTypes = new HashMap<>();
    classNames = new HashMap<>();
    methodTypes = new HashMap<>();
    constructorTypes = new HashMap<>();
    semanticData = new SemanticCheckData(errorLog, funcTypes, classNames, methodTypes, constructorTypes);
  }

  public HashMap<String, Newclass> getClassNames() {
    return classNames;
  }

  public Scope getGlobalScope() {
    return globalScope;
  }

  public void typeCheck(Program program) {
    //getting function return and parameter types
    List<Function> functions = program.getFunctions();
    buildFunctionTypeSignature(functions, funcTypes);


    //getting class names
    List<Newclass> classes = program.getClasses();
    buildClassTypeSignatures(classes);


    //type checking main
    Scope main = new Scope(globalScope);
    globalScope.addChild(main);
    semanticData.setCurrScope(main);
    List<Stat> statements = program.getStatements();
    typeCheckStats(statements, null, main);


    //type checking functions
    for (Function function : functions) {
      typeCheckFunction(function, globalScope);
    }


    //type checking classes
    Scope globalClassScope = new Scope(globalScope);
    globalScope.addChild(globalClassScope);
    for (Newclass newclass : classes) {
      insideClass = newclass.getName().getClassName();
      semanticData.setInsideClass(insideClass);
      typeCheckClass(newclass, globalClassScope);
    }
    insideClass = null;
    semanticData.setInsideClass("");


    //printing errors
    errorLog.semanticCheckResult();
  }

  private void buildClassTypeSignatures(List<Newclass> classes) {
    for (Newclass newclass : classes) {

      //check for duplicate classes
      String className = newclass.getName().getClassName();
      if (classNames.get(className) != null) {
        System.out.println("Error in line " + newclass + ":" + "\n" +
                "Class " + className + " redefined");
        System.out.println("#syntax_error#");
        System.exit(100);
      }
      classNames.put(className, newclass);


      //build all the method and constructor signatures for the class
      HashMap<String, List<FunctionType>> methodType = new HashMap<>();
      methodTypes.put(className, methodType);
      buildFunctionTypeSignature(newclass.getMethods(), methodType);
      //add a default constructor if class has no constructor
      if (newclass.getConstructors().size() == 0) {
        Function defaultConstructor = new Function(new ClassType(className),
                new Ident(className, 0, 0), new ArrayList<>(), new ArrayList<>()
                , 0, 0);
        newclass.getConstructors().add(defaultConstructor);
      }
      buildFunctionTypeSignature(newclass.getConstructors(), constructorTypes);
    }
  }

  private void buildFunctionTypeSignature(List<Function> functions,
                                          HashMap<String, List<FunctionType>> funcTypes) {
    for (Function function : functions) {
      //store function return and parameter types
      String funcName = function.getName().getString();
      List<Type> argTypes = new ArrayList<>();
      List<Param> params = function.getParams();
      for (Param param : params) {
        argTypes.add(param.getType());
      }
      Type returnType = function.getReturnType();
      FunctionType funcType = new FunctionType(argTypes, returnType, function.getFuncName(), function.isPublic());


      //get previous functions with same name
      List<FunctionType> fTypes = funcTypes.get(funcName);
      if (fTypes == null) {
        fTypes = new ArrayList<>();
      } else {
        boolean sameFuncParams;
        for (FunctionType fType : fTypes) {
          sameFuncParams = false;
          List<Type> funcArgTypes = fType.getInputType();
          if (funcArgTypes.size() == argTypes.size()) {
            sameFuncParams = true;
            for (int i = 0; i < funcArgTypes.size(); i++) {
              if (!funcArgTypes.get(i).match(argTypes.get(i))) {
                sameFuncParams = false;
                break;
              }
            }
          }
          if (sameFuncParams) {
            String errorFunc = returnType.toString() + " " + funcName + "( ";
            for (Type t : argTypes) {
              errorFunc += t.toString() + " ";
            }
            errorFunc += ")";
            System.out.println("Error in line:" + function.getLineNum() + ":" +
                    function.getLinePos() + "\n" + errorFunc + " redefined");
            System.out.println("#syntax_error#");
            System.exit(100);
          }
        }
      }
      fTypes.add(funcType);
      funcTypes.put(funcName, fTypes);
    }
  }

  private void typeCheckFunction(Function function, Scope currScope) {
    //give a new scope to each function
    Scope funcScope = new Scope(currScope, function.getFuncName());
    currScope.addChild(funcScope);


    //add parameters to scope
    typeCheckParamList(function, funcScope);


    //type check body
    List<Stat> funcBody = function.getStatements();
    typeCheckStats(funcBody, function.getReturnType(), funcScope);
  }

  private void typeCheckClass(Newclass newclass, Scope globalClassScope) {
    //add class to globalClassScope
    Scope classScope = new Scope(globalClassScope);
    globalClassScope.addChild(classScope);
    String className = newclass.getName().getClassName();


    //add class attributes to scope
    for (ClassAttribute attribute : newclass.getAttributes()) {
      Type t = attribute.getType();
      classScope.addNewVar(attribute.getName().getString(), t);
      t.declare();
      t.setToAttribute();

      if (t.match(new BaseType(BaseTypeEnum.VOID))) {
        Ident attrName = attribute.getName();
        errorLog.error("Error in line " + attrName.getLineNum() + ":" +
                        attrName.getLinePos() + "\n" + "Attribute " +
                        attrName.getString() + " cannot have type void");
      }
    }


    //validate constructors
    Scope construcScope;
    for (Function constructor : newclass.getConstructors()) {
      //add constructor Scope
      construcScope = new Scope(classScope);
      classScope.addChild(construcScope);

      //check constructor name is same as class name
      if (!constructor.getName().getString().equals(className)) {
        System.out.println("Error in line " + constructor.getLineNum() + ":" +
                constructor.getLinePos() + "\n" +
                "Invalid constructor in class " + className);
        System.out.println("#syntax_error#");
        System.exit(100);
      }

      //add constructor parameters to Scope
      typeCheckParamList(constructor, construcScope);

      //check the stats, expecting no return type
      typeCheckStats(constructor.getStatements(), null, construcScope);
    }

    //validate methods
    for (Function func : newclass.getMethods()) {
      typeCheckFunction(func, classScope);
    }
  }

  private void typeCheckParamList(Function function, Scope currScope) {
    for (Param param : function.getParams()) {
      Type paramType = param.getType();
      paramType.declare();
      paramType.setToParam();
      currScope.addNewVar(param.getName(), paramType);

      if (param.getType().match(new BaseType(BaseTypeEnum.VOID))) {
        errorLog.error("Error in line " + function.getLineNum() +
                ":" + function.getLinePos() + "\n" +
                "Parameter " + param.getName() + " cannot have type VOID");
      }
    }

  }

  private void typeCheckClassCall(ClassCall stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    stat.getType(semanticData);
  }

  private void typeCheckStats(List<Stat> stats, Type retType, Scope currScope) {
    for (Stat stat : stats) {
      typeCheckStat(stat, retType, currScope);
    }
  }

  private void typeCheckStat(Stat stat, Type retType, Scope currScope) {

    switch (stat.getStatType()) {
      case IF:
        typeCheckIf((IfStat) stat, retType, currScope);
        return;

      case WHILE:
        typeCheckWhile((WhileStat) stat, retType, currScope);
        return;

      case BEGIN:
        typeCheckBegin((BeginStat) stat, retType, currScope);
        return;

      case ASSIGNMENT:
        typeCheckAssignment((AssignmentStat) stat, currScope);
        return;

      case DECLARATION:
        typeCheckDeclaration((DeclarationStat) stat, currScope);
        return;

      case READ:
        typeCheckRead((ReadStat) stat, currScope);
        return;

      case FREE:
        typeCheckFree((ExprStat) stat, currScope);
        return;

      case PRINT:
      case PRINTLN:
        typeCheckPrint((ExprStat) stat, currScope);
        return;

      case EXIT:
        typeCheckExit((ExprStat) stat, currScope);
        return;

      case RETURN:
        typeCheckReturn((ExprStat) stat, retType, currScope);
        return;

      case FOR:
        typeCheckForLoop((ForLoopStat) stat, retType, currScope);
        return;

      case DO_UNTIL:
        typeCheckDoUntil((DoUntilStat) stat, retType, currScope);
        return;

      case BREAK:
      case CONTINUE:
        typeCheckLoopControl((LoopControlStat) stat);
        return;

      case CALL:
        typeCheckCall((Call) stat, currScope);
        return;

      case CLASS_CALL:
        typeCheckClassCall((ClassCall) stat, currScope);
        return;
      default:
        return;
    }
  }

  private void typeCheckCall(Call stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    stat.getType(semanticData);
  }

  private void typeCheckLoopControl(LoopControlStat stat) {
    if (!insideLoop) {
      String erroroneousStat;
      if (stat.isBreak()) {
        erroroneousStat = "BREAK";
      } else {
        erroroneousStat = "CONTINUE";
      }
      semanticData.getErrorLog().error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              erroroneousStat + " used outside of a loop");
    }
  }

  private void typeCheckForLoop(ForLoopStat stat, Type retType, Scope currScope) {
    insideLoop = true;

    StatType initType = stat.getInit().getStatType();
    if (initType != StatType.ASSIGNMENT && initType != StatType.DECLARATION) {
      errorLog.error("Error in line " + stat.getInit().getLineNum() + ":"
              + stat.getLinePos() + "\n" +
              "FOR LOOP expression expected a declaration or assignment as" +
              " first parameter, but was given a " + initType.toString()
              + " statement");
    }

    StatType incrementType = stat.getIncrement().getStatType();
    if (incrementType != StatType.ASSIGNMENT) {
      errorLog.error("Error in line " + stat.getInit().getLineNum() + ":"
              + stat.getLinePos() + "\n" +
              "FOR LOOP expression expected an assignment as third parameter," +
              " but was given a " + incrementType.toString() + " statement");
    }

    Scope forLoopBody = new Scope(currScope);
    currScope.addChild(forLoopBody);

    //init is part of the for scope
    semanticData.setCurrScope(forLoopBody);
    typeCheckStat(stat.getInit(), retType, forLoopBody);

    //condition can have init var inside it but not any of the other vars inside
    //the for body
    semanticData.setCurrScope(forLoopBody);
    Type conditionType = stat.getCondition().getType(semanticData);
    if (!conditionType.match(BOOL_TYPE)) {
      errorLog.error("Error in line " + stat.getLineNum() + ":" +
              stat.getLinePos() + "\nFOR LOOP expression expected BOOL as " +
              "second parameter, but was given " + conditionType.toString());
    }


    typeCheckStat(stat.getIncrement(), retType, forLoopBody);
    typeCheckStats(stat.getStats(), retType, forLoopBody);

    insideLoop = false;
  }

  private void typeCheckDoUntil(DoUntilStat stat, Type retType, Scope currScope) {
    insideLoop = true;

    semanticData.setCurrScope(currScope);
    Type type = stat.getCondition().getType(semanticData);
    if (!type.match(BOOL_TYPE)) {
      errorLog.error("Error in line " + stat.getLineNum() + ":" +
              stat.getLinePos() + "\n" +
              "DO UNTIL expression expected BOOL, but was given " +
              type.toString());
    }
    Scope doUntilBody = new Scope(currScope);
    currScope.addChild(doUntilBody);
    typeCheckStats(stat.getStats(), retType, doUntilBody);

    insideLoop = false;
  }

  private void typeCheckIf(IfStat stat, Type retType, Scope currScope) {

    semanticData.setCurrScope(currScope);
    Type type = stat.getExpr().getType(semanticData);
    if (!type.match(BOOL_TYPE)) {
      errorLog.error("Error in line " + stat.getLineNum() + ":" +
              stat.getLinePos() + "\n" +
              "IF expression expected BOOL, but was given " +
              type.toString());
    }
    Scope thenScope = new Scope(currScope);
    Scope elseScope = new Scope(currScope);
    currScope.addChild(thenScope);
    currScope.addChild(elseScope);

    typeCheckStats(stat.getThenStats(), retType, thenScope);
    typeCheckStats(stat.getElseStats(), retType, elseScope);
  }

  private void typeCheckWhile(WhileStat stat, Type retType, Scope currScope) {
    insideLoop = true;

    semanticData.setCurrScope(currScope);
    Type type = stat.getExpr().getType(semanticData);

    if (!type.match(BOOL_TYPE)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "WHILE expression expected BOOL, but was given " +
              type.toString());
    }
    Scope whileBody = new Scope(currScope);
    currScope.addChild(whileBody);
    typeCheckStats(stat.getStats(), retType, whileBody);

    insideLoop = false;
  }

  private void typeCheckBegin(BeginStat stat, Type retType, Scope currScope) {
    Scope beginBody = new Scope(currScope);
    currScope.addChild(beginBody);
    typeCheckStats(stat.getStats(), retType, beginBody);
  }

  private void typeCheckAssignment(AssignmentStat stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    semanticData.setAssignLhs(true);
    Type leftType = stat.getLhs().getType(semanticData);
    semanticData.setAssignLhs(false);

    semanticData.setCallType(leftType);
    Type rightType = stat.getRhs().getType(semanticData);

    if (!leftType.match(rightType) &&
            !(leftType instanceof ErrorType) &&
            !(rightType instanceof ErrorType)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "ASSIGNMENT expected types to match, but was given " +
              leftType.toString() + " and " + rightType.toString());
    }
  }

  private void typeCheckDeclaration(DeclarationStat stat, Scope currScope) {
    String varName = stat.getName().getString();


    if (stat.getType().match(new BaseType(BaseTypeEnum.VOID))) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              varName + " cannot be declared with type VOID");
    }
    //variable defined in current scope
    if (currScope.contains(varName)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              varName + " already exists in this scope");
    }

    Type varType = stat.getType();
    semanticData.setCurrScope(currScope);
    semanticData.setCallType(varType);
    Type rhsType = stat.getRHS().getType(semanticData);

    currScope.addNewVar(varName, varType);
    if (!varType.match(rhsType) && !ERROR_TYPE.match(rhsType)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "DECLARATION rhs expected " + varType.toString() +
              ", but was given " + rhsType.toString());
    }
  }

  private void typeCheckRead(ReadStat stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    Type bufferType = stat.getLhs().getType(semanticData);
    if (!bufferType.match(INT_TYPE) &&
            !bufferType.match(new BaseType(BaseTypeEnum.CHAR)) &&
            !bufferType.match(new BaseType(BaseTypeEnum.STRING))) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "READ expression expected INT, CHAR or STRING, " +
              "but was given " + bufferType.toString());
    }
  }

  private void typeCheckFree(ExprStat stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    Type type = stat.getExpr().getType(semanticData);
    if (!(type instanceof PairType) && !(type instanceof ArrayType)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "FREE expression expected PAIR or ARRAY, but was given " +
              type.toString());
    }
  }

  private void typeCheckPrint(ExprStat stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    stat.getExpr().getType(semanticData);
  }

  private void typeCheckExit(ExprStat stat, Scope currScope) {
    semanticData.setCurrScope(currScope);
    Type type = stat.getExpr().getType(semanticData);
    if (!type.match(INT_TYPE)) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "EXIT expected an INT, but was given " + type.toString());
    }
  }

  private void typeCheckReturn(ExprStat stat, Type retType, Scope currScope) {
    if (retType == null) {
      errorLog.error("Error in line " + stat.getLineNum() +
              ":" + stat.getLinePos() + "\n" +
              "RETURN statement not expected outside of a function");
    } else {
      semanticData.setCurrScope(currScope);
      Type type = stat.getExpr().getType(semanticData);

      if (!type.match(retType) && !ERROR_TYPE.match(type) &&
              !retType.match(new BaseType(BaseTypeEnum.VOID))) {
        errorLog.error("Error in line " + stat.getLineNum() +
                ":" + stat.getLinePos() + "\n" +
                "Function expected " + retType.toString() +
                " return type, but was given " + type.toString());
      }
    }
  }
}
