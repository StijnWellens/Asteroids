package asteroids.model;

import java.util.Random;

import asteroids.Util;

/**
 * A class representing an asteroid with a specified position, velocity and radius.
 * 
 * @invar 	The x position of the asteroid must always be a valid x position. 
 * 			| isValidX(getX())
 * @invar 	The y position of the asteroid must always be a valid y position. 
 * 			| isValidY(getY())
 * @invar 	The maximum velocity of the asteroid must always be a valid maximum
 *        	velocity. 
 *        	| isValidMaxVelocity(getMaxVelocity())
 * @invar 	The velocity of the asteroid must always be a valid velocity. 
 * 			| isValidVelocity(getXVelocity(), getYVelocity())
 * @invar 	The lowerBoundRadius of the asteroid must always be a valid.
 *        	lowerBoundRadius. 
 *        	| isValidLowerBoundRadius(getLowerBoundRadius())
 * @invar 	The radius of the asteroid must always be a valid radius. 
 * 			| isValidRadius(getRadius())
 * @invar	The mass of the asteroid must always be a valid mass.
 * 			| isValidMass(getMass())
 * @invar	The randomobject of this asteroid must always be a valid random.
 * 			| isValidRandom(getRandom())
 * @invar	This asteroid must always have a proper world.
 * 			| hasProperWorld()
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 * 
 */
public class Asteroid extends SpaceObject {
	
	private static final double DENSITY = 2.65E12; // in kg/km³
	private static final double PI = Math.PI;
	

	/**
	 * Initializes this new default asteroid.
	 * 
	 * @effect	This asteroid is initialized as a default SpaceObject.
	 * 			| super()
	 * @effect	The RandomGenerator of this asteroid is set to a new default RandomGenerator.
	 * 			| setRandom(new Random())
	 * @effect	The mass of this new Asteroid will be set to (4PI/3)*(this.getRadius())³*DENSITY.
	 * 			| setMass((4PI/3)*(this.getRadius())³*DENSITY)
	 */
	public Asteroid() throws IllegalArgumentException
	{
		super();
		setRandom(new Random());
		double r = this.getRadius();
		setMass((4/3)*PI*(r*r*r)*DENSITY);
	}
	
