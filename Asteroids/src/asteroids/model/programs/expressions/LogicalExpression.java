package asteroids.model.programs.expressions;

public abstract class LogicalExpression extends BinaryExpression {

	protected LogicalExpression(Expression left, Expression right) throws IllegalArgumentException {
		super(left,right);
		if(left.getType() == Type.BOOL && right.getType() == Type.BOOL)
			throw new IllegalArgumentException();
	}
	
	@Override
	public Type getType() {
		return Type.BOOL;
	}

	
}
