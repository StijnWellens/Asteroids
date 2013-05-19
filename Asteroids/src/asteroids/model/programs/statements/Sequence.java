package asteroids.model.programs.statements;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

public class Sequence extends Statement {

	private List<Statement> statements;
	
	public Sequence(int line, int column, List<Statement> statements){
		super(line,column);
		this.statements = statements;
	}
	
	@Basic
	public List<Statement> getStatements(){
		return this.statements;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
