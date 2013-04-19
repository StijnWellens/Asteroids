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
 * @invar	The mass of the spaceObject must always be a valid mass.
 * 			| isValidMass(getMass())
 * @invar	Each spaceObject must have a proper world.
 * 			| hasProperWorld()
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
	 *         component, 11 as its radius. 
	 *         The state of the spaceObject is initialized to CREATED.
	 *         | setMaxVelocity(300000); 
	 *         | setPosition(0,0); 
	 *         | setVelocity(0,0); 
	 *         | setState(State.CREATED);
	 * @post	The new radius of the spaceObject is equal to 11. 
	 * 			| (new this).getRadius() == 11
	 */
	@Model
	protected SpaceObject() {
		setMaxVelocity(300000);
		
		setPosition(0,0);
		setVelocity(0, 0);
		this.radius = 11;
		setState(State.CREATED);

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
	 * @effect This new spaceObject is initialized with the given x as its initial x
	 *         coordinate, the given y as its initial y coordinate, 300000 as
	 *         its maximum velocity, the given xVelocity as its x velocity
	 *         component, the given YVelocity as its y velocity component,
	 *         the given radius as its radius.  
	 *         The state of the spaceObject is initialized to CREATED.
	 *         | setMaxVelocity(300000); 
	 *         | setPosition(x,y); 
	 *         | setVelocity(xVelocity, yVelocity); 
	 *         | setState(State.CREATED);
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
				
		setPosition(x,y);
		setVelocity(xVelocity, yVelocity);
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		setState(State.CREATED);
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
	 * 
	 * @return 	position
	 * 			the position vector of the spaceObject
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
	public double getMaxVelocity() {
		return this.maxV;
	}

	/**
	 * Checks whether the given maximum velocity is valid.
	 * 
	 * @param maxV
	 *        The given maximum velocity.
	 * @return 	False if the double maxV is not a number or is infinite. 
	 * 			| if(Double.isNaN(maxV) == true || Double.isInfinite(maxV)  == true) 
	 * 			| then result == false
	 * @return True if and only if the given maximum velocity is positive or 0
	 *         and not exceeding the speed of light. 
	 *         | if((maxV >= 0) && (maxV <= LIGHTSPEED) 
	 *         | then result == true
	 */
	public boolean isValidMaxVelocity(double maxV) {
		if (Double.isNaN(maxV) || Double.isInfinite(maxV)) {
			return false;
		} else {
			return ((maxV >= 0) && (maxV <= LIGHTSPEED));
		}
	}

	/**
	 * 
	 * @param maxV
	 *        The given maximum velocity of the spaceObject.
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
	 * @post 	True if and only if the velocity component is a number and not infinite. 
	 * 			| result == (!Double.isNaN(comp) && !Double.isInfinite(comp))
	 */
	public boolean isValidVelocityComp(double comp) {
		return (!Double.isNaN(comp) && !Double.isInfinite(comp));
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
	 * @post If the given velocity is valid, then the new velocity
	 *       components will be the given components. 
	 *       | if(isValidVelocity(vx,vy))
	 *       |		then ((new this).getXVelocity() == vx) && ((new this).getYVelocity() == vy)
	 * @post	If the given velocity isn't valid but the components are, 
	 * 			then the new velocity components will be equally scaled so the new velocity won't exceed the maximum velocity.
	 * 			| if(!isValidVelocity(vx,vy) && (isValidVelocityComp(vx) && isValidVelocityComp(vy)))
	 * 			|		then ((new this).getXVelocity() == vx * scaleFactor) && ((new this).getYVelocity() == vy * scaleFactor) 
	 * 						&& ((new this).getVelocity().getModulus() == getMaxVelocity())
	 */
	public void setVelocity(double vx, double vy) {
		if (isValidVelocity(vx, vy)) {
			this.velocity.setComp(vx, vy);
		}
		else {
			if(isValidVelocityComp(vx) && isValidVelocityComp(vy))
			{
				double tempAmount = (this.getMaxVelocity())	/ Math.sqrt(vx * vx + vy * vy);
				double tempVx = vx * tempAmount;
				double tempVy = vy * tempAmount;
	
				this.velocity.setComp(tempVx, tempVy);
			}
		}
	}
	
	/**
	 * Set the velocity of the spaceObject.
	 * 
	 * @param velocity
	 *        The given velocity vector.
	 * @effect 	Set the velocity to the given velocity vector with the setVelocity with velocity components method.
	 *       	| setVelocity(velocity.getXComp(), velocity.getYComp())
	 */
	public void setVelocity(Vector velocity) {
		this.setVelocity(velocity.getXComp(), velocity.getYComp());
	}
	
	// Radius

	private final double	radius; // in km
	private static double	lowerBoundRadius = 0;

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
	 * 			| 	((!Double.isNaN(radius)) 
	 * 			| 		&& (!Double.isInfinite(radius))
	 * 			| 		&& (radius > lowerBoundRadius) )
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
	 * 			|	result == 
	 * 			| 		(duration >= 0 && Double.isNaN(duration) && !Double.isInfinite(duration))
	 */
	public boolean isValidDuration(double duration) {
		return ((Util.fuzzyEquals(duration,0) || !Util.fuzzyLessThanOrEqualTo(duration,0)) && (!Double.isNaN(duration)) && (!Double.isInfinite(duration)));
	}

	/**
	 * Changes the position of the spaceObject based on the current position, current
	 * orientation, velocity and a given time duration.
	 * 
	 * @param	duration
	 *         	How long the spaceObject needs to move.
	 * @effect 	The new x-coordinate of this spaceObject is equal to the old x-coordinate
	 *       	of this spaceObject incremented with the product of the x-velocity of the
	 *       	spaceObject with the given time duration.  
	 *       	|	(new this).getX() == this.getX() + this.getXVelocity()*duration
	 * @effect 	The new y-coordinate of this spaceObject is equal to the old y-coordinate
	 *       	of this spaceObject incremented with the product of the y-velocity of the
	 *       	spaceObject with the given time duration. 
	 *       	|	(new this).getY() == this.getY() + this.getYVelocity()*duration
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
	
	private double mass; // in kg
	
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
	
	/**
	 * Calculate the distance between two spaceObjects.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other spaceObject.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other spaceObject.
	 * @return	The distance between the two spaceObjects.
	 * 			| Util.fuzzyEquals( result, Vector.getModulus(( spaceObject1.getX() - spaceObject2.getX() ), ( spaceObject1.getY() - spaceObject2.getY() ))
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
	 * Return a boolean reflecting whether the given spaceObject overlaps with the other given spaceObject.	
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other spaceObject.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other spaceObject.
	 * @return	True if and only if the given spaceObject overlaps with the current spaceObject.
	 * 			| result == (this.getDistanceBetween(spaceObject1,spaceObject2) < 0)
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 */
	public static boolean overlap(SpaceObject spaceObject1, SpaceObject spaceObject2) throws IllegalArgumentException
	{
		if((spaceObject1 == null) || (spaceObject2 == null))
			throw new IllegalArgumentException();
		return (getDistanceBetween(spaceObject1,spaceObject2) < 0);
	}	
	
	/**
	 * Return a boolean reflecting whether this spaceObject overlaps with a spaceObject in a given world.
	 * 
	 * @param 	world
	 * 			The world to check. 
	 * @return 	True if and only if this object overlaps with one of the spaceObjects in the given world excluding the object itself.
	 * 			| for each spaceObject in world.getSpaceObjects()
	 * 			|	if(SpaceObject.overlap(spaceObject,this))
	 * 			|		then result == true
	 * @return	False if the given world is null.
	 * 			| 	if(world == null)
	 * 			|		then result == false
	 */
	public boolean overlapWithWorldObject(World world){
		if(world == null)
			return false;
		for(SpaceObject spaceObject: world.getSpaceObjects()){
			if(spaceObject == this){}
			else if(Bullet.class.isInstance(spaceObject)){}
			else if(SpaceObject.overlap(spaceObject, this))
				return true;
		}
		return false;
	}
	
	// State
	
	/**
	 * A enumeration class to describe the state of a SpaceObject.
	 * The possibilities are CREATED, ACTIVE and TERMINATED.
	 * 
	 * @author Julie Wouters & Stijn Wellens
	 *
	 */
	@Value
	protected enum State {
		TERMINATED, CREATED, ACTIVE
	};

	private State state;

	/**
	 * Returns the state of this SpaceObject.
	 * 
	 * @return 	state
	 * 			the state of the spaceObject
	 */
	public State getState() {
		return this.state;
	}
	
	/**
	 * Checks whether the given state is valid or not.
	 * 
	 * @param 	state
	 * 			The state to check.
	 * @return	True if and only if the given state is not null.
	 * 			| result == (state != null)
	 */
	public boolean isValidState(State state) {
		if(state == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Set the state of this SpaceObject to the given state. 
	 * 
	 * @param 	state
	 * 			The state to set.
	 * @post	The new state will be the given state.
	 * 			| (new this).getState() == state
	 * @throws 	IllegalArgumentException
	 * 			Throws an exception when the given state is not valid.
	 * 			| !isValidState(state)
	 */
	public void setState(State state) throws IllegalArgumentException {
		if(!isValidState(state))
			throw new IllegalArgumentException();
		this.state = state;
	}

	// World
	
	private World world;
	
	/**
	 * Returns the world of this SpaceObject.
	 * 
	 * @return	world
	 * 			the world of this SpaceObject
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Checks whether this SpaceObject can have a given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	Returns true if and only if this SpaceObject lies between the borders of the given world.
	 * 			| if( 0 <= [this.getXPosition() - this.getRadius(), this.getXPosition() + this.getRadius()] <= world.getWidth()
	 * 			|			&&   0 <= [this.getYPosition() - this.getRadius(), this.getYPosition() + this.getRadius()] <= world.getHeight() )
	 * 			|	then result == true
	 * @return	Returns true if the world is null.
	 * 			| if(world == null)
	 * 			| 	then result == true
	 * 
	 */
	@Raw
	public boolean canHaveAsWorld(World world){
		if(world == null)
			return true;
		if(!Util.fuzzyLessThanOrEqualTo(this.getX()+this.getRadius(),world.getWidth()))
			return false;
		if(Util.fuzzyLessThanOrEqualTo(this.getX()-this.getRadius(),0) && !Util.fuzzyEquals(this.getX()-this.getRadius(), 0))
			return false;
		if(!Util.fuzzyLessThanOrEqualTo(this.getY()+this.getRadius(),world.getHeight()))
			return false;
		if(Util.fuzzyLessThanOrEqualTo(this.getY()-this.getRadius(),0) && !Util.fuzzyEquals(this.getY()-this.getRadius(), 0))
			return false;
		return true;
	}
	
	/**
	 * Sets the world of this SpaceObject.
	 * 
	 * @param 	world
	 * 			The world to set.
	 * @post	The new world of this SpaceObject will be the given world.
	 * 			| (new this).getWorld() == world	 
	 * @throws 	IllegalArgumentException
	 * 			Throws an exception when this object can't have the given world as its world.
	 * 			| !canHaveAsWorld(world)
	 */
	@Raw
	protected void setWorld(World world)throws IllegalArgumentException{
		if(!this.canHaveAsWorld(world))
			throw new IllegalArgumentException();
		this.world = world;
		
	}
		
	/**
	 * Checks whether this SpaceObject has a proper world.
	 * 
	 * @return	True if and only if this SpaceObject can have its world as world, 
	 * 			if this object has no world or if this object has a world that contains this object.
	 * 			| result == canHaveAsWorld(getWorld()) 
	 * 			|		&& ( (getWorld() == null) || (getWorld().containsSpaceObject(this)) )
	 */
	public boolean hasProperWorld() {
		return canHaveAsWorld(getWorld()) && ( (getWorld() == null) || (getWorld().containsSpaceObject(this)) );
		
	}
	
	/**
	 * Puts this SpaceObject into the given world.
	 *  
	 * @param 	world
	 * 			The world where this SpaceObject has to get in.
	 * @effect	The given world will be set as the world of this SpaceObject.
	 * 			| setWorld(world)
	 * @effect	This SpaceObject will be added to the SpaceObjects of the given world.
	 * 			| world.addSpaceObject(this)
	 * @effect	The state of this SpaceObject will be set to ACTIVE.
	 * 			| setState(State.ACTIVE)	
	 * @throws 	IllegalStateException
	 * 			Throws illegal state exception when this object is already in a world 
	 * 			or when its state is not CREATED.
	 * 			| (this.getState() != State.CREATED) || (this.getWorld()!= null)
	 * @throws 	IllegalArgumentException
	 * 			Throws illegal argument exception when the given world is null 
	 * 			or this object can't have the given world as world.
	 * 			| world == null || !this.canHaveAsWorld(world)
	 */
	public void flyIntoWorld(World world) throws IllegalStateException, IllegalArgumentException
	{
		if((this.getState() != State.CREATED) || (this.getWorld()!= null))
			throw new IllegalStateException();
		if(world == null || !this.canHaveAsWorld(world) )
			throw new IllegalArgumentException();
		
		this.setWorld(world);
		world.addSpaceObject(this);
		this.setState(State.ACTIVE);
	}
	
	/**
	 * Removes this SpaceObject from the given world.
	 * 
	 * @param 	world
	 * 			the world where the SpaceObject has to be removed
	 * @effect  Removes this SpaceObject from the given world.
	 * 			| world.removeSpaceObject(this)
	 * @effect	Sets the world of this SpaceObject to null.
	 * 			| setWorld(null)
	 * @effect	Sets the state of this SpaceObject to TERMINATED.
	 * 			| setState(State.TERMINATED)
	 * @throws 	IllegalStateException
	 * 			Throws illegal state exception when this SpaceObject is not ACTIVE or when its world is null.
	 * 			| this.getState() != State.ACTIVE || this.getWorld() == null
	 * @throws 	IllegalArgumentException
	 * 			Throws illegal argument exception when the given world is not equal to the SpaceObject's world.
	 * 			| world != this.getWorld()
	 */
	public void die(World world) throws IllegalStateException, IllegalArgumentException{
		if(world != this.getWorld())
			throw new IllegalArgumentException();
		if(this.getState() != State.ACTIVE || this.getWorld() == null)
			throw new IllegalStateException();
		
		world.removeSpaceObject(this);
		this.setWorld(null);
		this.setState(State.TERMINATED);
		
	}
		
}
