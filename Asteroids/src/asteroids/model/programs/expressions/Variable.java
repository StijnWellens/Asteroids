package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
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
	
	private boolean willBeAssigned;
	
	@Basic
	public boolean willBeAssigned() {
		return this.willBeAssigned;
	}
	
	public void setWillBeAssigned(boolean assigned) {
		this.willBeAssigned = assigned;
	}
	
	@Override
	public boolean typeCheck() {
		Variable global = this.controller.getProgram().getGlobal(this.getName());
		if(global == null || !global.willBeAssigned())
			return false;
		return super.typeCheck(); 
	}
	
	@Override
	public boolean equals(Object other) {
		return (super.equals(other) && this.getName().equals(((Variable)other).getName()));
	}
	
	@Override
	public int hashCode() {
		if(!isValueSet() && !isTypeSet() || (this.value == null && this.type == null)) {
			return ("" + this.getName().hashCode() + "null" + "null").hashCode();
		}
		if(!isValueSet() || this.value == null) {
			return ("" + this.getName().hashCode() + "null" + this.type.hashCode()).hashCode();
		}
		if(!isTypeSet() || this.type == null) {
			return ("" + this.getName().hashCode() + this.value.hashCode() + "null").hashCode();
		}
		return ("" + this.getName().hashCode() + this.value.hashCode() + this.type.hashCode()).hashCode();
	}
	
}
