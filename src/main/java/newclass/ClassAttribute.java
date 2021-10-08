package newclass;
import types.*;
import expressions.*;

public class ClassAttribute {

	private boolean isPublic;
	private Type type;
	private Ident name;
	
	public ClassAttribute(boolean isPublic, Type type, Ident name){
		this.isPublic = isPublic;
		this.type = type;
		this.name = name;
	}

	public boolean isPublic(){
		return isPublic;
	}

	public Type getType(){
		return type;
	}

	public Ident getName(){
		return name;
	}

}	
