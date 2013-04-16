package asteroids.model;

import java.util.Random;

public class Asteroid extends SpaceObject {
	
	private static final double DENSITY = 2.65*1000000000000.0;
	private static final double PI = Math.PI;
	
	private Random random;
	
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
	
	public Asteroid(double x, double y, double xVelocity, double yVelocity,
			double radius, Random random) throws IllegalArgumentException
	{
		super(x, y , xVelocity, yVelocity,radius);
		setRandom(random);
		setMass((4/3)*PI*(radius*radius*radius)*DENSITY);
	}
	
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
	
	public boolean canHaveAsWorld(World world){
		return (super.canHaveAsWorld(world))&& 
				(!this.overlapWithWorldObject(world));	
	}
	
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
			child1.flyIntoWorld(world);
			child2.flyIntoWorld(world);
		}
		
	}
	
	
}
