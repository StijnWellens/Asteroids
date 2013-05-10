package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a bullet with a specified position and velocity.
 * 
 * @invar 	The x position of the bullet must always be a valid x position. 
 * 			| isValidX(getX())
 * @invar 	The y position of the bullet must always be a valid y position. 
 * 			| isValidY(getY())
 * @invar 	The maximum velocity of the bullet must always be a valid maximum
 *        	velocity. 
 *        	| isValidMaxVelocity(getMaxVelocity())
 * @invar 	The velocity of the bullet must always be a valid velocity. 
 * 			| isValidVelocity(getXVelocity(), getYVelocity())
 * @invar 	The lowerBoundRadius of the bullet must always be a valid.
 *        	lowerBoundRadius. 
 *        	| isValidLowerBoundRadius(getLowerBoundRadius())
 * @invar 	The radius of the bullet must always be a valid radius. 
 * 			| isValidRadius(getRadius())
 * @invar	The mass of the bullet must always be a valid mass.
 * 			| isValidMass(getMass())
 * @invar	This bullet must always have a proper world.
 * 			| hasProperWorld()
 * @invar	This bullet must always hava a proper source.
 * 			| hasProperSource()
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 * 
 */
public class Bullet extends SpaceObject {

	private static final double DENSITY = 7.8E12;  //in kg/km³
	private static final double PI = Math.PI;
	private final Ship source;
	private static final double RADIUS = 3; // in km
	private static final double INITIAL_SPEED = 250; // in km/s
	
	/**
	 * Initializes this new default bullet.
	 * 
	 * @effect	This new bullet is initialized as a SpaceObject with 10 and 10 as x and y position components,
	 * 			0 and 0 as the x and y velocity components and the RADIUS of all bullets as its radius.
	 * 			| super(x, y, xVelocity, yVelocity, Bullet.RADIUS)
	 * @effect	The mass of this new Bullet will be set to (4PI/3)*(RADIUS)³*DENSITY.
	 * 			| setMass((4PI/3)*(RADIUS)³*DENSITY)
	 * @post	The source of this new bullet will be set to null.
	 * 			| (new this).getSource() == null
	 */
	public Bullet() throws IllegalArgumentException
	{
		super(10, 10, 0, 0, RADIUS);
		this.source = null;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
	}
	
	/**
	 * Initializes this new bullet with the given x and y position components and the given x and y velocity components.
	 * 
	 * @param 	x
	 *        	The initial x position for this new Bullet.
	 * @param 	y
	 *        	The initial y position for this new Bullet.
	 * @param 	xVelocity
	 *        	The initial x velocity component for this new Bullet.
	 * @param 	yVelocity
	 *        	The initial y velocity component for this new Bullet.
	 * @effect	This new bullet is initialized as a SpaceObject with the given x and y position components,
	 * 			the given x and y velocity components and the RADIUS of all bullets as its radius.
	 * 			| super(x, y, xVelocity, yVelocity, Bullet.RADIUS)
	 * @effect	The mass of this new Bullet will be set to (4PI/3)*(RADIUS)³*DENSITY.
	 * 			| setMass((4PI/3)*(RADIUS)³*DENSITY)
	 * @post	The source of this new bullet will be set to null.
	 * 			| (new this).getSource() == null
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity) throws IllegalArgumentException
	{
		super(x, y, xVelocity, yVelocity, RADIUS);
		this.source = null;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
	}
	
	/**
	 * Initializes this new bullet based on the given source, a ship.
	 * 
	 * @param 	ship
	 *        	The source of this new Bullet.
	 * @effect	This new bullet is initialized as a SpaceObject 
	 * 			with the x and y position components right next to its source at the direction it is facing,
	 * 			with the x and y velocity components as components of the initial velocity vector pointing the direction the ship is facing
	 * 			and with the RADIUS of all bullets as its radius.
	 * 			| super(ship.getX()+(ship.getRadius()+RADIUS)*Math.cos(ship.getDirection()), ship.getY()+(ship.getRadius()+RADIUS)*Math.sin(ship.getDirection()), 
	 *			|			INITIAL_SPEED*Math.cos(ship.getDirection()), INITIAL_SPEED*Math.sin(ship.getDirection()),RADIUS);
	 * @effect	The mass of this new Bullet will be set to (4PI/3)*(RADIUS)³*DENSITY.
	 * 			| setMass((4PI/3)*(RADIUS)³*DENSITY)
	 * @post	The source of this new bullet will be set to the given ship.
	 * 			| (new this).getShip() == ship
	 */
	public Bullet(Ship ship) throws IllegalArgumentException
	{
		super(ship.getX()+(ship.getRadius()+RADIUS)*Math.cos(ship.getDirection()), ship.getY()+(ship.getRadius()+RADIUS)*Math.sin(ship.getDirection()) , 
				INITIAL_SPEED*Math.cos(ship.getDirection()), INITIAL_SPEED*Math.sin(ship.getDirection()),RADIUS);
		if(!canHaveAsSource(ship))
			throw new IllegalArgumentException();
		this.source = ship;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
	}
	
