package asteroids.model.programs.statements;
import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.Type;
import asteroids.model.programs.expressions.*;

public class WhileLoop extends ComplexStatement {

	private Expression condition;
	private Statement body;
	
	public WhileLoop(int line, int column, Expression condition,Statement body) throws IllegalArgumentException{
		super(line,column);
		if(condition.getType() != Type.BOOL)
			throw new IllegalArgumentException();
		this.condition = condition;
		this.body = body;
	}
	
	@Basic
	public Expression getCondition(){
		return this.condition;
	}
	
	@Basic
	public Statement getBody(){
		return this.body;
	}
	
	@Override
	public void execute() {
		while((Boolean) this.getCondition().getValue()){
			this.getBody().execute();
		}
	}

}
