package expressions;

import compiler.*;
import functions.FunctionType;
import types.*;

import java.util.HashMap;
import java.util.List;

public interface AssignRHS {

  Type getType(SemanticCheckData semanticCheckData);

  Type getType();
}