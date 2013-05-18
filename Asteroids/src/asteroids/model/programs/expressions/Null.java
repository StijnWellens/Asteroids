package asteroids.model.programs.expressions;

import asteroids.model.*;
import asteroids.model.programs.Type;

public class Null extends StandardExpression {

	public Null(int line, int column) {
		super(line, column);
	}

	@Override
	public Type getType() {
		return Type.ENTITY;
	}

	@Override
	public SpaceObject getValue() {
		return null;
	}

	@Override
	public boolean equals(Object other) {
		return other == null;
	}

}
