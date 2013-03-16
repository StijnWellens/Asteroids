package asteroids.model;

import asteroids.IShip;
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
 * @author 	Julie Wouters & Stijn Wellens
 * 
 */
public class Ship implements IShip {

	/**
	 * Create a new default ship with default settings.
	 * 
	 * @effect This new ship is initialized with the default x coordinate 0 as
	 *         its x coordinate, 0 as its y coordinate, 300000 as its maximum
	 *         velocity, 0 as its x velocity component, 0 as its y velocity
	 *         component, PI/2 as its direction, 1 as its radius. 
	 *         | setMaxVelocity(300000); 
	 *         | setPosition(0,0); 
	 *         | setVelocity(0,0); 
	 *         | setDirection(PI / 2); 
	 *         | setRadius(1);
	 */
	public Ship() {
		setMaxVelocity(300000);
		
		setPosition(0,0);
		setVelocity(0, 0);
		setDirection(PI / 2);
		setRadius(1);

	}

	/**
	 * Create a new ship with given x and y position, x and y velocity
	 * components, direction and radius.
	 * 
	 * @param x
	 *        The initial x position for this new ship.
	 * @param y
	 *        The initial y position for this new ship.
	 * @param xVelocity
	 *        The initial x velocity component for this new ship.
	 * @param yVelocity
	 *        The initial y velocity component for this new ship.
	 * @param radius
	 *        The radius of this new ship.
	 * @param angle
	 *        The initial direction angle for this new ship.
	 * @effect This new ship is initialized with the given x as its initial x
	 *         coordinate, the given y as its initial y coordinate, 300000 as
	 *         its maximum velocity, the given xVelocity as its x velocity
	 *         component, the given YVelocity as its y velocity component, the
	 *         given angle as its initial direction, the given radius as its
	 *         radius. 
	 *         | setMaxVelocity(300000); 
	 *         | setPosition(x,y); 
	 *         | setVelocity(xVelocity, yVelocity); 
	 *         | setDirection(angle); 
	 *         | setRadius(radius);
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle) throws IllegalArgumentException {
		setMaxVelocity(300000);

		setPosition(x,y);
		setVelocity(xVelocity, yVelocity);
		setDirection(angle);
		setRadius(radius);

	}

	// Position: defensive programming

	private Vector position = new Vector();
	

	/**
	 * Returns the x Position of the ship.
	 * 
	 * @return 	xPosition 
	 * 			the x Position of the ship
	 */
	@Basic
	public double getX() {
		return this.position.getXComp();
	}

	/**
	 * Returns the y Position of the ship.
	 * 
	 * @return yPosition the y Position of the ship
	 */
	@Basic
	public double getY() {
		return this.position.getYComp();
	}
	
	/**
	 * Returns the position of the ship.
	 */
	@Basic
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Checks whether the given position component is a valid position component for a ship.
	 * 
	 * @param 	comp
	 *        	The position component to check.
	 * @return  True if and only if the given position component is a number. 
	 * 			| result == !Double.isNaN(comp)
	 */
	public boolean isValidPositionComp(double comp) {
		return !Double.isNaN(comp);
	}

	/**
	 * 
	 * @param 	x
	 *         	The new x position of this ship.
	 * @param 	y
	 *         	The new y position of this ship.
	 * @post 	The new x and y position of this ship are equal to the given x and y position of this ship. 
	 * 			| (new this).getX() == x
	 * 			| (new this).getY() == y
	 * @throws IllegalArgumentException
	 *         The given x position is not a valid x position. 
	 *         | !isValidPositionComp(x)
	 * @throws IllegalArgumentException
	 *         The given y position is not a valid y position. 
	 *         | !isValidPositionComp(y)
	 */
	public void setPosition(double x, double y) throws IllegalArgumentException {
		if (!isValidPositionComp(x) || !isValidPositionComp(y))
			throw new IllegalArgumentException();
		this.position.setComp(x,y);
	}

	// Velocity: total programming

	private Vector velocity = new Vector();
	private double maxV;					// in km/s

	public static final double	LIGHTSPEED	= 300000;

	/**
	 * Returns the x component of the velocity of the ship.
	 */
	@Basic
	public double getXVelocity() {
		return this.velocity.getXComp();
	}

