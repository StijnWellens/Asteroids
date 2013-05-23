package asteroids.model.programs.expressions;

import asteroids.model.*;
import asteroids.model.programs.Type;

public class EntityLiteral extends StandardExpression {

	public EntityLiteral(int line, int column, SpaceObject value) {
		super(line, column);
		this.value = value;
	}

	private SpaceObject value;
	
	@Override
	public Type getType() {
		return Type.ENTITY;
	}

	@Override
	public SpaceObject getValue() {
		return this.value;
	}

}
