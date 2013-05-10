package asteroids.model;
 
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * The class representing a collision between two SpaceObjects or between a SpaceObject and the border of a world.
 * 
 * @invar 	The first object, SpaceObject1, has always to be valid.
 * 			| isValidObject(getObject1())
 * @author 	Julie Wouters & Stijn Wellens
 * 			Students Bachelor of Science in Engineering 
 * 			(Computer Science and electrical engineering)
 * 			link to our code repository:
 * 			https://github.com/StijnWellens/Asteroids.git
 */
public class Collision {
	
	private static final double	PI	= Math.PI;
	
	/**
	 * Initializes a collision with two SpaceObjects.
	 * 
	 * @param so1
	 * @param so2
	 * @effect	...
	 * 			| setObjects(so1,so2)
	 * @effect	...
	 * 			| setNmbOfCollisions(0)
	 * @effect	...
	 * 			| calclateTimeToCollision()
	 */
	public Collision(SpaceObject so1, SpaceObject so2) throws IllegalArgumentException
	{
		this.setObjects(so1,so2);
		this.nmbOfCollisions = 0;
		this.calculateTimeToCollision();
	}
	
	/**
	 * Initializes a collision with a SpaceObject and the border of the world of this SpaceObject.
	 * 
	 * @param so1
	 * @effect	...
	 * 			| setObject(so1)
	 * @effect	...
	 * 			| setNmbOfCollisions(0)
	 * @effect	...
	 * 			| calclateTimeToCollision()
	 */
	public Collision(SpaceObject so1) throws IllegalArgumentException
	{
		this.setObject(so1);
		this.nmbOfCollisions = 0;
		this.calculateTimeToCollision();
	}
	
	// NumberOfCollisions
	
	private int nmbOfCollisions;
	
	/**
	 * Returns the number of collisions of this collision.
	 * 
	 * @return 	...
	 * 			| this.nmbOfCollisions
	 */
	@Basic 
	public int getNmbOfCollisions()
	{
		return this.nmbOfCollisions;
	} 
	
	/**
	 * 
	 * @param nmb
	 * @return	...
	 * 			| result == (nmb>=0)
	 */
	public boolean isValidNmbOfCollisions(int nmb)
	{
		return (nmb>=0);
	}
	
	/**
	 * 
	 * @param nmb
	 * @post	...
	 * 			| (new this).getNmbOfCollisions() == nmb
	 * @throws	IllegalArgumentException
	 * 			| !isValidNmbOfCollisions(nmb)
	 */
	public void setNmbOfCollisions(int nmb) throws IllegalArgumentException
	{
		if(!isValidNmbOfCollisions(nmb))
			throw new IllegalArgumentException();
	}
	
	// Objects
	
	private SpaceObject spaceObject1;
	private SpaceObject spaceObject2;
	
	/**
	 * Returns the first spaceObject of this collision.
	 * 
	 * @return	...
	 * 			| this.spaceObject1
	 */
	@Basic
	public SpaceObject getObject1()
	{
		return this.spaceObject1;
	}
	
	/**
	 * Returns the second spaceObject of this collision. This is null if this collision is a collision with a worlds border.
	 * 
	 * @return	...
	 * 			| this.spaceObject2
	 */
	@Basic
	public SpaceObject getObject2()
	{
		return this.spaceObject2;
	}
	
