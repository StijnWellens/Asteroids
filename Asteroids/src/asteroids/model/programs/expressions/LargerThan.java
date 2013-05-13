package asteroids.model.programs.expressions;

public class LargerThan extends ComparisonExpression {

	public LargerThan(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) > (Double)(this.getRightPart().getValue());
	}

}
