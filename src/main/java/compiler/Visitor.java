package compiler;

import expressions.*;
import functions.*;
import newclass.*;
import statements.*;
import types.*;
import antlr.BasicParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static antlr.BasicParser.*;
import static java.lang.System.exit;


public class Visitor implements BasicParserVisitor {

  @Override
  public Program visitProgram(ProgramContext ctx) {
    List<Function> functions = new ArrayList<>();
    Map<String, Integer> overloadingCount = new HashMap<>();

    List<Stat> statements = visitStat(ctx.stat());

    for (FuncContext func : ctx.func()) {
      Function f = visitFunc(func);

      setOverloadingNum(overloadingCount, f);

      functions.add(f);
    }

    List<Newclass> classes = new ArrayList<>();
    for (NewclassContext context : ctx.newclass()) {
      Newclass newclass = visitNewclass(context);

      for (Function method : newclass.getMethods()) {
        setOverloadingNum(overloadingCount, method);
      }
      for (Function constructor : newclass.getConstructors()) {
        setOverloadingNum(overloadingCount, constructor);
      }

      classes.add(newclass);
    }

    return new Program(functions, statements, classes);
  }

  private void setOverloadingNum(Map<String, Integer> overloadingCount,
                                 Function f) {
    //work out if the function is going to be overloaded or not
    Integer oldValue = overloadingCount.getOrDefault(f.getName().getString(), 0);
    overloadingCount.put(f.getName().getString(), oldValue + 1);
    f.setOverloadingNum(oldValue);
  }

