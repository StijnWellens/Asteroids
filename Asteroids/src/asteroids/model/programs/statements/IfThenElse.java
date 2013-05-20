package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.Type;
import asteroids.model.programs.expressions.*;

public class IfThenElse extends Statement {

	private Expression condition;
	private Statement then;
	private Statement otherwise;

	public IfThenElse(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		super(line, column);
		if(condition.getType() != Type.BOOL)
			throw new IllegalArgumentException();
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
	}

	@Basic
	public Expression getCondition(){
		return this.condition;
	}
	
	@Basic
	public Statement getThenStatement() {
		return this.then;
	}

	@Basic
	public Statement getOtherwiseStatement() {
		return this.otherwise;
	}

	@Override
	public void execute() {
		if((Boolean)this.getCondition().getValue())
			this.getThenStatement().execute();
		else
			this.getOtherwiseStatement().execute();
	}

}
