package functions;

import newclass.Newclass;
import statements.Stat;

import java.util.List;

public class Program {

  private final List<Newclass> classes;
  private final List<Function> functions;
  private final List<Stat> statements;

  public Program(List<Function> functions, List<Stat> statements, List<Newclass> classes){
    this.functions = functions;
    this.statements = statements;
    this.classes = classes;
  }

  public List<Function> getFunctions() {
    return functions;
  }

  public List<Stat> getStatements() {
    return statements;
  }

  public List<Newclass> getClasses() {
    return classes;
  }
}
