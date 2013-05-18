package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public class Variable extends StandardExpression {

	public Variable(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
		this.value = null;
	}

	private String name;
	
	public String getName() {
		return this.name;
	}
	
	private final Type type;
	
	@Override
	public Type getType() {
		return this.type;
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

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Variable))
				return false;
		if(this.getValue() == null && other != null)
			return false;
		if(this.getValue() == null && other == null)
			return false;
		return this.getValue().equals(((Variable)other).getValue());
	}
}
