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
	}
	
	public Ship(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle, double mass) {
		super(x, y , xVelocity, yVelocity,radius,angle);
		setMass(mass);
		setThruster(false, 1.1*100000000);
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
