package asteroids.model.programs.statements;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

public class Sequence extends ComplexStatement {

	private List<Statement> statements;
	private int nmbOfDoneExecutions;
	
	public Sequence(int line, int column, List<Statement> statements){
		super(line,column);
		this.statements = statements;
		this.nmbOfDoneExecutions = 0;
	}
	
	@Basic
	public List<Statement> getStatements(){
		return new ArrayList<Statement>(this.statements);
	}
	
	@Override
	public void execute() {
		boolean actionEncountered = false;
		while(!actionEncountered && !isFinished()){
			if(this.nmbOfDoneExecutions < this.getStatements().size()){
				Statement statement = this.getStatements().get(this.nmbOfDoneExecutions);
				statement.execute();
				if(statement instanceof ActionStatement){
					actionEncountered = true;
				}
				if(statement.isFinished()) {
					nmbOfDoneExecutions++;
				}
				else {
					actionEncountered = true;
				}
			}
			else
				setFinished(true);
		}
		if(this.nmbOfDoneExecutions >= this.getStatements().size())
			setFinished(true);
	}

	@Override
	public boolean typeCheck() {
		for(Statement statement: this.getStatements()) {
			if(!statement.typeCheck())
				return false;
		}
		return true;
	}

	@Override
	public boolean containsActionStatement() {
		for(Statement statement: this.getStatements()) {
			if(statement.containsActionStatement())
				return true;
		}
		return false;
	}
	
	@Override
	public void reset() {
		super.reset();
		this.nmbOfDoneExecutions = 0;
		for(Statement statement: this.getStatements()) {
			statement.reset();
		}
	}

}