	/**
	 * Returns the ship which is the source of this bullet.
	 * 
	 * @return	The source of the bullet
	 * 			| this.ship
	 */
	@Basic
	@Immutable
	public final Ship getSource(){
		return this.source;
	}
	
	/**
	 * Check whether this bullet can have a given ship as its source.
	 * 
	 * @param 	ship
	 * 			The ship to check.
	 * @return	True if the ship is null.
	 * 			| result == true
	 * @return	True if the given ship can have this bullet as active bullet.
	 * 			|
	 */
	public boolean canHaveAsSource(Ship ship) {
		if(ship == null)
			return true;
		return ship.canHaveAsActiveBullet(this);
	}
	
	/**
	 * Check whether this Bullet has a proper source.
	 * 
	 * @return	False if this bullet can't have its source as its source.
	 * 			| if(!canHaveAsSource(this.getSource()))
	 * 			|	then result == false 
	 * @return	True if the state of this bullet is not ACTIVE.
	 * 			| if(this.getState() != State.ACTIVE)
	 * 			|	then result == true
	 * @return	True if the source of this bullet is null.
	 * 			| if(source == null)
	 * 			|	then result == true
	 * @return	True if the source of this bullet contains this bullet as an active bullet.
	 * 			| if(this.getSource().containsActiveBullet(this))
	 * 			|	then result == true
	 */
	public boolean hasProperSource() {
		if(!canHaveAsSource(this.getSource()))
			return false;
		if(this.getState() != State.ACTIVE)
			return true;
		if(source == null)
			return true;
		if(this.getSource().containsActiveBullet(this))
			return true;
		return false;
	}

	/**
	 * Checks whether this Bullet kills the given other SpaceObject.
	 * 
	 * @param	other
	 * 			The SpaceObject to be checked.
	 * @return	False when the other SpaceObject is null.
	 * 			| if(other == null)
	 * 			|	then result == false
	 * @return	True when the other SpaceObject is a bullet.
	 * 			| if(other instanceof Bullet)
	 * 			|	then result == true
	 * @return	True when the other SpaceObject is an asteroid.
	 * 			| if(other instanceof Asteroid)
	 * 			|	then result == true
	 * @return	True when the other SpaceObject is a ship 
	 * 				and this bullet his source isn't the given ship.
	 * 			| if(other instanceof Ship)
	 * 			|	then result == (this.getShip() != ((Ship)other))
	 * @return	Returns true if the other object will be killed by this.
	 * 			| result == other.willBeKilledByOther(this)
	 */
	@Override
	public boolean killsOther(SpaceObject other) {
		if(other == null)
			return false;
		if(other instanceof Bullet)
			return true;
		if(other instanceof Asteroid)
			return true;
		if(other instanceof Ship)
			return (this.getSource() != ((Ship)other));
		return other.willBeKilledByOther(this);
	}

	/**
	 * Checks whether this Bullet will be killed by the given other SpaceObject.
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
		if(other instanceof Bullet)
			return true;
		if(other instanceof Asteroid)
			return true;
		if(other instanceof Ship)
			return (this.getSource() != ((Ship)other));
		return other.killsOther(this);
	}
	
	@Override
	public void die()
	{
		super.die();
		this.getSource().removeActiveBullet(this);		
	}
	
	@Override
	public void flyIntoWorld(World world)
	{
		if(this.getSource().getActiveBulletsInWorld(world).size() < 3)
		{
			super.flyIntoWorld(world);
			this.getSource().addActiveBullet(this);
		}		
	}
	
}
