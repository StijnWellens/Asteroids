package asteroids.model.programs.expressions;

public class EqualTo extends EqualityExpression {

	public EqualTo(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Boolean getValue() {
		if(this.getLeftPart().getValue() == null && this.getRightPart().getValue() == null)
			return true;
		if(this.getLeftPart().getValue() == null)
			return false;
		return (this.getLeftPart().getValue()).equals(this.getRightPart().getValue());
	}

	@Override
	public String getSymbol() {
		return "==";
	}
}
