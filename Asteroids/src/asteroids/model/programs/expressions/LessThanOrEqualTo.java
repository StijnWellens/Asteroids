package asteroids.model.programs.expressions;

public class LessThanOrEqualTo extends ComparisonExpression {

	public LessThanOrEqualTo(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Boolean getValue() {
		return (Double)(this.getLeftPart().getValue()) <= (Double)(this.getRightPart().getValue());
	}

	@Override
	public String getSymbol() {
		return "<=";
	}
}
