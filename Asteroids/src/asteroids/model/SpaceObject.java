package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class SpaceObject {

	/**
	 * Create a new default spaceObject with default settings.
	 * 
	 * @effect This new spaceObject is initialized with the default x coordinate 0 as
	 *         its x coordinate, 0 as its y coordinate, 300000 as its maximum
	 *         velocity, 0 as its x velocity component, 0 as its y velocity
	 *         component, PI/2 as its direction, 1 as its radius. 
	 *         | setMaxVelocity(300000); 
	 *         | setPosition(0,0); 
	 *         | setVelocity(0,0); 
	 *         | setDirection(PI / 2); 
	 *         | setRadius(1);
	 *         | 
	 */
	public SpaceObject() {
		setMaxVelocity(300000);
		
		setPosition(0,0);
		setVelocity(0, 0);
		setDirection(PI / 2);
		this.radius = 1;

	}

	/**
	 * Create a new spaceObject with given x and y position, x and y velocity
	 * components, direction and radius.
	 * 
	 * @param x
	 *        The initial x position for this new spaceObject.
	 * @param y
	 *        The initial y position for this new spaceObject.
	 * @param xVelocity
	 *        The initial x velocity component for this new spaceObject.
	 * @param yVelocity
	 *        The initial y velocity component for this new spaceObject.
	 * @param radius
	 *        The radius of this new spaceObject.
	 * @param angle
	 *        The initial direction angle for this new spaceObject.
	 * @effect This new spaceObject is initialized with the given x as its initial x
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
	public SpaceObject(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle) throws IllegalArgumentException {
		setMaxVelocity(300000);

		setPosition(x,y);
		setVelocity(xVelocity, yVelocity);
		setDirection(angle);
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;

	}
	

	// Position: defensive programming
	
	private Vector position = new Vector();
	
	/**
	 * Returns the x Position of the spaceObject.
	 * 
	 * @return 	xPosition 
	 * 			the x Position of the spaceObject
	 */
	@Basic
	public double getX() {
		return this.position.getXComp();
	}

	/**
	 * Returns the y Position of the spaceObject.
	 * 
	 * @return yPosition 
	 * 			the y Position of the spaceObject
	 */
	@Basic
	public double getY() {
		return this.position.getYComp();
	}
	
	/**
	 * Returns the position of the spaceObject.
	 */
	@Basic
	public Vector getPosition() {
		return this.position;
	}

	/**
	 * Checks whether the given position component is a valid position component for a spaceObject.
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
	 *         	The new x position of this spaceObject.
	 * @param 	y
	 *         	The new y position of this spaceObject.
	 * @post 	The new x and y position of this spaceObject are equal to the given x and y position of this spaceObject. 
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
	 * Returns the x component of the velocity of the spaceObject.
	 */
	@Basic
	public double getXVelocity() {
		return this.velocity.getXComp();
	}

	/**
	 * Returns the y component of the velocity of the spaceObject.
	 */
	@Basic
	public double getYVelocity() {
		return this.velocity.getYComp();
	}
	
	/**
	 * Returns the velocity of the spaceObject.
	 */
	@Basic
	public Vector getVelocity() {
		return this.velocity;
	}

	/**
	 * Returns the maximum velocity of the spaceObject.
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
	 * 			| if(Double.isNaN(maxV) == true) 
	 * 			| then result == false
	 * @return True if and only if the given maximum velocity is positive or 0
	 *         and not exceeding the speed of light. 
	 *         | if((maxV >= 0) && (maxV <= LIGHTSPEED) 
	 *         | then result == true
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
	 *            The given maximum velocity of the spaceObject.
	 * @post If the absolute value of the given maximum velocity is valid, the
	 *       maximum velocity will be the absolute value of the given maximum
	 *       velocity. 
	 *       | if (isValidMaxVelocity(Math.abs(maxV))) 
	 *       | then (new this).getMaxVelocity() == Math.abs(maxV)
	 * @post If the absolute value of the given maximum velocity exceeds the
	 *       speed of light, then the maximum velocity will be the speed of
	 *       light. 
	 *       | if (Math.abs(maxV) > LIGHTSPEED) 
	 *       | then (new this).getMaxVelocity() == LIGHTSPEED
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
	 * 			|if((Double.isNaN(vx) == true)|| (Double.isNaN(vy) == true)) 
	 * 			| then result == false
	 * @return True if and only if the velocity is equal or less than the valid
	 *         maximum speed of the spaceObject. 
	 *         | if (vx <0 && vy<0) 
	 *         | then result == false 
	 *         | else if(isValidMaxVelocity(this.maxV))&&(Math.sqrt(vx*vx+vy*vy) <= this.maxV) ) 
	 *         | then result == true 
	 *         | else 
	 *         | then result == false
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
	 * Set the velocity of the spaceObject.
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
	 * Changes the spaceObject's velocity based on the current velocity, its direction
	 * and on a given amount.
	 * 
	 * @param 	amount
	 *        	The amount that needs to be added to the current velocity.
	 * @post 	If the given amount is valid, the new x velocity will be set to
	 *         	the sum of the old x velocity and the amount that has been
	 *         	multiplied with the cosine of the direction. 
	 *         	| if(!isValidAmount(amount)) 
	 *         	| then (new this).getXVelocity() == this.getXVelocity() + amount*Math.sin(this.direction);
	 * @post 	If the given amount is valid, the new y velocity will be set to
	 *         	the sum of the old y velocity and the amount that has been
	 *         	multiplied with the sine of the direction. 
	 *         	| if(!isValidAmount(amount)) 
	 *         	| then (new this).getYVelocity() == this.getYVelocity() + amount*Math.cos(this.direction);
	 * @post	If the new x and y velocity, calculated as above, aren't valid which means they exceed the maximum velocity.
	 * 			Then they will be reduced such that the speed becomes the maximum velocity.
	 * 			| if((new this).getVelocity().getModulus() > this.getMaxVelocity())
	 * 			| 		then (new(new this)).getVelocity().getModulus() == this.getMaxVelocity()
	 */
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

	// Radius

	private final double	radius;
	private static double	lowerBoundRadius;

	/**
	 * Returns the lower bound of the radius of all spaceObjects.
	 * 
	 * @return lower bound of the radius The lower bound of the radius of the
	 *         spaceObject
	 */
	@Basic
	@Raw
	public double getLowerBoundRadius() {
		return lowerBoundRadius;
	}

	/**
	 * Check whether the lower bound of the radius is valid for all spaceObjects.
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
	 * Set the lowerBoundRadius of all the spaceObjects to the given lowerBoundRadius.
	 * 
	 * @param 	lowerBoundRadius
	 *          The new lowerBoundRadius for all the spaceObjects.
	 * @post 	The new lowerBoundRadius of all the spaceObjects is equal to the given
	 *       	lowerBoundRadius. | new.getLowerBoundRadius() == lowerBoundRadius
	 * @throws 	IllegalArgumentException
	 *          The given lowerBoundRadius is not a valid lowerBoundRadius for any spaceObject. 
	 *          | ! isValidLowerBoundRadius(lowerBoundRadius)
	 */
	private static void setLowerBoundRadius(double lowerBoundRadius)
			throws IllegalArgumentException {
		if (!isValidLowerBoundRadius(lowerBoundRadius))
			throw new IllegalArgumentException();
		SpaceObject.lowerBoundRadius = lowerBoundRadius;

	}

	/**
	 * Returns the radius of this spaceObject.
	 * 
	 * @return radius The radius of the spaceObject
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check whether the given radius is a valid radius for this spaceObject.
	 * 
	 * @param radius
	 *        The radius to check.
	 * @return True if and only if the given radius is higher than the lowerBoundRadius of the spaceObjects and it is a number. 
	 * 			| result == 
	 * 			| ((!Double.isNaN(radius)) 
	 * 			| && (!Double.isInfinite(radius))
	 * 			| && (radius > lowerBoundRadius) )
	 */
	public boolean isValidRadius(double radius) {
		return ((!Double.isNaN(radius)) && (!Double.isInfinite(radius)) && (radius > getLowerBoundRadius()));
	}

	/**
	 * Set the radius of the spaceObject to the given radius.
	 * 
	 * @param 	radius
	 *         	The new radius for the spaceObject.
	 * @post 	The new radius of the spaceObject is equal to the given radius. 
	 * 			| (new this).getRadius() == radius
	 * @throws 	IllegalArgumentException
	 *          The given radius is not a valid radius for this spaceObject. 
	 *          | !isValidRadius(radius)
	 */
	//TODO documentatie naar constructor
	public final void setRadius(double radius) throws IllegalArgumentException {
		
	}

	/**
	 * Check whether the given duration is a valid duration.
	 * 
	 * @param 	duration
	 *       	The duration to check.
	 * @return 	True if and only if the given duration is not below zero and if it is a number. 
	 * 			|result == 
	 * 			| (duration >= 0 && Double.isNaN(duration))
	 */
	public boolean isValidDuration(double duration) {
		return (duration >= 0 && !Double.isNaN(duration));
	}

	/**
	 * Changes the position of the spaceObject based on the current position, current
	 * orientation, velocity and a given time duration.
	 * 
	 * @param	duration
	 *         	How long the spaceObject needs to move.
	 * @effect 	The new x-coordinate of this spaceObject is equal to the old x-coordinate
	 *       	of this spaceObject incremented with the product of the x-velocity of the
	 *       	spaceObject with the cosine of the direction of the spaceObject with the given time duration.  
	 *       	|(new this).getX() == this.getX() + this.getXVelocity()*Math.cos(this.getDirection())*duration
	 * @effect 	The new y-coordinate of this spaceObject is equal to the old y-coordinate
	 *       	of this spaceObject incremented with the product of the y-velocity of the
	 *       	spaceObject with the sine of the direction of the spaceObject with the given time duration. 
	 *       	|(new this).getY() == this.getY() + this.getYVelocity()*Math.sin(this.getDirection())*duration
	 * @throws 	IllegalArgumentException
	 *          The given duration is not a valid duration. 
	 *          |!isValidDuration(duration)
	 */
	public void move(double duration) throws IllegalArgumentException {
		if (!isValidDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getX() + getXVelocity() * duration, getY() + getYVelocity() * duration);
		
	}
	
	/**
	 * Calculate the distance between two spaceObjects.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other spaceObject.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other spaceObject.
	 * @return	The distance between the two spaceObjects.
	 * 			| Util.fuzzyEquals( Vector.getModulus(( spaceObject1.getX() - spaceObject2.getX() ), ( spaceObject1.getY() - spaceObject2.getY() ))
	 * 			|		-	( spaceObject1.getRadius() + spaceObject2.getRadius() ) )
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 * @throws	IllegalArgumentException
	 * 			The distance between the two spaceObjects is not a number.
	 * 			| Double.isNaN(distance) 
	 */
	public static double getDistanceBetween(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
		
		double distance;
		
		double sumOfRadius = Vector.sumOfComponents( spaceObject1.getRadius(),spaceObject2.getRadius() );
		double distanceWithoutRadius = Vector.getModulus(Vector.sumOfComponents(spaceObject1.getX(),-spaceObject2.getX()), Vector.sumOfComponents(spaceObject1.getY(),-spaceObject2.getY()));
		distance = Vector.sumOfComponents(distanceWithoutRadius, -sumOfRadius);
		
		
		if(Double.isNaN(distance))
			throw new IllegalArgumentException();
		
		return distance;	
			
	}
	
	/**
	 * Return a boolean reflecting whether the given spaceObject overlaps with the current spaceObject.	
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other spaceObject.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other spaceObject.
	 * @return	True if and only if the given spaceObject overlaps with the current spaceObject.
	 * 			| result == (this.getDistanceBetween(spaceObject1,spaceObject2) <= 0)
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 */
	public static boolean overlap(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
		return (getDistanceBetween(spaceObject1,spaceObject2) <= 0);
	}
	
	/**
	 * Return when two spaceObjects will collide.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other.
	 * @return	Return when two spaceObjects will collide. Positive infinity if they never collide. For the specific calculation see the code.
	 * 			| dt
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 *
	 // infinity, time could not be negative
	  * if(result == Double.POSITIVE_INFINITY) then
	  * 	(this == other) 
	 * 
	 */
	public static double getTimeToCollision(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
				
		double dt;
		
		Vector dv = Vector.subtraction(spaceObject1.getVelocity(), spaceObject2.getVelocity());
		Vector dr = Vector.subtraction(spaceObject1.getPosition(), spaceObject2.getPosition());
		double dvdr = Vector.dotProduct(dv, dr);
		double dvdv = Vector.dotProduct(dv, dv);
		double d1 = Vector.multiplyComponents((dvdr),(dvdr)) ;
		double sigma = Vector.sumOfComponents(spaceObject1.getRadius(),spaceObject2.getRadius());
		double sigmaSquare = Vector.multiplyComponents(sigma, sigma);
		double d2 = Vector.multiplyComponents(dvdv, Vector.sumOfComponents(Vector.dotProduct(dr, dr), -sigmaSquare) );
		double d = Vector.sumOfComponents(d1, -d2);
					
		if(dvdr >= 0 || d <= 0)
		{
			dt = Double.POSITIVE_INFINITY;
		}		
		else{
			dt = -(Vector.multiplyComponents(Vector.sumOfComponents(dvdr, Math.sqrt(d)),(1d/(dvdv))));
		}
								
		return dt;
			
	}
	
	/**
	 * Return where two spaceObjects will collide.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other.
	 * @return	An array with the position of the future collision. With alpha the given angle between the two centers of the spacespaceObjects when they collide.
	 * 			| collision[0] = spaceObject1.getXPosition() + spaceObject1.getXVelocity() * getTimeToCollisiion() + cos(alpha)* spaceObject1.getRadius();
	 *			| collision[1] = spaceObject1.getYPosition() + spaceObject1.getYVelocity() * getTimeToCollisiion() + sin(alpha)* spaceObject1.getRadius();
	 *			| result == collision
	 * @return	Null if the two spaceObjects never collide.
	 * 			| if(getTimeToCollision(spaceObject1,spaceObject2) == Double.POSITIVE_INFINITY)
	 * 			|	then collision == null
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 */
	public static double[] getCollisionPosition(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
		
		double[] collision = new double[2];
		
		double time = getTimeToCollision(spaceObject1,spaceObject2);
		if(time == Double.POSITIVE_INFINITY)
		{
			collision = null;
		}
		else{
						
			Vector newPositionspaceObject1 = new Vector(spaceObject1.getPosition().getXComp() + spaceObject1.getXVelocity() * time, spaceObject1.getPosition().getYComp() + spaceObject1.getYVelocity() * time);
			Vector newPositionspaceObject2 = new Vector(spaceObject2.getPosition().getXComp() + spaceObject2.getXVelocity() * time, spaceObject2.getPosition().getYComp() + spaceObject2.getYVelocity() * time);
			Vector directionCollision = Vector.subtraction(newPositionspaceObject1, newPositionspaceObject2);
			
			double alpha = (Math.atan(Math.abs(directionCollision.getYComp())/Math.abs(directionCollision.getXComp())));
			
			if(directionCollision.getXComp() < 0 && directionCollision.getYComp() > 0 )
				alpha = PI - alpha;
			if(directionCollision.getXComp() < 0 && directionCollision.getYComp() < 0 )
				alpha = PI + alpha;
			if(directionCollision.getXComp() > 0 && directionCollision.getYComp() < 0 )
				alpha = - alpha;
			
			double collisionPositionX = spaceObject1.getPosition().getXComp() + spaceObject1.getXVelocity() * time - Math.cos(alpha) * spaceObject1.getRadius() ;
			double collisionPositionY = spaceObject1.getPosition().getYComp() + spaceObject1.getYVelocity() * time - Math.sin(alpha) * spaceObject1.getRadius() ;
			
			collision[0] = collisionPositionX; 
			collision[1] = collisionPositionY; 
		}
				
		return collision;
	}
	
	
}
