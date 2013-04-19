package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a ship with a specified position, velocity, direction, radius and mass.
 * 
 * @invar 	The x position of the ship must always be a valid x position. 
 * 			| isValidX(getX())
 * @invar 	The y position of the ship must always be a valid y position. 
 * 			| isValidY(getY())
 * @invar 	The maximum velocity of the ship must always be a valid maximum
 *        	velocity. 
 *        	| isValidMaxVelocity(getMaxVelocity())
 * @invar 	The velocity of the ship must always be a valid velocity. 
 * 			| isValidVelocity(getXVelocity(), getYVelocity())
 * @invar 	The direction of the ship must always be a valid direction. 
 * 			| isValidDirection(getDirection())
 * @invar 	The lowerBoundRadius of the ship must always be a valid.
 *        	lowerBoundRadius. 
 *        	| isValidLowerBoundRadius(getLowerBoundRadius())
 * @invar 	The radius of the ship must always be a valid radius. 
 * 			| isValidRadius(getRadius())
 * @invar	The mass of the ship must always be a valid mass.
 * 			| isValidMass(getMass())
 * @invar	The thruster of the ship must always be a valid thruster.
 * 			| isValidThruster(getThruster())
 * @invar	Each ship must have a proper world.
 * 			| hasProperWorld()
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 * 
 */
public class Ship extends SpaceObject{

	/**
	 * Initializes this new default ship.
	 * 
	 * @effect	This ship is initialized as a default SpaceObject.
	 * 			| super()
	 * @effect	The direction of this Ship is set to PI/2.
	 * 			| setDirection(PI/2)
	 *  @effect	The mass of this new ship will be set to 1 kg.
	 * 			| setMass(mass)
	 * @effect	The thruster of this new ship will be set to a thruster 
	 * 			which is not enabled and has a powerOutput of 1.1E18 kiloNewton.
	 * 			| setThruster(false, 1.1E18)
	 */
	public Ship() throws IllegalArgumentException {
		super();
		setDirection(PI / 2);
		setMass(1);
		setThruster(false, 1.1E18);
	}
	
	/**
	 * Initializes this new ship with the given x and y position components, the given x and y velocity components,
	 * the given radius, the given direction and the given mass.
	 * 
	 * @param 	x
	 *        	The initial x position for this new Ship.
	 * @param 	y
	 *        	The initial y position for this new Ship.
	 * @param 	xVelocity
	 *        	The initial x velocity component for this new Ship.
	 * @param 	yVelocity
	 *        	The initial y velocity component for this new Ship.
	 * @param 	radius
	 *        	The radius of this new Ship.
	 * @param	angle
	 * 			The initial direction of this new Ship.
	 * @param 	mass
	 * 			The mass of this new Ship.
	 * @effect	This new ship is initialized as a SpaceObject with the given x and y position components,
	 * 			the given x and y velocity components and the given radius.
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @effect	The direction of this new ship will be set to valid multiple of the given angle.
	 * 			| setDirection(makeAngleValdi(angle))
	 * @effect	The mass of this new ship will be set to the given mass.
	 * 			| setMass(mass)
	 * @effect	The thruster of this new ship will be set to a thruster 
	 * 			which is not enabled and has a powerOutput of 1.1E18 kiloNewton.
	 * 			| setThruster(false, 1.1E18)
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle, double mass) throws IllegalArgumentException {
		super(x, y , xVelocity, yVelocity,radius);
		setDirection(makeAngleValid(angle));
		setMass(mass);
		setThruster(false, 1.1E18);
	}
	
	// direction: nominal programming
	
	private static final double	PI	= Math.PI;
	private double	direction; // in radians
	
	/**
	 * Returns the angle of the direction of this spaceObject.
	 * 
	 * @return 	the angle of the direction of the spaceObject 
	 * 			| this.direction
	 */
	@Basic
	public double getDirection() {
		return this.direction;
	}

