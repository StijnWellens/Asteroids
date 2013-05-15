package asteroids.model.programs.expressions;

public class LargerThan extends ComparisonExpression {

	public LargerThan(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) > (Double)(this.getRightPart().getValue());
	}

}
