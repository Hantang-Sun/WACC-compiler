package compiler;

import java.util.ArrayList;
import java.util.List;

public class SemanticErrorLog {

  private List<String> errorLog;

  public SemanticErrorLog() {
    errorLog = new ArrayList<>();
  }

  public void error(String string) {
    errorLog.add(string);
  }

  public void semanticCheckResult() {
    if (!errorLog.isEmpty()) {
      for (String error: errorLog) {
        System.out.println(error);
      }
      System.out.println("#semantic_error#");
      System.exit(200);
    }
  }
}
