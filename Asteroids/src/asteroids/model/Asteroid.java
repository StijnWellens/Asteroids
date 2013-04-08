package asteroids.model;

public class Asteroid extends SpaceObject {
	
	private static final double DENSITY = 2.65*1000000000000.0;
	private static final double PI = Math.PI;
	
	public Asteroid() {
		super();
		setMass((4/3)*PI*(11*11*11)*DENSITY);
	}
	
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius) throws IllegalArgumentException
	{
		super(x, y , xVelocity, yVelocity,radius);
		setMass((4/3)*PI*(radius*radius*radius)*DENSITY);
	}
	
	public boolean canHaveAsWorld(World world){
		return (super.canHaveAsWorld(world))&& 
		(this.overlapWithWorldObject(world));	
	}
	
}
