package newclass;

import compiler.SemanticCheckData;
import expressions.AssignRHS;
import functions.*;
import types.ClassType;
import types.Type;

import java.util.List;

public class Newclass implements AssignRHS {

	private ClassType name;
	private List<ClassAttribute> attributes;
	private List<Function> constructors;
	private List<Function> methods;
	private Type type;

	public Newclass(ClassType name, List<ClassAttribute> attributes,
									List<Function> constructors, List<Function> methods) {
		this.name = name;
		this.attributes = attributes;
		this.constructors = constructors;
		this.methods = methods;
	}

	public ClassType getName(){
		return name;
	}

	public List<ClassAttribute> getAttributes(){
		return attributes;
	}

	public List<Function> getConstructors(){
		return constructors;
	}

	public List<Function> getMethods(){
		return methods;
	}

	@Override
	public Type getType(SemanticCheckData semanticCheckData) {
		return null;
	}

	@Override
	public Type getType() {
		return type;
	}
}
