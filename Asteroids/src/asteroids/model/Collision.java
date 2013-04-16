package asteroids.model;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

public class Collision {
	
	private static final double	PI	= Math.PI;

	public Collision(SpaceObject so1, SpaceObject so2) throws IllegalArgumentException
	{
		setObjects(so1,so2);
	}
	
	@Basic
	public SpaceObject getObject1()
	{
		return this.spaceObject1;
	}
	
	@Basic
	public SpaceObject getObject2()
	{
		return this.spaceObject2;
	}
	
	public boolean areValidObjects(SpaceObject object1, SpaceObject object2)
	{
		if((object1 == null) || (object2 == null))
			return false;
		if(object1 == object2)
			return false;
		if((object1.getWorld() == null) || (object2.getWorld()== null))
			return false;
		if(object1.getWorld() != object2.getWorld())
			return false;
		if(Bullet.class.isInstance(object1) && ((Bullet)object1).getShip()== object2)
			return false;
		if(Bullet.class.isInstance(object2) && ((Bullet)object2).getShip()== object1)
			return false;
		return true;
	}
	
	public void setObjects(SpaceObject object1, SpaceObject object2) throws IllegalArgumentException
	{
		if(!areValidObjects(object1,object2))
			throw new IllegalArgumentException();
		this.spaceObject1 = object1;
		this.spaceObject2 = object2;
	}
	
	public boolean contains(SpaceObject object)
	{
		if(object == this.getObject1())
			return true;
		if(object == this.getObject2())
			return true;
		return false;
	}
	
	private SpaceObject spaceObject1;
	private SpaceObject spaceObject2;
	
	/**
	 * Return when two spaceObjects will collide.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other.
	 * @return	Return when two spaceObjects will collide. Positive infinity if they never collide. For the specific calculation see the code.
	 * 			| dt
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 *
	 // infinity, time could not be negative
	  * if(result == Double.POSITIVE_INFINITY) then
	  * 	(this == other) 
	 * 
	 */
	public double getTimeToCollision() 
	{
		if(getObject2() == null)
		{
			double width = getObject1().getWorld().getWidth();
			double height = getObject1().getWorld().getHeight();
			double dtx = getTimeToCollisionWithAxis(getObject1().getX(), getObject1().getXVelocity(), width);
			double dty = getTimeToCollisionWithAxis(getObject1().getY(), getObject1().getYVelocity(), height);
							
			if(dtx <= dty)
				return dtx;
			else
				return dty;
		}
		else
		{
			double dt;
			
			Vector dv = Vector.subtraction(getObject1().getVelocity(), getObject2().getVelocity());
			Vector dr = Vector.subtraction(getObject1().getPosition(), getObject2().getPosition());
			double dvdr = Vector.dotProduct(dv, dr);
			double dvdv = Vector.dotProduct(dv, dv);
			double d1 = Vector.multiplyComponents((dvdr),(dvdr)) ;
			double sigma = Vector.sumOfComponents(getObject1().getRadius(),getObject2().getRadius());
			double sigmaSquare = Vector.multiplyComponents(sigma, sigma);
			double d2 = Vector.multiplyComponents(dvdv, Vector.sumOfComponents(Vector.dotProduct(dr, dr), -sigmaSquare) );
			double d = Vector.sumOfComponents(d1, -d2);
						
			if(dvdr >= 0 || d <= 0)
			{
				dt = Double.POSITIVE_INFINITY;
			}		
			else{
				dt = -(Vector.multiplyComponents(Vector.sumOfComponents(dvdr, Math.sqrt(d)),(1d/(dvdv))));
			}
									
			return dt;
		}
			
	}
	
	/**
	 * Return the time to collision from a point of a ship with a border in one direction.
	 * 
	 * @param position
	 * @param velocity
	 * @param axisMax
	 * @return
	 */
	//TODO
	private double getTimeToCollisionWithAxis(double position, double velocity, double axisMax)
	{
		
		double dt;
		
		if(velocity>0)
			dt = Vector.multiplyComponents(axisMax-getObject1().getRadius()-position, 1/velocity);
		else if(velocity<0)
			dt = Vector.multiplyComponents(getObject1().getRadius()-position, 1/velocity);
		else
			dt = Double.POSITIVE_INFINITY;
		return dt;
	}
	
