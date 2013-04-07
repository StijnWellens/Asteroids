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
		setThruster(false, 1.1*100000000);
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
	
	
	public void thrust() {
		Thruster thruster = this.getThruster();
		Vector acceleration = thruster.generateAcceleration(this.getDirection(), this.getMass());
		
		
	}
	
}