	/**
	 * 
	 * @param object
	 * @return	...
	 * 			| result == (object != null) && (object.getWorld() != null)
	 */
	public boolean isValidObject(SpaceObject object)
	{
		if((object == null) || (object.getWorld() ==  null))
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param object
	 * @return	...
	 * 			| result == ( (isValidObject(object1) && isValidObject(object2))
	 * 			|		&&	(object1 != object2)
	 * 			|		&&	object1.getWorld() == object2.getWorld()
	 * 			|		&&	(!Bullet.class.isInstance(object1) || ((Bullet)object1).getShip()!= object2)
	 * 			|		&&	(!Bullet.class.isInstance(object2) || ((Bullet)object2).getShip()!= object1) )
	 */
	public boolean areValidObjects(SpaceObject object1, SpaceObject object2)
	{
		if(!isValidObject(object1) || !isValidObject(object2))
			return false;
		if(object1 == object2)
			return false;
		if(object1.getWorld() != object2.getWorld())
			return false;
		if(Bullet.class.isInstance(object1) && ((Bullet)object1).getSource()== object2)
			return false;
		if(Bullet.class.isInstance(object2) && ((Bullet)object2).getSource()== object1)
			return false;
		return true;
	}
	
	/**
	 * 
	 * @param 	object
	 * @post	...
	 * 			| (new this).getObject1() == object
	 * @post	...	
	 * 			| (new this).getObject2() == null
	 * @throws 	IllegalArgumentException
	 * 			| !isValidObject(object)
	 */
	public void setObject(SpaceObject object) throws IllegalArgumentException
	{
		if(!isValidObject(object))
			throw new IllegalArgumentException();
		this.spaceObject1 = object;
		this.spaceObject2 = null;
	}	
	
	/**
	 * 
	 * @param 	object
	 * @post	...
	 * 			| (new this).getObject1() == object1
	 * @post	...	
	 * 			| (new this).getObject2() == object2
	 * @throws 	IllegalArgumentException
	 * 			| !areValidObjects(object1, object2)
	 */
	public void setObjects(SpaceObject object1, SpaceObject object2) throws IllegalArgumentException
	{
		if(!areValidObjects(object1,object2))
			throw new IllegalArgumentException();
		this.spaceObject1 = object1;
		this.spaceObject2 = object2;
	}
	
	/**
	 * 	
	 * @param 	object
	 * @return	...
	 * 			| result == (object == this.getObject1()) || (object == this.getObject2())
	 */
	public boolean contains(SpaceObject object)
	{
		if(object == this.getObject1())
			return true;
		if(object == this.getObject2())
			return true;
		return false;
	}
	
	private double timeToCollision;
		
	/**
	 * 
	 * @return	...
	 * 			| dtx == getTimeToCollisionWithAxis(getObject1().getX(), getObject1().getXVelocity(), width)
	 * 			| dty == getTimeToCollisionWithAxis(getObject1().getY(), getObject1().getYVelocity(), height)
	 * 			| if (dtx <= dty)
	 * 			|		then result == dtx
	 * 			| if (dty < dtx)
	 * 			|		then result == dty
	 */
	public double getTimeToCollisionWithBorder()
	{
		double width = getObject1().getWorld().getWidth();
		double height = getObject1().getWorld().getHeight();
		Vector leftSubCorner = getObject1().getWorld().getLeftSubCorner();
		double dtx = getTimeToCollisionWithAxis(getObject1().getX(), getObject1().getXVelocity(), leftSubCorner.getXComp(), leftSubCorner.getXComp() + width);
		double dty = getTimeToCollisionWithAxis(getObject1().getY(), getObject1().getYVelocity(), leftSubCorner.getYComp(), leftSubCorner.getYComp() + height);
						
		if(dtx <= dty)
			return dtx;
		else
			return dty;
	}
	
	/**
	 * Return the time to collision from a point of a ship with a border in one direction.
	 * 
	 * @param position
	 * @param velocity
	 * @param axisMax
	 * @return	...
	 * 			| if(result < Double.POSITIVE_INFINITY) then
	 * 			|	for each time in 0.0..Double.MAX_VALUE:
	 * 			|		if(time > result) then
	 * 			|			!this.canHaveAsWorld(this.getWorld())
	 * @return	...
	 * 			| if(result == Double.POSITIVE_INFINITY) then
	 * 			|	velocity == 0		
	 */
	private double getTimeToCollisionWithAxis(double position, double velocity, double axisMin, double axisMax)
	{
		
		double dt;
		
		if(velocity>0)
			dt = Vector.multiplyComponents(axisMax-getObject1().getRadius()-position-axisMin, 1/velocity);
		else if(velocity<0)
			dt = Vector.multiplyComponents(getObject1().getRadius()-position, 1/velocity);
		else
			dt = Double.POSITIVE_INFINITY;
		return dt;
	}
	
	
	/**
	 * Return when two spaceObjects will collide.
	 * 
	 * @param 	spaceObject1
	 * 			The first spaceObject to compare with the other.
	 * @param 	spaceObject2
	 * 			The second spaceObject to compare with the other.
	 * @return	Return when two spaceObjects will collide. 
	 * 			| if(result < Double.POSITIVE_INFINITY) then
	 * 			|	if(spaceObject1.move(result) && spaceObject2.move(result))
	 * 			|		then Util.fuzzyEquals(SpaceObject.getDistanceBetween(spaceObject1,spaceObject2),0)
	 * @return  Return positive infinity if they never collide. 
	 * 			| if(result == Double.POSITIVE_INFINITY) then
	 * 			| 	for each time in 0.0..Double.POSITIVE_INFINITY
	 * 			|		if(!Double.isInfinite(time)) then
	 * 			|			if(spaceObject1.move(time) && spaceObject2.move(time))
	 * 			|				then !Util.fuzzyEquals(SpaceObject.getDistanceBetween(spaceObject1,spaceObject2),0)
	 * @throws	IllegalArgumentException
	 * 			Throws exception when one of the given spaceObjects is null.
	 * 			| (spaceObject1 == null) || (spaceObject2 == null)
	 *
	 // infinity, time could not be negative
	 * if(result == Double.POSITIVE_INFINITY) then
	 * 	(this == other) 
	 * 
	 */
	public double getTimeToCollisionWithObject()
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
	
	/**
	 * 
	 * @return	...
	 * 			| if(getObject2() == null)
	 * 			|	then result == getTimeToCollisionWithBorder()
	 * 			| else
	 * 			|	then result == getTimeToCollisionWithObject()
	 */
	public double getTimeToCollision() 
	{
		calculateTimeToCollision();
		return this.timeToCollision;		
	}
	
	public boolean isValidTimeToCollision(double time)
	{
		return (!Double.isNaN(time));
	}
	
	public void setTimeToCollision(double time) throws IllegalArgumentException
	{
		if(!isValidTimeToCollision(time))
			throw new IllegalArgumentException();
		this.timeToCollision = time;
	}
	
	public void calculateTimeToCollision()
	{
		if(getObject2() == null)
		{
			this.setTimeToCollision(getTimeToCollisionWithBorder());
		}
		else
		{
			this.setTimeToCollision(getTimeToCollisionWithObject());
		}
	}
	
	/*public void updateTimeToCollision(double dt)
	{
		double newTime = this.getTimeToCollision() - dt;
		
		if(Util.fuzzyLessThanOrEqualTo(newTime, 0))
		{
			this.calculateTimeToCollision();
		}
		else if(Ship.class.isInstance(getObject1()) && (((Ship)getObject1()).isThrusterEnabled()))
		{
			this.calculateTimeToCollision();
		}
		else if(Ship.class.isInstance(getObject2()) && (((Ship)getObject2()).isThrusterEnabled()))
		{
			this.calculateTimeToCollision();
		}
		else
		{
			this.setTimeToCollision(newTime);
		}
			
	}*/
	
	/**
	 * Return where two spaceObjects will collide.
	 * 
	 * @return	An array with the position of the future collision. With alpha the given angle between the two centers of the spaceObjects when they collide.
	 * 			| alpha == Math.acos(
	 * 			| collision[0] = getObject1().getXPosition() + getObject1().getXVelocity() * getTimeToCollisiion() + cos(alpha)* getObject1().getRadius();
	 *			| collision[1] = getObject1().getYPosition() + getObject1().getYVelocity() * getTimeToCollisiion() + sin(alpha)* getObject1().getRadius();
	 *			| result == collision
	 * @return	Null if the two spaceObjects never collide.
	 * 			| if(getTimeToCollision() == Double.POSITIVE_INFINITY)
	 * 			|	then collision == null
	 */
	public double[] getCollisionPositionWithObject()
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
	
	//TODO
	/**
	 * Return where a SpaceObject will collide with the border.
	 * 
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
	public double[] getCollisionPositionWithBorder()
	{
		double[] collision = new double[2];
		
		double time = getTimeToCollision();
		if(time == Double.POSITIVE_INFINITY)
		{
			collision = null;
		}
		else
		{
			SpaceObject object = getObject1();
			
			double width = object.getWorld().getWidth();
			double height = object.getWorld().getHeight();
			Vector leftSubCorner = getObject1().getWorld().getLeftSubCorner();
			double dtx = getTimeToCollisionWithAxis(object.getX(), object.getXVelocity(), leftSubCorner.getXComp(), leftSubCorner.getXComp() + width);
			double dty = getTimeToCollisionWithAxis(object.getY(), object.getYVelocity(), leftSubCorner.getYComp(), leftSubCorner.getYComp() + height);
							
			if(dtx <= dty)
			{
				double sign;
				if(object.getXVelocity() < 0)
					sign = -1;
				else
					sign = 1;
					
				collision[0] = object.getX()+ object.getXVelocity()*dtx + sign * object.getRadius();
				collision[1] = object.getY()+ object.getYVelocity()*dtx;
			}
			else
			{
				double sign;
				if(object.getYVelocity() < 0)
					sign = -1;
				else
					sign = 1;
				
				collision[0] = object.getX()+ object.getXVelocity()*dty;
				collision[1] = object.getY()+ object.getYVelocity()*dty + sign * object.getRadius();
			}					
		}
		
		return collision;
	}
	
	/**
	 * 
	 * @return	...
	 * 			| if(getObject2() == null)
	 * 			|	then result == getCollisionPositionWithBorder()
	 * 			| else
	 * 			|	then result == getCollisionPositionWithObject()
	 */
	public double[] getCollisionPosition() 
	{
		if(getObject2() == null)
		{
			return this.getCollisionPositionWithBorder();
		}
		else
		{
			return this.getCollisionPositionWithObject();
		}
	}
	
	public void bounceOff() 
	{
		Vector dv = Vector.subtraction(getObject2().getVelocity(), getObject1().getVelocity());
		Vector dr = Vector.subtraction(getObject2().getPosition(), getObject1().getPosition());
		double dvdr = Vector.dotProduct(dv, dr);
		double sigma = Vector.sumOfComponents(getObject1().getRadius(),getObject2().getRadius());
		
		double mi = getObject1().getMass();
		double mj = getObject2().getMass();
		
		double J = (2*mi*mj*dvdr)/(sigma*(mi+mj));
		double Jx = (J*dr.getXComp())/sigma;
		double Jy = (J*dr.getYComp())/sigma;
		
		Vector newVelocityThis = new Vector(getObject1().getVelocity().getXComp() + (Jx/mi), getObject1().getVelocity().getYComp() + (Jy/mi));
		Vector newVelocityOther = new Vector(getObject2().getVelocity().getXComp() - (Jx/mj), getObject2().getVelocity().getYComp() - (Jy/mj));
		
		getObject1().setVelocity(newVelocityThis);
		getObject2().setVelocity(newVelocityOther);
	}
	
	public void executeWithBorder()
	{
		if(Bullet.class.isInstance(getObject1()) && this.nmbOfCollisions >= 2)
		{
			getObject1().die();
		}
		else {
			Vector velocity = getObject1().getVelocity();
			
			double[] collision = getCollisionPosition();
			
			if(Util.fuzzyEquals(collision[1], 0) || Util.fuzzyEquals(collision[1], getObject1().getWorld().getHeight()))
			{ 
				getObject1().setVelocity(velocity.getXComp(), -velocity.getYComp());
			}
			else if(Util.fuzzyEquals(collision[0], 0) || Util.fuzzyEquals(collision[0], getObject1().getWorld().getWidth()))
			{
				getObject1().setVelocity(-velocity.getXComp(), velocity.getYComp());
			} 
		}
	}
	
	public void executeWithObject()
	{
		SpaceObject object1 = getObject1();
		SpaceObject object2 = getObject2();
		if(object1.killsOther(object2) && object2.killsOther(object1))
		{
			object1.die();
			object2.die();
		}
		else if(object1.killsOther(object2))
		{
			object2.die();
		}
		else if(object2.killsOther(object1))
		{
			object1.die();
		}
		else
		{
			bounceOff();
		}
	}
	
	public void execute() 
	{
		this.nmbOfCollisions ++;
				
		if(getObject2()== null )
		{
			executeWithBorder();						
		}
		else
		{
			executeWithObject();
		}				
	}
}