	/**
	 * Returns whether the given direction is valid or not.
	 * 
	 * @param angle
	 *        The angle in which the spaceObject moves.
	 * @return True if and only if the given angle is a number and is between
	 *         zero and 2*Pi. 
	 *         | result == ((!Double.isNaN(angle)) && (angle >=0) && (angle < 2*PI))
	 */
	public boolean isValidDirection(double angle) {
		return ((!Double.isNaN(angle)) && (angle >= 0) && (angle < 2 * PI));
	}

	/**
	 * Sets the direction of the spaceObject to the given direction.
	 * 
	 * @param angle
	 *        The angle of the given direction.
	 * @pre The given direction must be a valid direction. 
	 * 		| isValidDirection(angle)
	 * @post The new direction of this spaceObject is equal to the given direction. 
	 * 		| (new this).getDirection() == angle
	 */
	public void setDirection(double angle) {
		assert isValidDirection(angle);

		this.direction = angle;

	}
	
	/**
	 * Turns the spaceObject with a given angle.
	 * 
	 * @param angle
	 *        The angle that needs to be added to the current direction.
	 * @pre The sum of the old direction and the given angle must be a valid direction. 
	 * 		| isValidDirection(this.getDirection()+angle)
	 * @effect The direction of the spaceObject is set to the sum of the current direction and the given angle. 
	 * 		| (new this).setDirection(this.getDirection() + angle)
	 */
	public void turn(double angle) {
		assert isValidDirection(angle);

		setDirection(makeAngleValid(getDirection() + angle));
	}
	
	/**
	 * Converts the given angle to a valid angle (0-2PI)
	 * 
	 * @param 	angle
	 * 			the angle to convert
	 * @return	The converted valid angle which is between zero and two PI.
	 * 			| result == convertedAngle 
	 * 			|	with 0 <= convertedAngle < (2PI)
	 * @return 	Return 0 if the given angle is not a number or infinite.
	 * 			| if(Double.isNaN(angle) || Double.isInfinite(angle))
	 * 			|	then result == 0
	 */
	public static double makeAngleValid(double angle)
	{
		if(Double.isNaN(angle) || Double.isInfinite(angle))
		{
			angle = 0;
		}
		
		double tempAngle = angle%(2*PI);
		if(tempAngle < 0)
		{
			return tempAngle + (2*PI);
		}
		else
		{
			return tempAngle;
		}
	}
	
	// Thruster
	
	private Thruster thruster;	
	
	public Thruster getThruster()
	{
		return this.thruster;
	}
	
	public boolean isValidThruster(Thruster thruster)
	{
		return (thruster != null);
	}
	
	public void setThruster(boolean enabled, double powerOutput) throws IllegalArgumentException
	{
		Thruster thruster = new Thruster(enabled, powerOutput);		
		
		if(!isValidThruster(thruster))
			throw new IllegalArgumentException();
		
		this.thruster = thruster;
	}		
	
	public void thrust(double time) {
		if( !Double.isNaN(time) && !Double.isInfinite(time) && time >=0)
		{
			Thruster thruster = this.getThruster();
			Vector newAcceleration = thruster.generateAcceleration(this.getDirection(), this.getMass());
									
			double tempVx = this.getXVelocity() + newAcceleration.getXComp()* time;
			double tempVy = this.getYVelocity() + newAcceleration.getYComp()* time;

			this.setVelocity(tempVx,tempVy);
		}				
	}
	
	public boolean canHaveAsWorld(World world){
		return (super.canHaveAsWorld(world)) && 
				(!this.overlapWithWorldObject(world));
	}
	
	public void fireBullet() throws IllegalStateException {
		if(this.getState() != State.ACTIVE || this.getWorld() == null)
			throw new IllegalStateException();
		
		SpaceObject bullet = new Bullet(this);
		bullet.flyIntoWorld(this.getWorld());
		
			
	}
		
}
