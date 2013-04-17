package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a ship with a position, velocity, direction and radius
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
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 * 
 */
public class Ship extends SpaceObject{

	
	public Ship() {
		super();
		setDirection(PI / 2);
	}
	
	public Ship(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle, double mass) {
		super(x, y , xVelocity, yVelocity,radius);
		setDirection(angle);
		setMass(mass);
		setThruster(false, 1.1E18);
		setAcceleration(0,0);
	}
	
	// direction: nominal programming
	
	private static final double	PI	= Math.PI;
	private double				direction;
	
	/**
	 * Returns the angle of the direction of this spaceObject.
	 * 
	 * @return the angle of the direction of the spaceObject | this.direction
	 */
	@Basic
	@Raw
	public double getDirection() {
		return this.direction;
	}

	/**
	 * Returns whether the given direction is valid or not.
	 * 
	 * @param angle
	 *            The angle in which the spaceObject moves.
	 * @return True if and only if the given angle is a number and is between
	 *         zero and 2*Pi. 
	 *         | result == ((!Double.isNaN(angle)) && (angle >=0) && (angle < 2*PI))
	 */
	public boolean isValidDirection(double angle) {
		return ((!Double.isNaN(angle)) && (angle >= 0) && (angle < 2 * PI));
	}

	/**
	 * Set the direction of the spaceObject to the given direction.
	 * 
	 * @param angle
	 *            The angle of the given direction.
	 * @pre The given direction must be a valid direction. 
	 * 		| isValidDirection(angle)
	 * @post The new direction of the spaceObject is equal to the modulo 2*Pi of the given direction of the spaceObject. 
	 * 		| (new this).getDirection() == angle
	 */
	public void setDirection(double angle) {
		assert isValidDirection(angle);

		this.direction = angle;

	}
	
	/**
	 * Returns the x component of the acceleration of the ship.
	 */
	@Basic
	public double getXAcceleration() {
		return this.acceleration.getXComp();
	}

	/**
	 * Returns the y component of the acceleration of the ship.
	 */
	@Basic
	public double getYAcceleration() {
		return this.acceleration.getYComp();
	}
	
	/**
	 * Returns the acceleration of the ship.
	 */
	@Basic
	public Vector getAcceleration() {
		return this.acceleration;
	}

	
	/**
	 * Check if the acceleration component is valid.
	 * 
	 * @param 	comp
	 *        	The velocity component to be checked.
	 * @post 	True if and only if the acceleration component is a number and not infinite. 
	 * 			| result == (!Double.isNaN(comp) && !Double.isInfinite(comp))
	 */
	public boolean isValidAccelerationComp(double comp) {
		return (!Double.isNaN(comp) && !Double.isInfinite(comp));
	}

	/**
	 * Checks if the given acceleration components are valid.
	 * 
	 * @param ax
	 *            The x component of the acceleration to be checked.
	 * @param ay
	 *            The y component of the acceleration to be checked.
	 * @return False if the doubles ax and ay aren't valid acceleration components. 
	 * 			|if((!isValidAccelerationComp(ax))|| (!isValidAccelerationComp(ay))) 
	 * 			| then result == false
	 * @return True if and only if the acceleration is equal or less than the maximum value of a Double
	 *         | if((Math.sqrt(ax*ax+ay*ay) <= Double.MAX_VALUE) ) 
	 *         | then result == true 
	 *         | else 
	 *         | then result == false
	 */
	public boolean isValidAcceleration(double ax, double ay) {
		if ((!isValidAccelerationComp(ax)) || (!isValidAccelerationComp(ay))) {
			return false;
		}
		return Util.fuzzyLessThanOrEqualTo(Math.sqrt(ax * ax + ay * ay),
					Double.MAX_VALUE);
		
	}

	/**
	 * Set the acceleration of the ship.
	 * 
	 * @param ax
	 *        The given x component of the acceleration.
	 * @param ay
	 *        The given y component of the acceleration.
	 * @post If the given acceleration components are valid, then the new acceleration
	 *       components will be the given components. 
	 *       | if(isValidAcceleration(ax,ay))
	 *       |		then ((new this).getXAcceleration() == ax) && ((new this).getYAcceleration() == ay)
	 */
	public void setAcceleration(double ax, double ay) {
		if (isValidAcceleration(ax, ay)) {
			if(this.getAcceleration() == null)
				this.acceleration = new Vector(ax,ay);
			else
				this.acceleration.setComp(ax, ay);
		}
	}
	
	/**
	 * Set the acceleration of the ship.
	 * 
	 * @param acceleration
	 *        The given acceleration vector.
	 * @post If the given acceleration components of the acceleration vector are valid, then the new acceleration
	 *       vector will be the given acceleration vector. 
	 *       | if(isValidAcceleration(acceleration.getXComp(),acceleration.getYComp()))
	 *       |		then ((new this).getXAcceleration() == acceleration.getXComp()) && ((new this).getYAcceleration() == acceleration.getYComp())
	 */
	public void setAcceleration(Vector acceleration) {
		if (isValidAcceleration(acceleration.getXComp(), acceleration.getYComp())) {
			this.acceleration = acceleration;
		}
	}
	
	private Vector acceleration;
	

	/**
	 * Turns the spaceObject with a given angle.
	 * 
	 * @param angle
	 *            The angle that needs to be added to the current direction.
	 * @pre The sum of the old direction and the given angle must be a valid direction. 
	 * 		| isValidDirection(this.getDirection()+angle)
	 * @effect The direction of the spaceObject is set to the sum of the current direction and the given angle. 
	 * 		| (new this).setDirection(this.getDirection() + angle)
	 */
	public void turn(double angle) {
		assert isValidDirection(getDirection() + angle);

		setDirection(getDirection() + angle);
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
	
	public Thruster getThruster()
	{
		return this.thruster;
	}
	
	public void setThruster(boolean enabled, double powerOutput) throws IllegalArgumentException
	{
		this.thruster = new Thruster(enabled, powerOutput);
	}
	
	private Thruster thruster;	
	
	
	public void thrust(double time) {
		if( !Double.isNaN(time) && !Double.isInfinite(time) && time >=0)
		{
			Thruster thruster = this.getThruster();
			Vector acceleration = this.getAcceleration();
			Vector newAcceleration = thruster.generateAcceleration(this.getDirection(), this.getMass(), time);
			this.setAcceleration(acceleration.getXComp()+newAcceleration.getXComp(), acceleration.getYComp()+newAcceleration.getYComp());
			this.setVelocity(this.getXVelocity()+this.getXAcceleration()*time, this.getYVelocity()+acceleration.getYComp()*time);
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
