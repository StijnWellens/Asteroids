package asteroids.model.programs.statements;
import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.Type;
import asteroids.model.programs.expressions.*;

public class WhileLoop extends ComplexStatement {

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
		boolean suddenStop = false;
		while((Boolean)(this.getCondition().getValue()) && !suddenStop){
			this.getBody().execute();
			if(this.getBody() instanceof ActionStatement || !this.getBody().isFinished())
				suddenStop = true;
			if(this.getBody().isFinished())
				this.getBody().reset();
			if(this.containsActionStatement() == false)
				suddenStop = true;
		}
		if(suddenStop == false)
			this.setFinished(true);
	}

	@Override
	public boolean typeCheck() {
		if(!this.getCondition().typeCheck() || !this.getCondition().getType().equals(Type.BOOL))
			return false;
		if(!this.getBody().typeCheck())
			return false;
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		return this.getBody().containsActionStatement();
	}

	@Override
	public void reset() {
		super.reset();
		this.getBody().reset();
	}
}
