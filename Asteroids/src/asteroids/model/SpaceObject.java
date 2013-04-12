package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a spaceObject with a position, velocity, direction and radius
 * 
 * @invar 	The x position of the spaceObject must always be a valid x position. 
 * 			| isValidX(getX())
 * @invar 	The y position of the spaceObject must always be a valid y position. 
 * 			| isValidY(getY())
 * @invar 	The maximum velocity of the spaceObject must always be a valid maximum
 *        	velocity. 
 *        	| isValidMaxVelocity(getMaxVelocity())
 * @invar 	The velocity of the spaceObject must always be a valid velocity. 
 * 			| isValidVelocity(getXVelocity(), getYVelocity())
 * @invar 	The lowerBoundRadius of the spaceObject must always be a valid.
 *        	lowerBoundRadius. 
 *        	| isValidLowerBoundRadius(getLowerBoundRadius())
 * @invar 	The radius of the spaceObject must always be a valid radius. 
 * 			| isValidRadius(getRadius())
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 * 
 */
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
	 * @post	The new radius of the spaceObject is equal to 1. 
	 * 			| (new this).getRadius() == 1
	 */
	@Model
	protected SpaceObject() {
		setMaxVelocity(300000);
		setLowerBoundRadius(0);
		setPosition(0,0);
		setVelocity(0, 0);
		this.radius = 11;

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
	 * @post 	The new radius of the spaceObject is equal to the given radius. 
	 * 			| (new this).getRadius() == radius       
	 * @throws 	IllegalArgumentException
	 *          The given radius is not a valid radius for this spaceObject. 
	 *          | !isValidRadius(radius)
	 */
	@Model
	protected SpaceObject(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException {
		setMaxVelocity(300000);
		setLowerBoundRadius(0);
		
		setPosition(x,y);
		setVelocity(xVelocity, yVelocity);
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
	 * @return False if the doubles vx and vy aren't valid velocity components. 
	 * 			|if((!isValidVelocityComp(vx))|| (!isValidVelocityComp(vy))) 
	 * 			| then result == false
	 * @return True if and only if the velocity is equal or less than the valid
	 *         maximum speed of the spaceObject. 
	 *         | if((Math.sqrt(vx*vx+vy*vy) <= this.maxV) ) 
	 *         | then result == true 
	 *         | else 
	 *         | then result == false
	 */
	public boolean isValidVelocity(double vx, double vy) {
		if ((!isValidVelocityComp(vx)) || (!isValidVelocityComp(vy))) {
			return false;
		}
		return Util.fuzzyLessThanOrEqualTo(Math.sqrt(vx * vx + vy * vy),
					this.maxV);
		
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
	public void setVelocity(double vx, double vy) {
		if (isValidVelocity(vx, vy)) {
			this.velocity.setComp(vx, vy);
		}
	}
	
	public void setVelocity(Vector velocity) {
		if (isValidVelocity(velocity.getXComp(), velocity.getYComp())) {
			this.velocity = velocity;
		}
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
	public static double getLowerBoundRadius() {
		return lowerBoundRadius;
	}

	/**
	 * Check whether the lower bound of the radius is valid for all spaceObjects.
	 * 
	 * @param 	lowerBoundRadius
	 *          the lower bound to check
	 * @return True if and only if the lower bound of the radius is not infinite, not zero or below zero and if is a number. 
	 * 			| result == 
	 * 			| ( (!Double.isInfinite(lowerBoundRadius))
	 * 			|	  && (!Double.isNaN(lowerBoundRadius)) 
	 * 			|		&& (lowerBoundRadius >= 0) )
	 */
	public static boolean isValidLowerBoundRadius(double lowerBoundRadius) {
		return ((!Double.isNaN(lowerBoundRadius)) && (!Double.isInfinite(lowerBoundRadius)) && (lowerBoundRadius >= 0));
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
	protected static void setLowerBoundRadius(double lowerBoundRadius)
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
	 * Check whether the given duration is a valid duration.
	 * 
	 * @param 	duration
	 *       	The duration to check.
	 * @return 	True if and only if the given duration is not below zero, is not infinite and if it is a number. 
	 * 			|result == 
	 * 			| (duration >= 0 && Double.isNaN(duration) && !Double.isInfinite(duration))
	 */
	public boolean isValidDuration(double duration) {
		return ((duration >= 0) && (!Double.isNaN(duration)) && (!Double.isInfinite(duration)));
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
	
	// Mass
	
	/**
	 * Return the mass of this ship.
	 * @return	the mass
	 * 			| this.mass
	 */
	@Basic
	public double getMass(){
		return this.mass;
	}
	
	/**
	 * Check whether the mass of the ship is valid.
	 * 
	 * @param	mass
	 * 			The mass to check.
	 * @return	True if and only if the mass is a number, strictly positive and not infinite.
	 * 			| result == ( (!Double.isNaN(mass)) 
	 * 			| 	&& (!Double.isInfinite(mass))
	 * 			|	&& (mass > 0) )
	 */
	public boolean isValidMass(double mass) {
		return (!Double.isNaN(mass)) && (!Double.isInfinite(mass)) && (mass > 0); 
	}
	
	/**
	 * Set the mass of this ship.
	 * 
	 * @param 	mass
	 * 			The mass to set.
	 * @post	The new mass of the ship is equal to the given mass.
	 * 			| (new this).getMass() == mass
	 * @throws	IllegalArgumentException
	 * 			Throws exception if the given mass is not valid.
	 * 			| !isValidMass(mass)
	 */
	public void setMass(double mass) throws IllegalArgumentException{
		if(!isValidMass(mass))
			throw new IllegalArgumentException();
		this.mass = mass;
	}
	
	private double mass;
	
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
	 */
	public static double getDistanceBetween(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
		
		double distance;
		
		double sumOfRadius = Vector.sumOfComponents( spaceObject1.getRadius(),spaceObject2.getRadius() );
		double distanceWithoutRadius = Vector.getModulus(Vector.sumOfComponents(spaceObject1.getX(),-spaceObject2.getX()), Vector.sumOfComponents(spaceObject1.getY(),-spaceObject2.getY()));
		distance = Vector.sumOfComponents(distanceWithoutRadius, -sumOfRadius);
				
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
	 * Return the time to collision from a point of a ship with a border in one direction.
	 * 
	 * @param position
	 * @param velocity
	 * @param axisMax
	 * @return
	 */
	//TODO
	private double getTimeToCollisionWithAxis(double position, double velocity, double axisMax)
	{
		
		double dt;
		
		if(velocity>0)
			dt = Vector.multiplyComponents(axisMax-this.getRadius()-position, 1/velocity);
		else if(velocity<0)
			dt = Vector.multiplyComponents(this.getRadius()-position, 1/velocity);
		else
			dt = Double.POSITIVE_INFINITY;
		return dt;
	}
	
	/**
	 * Returns the time to collision for this ship with the border.
	 * 
	 * @return
	 */
	public double getTimeToCollisionWithBorder(){
		if(this.getWorld()==null)
			return Double.POSITIVE_INFINITY;
		double width = this.getWorld().getWidth();
		double height = this.getWorld().getHeight();
		double dtx = getTimeToCollisionWithAxis(this.getX(), this.getXVelocity(), width);
		double dty = getTimeToCollisionWithAxis(this.getY(),this.getYVelocity(), height);
						
		if(dtx <= dty)
			return dtx;
		else
			return dty;
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
			
			if(directionCollision.getYComp() == 0) {
				if(Math.signum(directionCollision.getXComp()) < 0)
					alpha = PI;
				else
					alpha = 0;
			}
			if(directionCollision.getXComp() == 0) {
				if(Math.signum(directionCollision.getYComp()) < 0)
					alpha = -PI/2;
				else
					alpha = PI/2;
			}
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
	
	private static final double	PI	= Math.PI;
	
	public boolean canHaveAsWorld(World world){
		if((this.getX()+this.getRadius())> world.getWidth())
			return false;
		if((this.getY()+this.getRadius())> world.getHeight())
			return false;
		return true;
	}
	
	public boolean overlapWithWorldObject(World world){
		for(SpaceObject spaceObject: world.getSpaceObjects()){
			if(Bullet.class.isInstance(spaceObject)){}
			else if(SpaceObject.overlap(spaceObject, this))
				return false;
		}
		return true;
	}
	
	private World world;
	
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	public void setWorld(World world)throws IllegalArgumentException{
		if(!this.canHaveAsWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
	}
	
	public void die(){
		if(this.getWorld()!=null){
			this.getWorld().removeSpaceObject(this);
			this.setWorld(null);
		}
	}
	
	public void collision(SpaceObject spaceObject){}
	
	public void collisionWithBorder()
	{
		if(this.getWorld()!=null){
			double time = this.getTimeToCollisionWithBorder();
			Vector position = this.getPosition();
			Vector velocity = this.getVelocity();
			
			if(Util.fuzzyEquals(position.getXComp()+velocity.getXComp()*time, 0) || Util.fuzzyEquals(position.getXComp()+velocity.getXComp()*time, this.getWorld().getWidth()))
			{
				this.setVelocity(-velocity.getXComp(), velocity.getYComp());
			}
			else
			{
				this.setVelocity(velocity.getXComp(), -velocity.getYComp());
			}
				
		}
	}
	
	public void bounceOff(SpaceObject spaceObject) throws IllegalArgumentException
	{
		if(spaceObject == null)
			throw new IllegalArgumentException();
		
		Vector dv = Vector.subtraction(this.getVelocity(), spaceObject.getVelocity());
		Vector dr = Vector.subtraction(this.getPosition(), spaceObject.getPosition());
		double dvdr = Vector.dotProduct(dv, dr);
		double sigma = Vector.sumOfComponents(this.getRadius(),spaceObject.getRadius());
		
		double mi = this.getMass();
		double mj = spaceObject.getMass();
		
		double J = (2*mi*mj*dvdr)/(sigma*(mi+mj));
		double Jx = (J*dr.getXComp())/sigma;
		double Jy = (J*dr.getYComp())/sigma;
		
		Vector newVelocityThis = new Vector(this.getVelocity().getXComp() + (Jx/mi), this.getVelocity().getYComp() + (Jy/mi));
		Vector newVelocityOther = new Vector(spaceObject.getVelocity().getXComp() + (Jx/mj), spaceObject.getVelocity().getYComp() + (Jy/mj));
		
		this.setVelocity(newVelocityThis);
		spaceObject.setVelocity(newVelocityOther);
		
	}
}
