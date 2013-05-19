package asteroids.model.programs.statements;

import asteroids.model.programs.expressions.*;

public class Assignment extends Statement {

	private String variable;
	private Expression exp;
	
	public Assignment(int line, int column, String variable, Expression exp){
		super(line,column);
		this.variable = variable;
		this.exp = exp;
	}
	
	public String getVariable(){
		return this.variable;
	}
	
	public Expression getExpression(){
		return this.exp;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
