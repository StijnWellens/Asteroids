package asteroids.model;

import java.util.Random;

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
		
	public Random getRandom()
	{
		return this.random;
	}
	
	public boolean isValidRandom(Random random)
	{
		return (random != null);
	}
	
	public void setRandom(Random random)throws IllegalArgumentException
	{
		if(!isValidRandom(random))
			throw new IllegalArgumentException();
		this.random = random;
	}
	
	@Override
	public boolean canHaveAsWorld(World world){
		return (super.canHaveAsWorld(world))&& 
				(!this.overlapWithWorldObject(world));	
	}
	
	@Override
	public void die(World world){
		super.die(world);
		
		if(this.getRadius()>=30 && getRandom() != null){
			double randomDouble = getRandom().nextDouble();
			double speedParent = this.getVelocity().getModulus();
			double newVelocity = 1.5*speedParent;
			double newXVelocity = newVelocity*Math.sqrt(randomDouble);
			double newYVelocity = newVelocity*Math.sqrt(1-randomDouble);
			double cos = newXVelocity/newVelocity;
			double sin = newYVelocity/newVelocity;
			SpaceObject child1 = new Asteroid(this.getX()+(this.getRadius()/2)*cos,this.getY()+(this.getRadius()/2)*sin,
					newXVelocity,newYVelocity,this.getRadius()/2,this.getRandom());
			SpaceObject child2 = new Asteroid(this.getX()-(this.getRadius()/2)*cos,this.getY()-(this.getRadius()/2)*sin,
					-newXVelocity,-newYVelocity,this.getRadius()/2,this.getRandom());
			try{
				child1.flyIntoWorld(world);
				child2.flyIntoWorld(world);
			}
			catch(IllegalArgumentException iae){
				
			}
			
		}
		
	}
	
	
}
