package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.parsing.ProgramFactory.*;

public class ForEach extends Statement {

	private ForeachType type;
	private String variableName;
	private Statement body;
	
	public ForEach(int line, int column, ForeachType type, String variableName,
						Statement body){
		super(line,column);
		this.type = type;
		this.variableName = variableName;
		this.body = body;
	}
	
	@Basic
	public ForeachType getType(){
		return this.type;
	}
	
	@Basic
	public String getVariableName(){
		return this.variableName;
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
