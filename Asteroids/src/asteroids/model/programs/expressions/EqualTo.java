package asteroids.model.programs.expressions;

public class EqualTo extends ComparisonExpression {

	public EqualTo(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) == (Double)(this.getRightPart().getValue());
	}

}
