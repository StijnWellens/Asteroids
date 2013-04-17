package asteroids.model;

public class Thruster {
	
	/**
	 * 
	 * @param enabled
	 * @param powerOutput
	 * @throws IllegalArgumentException
	 */
	public Thruster(boolean enabled, double powerOutput) throws IllegalArgumentException
	{
		this.setEnabled(enabled);
		this.setPowerOutput(powerOutput);
	}
	
	/**
	 * Returns whether the thruster  is active or not.
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
	private boolean isEnabled;
	
	/**
	 * Return the powerOutput of the thruster.
	 * 
	 * @return	The power output of this thruster.
	 * 			| this.powerOutput
	 */
	public double getPowerOutput() {
		return this.powerOutput;
	}
	
	public boolean isValidPowerOutput(double powerOutput) {
		return (!Double.isNaN(powerOutput)) && (!Double.isInfinite(powerOutput));
	}
	
	public void setPowerOutput(double powerOutput) throws IllegalArgumentException {
		if(!isValidPowerOutput(powerOutput))
			throw new IllegalArgumentException();
		this.powerOutput = powerOutput;
		
	}
	
	private double powerOutput;
	
	public Vector generateAcceleration (double direction, double mass, double time) throws IllegalArgumentException {
		if(this.isThrusterEnabled()) 
		{
			/*double a = Vector.multiplyComponents((this.getPowerOutput()*time),(1/mass));
			double x = Vector.multiplyComponents(a, Math.cos(direction));
			double y = Vector.multiplyComponents(a, Math.sin(direction));*/
			double a = (this.getPowerOutput()*time)/mass;
			double x = a * Math.cos(direction);
			double y = a * Math.sin(direction);
			return new Vector(x,y);
		}
		else
			return new Vector(0,0);
	}
	
}
