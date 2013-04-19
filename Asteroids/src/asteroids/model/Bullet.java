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
	private final Ship ship;
	private static final double RADIUS = 3; // in km
	private static final double INITIAL_SPEED = 250; // in km/s
	
	
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
	 * 			| (new this).getShip() == null
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity) throws IllegalArgumentException
	{
		super(x, y, xVelocity, yVelocity, RADIUS);
		this.ship = null;
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
		this.ship = ship;
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
	public final Ship getShip(){
		return this.ship;
	}
	
	
}
