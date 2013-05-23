package asteroids.model.programs.expressions;

public abstract class StandardExpression extends Expression {

	protected StandardExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean equals(Object other){
		if(this.getClass() != other.getClass())
			return false;
		if(this.getValue() == null && other != null)
			return false;
		return (getValue().equals(((StandardExpression) other).getValue()));
	}

	
	@Override
	public boolean containsExpression(Expression expression) {
		return expression == this;
	}
	
	@Override
	public String toString() {
		return "" + this.getValue();
	}	
}