	/**
	 * Returns the y component of the velocity of the ship.
	 */
	@Basic
	public double getYVelocity() {
		return this.velocity.getYComp();
	}
	
	/**
	 * Returns the velocity of the ship.
	 */
	@Basic
	public Vector getVelocity() {
		return this.velocity;
	}

	/**
	 * Returns the maximum velocity of the ship.
	 * 
	 */
	@Basic
	@Immutable
	public double getMaxVelocity() {
		return this.maxV;
	}

	/**
	 * Checks whether the given maximum velocity is valid.
	 * 
	 * @param maxV
	 *            The given maximum velocity.
	 * @return False if the double maxV is not a number. 
	 * 	| if(Double.isNaN(maxV) 
	 *         == true) | then result == false
	 * @return True if and only if the given maximum velocity is positive or 0
	 *         and not exceeding the speed of light. | if((maxV >= 0) && (maxV
	 *         <= LIGHTSPEED) | then result == true
	 */
	public boolean isValidMaxVelocity(double maxV) {
		if (Double.isNaN(maxV) == true) {
			return false;
		} else {
			return ((maxV >= 0) && (maxV <= LIGHTSPEED));
		}
	}

	/**
	 * 
	 * @param maxV
	 *            The given maximum velocity of the ship.
	 * @post If the absolute value of the given maximum velocity is valid, the
	 *       maximum velocity will be the absolute value of the given maximum
	 *       velocity. | if (isValidMaxVelocity(Math.abs(maxV))) | then (new
	 *       this).getMaxVelocity() == Math.abs(maxV)
	 * @post If the absolute value of the given maximum velocity exceeds the
	 *       speed of light, then the maximum velocity will be the speed of
	 *       light. | if (Math.abs(maxV) > LIGHTSPEED) | then (new
	 *       this).getMaxVelocity() == LIGHTSPEED
	 * 
	 */
	public void setMaxVelocity(double maxV) {
		if (isValidMaxVelocity(Math.abs(maxV))) {
			this.maxV = Math.abs(maxV);
		} else {
			this.maxV = LIGHTSPEED;
		}

	}

	/**
	 * Check if the velocity component is valid.
	 * 
	 * @param 	comp
	 *        	The velocity component to be checked.
	 * @post 	True if and only if the velocity component is a number. 
	 * 			| result == (!Double.isNaN(comp))
	 */
	public boolean isValidVelocityComp(double comp) {
		return (!Double.isNaN(comp));
	}

	/**
	 * Checks if the given velocity components are valid.
	 * 
	 * @param vx
	 *            The x component of the velocity to be checked.
	 * @param vy
	 *            The y component of the velocity to be checked.
	 * @return False if the double vx or vy is not a number. 
	 * 			|
	 *         if((Double.isNaN(vx) == true)|| (Double.isNaN(vy) == true)) |
	 *         then result == false
	 * @return True if and only if the velocity is equal or less than the valid
	 *         maximum speed of the ship. | if (vx <0 && vy<0) | then result ==
	 *         false | else if(
	 *         isValidMaxVelocity(this.maxV))&&(Math.sqrt(vx*vx+vy*vy) <=
	 *         this.maxV) ) | then result == true | else | then result == false
	 */
	public boolean isValidVelocity(double vx, double vy) {
		if ((!isValidVelocityComp(vx)) || (!isValidVelocityComp(vy))) {
			return false;
		}
		if (isValidMaxVelocity(this.maxV)) {
			return Util.fuzzyLessThanOrEqualTo(Math.sqrt(vx * vx + vy * vy),
					this.maxV);
		} else {
			return false;
		}
	}

	/**
	 * Set the velocity of the ship.
	 * 
	 * @param vx
	 *        The given x component of the velocity.
	 * @param vy
	 *        The given y component of the velocity.
	 * @post If the given velocity components are valid, then the new velocity
	 *       components will be the given components. 
	 *       | if(isValidVelocity(vx,vy))
	 *       |		then ((new this).getXVelocity() == vx) && ((new this).getYVelocity() == vy)
	 */
	private void setVelocity(double vx, double vy) {
		if (isValidVelocity(vx, vy)) {
			this.velocity.setComp(vx, vy);
		}
	}

