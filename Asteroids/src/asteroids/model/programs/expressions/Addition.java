package asteroids.model.programs.expressions;

public class Addition extends MathBinaryExpression {

	public Addition(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);		
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) + (Double)(this.getRightPart().getValue());
	}

}
