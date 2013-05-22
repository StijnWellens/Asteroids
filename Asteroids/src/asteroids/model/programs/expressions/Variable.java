package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.programs.*;

public class Variable extends StandardExpression {

	public Variable(int line, int column, String name, ProgramController controller) {
		super(line, column);
		this.name = name;
		this.type = null;
		this.value = null;
		this.controller = controller;
	}
	
	public Variable(int line, int column, String name, Type type, ProgramController controller) {
		super(line, column);
		this.name = name;
		this.type = type;
		this.value = null;
		this.controller = controller;
	}
	
	private ProgramController controller;

	private String name;
	
	@Basic
	public String getName() {
		return this.name;
	}
	
	private Type type;
	
	@Override @Basic
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type type){
		this.type = type;
		this.isTypeSet = true;
	}
	
	private Object value;
	
	@Override @Basic
	public Object getValue() {
		if(this.isValueSet())
			return this.value;
		else
			return 
	}

	public void setValue(Expression value) {
		if(isTypeSet() && value.getType() == this.getType()) {
			this.value = value;
			this.isValueSet = true;
		}
	}
	
	public boolean isValueSet() {
		return this.isValueSet;
	}
	
	private boolean isValueSet;
	
	public boolean isTypeSet() {
		return this.isTypeSet;
	}
	
	private boolean isTypeSet;
	
	@Override
	public String toString(){
		return "" + this.getName();
	}
}
