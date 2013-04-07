package asteroids.model;

public class Bullet extends SpaceObject {

	private static final double DENSITY = 7.8*1000000000000.0;
	private static final double PI = Math.PI;
	
	public Bullet() {
		super();
		setMass((4/3)*PI*(11*11*11)*DENSITY);
	}
	
	public Bullet(double x, double y, double xVelocity, double yVelocity,
			double radius)
	{
		super(x, y , xVelocity, yVelocity,radius);
		setMass((4/3)*PI*(radius*radius*radius)*DENSITY);
	}
	
	
}