	/**
	 * Check if the amount that has to be added to a velocity is valid.
	 * 
	 * @param amount
	 *            The amount to be checked.
	 * @post True if and only if the amount is a number and is higher than or
	 *       equal to zero. 
	 *       | result == 
	 *       | ( (!Double.isNaN(amount)) 
	 *       | && (amount >= 0) )
	 */
	private boolean isValidAmount(double amount) {
		return ((!Double.isNaN(amount)) && (amount >= 0));
	}

	/**
	 * Changes the ship's velocity based on the current velocity, its direction
	 * and on a given amount.
	 * 
	 * @param amount
	 *            The amount that needs to be added to the current velocity.
	 * @effect If the given amount is valid, the new x velocity will be set to
	 *         the sum of the old x velocity and the amount that has been
	 *         multiplied with the cosine of the direction. 
	 *         | if(!isValidAmount(amount)) 
	 *         | then (new this).getXVelocity() == this.getXVelocity() + amount*Math.sin(this.direction);
	 * 
	 */
	// TODO Afwerken documentatie!
	public void thrust(double amount) {

		if (!isValidAmount(amount)) {
			amount = 0;
		}

		double tempVx = getXVelocity() + amount * Math.cos(getDirection());
		double tempVy = getYVelocity() + amount * Math.sin(getDirection());

		if (isValidVelocity(tempVx, tempVy)) {
			setVelocity(tempVx, tempVy);
		} else {
			double tempAmount = (getMaxVelocity())
					/ Math.sqrt(tempVx * tempVx + tempVy * tempVy);
			tempVx = tempVx * tempAmount;
			tempVy = tempVy * tempAmount;

			setVelocity(tempVx, tempVy);
		}
	}

	// Direction: nominal programming

	private double				direction;
	private static final double	PI	= Math.PI;

	/**
	 * Returns the angle of the direction of this ship.
	 * 
	 * @return the angle of the direction of the ship | this.direction
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
	 *            The angle in which the ship moves.
	 * @return True if and only if the given angle is a number and is between
	 *         zero and 2*Pi. 
	 *         | result == ((!Double.isNaN(angle)) && (angle >=0) && (angle < 2*PI))
	 */
	public boolean isValidDirection(double angle) {
		return ((!Double.isNaN(angle)) && (angle >= 0) && (angle < 2 * PI));
	}

	/**
	 * Set the direction of the ship to the given direction.
	 * 
	 * @param angle
	 *            The angle of the given direction.
	 * @pre The given direction must be a valid direction. 
	 * 		| isValidDirection(angle)
	 * @post The new direction of the ship is equal to the modulo 2*Pi of the given direction of the ship. 
	 * 		| (new this).getDirection() == angle
	 */
	public void setDirection(double angle) {
		assert isValidDirection(angle);

		this.direction = angle;

	}

	/**
	 * Turns the ship with a given angle.
	 * 
	 * @param angle
	 *            The angle that needs to be added to the current direction.
	 * @pre The sum of the old direction and the given angle must be a valid direction. 
	 * 		| isValidDirection(this.getDirection()+angle)
	 * @effect The direction of the ship is set to the sum of the current direction and the given angle. 
	 * 		| (new this).setDirection(this.getDirection() + angle)
	 */
	public void turn(double angle) {
		assert isValidDirection(getDirection() + angle);

		setDirection(getDirection() + angle);
	}

	// Radius

	private double			radius;
	private static double	lowerBoundRadius;

	/**
	 * Returns the lower bound of the radius of all ships.
	 * 
	 * @return lower bound of the radius The lower bound of the radius of the
	 *         ship
	 */
	@Basic
	@Raw
	public double getLowerBoundRadius() {
		return lowerBoundRadius;
	}

	/**
	 * Check whether the lower bound of the radius is valid for all ships.
	 * 
	 * @param 	lowerBoundRadius
	 *          the lower bound to check
	 * @return True if and only if the lower bound of the radius is not zero or below zero and if is a number. 
	 * 			| result == 
	 * 			| ( (lowerBoundRadius >= 0) 
	 * 			| && (!Double.isNaN(lowerBoundRadius)) )
	 */
	public static boolean isValidLowerBoundRadius(double lowerBoundRadius) {
		return ((!Double.isNaN(lowerBoundRadius)) && (lowerBoundRadius >= 0));
	}

