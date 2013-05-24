package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.Basic;
import asteroids.model.programs.*;
import asteroids.model.programs.expressions.*;

public class Assignment extends StandardStatement {

	private String variable;
	private Expression exp;
	private ProgramController controller;
	
	public Assignment(int line, int column, String variable, Expression exp, ProgramController controller){
		super(line,column);
		this.variable = variable;
		this.exp = exp;
		this.controller = controller;
	}
	
	@Basic
	public String getVariable(){
		return this.variable;
	}
	
	@Basic
	public Expression getExpression(){
		return this.exp;
	}
	
	@Override
	public void execute() {
		Variable global = this.controller.getProgram().getGlobal(this.getVariable());
		global.setValue(exp);
		
		setFinished(true);
	}

	@Override
	public boolean typeCheck() {
		if(!this.getExpression().typeCheck())
			return false;
		Variable global = this.controller.getProgram().getGlobal(this.getVariable());
		if(global == null)
			return false;
		if(!global.getType().equals(this.getExpression().getType()))
			return false;
		global.setWillBeAssigned(true);
		return true; 
	}
	
	@Override
	public boolean containsActionStatement() {
		return false;
	}

}
