package asteroids.model;


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
	public double getPowerOutput() {
		return this.powerOutput;
	}
	
	/**
	 * Checks whether the given powerOutput is a valid powerOutput.
	 * 
	 * @param powerOutput
	 * @return
	 */
	public boolean isValidPowerOutput(double powerOutput) {
		return (!Double.isNaN(powerOutput)) && (!Double.isInfinite(powerOutput));
	}
	
	public void setPowerOutput(double powerOutput) throws IllegalArgumentException {
		if(!isValidPowerOutput(powerOutput))
			throw new IllegalArgumentException();
		this.powerOutput = powerOutput;
		
	}	
	
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