	/**
	 * Set the lowerBoundRadius of all the ships to the given lowerBoundRadius.
	 * 
	 * @param 	lowerBoundRadius
	 *          The new lowerBoundRadius for all the ships.
	 * @post 	The new lowerBoundRadius of all the ships is equal to the given
	 *       	lowerBoundRadius. | new.getLowerBoundRadius() == lowerBoundRadius
	 * @throws 	IllegalArgumentException
	 *          The given lowerBoundRadius is not a valid lowerBoundRadius for any ship. 
	 *          | ! isValidLowerBoundRadius(lowerBoundRadius)
	 */
	private static void setLowerBoundRadius(double lowerBoundRadius)
			throws IllegalArgumentException {
		if (!isValidLowerBoundRadius(lowerBoundRadius))
			throw new IllegalArgumentException();
		Ship.lowerBoundRadius = lowerBoundRadius;

	}

	/**
	 * Returns the radius of this ship.
	 * 
	 * @return radius The radius of the ship
	 */
	@Basic
	@Raw
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check whether the given radius is a valid radius for this ship.
	 * 
	 * @param radius
	 *        The radius to check.
	 * @return True if and only if the given radius is higher than the lowerBoundRadius of the ships and it is a number. 
	 * 			| result == 
	 * 			| ((!Double.isNaN(radius)) 
	 * 			| && (radius > lowerBoundRadius) )
	 */
	public boolean isValidRadius(double radius) {
		return ((!Double.isNaN(radius)) && (radius > getLowerBoundRadius()));
	}

	/**
	 * Set the radius of the ship to the given radius.
	 * 
	 * @param 	radius
	 *         	The new radius for the ship.
	 * @post 	The new radius of the ship is equal to the given radius. 
	 * 			| (new this).getRadius() == radius
	 * @throws 	IllegalArgumentException
	 *          The given radius is not a valid radius for this ship. 
	 *          | !isValidRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
	}

	/**
	 * Check whether the given duration is a valid duration.
	 * 
	 * @param 	duration
	 *       	The duration to check.
	 * @return 	True if and only if the given duration is not below zero. 
	 * 			|result == | duration >= 0
	 */
	public boolean isValidDuration(double duration) {
		return duration >= 0;
	}

	/**
	 * Changes the position of the ship based on the current position, current
	 * orientation, velocity and a given time duration.
	 * 
	 * @param	duration
	 *         	How long the ship needs to move.
	 * @post 	The new x-coordinate of this ship is equal to the old x-coordinate
	 *       	of this ship incremented with the product of the x-velocity of the
	 *       	ship with the cosine of the direction of the ship with the given time duration.  
	 *       	|(new this).getX() == this.getX() + this.getXVelocity()*Math.cos(this.getDirection())*duration
	 * @post 	The new y-coordinate of this ship is equal to the old y-coordinate
	 *       	of this ship incremented with the product of the y-velocity of the
	 *       	ship with the sine of the direction of the ship with the given time duration. 
	 *       	|(new this).getY() == this.getY() + this.getYVelocity()*Math.sin(this.getDirection())*duration
	 * @throws 	IllegalArgumentException
	 *          The given duration is not a valid duration. 
	 *          |!isValidDuration(duration)
	 */
	// TODO
	public void move(double duration) {
		if (!isValidDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getX() + getXVelocity() * duration, getY() + getYVelocity() * duration);
		
	}
	
	/**
	 * Calculate the distance between two ships.
	 * 
	 * @param 	ship1
	 * 			The first ship to compare with the other ship.
	 * @param 	ship2
	 * 			The second ship to compare with the other ship.
	 * @return	The distance between the two ships.
	 * 			| distance = Vector.getModulus(( ship1.getX() - ship2.getX() ), ( ship1.getY() - ship2.getY() ))
	 * 			|		-	( ship1.getRadius() + ship2.getRadius() )
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given ships is null.
	 * 			| (ship1 == null) || (ship2 == null)
	 * @throws	IllegalArgumentException
	 * 			The distance between the two ships is not a number.
	 * 			| Double.isNaN(distance) 
	 */
	public static double getDistanceBetween(Ship ship1, Ship ship2) throws IllegalArgumentException
	{
		if((ship1 == null) || (ship2 == null))
			throw new IllegalArgumentException();
		
		double distance;
		
		try{
			double sumOfRadius = Vector.sumOfComponents( ship1.getRadius(),ship2.getRadius() );
			double distanceWithoutRadius = Vector.getModulus(Vector.sumOfComponents(ship1.getX(),-ship2.getX()), Vector.sumOfComponents(ship1.getY(),-ship2.getY()));
			distance = Vector.sumOfComponents(distanceWithoutRadius, -sumOfRadius);
		}
		catch(ArithmeticException ae) {
			distance = 0;
		}
		
		if(Double.isNaN(distance))
			throw new IllegalArgumentException();
		
		return distance;	
			
	}
	
