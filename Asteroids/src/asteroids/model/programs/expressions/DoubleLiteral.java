package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public class DoubleLiteral extends StandardExpression {

	public DoubleLiteral(int line, int column, double value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}

	private double value;
	
	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int toString() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
