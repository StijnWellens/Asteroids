package asteroids.model.programs.expressions;

public class Subtraction extends MathBinaryExpression {

	public Subtraction(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Double getValue() {
		return (Double)(this.getLeftPart().getValue()) - (Double)(this.getRightPart().getValue());
	}

}
