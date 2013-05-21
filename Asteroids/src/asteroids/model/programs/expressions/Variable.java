package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.programs.Type;

public class Variable extends StandardExpression {

	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
		this.type = null;
		this.value = null;
	}
	
	public Variable(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
		this.value = null;
	}

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
	}
	
	private Object value;
	
	@Override @Basic
	public Object getValue() {
		return this.value;
	}

	public void setValue(Expression value) {
		if(value.getType() == this.getType())
			this.value = value;
	}
	
	@Override
	public String toString(){
		return "" + this.getName();
	}
}
