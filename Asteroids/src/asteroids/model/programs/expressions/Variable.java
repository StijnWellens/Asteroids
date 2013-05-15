package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public class Variable extends StandardExpression {

	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	private String name;
	
	public String getName() {
		return this.name;
	}
	
	private Type type;
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	public final void setType(Type type) {
		this.type = type;
	}

	private Object value;
	
	@Override
	public Object getValue() {
		return this.value;
	}

	public void setValue(Expression value) {
		if(value.getType() == this.getType())
			this.value = value;
	}
}
