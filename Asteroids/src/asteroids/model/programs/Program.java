package asteroids.model.programs;

import java.util.*;
import java.util.Map.Entry;

import be.kuleuven.cs.som.annotate.*;

import asteroids.Util;
import asteroids.model.Ship;
import asteroids.model.programs.expressions.*;
import asteroids.model.programs.statements.*;

/**
 * 
 * @author Stijn Wellens & Julie Wouters
 *
 * @inv	...
 * 		|	hasProperShip()
 */
public class Program {

	public Program(Map<String, Type> globals, Statement statement, ProgramController controller) throws IllegalArgumentException 
	{
		if(globals == null || statement == null || controller == null)
			throw new IllegalArgumentException();		
		
		Set<Variable> inputGlobals = new HashSet<Variable>();
		
		int i = 0;
		for(Entry<String, Type> entry: globals.entrySet() ) {
			inputGlobals.add(new Variable(0,i,entry.getKey(),entry.getValue(), controller));
			i++;
		}
		
		this.globals = inputGlobals;
		this.statement = statement;
		this.controller = controller;
		this.controller.initializeProgramController(this);
	}

	@Basic @Immutable
	public ProgramController getController() {
		return this.controller;
	}
	
	@Basic @Immutable
	public Set<Variable> getGlobals() {
		return new HashSet<Variable>(this.globals);
	}
	
	@Basic
	public Variable getGlobal(String name){
		Set<Variable> setGlobals = this.getGlobals();
		for(Variable global: setGlobals) {
			if(global.getName().equals(name)) {
				return global;
			}
		}
		return null;
	}
	
	@Basic @Immutable
	public Statement getStatement() {
		return this.statement;
	}
	
	private final ProgramController controller;
	private final Set<Variable> globals;
	private final Statement statement;
	
	@Basic
	public Ship getShipRunningProgram() {
		return this.shipRunningProgram;
	}
	
	public boolean isValidShipRunningProgram(Ship ship) {
		return (ship != null);
	}
	
	@Raw
	public void setShipRunningProgram(@Raw Ship ship) throws IllegalArgumentException {
		if(!isValidShipRunningProgram(ship))
			throw new IllegalArgumentException();
		this.shipRunningProgram = ship;
	}
	
	public boolean hasProperShip() {
		if(this.getShipRunningProgram() == null)
			return true;
		return this.getShipRunningProgram().getProgram().equals(this);		
	}
	
	private Ship shipRunningProgram;
	
	private double nmbOfExecutionsNotYetExecuted;
	
	@Basic
	public boolean isFinished() {
		return this.isFinished;
	}
	private boolean isFinished;
	
	public void execute(double nmbOfExecutions) {
		double executions = nmbOfExecutions + this.nmbOfExecutionsNotYetExecuted;
		while((!Util.fuzzyLessThanOrEqualTo(executions, 1) || Util.fuzzyEquals(executions, 1))
					 && !isFinished()){
			this.getStatement().execute();
			executions--;
			if(this.getStatement().isFinished())
				this.isFinished = true;
		}
		this.nmbOfExecutionsNotYetExecuted = executions;
	}
	
}
