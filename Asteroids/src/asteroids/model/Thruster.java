package asteroids.model;


import be.kuleuven.cs.som.annotate.*;
import asteroids.Util;
 
/**
 * A class representing a thruster who can be enabled and has a specified powerOutput.
 * 
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 */
public class Thruster {
	
	/**
	 * Initializes a new Thruster with the given state and the given powerOutput.
	 * 
	 * @param 	enabled
	 * 			The given boolean who decides that the new Thruster will be enabled or not.
	 * @param 	powerOutput
	 * 			The given powerOutput of the new Thruster.
	 * @effect  Set the state of this Thruster to the given state.
	 * 			| setEnabled(enabled)
	 * @effect	Set the powerOutput of this Thruster to the given powerOutput.
	 * 			| setPowerOutput(powerOutput)
	 */
	public Thruster(boolean enabled, double powerOutput) throws IllegalArgumentException
	{
		this.setEnabled(enabled);
		this.setPowerOutput(powerOutput);
	}
	
	// isEnabled
	
	private boolean isEnabled;
	
	/**
	 * Returns whether the thruster  is active or not.
	 * 
	 * @return	True if and only if the thruster is enabled.
	 * 			| this.isEnabled
	 */
	@Basic
	public boolean isThrusterEnabled() {
		return this.isEnabled;
	}
	
	/**
	 * Set the thruster to a given state.
	 * 
	 * @param 	state
	 * 			The given state for the thruster.
	 * @post	The state of the thruster is equal to the given state.
	 * 			|	(new this).isThrusterEnabled() == state 
	 */
	public void setEnabled(boolean state)
	{
		this.isEnabled = state;		
	}
	
	// PowerOutput
	
	private double powerOutput; // in kN
	
	/**
	 * Return the powerOutput of the thruster.
	 * 
	 * @return	The power output of this thruster.
	 * 			| this.powerOutput
	 */
	@Basic
	public double getPowerOutput() {
		return this.powerOutput;
	}
	
	/**
	 * Checks whether the given powerOutput is a valid powerOutput.
	 * 
	 * @param 	powerOutput
	 * 			The powerOutput to check.
	 * @return	True if and only if the given powerOutput is a number and not infinite.
	 * 			| result == (!Double.isNaN(powerOutput)) && (!Double.isInfinite(powerOutput))
	 */
	public boolean isValidPowerOutput(double powerOutput) {
		return (!Double.isNaN(powerOutput)) && (!Double.isInfinite(powerOutput));
	}
	
	/**
	 * Set the powerOutput of this thruster to the given powerOutput.
	 * 
	 * @param 	powerOutput
	 * 			The given powerOutput to set.
	 * @post	The new powerOutput of this thruster will be the given powerOutput.
	 * 			| (new this).getPowerOutput() = powerOutput
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when the given powerOutput isn't valid.
	 * 			| !isValidPowerOutput(powerOutput)
	 */
	public void setPowerOutput(double powerOutput) throws IllegalArgumentException {
		if(!isValidPowerOutput(powerOutput))
			throw new IllegalArgumentException();
		this.powerOutput = powerOutput;
		
	}	
	
	/**
	 * Generates an acceleration vector in a given direction for a given mass.
	 * 
	 * @param 	direction
	 * 			The given direction in which the vector should point.
	 * @param 	mass
	 * 			The given mass that has to be accelerated by this thruster.
	 * @return	The acceleration vector based on F = m * a , with F the powerOutput from this thruster
	 * 			and m the given mass.
	 * 			| a = (this.getPowerOutput())/mass	
	 * 			|	result == new Vector( a * Math.cos(direction), a * Math.sin(direction))	
	 * @return	A vector with both components 0, when the thruster isn't enabled.
	 * 			| if(!isThrusterEnabled())
	 * 			|	then result == new Vector(0,0)			
	 * @throws IllegalArgumentException
	 */
	public Vector generateAcceleration (double direction, double mass) throws IllegalArgumentException {
		if(this.isThrusterEnabled()) 
		{
			double a = Vector.multiplyComponents((this.getPowerOutput()),(1/mass));
			double x;
			double y;
			
			if(Util.fuzzyEquals(Math.cos(direction), 0))
				x = 0;
			else
				x = a * Math.cos(direction);
			
			if(Util.fuzzyEquals(Math.sin(direction), 0))
				y = 0;
			else
				y = a * Math.sin(direction);
			return new Vector(x,y);
		}
		else
			return new Vector(0,0);
	}
	
}
