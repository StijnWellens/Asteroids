package asteroids.model.programs;

import java.util.*;
import java.util.Map.Entry;

import be.kuleuven.cs.som.annotate.*;

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
			inputGlobals.add(new Variable(0,i,entry.getKey(),entry.getValue()));
			i++;
		}
		
		this.globals = inputGlobals;
		this.statement = statement;
		this.controller = controller;
		this.controller.initializeProgramController(this);
	}

	private final ProgramController controller;
	private final Set<Variable> globals;
	private final Statement statement;
	
	private Ship shipRunningProgram;
	
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
	
	private int amountOfDoneExecutions;
	
	public void execute(int nmbOfExecutions) {
		
	}
	
}