	/**
	 * Return where two spaceObjects will collide.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other.
	 * @return	An array with the position of the future collision. With alpha the given angle between the two centers of the spacespaceObjects when they collide.
	 * 			| collision[0] = spaceObject1.getXPosition() + spaceObject1.getXVelocity() * getTimeToCollisiion() + cos(alpha)* spaceObject1.getRadius();
	 *			| collision[1] = spaceObject1.getYPosition() + spaceObject1.getYVelocity() * getTimeToCollisiion() + sin(alpha)* spaceObject1.getRadius();
	 *			| result == collision
	 * @return	Null if the two spaceObjects never collide.
	 * 			| if(getTimeToCollision(spaceObject1,spaceObject2) == Double.POSITIVE_INFINITY)
	 * 			|	then collision == null
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 */
	public double[] getCollisionPosition() 
	{
		if(getObject2() == null)
		{
			return null;
		}
		else
		{
			double[] collision = new double[2];
			
			double time = getTimeToCollision();
			if(time == Double.POSITIVE_INFINITY)
			{
				collision = null;
			}
			else{
							
				Vector newPositionspaceObject1 = new Vector(getObject1().getPosition().getXComp() + getObject1().getXVelocity() * time, getObject1().getPosition().getYComp() + getObject1().getYVelocity() * time);
				Vector newPositionspaceObject2 = new Vector(getObject2().getPosition().getXComp() + getObject2().getXVelocity() * time, getObject2().getPosition().getYComp() + getObject2().getYVelocity() * time);
				Vector directionCollision = Vector.subtraction(newPositionspaceObject1, newPositionspaceObject2);
				
				double alpha = (Math.atan(Math.abs(directionCollision.getYComp())/Math.abs(directionCollision.getXComp())));
				
				if(directionCollision.getYComp() == 0) {
					if(Math.signum(directionCollision.getXComp()) < 0)
						alpha = PI;
					else
						alpha = 0;
				}
				if(directionCollision.getXComp() == 0) {
					if(Math.signum(directionCollision.getYComp()) < 0)
						alpha = -PI/2;
					else
						alpha = PI/2;
				}
				if(directionCollision.getXComp() < 0 && directionCollision.getYComp() > 0 )
					alpha = PI - alpha;
				if(directionCollision.getXComp() < 0 && directionCollision.getYComp() < 0 )
					alpha = PI + alpha;
				if(directionCollision.getXComp() > 0 && directionCollision.getYComp() < 0 )
					alpha = - alpha;
				
				double collisionPositionX = getObject1().getPosition().getXComp() + getObject1().getXVelocity() * time - Math.cos(alpha) * getObject1().getRadius() ;
				double collisionPositionY = getObject1().getPosition().getYComp() + getObject1().getYVelocity() * time - Math.sin(alpha) * getObject1().getRadius() ;
				
				collision[0] = collisionPositionX; 
				collision[1] = collisionPositionY; 
			}
					
			return collision;
		}
	}
	
	public void bounceOff() 
	{
		Vector dv = Vector.subtraction(getObject1().getVelocity(), getObject2().getVelocity());
		Vector dr = Vector.subtraction(getObject1().getPosition(), getObject2().getPosition());
		double dvdr = Vector.dotProduct(dv, dr);
		double sigma = Vector.sumOfComponents(getObject1().getRadius(),getObject2().getRadius());
		
		double mi = getObject1().getMass();
		double mj = getObject2().getMass();
		
		double J = (2*mi*mj*dvdr)/(sigma*(mi+mj));
		double Jx = (J*dr.getXComp())/sigma;
		double Jy = (J*dr.getYComp())/sigma;
		
		Vector newVelocityThis = new Vector(getObject1().getVelocity().getXComp() + (Jx/mi), getObject1().getVelocity().getYComp() + (Jy/mi));
		Vector newVelocityOther = new Vector(getObject2().getVelocity().getXComp() + (Jx/mj), getObject2().getVelocity().getYComp() + (Jy/mj));
		
		getObject1().setVelocity(newVelocityThis);
		getObject2().setVelocity(newVelocityOther);
		
	}
	
	public void execute() 
	{
		if(getObject2()== null)
		{
			double time = getTimeToCollision();
			Vector position = getObject1().getPosition();
			Vector velocity = getObject1().getVelocity();
			
			if(Util.fuzzyEquals(position.getXComp()+velocity.getXComp()*time, 0) || Util.fuzzyEquals(position.getXComp()+velocity.getXComp()*time, getObject1().getWorld().getWidth()))
			{
				getObject1().setVelocity(-velocity.getXComp(), velocity.getYComp());
			}
			else
			{
				getObject1().setVelocity(velocity.getXComp(), -velocity.getYComp());
			}
		}
		else
		{
			if(Bullet.class.isInstance(getObject1()) || Bullet.class.isInstance(getObject2()))
				
			{
				getObject1().die(getObject1().getWorld());
				getObject2().die(getObject2().getWorld());
			}
			else if(getObject1().getClass() == getObject2().getClass())
				bounceOff();
			else if(Asteroid.class.isInstance(getObject1()))
			{
				getObject2().die(getObject2().getWorld());
			}
			else if(Asteroid.class.isInstance(getObject2()))
			{
				getObject1().die(getObject1().getWorld());
			}
		}
	}
}
