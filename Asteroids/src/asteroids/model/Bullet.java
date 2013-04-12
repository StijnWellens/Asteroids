package asteroids.model;

public class Bullet extends SpaceObject {

	private static final double DENSITY = 7.8*1000000000000.0;
	private static final double PI = Math.PI;
	private final Ship ship;
	private static final double RADIUS = 3;
	private static final double INITIAL_SPEED = 250;
	private int borderCollisions;
	
	public Bullet(double x, double y, double xVelocity, double yVelocity)
	{
		super(x, y, xVelocity, yVelocity, RADIUS);
		this.ship = null;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
		this.borderCollisions = 0;
	}
	
	public Bullet(Ship ship)
	{
		super(ship.getX()+(ship.getRadius()+RADIUS)*Math.cos(ship.getDirection()), ship.getY()+(ship.getRadius()+RADIUS)*Math.sin(ship.getDirection()) , 
				INITIAL_SPEED*Math.cos(ship.getDirection()), INITIAL_SPEED*Math.sin(ship.getDirection()),RADIUS);
		this.ship = ship;
		setMass((4/3)*PI*(RADIUS*RADIUS*RADIUS)*DENSITY);
		this.borderCollisions = 0;
	}
	
	public Ship getShip(){
		return this.ship;
	}
	
	public void collision(SpaceObject spaceObject) throws IllegalArgumentException
	{
		if(spaceObject == null)
			throw new IllegalArgumentException();
		spaceObject.die();
		this.die();
		
	}
	
	public void collisionWithBorder()
	{
		super.collisionWithBorder();
		this.borderCollisions ++;
		
		if(borderCollisions >= 2 )
			this.die();
			
	}
	
}
