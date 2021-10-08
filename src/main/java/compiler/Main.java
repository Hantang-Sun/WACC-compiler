package compiler;

import armPrinter.AssemblyFileGenerator;
import codeGen.CodeGenerator;
import functions.Program;
import antlr.*;
import newclass.Newclass;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

class Main{
  public static void main(String[] args) throws Exception {

    ANTLRInputStream input;
    String outPath = "";
    String path = "";
    if (args.length > 0) {
      path = args[0];
      File file = new File(path);
      FileInputStream fis = new FileInputStream(file);
      input = new ANTLRInputStream(fis);
      String  fileName = file.getName();
      outPath = fileName.substring(0,fileName.length()-4) + "s";
    } else {
      input = new ANTLRInputStream(System.in);
    }

    //lexing and tokenisation
    BasicLexer lexer = new BasicLexer(input);

    lexer.removeErrorListeners();
    lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

    //parse tree generation
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    BasicParser parser = new BasicParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(ThrowingErrorListener.INSTANCE);

    //generate AST
    Visitor visitor= new Visitor();
    Program ASTRoot = visitor.visitProgram(parser.program());

    //semantic error checking
    SemanticChecker semanticChecker = new SemanticChecker();
    semanticChecker.typeCheck(ASTRoot);


    // code gen
    if (!path.equals("")) {
      Scope symbolTable = semanticChecker.getGlobalScope();
      HashMap<String, Newclass> newclasses = semanticChecker.getClassNames();
      CodeGenerator codeGenerator = new CodeGenerator(ASTRoot, symbolTable, newclasses);
      AssemblyFileGenerator fileGenerator
          = new AssemblyFileGenerator(codeGenerator);

      fileGenerator.generateAssemblyFile(outPath);
    }


    System.out.println("BUILD SUCCESS");
  }
}