	/**
	 * Initializes this new Asteroid with the given x and y position components, the given x and y velocity components
	 * and the given radius.
	 * 
	 * @param 	x
	 *        	The initial x position for this new Asteroid.
	 * @param 	y
	 *        	The initial y position for this new Asteroid.
	 * @param 	xVelocity
	 *        	The initial x velocity component for this new Asteroid.
	 * @param 	yVelocity
	 *        	The initial y velocity component for this new Asteroid.
	 * @param 	radius
	 *        	The radius of this new Asteroid.
	 * @effect	This new Asteroid is initialized as a SpaceObject with the given x and y position components,
	 * 			the given x and y velocity components and the given radius.
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @effect	The RandomGenerator of this asteroid is set to a new default RandomGenerator.
	 * 			| setRandom(new Random())
	 * @effect	The mass of this new Asteroid will be set to (4PI/3)*(radius)³*DENSITY.
	 * 			| setMass((4PI/3)*(radius)³*DENSITY)
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException
	{
		super(x, y , xVelocity, yVelocity,radius);
		setRandom(new Random());
		setMass((4/3)*PI*(radius*radius*radius)*DENSITY);
	}
	
	/**
	 * Initializes this new Asteroid with the given x and y position components, the given x and y velocity components,
	 * the given radius and the given RandomGenerator.
	 * 
	 * @param 	x
	 *        	The initial x position for this new Asteroid.
	 * @param 	y
	 *        	The initial y position for this new Asteroid.
	 * @param 	xVelocity
	 *        	The initial x velocity component for this new Asteroid.
	 * @param 	yVelocity
	 *        	The initial y velocity component for this new Asteroid.
	 * @param 	radius
	 *        	The radius of this new Asteroid.
	 * @param 	random
	 *        	The RandomGenerator of this new Asteroid.
	 * @effect	This new Asteroid is initialized as a SpaceObject with the given x and y position components,
	 * 			the given x and y velocity components and the given radius.
	 * 			| super(x, y, xVelocity, yVelocity, radius)
	 * @effect	The RandomGenerator of this asteroid is set to the given RandomGenerator.
	 * 			| setRandom(random)
	 * @effect	The mass of this new Asteroid will be set to (4PI/3)*(radius)³*DENSITY.
	 * 			| setMass((4PI/3)*(radius)³*DENSITY)
	 */
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius, Random random) throws IllegalArgumentException
	{
		super(x, y , xVelocity, yVelocity,radius);
		setRandom(random);
		setMass((4/3)*PI*(radius*radius*radius)*DENSITY);
	}
	
	// Random
	
	private Random random;
	
	/**
	 * Returns the randomGenerator of this Asteroid.
	 * 
	 * @return	the randomGenerator of this Asteroid
	 * 			| this.random
	 */
	public Random getRandom()
	{
		return this.random;
	}
	
	/**
	 * Checks whether a given RandomGenerator is valid.
	 * 
	 * @param 	random
	 * 			the randomGenerator to check
	 * @return	True if and only if random isn't null.
	 * 			| result == (random!=null)
	 */
	public boolean isValidRandom(Random random)
	{
		return (random != null);
	}
	
	/**
	 * Sets the randomGenerator of this asteroid to the given randomGenerator.
	 * 
	 * @param 	random
	 * 			the new randomGenerator for this Asteroid
	 * @post	The new randomGenerator of this Asteroid will be the given randomGenerator.
	 * 			| (new this).getRandom() == random
	 * @throws 	IllegalArgumentException
	 * 			Throws an exception when the given randomGenerator isn't valid.
	 * 			| !isValidRandom(random)
	 */
	public void setRandom(Random random)throws IllegalArgumentException
	{
		if(!isValidRandom(random))
			throw new IllegalArgumentException();
		this.random = random;
	}
	
	/**
	 * Checks whether this Asteroid can have a given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	Returns true if and only if this Asteroid as a SpaceObject can have this world
	 * 			and if this Asteroid doesn't overlap with another object in this world.
	 * 			| result ==  (super.canHaveAsWorld(world)) 
	 * 			|				&& (!this.overlapWithWorldObject(world))
	 * 
	 */
	@Override
	public boolean canHaveAsWorld(World world){
		return (super.canHaveAsWorld(world))&& 
				(!this.overlapWithWorldObject(world));	
	}
	

	/**
	 * Removes this Asteroid from the given world.
	 * 
	 * @param 	world
	 * 			the world where the Asteroid has to be removed
	 * @effect  Removes this SpaceObject from the given world.
	 * 			| world.removeSpaceObject(this)
	 * @effect	Sets the world of this SpaceObject to null.
	 * 			| setWorld(null)
	 * @effect	Sets the state of this SpaceObject to TERMINATED.
	 * 			| setState(State.TERMINATED)
	 * @post	If the radius of this asteroid is bigger than or equal to 30 and the randomGenerator isn't null,
	 * 			then two new child asteroids will be created based on the parent asteroid.
	 * 			| if(this.getRadius()>=30 && getRandom() != null) then
	 * 			|	child1 = new Asteroid(..) 
	 * 			|	child2 = new Asteroid(..)
	 * 			|		with	SpaceObject.getDistanceBetween(child1, child2) == this.getRadius()
	 * 			|				for each child 1..2:
	 * 			|				(	SpaceObject.getDistanceBetween(this, child) == this.getRadius()/2
	 * 			|					child.getVelocity().getModulus() == this.getVelocity().getModulus() * 1.5
	 * 			|					child.getRadius() == (this.getRadius())/2
	 * 			|					child.getRandom() == (this.getRandom())	
	 * 			|				)
	 * @throws 	IllegalStateException
	 * 			Throws illegal state exception when this SpaceObject is not ACTIVE or when its world is null.
	 * 			| this.getState() != State.ACTIVE || this.getWorld() == null
	 * @throws 	IllegalArgumentException
	 * 			Throws illegal argument exception when the given world is not equal to the SpaceObject's world.
	 * 			| world != this.getWorld()
	 */
	@Override
	public void die(World world) throws IllegalStateException, IllegalArgumentException{
		super.die(world);
		
		if(this.getRadius()>=30 && getRandom() != null){
			double randomDouble = getRandom().nextDouble();
			double speedParent = this.getVelocity().getModulus();
			double newVelocity = 1.5*speedParent;
			double newXVelocity = newVelocity*Math.sqrt(randomDouble);
			double newYVelocity = newVelocity*Math.sqrt(1-randomDouble);
			
			double cos;
			double sin;
			
			if(Util.fuzzyEquals(newVelocity,0))
			{
				cos = 1;
				sin = 0;
			}
			else
			{
				cos = newXVelocity/newVelocity;
				sin = newYVelocity/newVelocity;
			}
			
			SpaceObject child1 = new Asteroid(this.getX()+(this.getRadius()/2)*cos,this.getY()+(this.getRadius()/2)*sin,
					newXVelocity,newYVelocity,this.getRadius()/2,this.getRandom());
			SpaceObject child2 = new Asteroid(this.getX()-(this.getRadius()/2)*cos,this.getY()-(this.getRadius()/2)*sin,
					-1*newXVelocity,-1*newYVelocity,this.getRadius()/2,this.getRandom());
			try{
				child1.flyIntoWorld(world);
				child2.flyIntoWorld(world);
			}
			catch(IllegalArgumentException iae){
				
			}
			
		}
		
	}

	/**
	 * Checks whether this Asteroid kills the given other SpaceObject.
	 * 
	 * @param	other
	 * 			The SpaceObject to be checked.
	 * @return	False when the other SpaceObject is null.
	 * 			| if(other == null)
	 * 			|	then result == false
	 * @return	False when the other SpaceObject is an asteroid.
	 * 			| if(other instanceof Asteroid)
	 * 			|	then result == false
	 * @return	True when the other SpaceObject is a ship.
	 * 			| if(other instanceof Ship)
	 * 			|	then result == true
	 * @return	Returns true if the other object will be killed by this.
	 * 			| result == other.willBeKilledByOther(this)
	 */
	@Override
	public boolean killsOther(SpaceObject other) {
		if(other == null)
			return false;
		if(other instanceof Asteroid)
			return false;
		if(other instanceof Ship)
			return true;
		return other.willBeKilledByOther(this);
	}

	/**
	 * Checks whether this Asteroid will be killed by the given other SpaceObject.
	 * 
	 * @param	other
	 * 			The SpaceObject to be checked.
	 * @return	False when the other SpaceObject is null.
	 * 			| if(other == null)
	 * 			|	then result == false
	 * @return	False when the other SpaceObject is an asteroid.
	 * 			| if(other instanceof Asteroid)
	 * 			|	then result == false
	 * @return	False when the other SpaceObject is a ship.
	 * 			| if(other instanceof Ship)
	 * 			|	then result == false
	 * @return	Returns true if the other object kills this.
	 * 			| result == other.killsOther(this)
	 */
	@Override
	public boolean willBeKilledByOther(SpaceObject other) {
		if(other == null)
			return false;
		if(other instanceof Asteroid)
			return false;
		if(other instanceof Ship)
			return false;
		return other.killsOther(this);
	}
	
	
}
