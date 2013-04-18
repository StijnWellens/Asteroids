package asteroids.model;


/**
 * 
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 */
public class Bullet extends SpaceObject {

	private static final double DENSITY = 7.8*1000000000000.0;
	private static final double PI = Math.PI;
	private final Ship ship;
	private static final double RADIUS = 3;
	private static final double INITIAL_SPEED = 250;
		
	public Bullet(double x, double y, double xVelocity, double yVelocity)
	{
		super(x, y, xVelocity, yVelocity, RADIUS);
		this.ship = null;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
	}
	
	public Bullet(Ship ship)
	{
		super(ship.getX()+(ship.getRadius()+RADIUS)*Math.cos(ship.getDirection()), ship.getY()+(ship.getRadius()+RADIUS)*Math.sin(ship.getDirection()) , 
				INITIAL_SPEED*Math.cos(ship.getDirection()), INITIAL_SPEED*Math.sin(ship.getDirection()),RADIUS);
		this.ship = ship;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	
}
