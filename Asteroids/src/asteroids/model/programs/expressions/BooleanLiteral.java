package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public class BooleanLiteral extends StandardExpression {

	public BooleanLiteral(int line, int column, boolean value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public Type getType() {
		return Type.BOOL;
	}

	private boolean value;
	
	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
