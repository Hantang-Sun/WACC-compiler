package compiler;

import functions.FunctionType;
import newclass.Newclass;
import types.Type;

import java.util.HashMap;
import java.util.List;

public class SemanticCheckData {

  private SemanticErrorLog errorLog;
  private Scope currScope;
  private HashMap<String, List<FunctionType>> funcTypes;
  private Type callType;
  private HashMap<String, Newclass> classNames;
  private HashMap<String, HashMap<String, List<FunctionType>>> methodTypes;
  private HashMap<String, List<FunctionType>> constructorTypes;
  private String insideClass;
  private boolean isAssignLhs;

  public SemanticCheckData(SemanticErrorLog errorLog,
                           HashMap<String, List<FunctionType>> funcTypes,
                           HashMap<String, Newclass> classNames,
                           HashMap<String, HashMap<String, List<FunctionType>>> methodTypes,
                           HashMap<String, List<FunctionType>> constructorTypes) {
    this.errorLog = errorLog;
    this.funcTypes = funcTypes;
    this.classNames = classNames;
    this.methodTypes = methodTypes;
    this.constructorTypes = constructorTypes;
  }

  public boolean isAssignLhs() {
    return isAssignLhs;
  }

  public void setAssignLhs(boolean assignLhs) {
    isAssignLhs = assignLhs;
  }

  public String getInsideClass() {
    return insideClass;
  }

  public void setInsideClass(String insideClass) {
    this.insideClass = insideClass;
  }

  public SemanticErrorLog getErrorLog() {
    return errorLog;
  }

  public HashMap<String, List<FunctionType>> getFuncTypes() {
    return funcTypes;
  }

  public HashMap<String, Newclass> getClassNames() {
    return classNames;
  }

  public HashMap<String, HashMap<String, List<FunctionType>>> getMethodTypes() {
    return methodTypes;
  }

  public HashMap<String, List<FunctionType>> getConstructorTypes() {
    return constructorTypes;
  }

  public Scope getCurrScope() {
    return currScope;
  }

  public Type getCallType() {
    return callType;
  }

  public void setCallType(Type callType) {
    this.callType = callType;
  }

  public void setCurrScope(Scope currScope) {
    this.currScope = currScope;
  }


}