  @Override
  public ClassAttribute visitAttribute(AttributeContext ctx) {
    boolean isPublic = (ctx.PUBLIC() != null);
    Type type = visitType(ctx.type());
    Ident name = new Ident(ctx.IDENT().getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
    return new ClassAttribute(isPublic, type, name);
  }

  @Override
  public Function visitConstructor(ConstructorContext ctx) {
    Ident name = new Ident(ctx.IDENT().getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
    List<Param> paramList = visitParamList(ctx.paramList());
    List<Stat> statements = visitStat(ctx.stat());
    return new Function(new ClassType(name.getString()), name, paramList,
            statements, ctx.start.getLine(), ctx.start.getCharPositionInLine());
  }

  @Override
  public Newclass visitNewclass(NewclassContext ctx) {
    String name = ctx.IDENT().getSymbol().getText();
    List<ClassAttribute> attributes = new ArrayList<>();
    for (AttributeContext attribute : ctx.attribute()) {
      attributes.add(visitAttribute(attribute));
    }

    List<Function> constructors = new ArrayList<>();
    for (ConstructorContext constructor : ctx.constructor()) {
      constructors.add(visitConstructor(constructor));
    }

    List<Function> funcs = new ArrayList<>();
    for (MethodContext methodContext: ctx.method()) {
      funcs.add(visitMethod(methodContext));
    }
    return new Newclass(new ClassType(name), attributes, constructors, funcs);
  }

  @Override
  public Function visitMethod(MethodContext ctx) {
    Function function = visitFunc(ctx.func());
    if (ctx.PUBLIC() == null) {
      function.setToPrivate();
    }
    return function;
  }

  @Override
  public Function visitFunc(FuncContext ctx) {
    Type returnType = visitType(ctx.type());
    String name = ctx.IDENT().getSymbol().getText();

    List<Param> params = visitParamList(ctx.paramList());
    List<Stat> statements = visitStat(ctx.stat());

    Function f = new Function(returnType, new Ident(name, ctx.start.getLine(),
        ctx.start.getCharPositionInLine()), params, statements,
        ctx.start.getLine(), ctx.start.getCharPositionInLine());

    checkNoStatsFollowReturn(f.getStatements());

    if (!f.getReturnType().match(new BaseType(BaseTypeEnum.VOID)) &&
            !validFunctionBody(f.getStatements())) {
      System.out.println("Line: " + f.getLineNum() + ":" +
          f.getLinePos() + " function " + f.getName().getString() +
          " missing return statement");
      System.out.println("#syntax_error#");
      exit(100);
    }

    return f;
  }

  private void checkNoStatsFollowReturn(List<Stat> statList) {
    boolean hasReturn = false;

    for (Stat stat : statList) {
      if (hasReturn) {
        System.out.println("Line: " + stat.getLineNum() + ":" +
                stat.getLinePos() + " " + stat.getStatType() +
                " statement cannot follow return statement");
        System.out.println("#syntax_error#");
        exit(100);
      }
      hasReturn = statHasReturn(stat);
    }
  }

  private boolean validFunctionBody(List<Stat> statList) {
    if (statList.isEmpty()) {
      return false;
    }
    return statHasReturn(statList.get(statList.size() - 1));
  }

  private boolean statHasReturn(Stat s) {
    switch (s.getStatType()) {
      case EXIT:
      case RETURN:
        return true;
      case IF:
        IfStat ifStat = (IfStat) s;
        return validFunctionBody(ifStat.getThenStats()) &&
            validFunctionBody(ifStat.getElseStats());
      case BEGIN:
        BeginStat beginStat = (BeginStat) s;
        return validFunctionBody(beginStat.getStats());
      case DO_UNTIL:
        DoUntilStat doUntilStat = (DoUntilStat) s;
        return validFunctionBody(doUntilStat.getStats());
      default:
        return false;
    }
  }

  @Override
  public List<Param> visitParamList(ParamListContext ctx) {
    List<Param> paramList = new ArrayList<>();

    if (ctx != null) {
      for (ParamContext param : ctx.param()) {
        paramList.add(visitParam(param));
      }
    }
    return paramList;
  }

  @Override
  public Param visitParam(ParamContext ctx) {
    Type type = visitType(ctx.type());
    String name = ctx.IDENT().getSymbol().getText();
    return new Param(type, name);
  }

  @Override
  public List<Stat> visitStat(StatContext ctx) {

    List<Stat> stats = new ArrayList<>();

    if (ctx.SEMI_COLON() != null) {
      for (int i = 0; i < 2; i++) {
        for (Stat stat : visitStat(ctx.stat(i))) {
          stats.add(stat);
        }
      }

    } else if (ctx.CALL() != null) {
      List<Expr> exprs = new ArrayList<>();

      if (ctx.argList() != null) {
        for (ExprContext expr : ctx.argList().expr()) {
          exprs.add(visitExpr(expr));
        }
      }

      stats.add(new Call(new Ident(ctx.IDENT().getSymbol().getText(),
              ctx.start.getLine(), ctx.start.getCharPositionInLine()), exprs,
              ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.BEGIN() != null) {
      stats.add(new BeginStat(visitStat(ctx.stat(0)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.WHILE() != null) {
      stats.add(new WhileStat(visitExpr(ctx.expr()), visitStat(ctx.stat(0)),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.IF() != null) {
      stats.add(new IfStat(visitExpr(ctx.expr()), visitStat(ctx.stat(0)),
          visitStat(ctx.stat(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.FOR() != null) {
      List<Stat> tmp = visitStat(ctx.stat(0));
      Stat init = null;
      if (tmp.size() == 1) {
        init = tmp.get(0);
      } else {
        System.out.println("#Syntax Error");
        System.out.println("Line: " + ctx.start.getLine() + ":" +
            ctx.start.getCharPositionInLine() + " First parameter of FOR " +
            "should contain one statement");
        System.exit(100);
      }

      tmp = visitStat(ctx.stat(1));
      Stat increment = null;
      if (tmp.size() == 1) {
        increment = tmp.get(0);
      } else {
        System.out.println("#Syntax Error");
        System.out.println("Line: " + ctx.start.getLine() + ":" +
            ctx.start.getCharPositionInLine() + " Third parameter of FOR " +
            "should contain one statement");
        System.exit(100);
      }

      stats.add(new ForLoopStat(init, visitExpr(ctx.expr()), increment,
          visitStat(ctx.stat(2)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.UNTIL() != null) {
      stats.add(new DoUntilStat(visitExpr(ctx.expr()), visitStat(ctx.stat(0)),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.FREE() != null) {
      stats.add(new ExprStat(StatType.FREE, visitExpr(ctx.expr()),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.EXIT() != null) {
      stats.add(new ExprStat(StatType.EXIT, visitExpr(ctx.expr()),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.RETURN() != null) {
      stats.add(new ExprStat(StatType.RETURN, visitExpr(ctx.expr()),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.PRINT() != null) {
      stats.add(new ExprStat(StatType.PRINT, visitExpr(ctx.expr()),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.PRINTLN() != null) {
      stats.add(new ExprStat(StatType.PRINTLN, visitExpr(ctx.expr()),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.READ() != null) {
      stats.add(new ReadStat(visitAssignLhs(ctx.assignLhs()),
              ctx.start.getLine(), ctx.start.getCharPositionInLine()));

    } else if (ctx.classOp().size() > 0) {
      List<ClassOp> classOps = new ArrayList<>();

      for (ClassOpContext classOp : ctx.classOp()) {
        classOps.add(visitClassOp(classOp));
      }

      if (ctx.SELF() != null) {
        stats.add(new ClassCall(new Ident("self",
            ctx.start.getLine(),ctx.start.getCharPositionInLine()), classOps,
            true, ctx.start.getLine(), ctx.start.getCharPositionInLine()));
      } else {
        stats.add(new ClassCall(new Ident(ctx.IDENT().getSymbol().getText(),
                ctx.start.getLine(), ctx.start.getCharPositionInLine()), classOps,
                false, ctx.start.getLine(), ctx.start.getCharPositionInLine()));
      }
    } else if (ctx.IDENT() != null) {
      stats.add(new DeclarationStat(visitType(ctx.type()),
          new Ident(ctx.IDENT().getSymbol().getText(), ctx.start.getLine(),
              ctx.start.getCharPositionInLine()),
          visitAssignRhs(ctx.assignRhs()), ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.ASSIGNMENT() != null) {
      stats.add(new AssignmentStat(visitAssignLhs(ctx.assignLhs()),
          visitAssignRhs(ctx.assignRhs()), ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.BREAK() != null) {
      stats.add(new LoopControlStat(true, ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));

    } else if (ctx.CONTINUE() != null) {
      stats.add(new LoopControlStat(false, ctx.start.getLine(),
          ctx.start.getCharPositionInLine()));
    }


    return stats;
  }

  @Override
  public AssignLHS visitAssignLhs(AssignLhsContext ctx) {
    if (ctx.classOp().size() > 0) {
      List<ClassOp> classOps = new ArrayList<>();

      for (ClassOpContext classOp : ctx.classOp()) {
        classOps.add(visitClassOp(classOp));
      }

      if (ctx.SELF() != null) {
        return new ClassCall(new Ident("self",
            ctx.start.getLine(),ctx.start.getCharPositionInLine()), classOps, true, ctx.start.getLine(),
                ctx.start.getCharPositionInLine());
      }
      return new ClassCall(new Ident(ctx.IDENT().getSymbol().getText(),
              ctx.start.getLine(), ctx.start.getCharPositionInLine()), classOps,
              false, ctx.start.getLine(), ctx.start.getCharPositionInLine());

    } else if (ctx.IDENT() !=  null) {
      return new Ident(ctx.IDENT().getSymbol().getText(), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.arrayElem() != null) {
      return visitArrayElem(ctx.arrayElem());

    } else if (ctx.pairElem() != null) {
      return visitPairElem(ctx.pairElem());

    }
    return null;
  }

  @Override
  public AssignRHS visitAssignRhs(AssignRhsContext ctx) {
    if (ctx.expr().size() == 2) {
      return new NewPair(visitExpr(ctx.expr(0)), visitExpr(ctx.expr(1)));

    } else if (ctx.expr().size() == 1) {
      return visitExpr(ctx.expr(0));

    } else if (ctx.arrayLiter() != null) {
      return visitArrayLiter(ctx.arrayLiter());

    } else if (ctx.pairElem() != null) {
      return visitPairElem(ctx.pairElem());

    } else if (ctx.classOp().size() > 0) {
      List<ClassOp> classOps = new ArrayList<>();

      for (ClassOpContext classOp : ctx.classOp()) {
        classOps.add(visitClassOp(classOp));
      }

      if (ctx.SELF() != null) {
        return new ClassCall(new Ident("self",
            ctx.start.getLine(),ctx.start.getCharPositionInLine()),
            classOps, true,
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new ClassCall(new Ident(ctx.IDENT().getSymbol().getText(),
              ctx.start.getLine(), ctx.start.getCharPositionInLine()), classOps,
              false, ctx.start.getLine(), ctx.start.getCharPositionInLine());


    } else if (ctx.NEW() != null){
      Ident classname = new Ident(ctx.IDENT().getSymbol().getText(),
          ctx.start.getLine(), ctx.start.getCharPositionInLine());
      List<Expr> exprs;
      if (ctx.argList() != null) {
        exprs = visitArgList(ctx.argList());
      } else {
        exprs = new ArrayList<>();
      }
      return new NewObject(classname, exprs, ctx.start.getLine(),
                        ctx.start.getCharPositionInLine());

    } else if (ctx.CALL() != null) {
      List<Expr> exprs = new ArrayList<>();

      if (ctx.argList() != null) {
        for (ExprContext expr : ctx.argList().expr()) {
          exprs.add(visitExpr(expr));
        }
      }

      return new Call(new Ident(ctx.IDENT().getSymbol().getText(),
          ctx.start.getLine(), ctx.start.getCharPositionInLine()), exprs,
          ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    return null;
  }

  @Override
  public ClassOp visitClassOp(ClassOpContext ctx) {
    //its a method call
    if (ctx.OPEN_BRACKETS() != null) {
      List<Expr> exprs = new ArrayList<>();

        if (ctx.argList() != null) {
          for (ExprContext expr : ctx.argList().expr()) {
            exprs.add(visitExpr(expr));
          }
        }

        return new MethodCall(new Ident(ctx.IDENT().getSymbol().getText(),
            ctx.start.getLine(), ctx.start.getCharPositionInLine()), exprs);

    //its a field access
    } else if (ctx.IDENT() != null) {
      return new Ident(ctx.IDENT().getSymbol().getText(),
          ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    return null;
  }

  @Override
  public List<Expr> visitArgList(ArgListContext ctx) {
    List<Expr> args = new ArrayList<>();

    for (ExprContext expr : ctx.expr()) {
      args.add(visitExpr(expr));
    }

    return args;
  }

  @Override
  public PairElem visitPairElem(PairElemContext ctx) {
    return new PairElem(visitExpr(ctx.expr()), ctx.FST() != null,
        ctx.start.getLine(), ctx.start.getCharPositionInLine());
  }

  @Override
  public Type visitType(TypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());

    } else if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());

    } else if (ctx.pairType() != null) {
      return visitPairType(ctx.pairType());

    } else if (ctx.IDENT() != null) {
      return new ClassType(ctx.IDENT().getSymbol().getText());
    }
    return null;
  }

  @Override
  public BaseType visitBaseType(BaseTypeContext ctx) {
    if (ctx.INT() != null) {
      return new BaseType(BaseTypeEnum.INT);

    } else if (ctx.BOOL() != null) {
      return new BaseType(BaseTypeEnum.BOOL);

    } else if (ctx.CHAR() != null) {
      return new BaseType(BaseTypeEnum.CHAR);

    } else if (ctx.STRING() != null) {
      return new BaseType(BaseTypeEnum.STRING);

    } else if (ctx.VOID() != null) {
      return new BaseType(BaseTypeEnum.VOID);
    }
    return null;
  }

  @Override
  public ArrayType visitArrayType(ArrayTypeContext ctx) {
    if (ctx.baseArrayType() != null) {
      return new ArrayType(visitBaseArrayType(ctx.baseArrayType()));

    } else if (ctx.arrayType() != null) {
      return new ArrayType(visitArrayType(ctx.arrayType()));
    }
    return null;
  }

  @Override
  public Type visitBaseArrayType(BaseArrayTypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());

    } else if (ctx.pairType() != null) {
      return visitPairType(ctx.pairType());

    }
    return null;
  }

  @Override
  public PairType visitPairType(PairTypeContext ctx) {
    if (ctx.pairElemType().size() == 2) {
      return new PairType(visitPairElemType(ctx.pairElemType(0)),
          visitPairElemType(ctx.pairElemType(1)));
    }
    return null;
  }

  @Override
  public Type visitPairElemType(PairElemTypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());

    } else if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());

    } else {
      return new PairType();
    }
  }

  @Override
  public Expr visitExpr(ExprContext ctx) {
    if (ctx.unaryOper() != null) {
      UnOp unOp = visitUnaryOper(ctx.unaryOper());
      return new UnOpExpr(unOp, visitExpr(ctx.expr().get(0)),
          ctx.start.getLine(), ctx.start.getCharPositionInLine());

    } else if (ctx.MODULO() != null) {
      return new BinOpExpr(BinOp.MOD, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.MULTIPLY() != null) {
      return new BinOpExpr(BinOp.MULT, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.DIVIDE() != null) {
      return new BinOpExpr(BinOp.DIVIDE, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.ADD() != null) {
      return new BinOpExpr(BinOp.ADD, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.MINUS() != null) {
      return new BinOpExpr(BinOp.SUB, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.GREATER_OR_EQUAL() != null) {
      return new BinOpExpr(BinOp.GREATEREQ, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.GREATER_THAN() != null) {
      return new BinOpExpr(BinOp.GREATER, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.LESS_OR_EQUAL() != null) {
      return new BinOpExpr(BinOp.LESSEQ, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.LESS_THAN() != null) {
      return new BinOpExpr(BinOp.LESS, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.EQUALS() != null) {
      return new BinOpExpr(BinOp.EQUALS, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.NOT_EQUALS() != null) {
      return new BinOpExpr(BinOp.NOTEQUAL, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.AND() != null) {
      return new BinOpExpr(BinOp.AND, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.OR() != null) {
      return new BinOpExpr(BinOp.OR, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.BITWISEOR() != null) {
      return new BinOpExpr(BinOp.BITWISEOR, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.BITWISEAND() != null) {
      return new BinOpExpr(BinOp.BITWISEAND, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.BITWISEXOR() != null) {
      return new BinOpExpr(BinOp.BITWISEXOR, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.SHIFTLEFT() != null) {
      return new BinOpExpr(BinOp.SHIFTLEFT, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.SHIFTRIGHT() != null) {
      return new BinOpExpr(BinOp.SHIFTRIGHT, visitExpr(ctx.expr().get(0)),
          visitExpr(ctx.expr().get(1)), ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else if (ctx.strLiter() != null) {
      return visitStrLiter(ctx.strLiter());

    } else if (ctx.boolLiter() != null) {
      return visitBoolLiter(ctx.boolLiter());

    } else if (ctx.charLiter() != null) {
      return visitCharLiter(ctx.charLiter());

    } else if (ctx.intLiter() != null) {
      return visitIntLiter(ctx.intLiter());

    } else if (ctx.pairLiter() != null) {
      return visitPairLiter(ctx.pairLiter());

    } else if (ctx.arrayElem() != null) {
      return visitArrayElem(ctx.arrayElem());

    } else if (ctx.IDENT() != null) {
      String name = ctx.IDENT().getSymbol().getText();
      return new Ident(name, ctx.start.getLine(),
          ctx.start.getCharPositionInLine());

    } else {
      return visitExpr(ctx.expr(0));
    }
  }

  @Override
  public UnOp visitUnaryOper(UnaryOperContext ctx) {
    if (ctx.NOT() != null) {
      return UnOp.NOT;

    } else if (ctx.CHR() != null) {
      return UnOp.CHR;

    } else if (ctx.LENGTH() != null) {
      return UnOp.LEN;

    } else if (ctx.ORDINAL() != null) {
      return UnOp.ORD;

    } else if (ctx.MINUS() != null) {
      return UnOp.NEGATE;

    } else if (ctx.COMPLEMENT() != null) {
      return UnOp.COMPLEMENT;
    }


    return null;
  }

  @Override
  public ArrayElem visitArrayElem(ArrayElemContext ctx) {
    String name = ctx.IDENT().getSymbol().getText();
    List<Expr> exprs = new ArrayList<>();

    for (ExprContext exprCtx : ctx.expr()) {
      exprs.add(visitExpr(exprCtx));
    }
    return new ArrayElem(name, exprs, ctx.start.getLine(),
        ctx.start.getCharPositionInLine());
  }

  @Override
  public IntLiter visitIntLiter(IntLiterContext ctx) {
    return new IntLiter(ctx.INTEGER().getSymbol().getText(),
        ctx.MINUS() != null);
  }

  @Override
  public BoolLiter visitBoolLiter(BoolLiterContext ctx) {
    return new BoolLiter(ctx.TRUE() != null);
  }

  @Override
  public CharLiter visitCharLiter(CharLiterContext ctx) {
    return new CharLiter(convertEscChar(
        ctx.CHAR_LITER().getSymbol().getText()));
  }

  // helper function to convert symbol text string to corresponding char
  private char convertEscChar(String s) {
    switch (s) {
      case "'\\n'":
        return '\n';
      case "'\\b'":
        return '\b';
      case "'\\t'":
        return '\t';
      case "'\\r'":
        return '\r';
      case "'\\f'":
        return '\f';
      case "'\\0'":
        return '\0';
      case "'\\\\'":
        return '\\';
      case "'\\\"'":
        return '\"';
      case "'\\''":
        return '\'';
      default:
        return s.charAt(1);
    }
  }

  @Override
  public StringLiter visitStrLiter(StrLiterContext ctx) {
    return new StringLiter(ctx.STR_LITER().getSymbol().getText());
  }

  @Override
  public ArrayLiter visitArrayLiter(ArrayLiterContext ctx) {
    List<Expr> exprs = new ArrayList<>();

    for (ExprContext expr : ctx.expr()) {
      exprs.add(visitExpr(expr));
    }
    return new ArrayLiter(exprs, ctx.start.getLine(),
        ctx.start.getCharPositionInLine());
  }

  @Override
  public PairLiter visitPairLiter(PairLiterContext ctx) {
    return new PairLiter();
  }

  @Override
  public Object visit(ParseTree parseTree) {
    //UNUSED METHOD
    return null;
  }

  @Override
  public Object visitChildren(RuleNode ruleNode) {
    //UNUSED METHOD
    return null;
  }

  @Override
  public Object visitTerminal(TerminalNode terminalNode) {
    //UNUSED METHOD
    return null;
  }

  @Override
  public Object visitErrorNode(ErrorNode errorNode) {
    //UNUSED METHOD
    return null;
  }
}


