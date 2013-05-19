package asteroids.model.programs.statements;
import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.expressions.*;

public class WhileLoop extends Statement {

	private Expression condition;
	private Statement body;
	
	public WhileLoop(int line, int column, Expression condition,Statement body){
		super(line,column);
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
		// TODO Auto-generated method stub

	}

}
