package asteroids.model.programs.expressions;

public class Or extends LogicalExpression {

	public Or(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
	}

	@Override
	public Object getValue() {
		return (Boolean)(this.getLeftPart().getValue()) || (Boolean)(this.getRightPart().getValue());
	}

}
