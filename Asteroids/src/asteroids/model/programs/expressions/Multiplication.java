package asteroids.model.programs.expressions;

public class Multiplication extends MathBinaryExpression {

	public Multiplication(int line, int column, Expression left, Expression right) {
		super(line, column, left, right);
	}

	@Override
	public Double getValue() {
		return (Double)(this.getLeftPart().getValue()) * (Double)(this.getRightPart().getValue());
	}

	@Override
	public String getSymbol() {
		return "*";
	}
}
