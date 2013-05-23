package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.SpaceObject;
import asteroids.model.programs.*;
import asteroids.model.programs.exceptions.IllegalProgramException;

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
		this.isTypeSet = true;
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
		if(!this.controller.isInitialized() || this.isTypeSet())
			return this.type;
		else {
			Variable global = controller.getProgram().getGlobal(this.getName());
			if(global != null && global.isTypeSet())
				return global.getType();
			else
				return null;
		}
	}
	
	public void setType(Type type){
		this.type = type;
		this.isTypeSet = true;
	}
	
	private Object value;
	
	@Override @Basic
	public Object getValue() {
		if(!this.controller.isInitialized() || this.isValueSet())
			return this.value;
		else {
			Variable global = this.controller.getProgram().getGlobal(this.getName());
			if(global != null && global.isValueSet())
				return global.getValue();
			else
				throw new IllegalProgramException("Variable not declared or assigned.");
		}
	}

	public void setValue(Expression value) {
		if(isTypeSet() && value.getType().equals(this.getType())) {
			this.value = value.getValue();
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
	
	@Override
	public boolean typeCheck() {
		if(!isValueSet()) {
			Variable global = this.controller.getProgram().getGlobal(this.getName());
			if(global == null)
				return false;
		}
		return super.typeCheck(); 
	}
}
