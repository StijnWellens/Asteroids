package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.expressions.*;

public class PrintStatement extends StandardStatement {
	
	private Expression e;
	
	public PrintStatement(int line, int column, Expression e){
		super(line,column);
		this.e = e;
	}
	
	@Basic
	public Expression getExpression(){
		return this.e;
	}

	@Override
	public void execute() {
		System.out.println(e.getValue());
		setFinished(true);
	}

}