	/**
	 * Return a boolean reflecting whether the given ship overlaps with the current ship.	
	 * 
	 * @param 	ship1
	 * 			The first ship to compare with the other ship.
	 * @param 	ship2
	 * 			The second ship to compare with the other ship.
	 * @return	True if and only if the given ship overlaps with the current ship.
	 * 			| result == (this.getDistanceBetween(ship1,ship2) <= 0)
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given ships is null.
	 * 			| (ship1 == null) || (ship2 == null)
	 */
	public static boolean overlap(Ship ship1, Ship ship2) throws IllegalArgumentException
	{
		if((ship1 == null) || (ship2 == null))
			throw new IllegalArgumentException();
		return (getDistanceBetween(ship1,ship2) <= 0);
	}
	
	/**
	 * Return when two ships will collide.
	 * 
	 * @param 	ship1
	 * 			The first ship to compare with the other.
	 * @param 	ship2
	 * 			The second ship to compare with the other.
	 * @return	Return when two ships will collide. Positive infinity if they never collide. For the specific calculation see the code.
	 * 			| dt
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given ships is null.
	 * 			| (ship1 == null) || (ship2 == null)
	 */
	public static double getTimeToCollision(Ship ship1, Ship ship2) throws IllegalArgumentException
	{
		if((ship1 == null) || (ship2 == null))
			throw new IllegalArgumentException();
		
		double dt;
		try{
			Vector dv = Vector.subtraction(ship1.getVelocity(), ship2.getVelocity());
			Vector dr = Vector.subtraction(ship1.getPosition(), ship2.getPosition());
			double dvdr = Vector.dotProduct(dv, dr);
			double dvdv = Vector.dotProduct(dv, dv);
			double d1 = Vector.multiplyComponents((dvdr),(dvdr)) ;
			double dModulus = Vector.multiplyComponents((dr.getModulus()),(dr.getModulus()));
			double d2 = Vector.multiplyComponents(dvdv, Vector.sumOfComponents(Vector.dotProduct(dr, dr), -dModulus) );
			double d = Vector.sumOfComponents(d1, -d2);
					
			if(dvdr >= 0 || d <= 0)
			{
				dt = Double.POSITIVE_INFINITY;
			}		
			else{
				dt = -(Vector.multiplyComponents(Vector.sumOfComponents(dvdr, Math.sqrt(d)),1/(dvdv)));
			}
		}
		catch(ArithmeticException ae)
		{
			dt = Double.POSITIVE_INFINITY;
		}
						
		return dt;
			
	}
	
	/**
	 * Return where two ships will collide.
	 * 
	 * @param 	ship1
	 * 			The first ship to compare with the other.
	 * @param 	ship2
	 * 			The second ship to compare with the other.
	 * @return	An array with the position of the future collision.
	 * 			| 
	 * @return	Null if the two ships never collide.
	 * 			| 
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given ships is null.
	 * 			| (ship1 == null) || (ship2 == null)
	 */
	public static double[] getCollisionPosition(Ship ship1, Ship ship2) throws IllegalArgumentException
	{
		if((ship1 == null) || (ship2 == null))
			throw new IllegalArgumentException();
		
		double[] collision = new double[2];
		
		try{
			double time = getTimeToCollision(ship1,ship2);
			Vector collisionPosition = Vector.sum(ship1.getPosition(), Vector.multiplyScalar(ship1.getVelocity(),time));
			
			collision[0] = collisionPosition.getXComp();
			collision[1] = collisionPosition.getYComp();
		}
		catch(ArithmeticException ae)
		{
			collision = null;
		}
		
		
		return collision;
	}

}
