package asteroids.model.programs.expressions;

public class Or extends LogicalExpression {

	public Or(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Object getValue() {
		return (Boolean)(this.getLeftPart().getValue()) || (Boolean)(this.getRightPart().getValue());
	}

}
