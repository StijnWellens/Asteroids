package asteroids.model.programs.expressions;

public class And extends LogicalExpression {

	public And(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
	}

	@Override
	public Object getValue() {
		return (Boolean)(this.getLeftPart().getValue()) && (Boolean)(this.getRightPart().getValue());
	}

}
