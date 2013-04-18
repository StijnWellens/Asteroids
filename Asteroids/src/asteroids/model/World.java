package asteroids.model;


import Collision;
import SpaceObject;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a world with a height and width.
 * 
 * @invar 	The height of the world must always be a valid height. 
 * 			| isValidHeight(getHeight())
 * @invar 	The width of the world must always be a valid width. 
 * 			| isValidWidth(getWidth())
 * @invar 	The upper bound coordinate of the world must always be a 
 * 			valid upper bound coordinate.
 *        	| isValidUpperBoundCoordinate(getUpperBoundCoordinate())
 * @author 	Julie Wouters & Stijn Wellens
 */

public class World {
	
	public World() {
		setUpperBoundCoordinate(Double.MAX_VALUE);
		this.height = 100;
		this.width = 100;		
	}
	
	public World(double width, double height) throws IllegalArgumentException {
		setUpperBoundCoordinate(Double.MAX_VALUE);
		
		if(!isValidHeight(height))
			throw new IllegalArgumentException();
		if(!isValidWidth(width))
			throw new IllegalArgumentException();
		
		this.height = height;
		this.width = width;
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| this.height
	 */
	@Basic
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @param height
	 * @return	...
	 * 			| result == 
	 * 			|		( (!Double.isNaN(height))
	 * 			|			&&	(height >= 0)
	 * 			|			&&	(height <= upperBoundCoordinate) )
	 */
	public boolean isValidHeight(double height) {
		return((!Double.isNaN(height)) && (height >= 0) && (height <= upperBoundCoordinate) );
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| this.width
	 */
	@Basic
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @param width
	 * @return	...
	 * 			| result == 
	 * 			|		( (!Double.isNaN(width))
	 * 			|			&&	(width >= 0)
	 * 			|			&&	(width <= upperBoundCoordinate) )
	 */
	public boolean isValidWidth(double width) {
		return((!Double.isNaN(width)) && (width >= 0) && (width <= upperBoundCoordinate) );
	}
		
	private final double height;
	private final double width;

	private static double	upperBoundCoordinate;

	/**
	 * 
	 * @return	...
	 * 			| upperBoundCoordinate
	 */
	@Basic
	public static double getUpperBoundCoordinate() {
		return upperBoundCoordinate;
	}

	/**
	 *  
	 * @param 	upperBoundCoordinate
	 *          
	 * @return 	...
	 * 			| result == 
	 * 			| ( (!Double.isNaN(upperBoundCoordinate))
	 * 			|		&& (upperBoundCoordinate >= 0) 
	 * 			|		&& (upperBoundCoordinate <= Double.MAX_VALUE) )
	 */
	public static boolean isValidUpperBoundCoordinate(double upperBoundCoordinate) {
		return ((!Double.isNaN(upperBoundCoordinate)) && (upperBoundCoordinate >= 0) && (upperBoundCoordinate <= Double.MAX_VALUE));
	}

	/**
	 * 
	 * @param 	upperBoundCoordinate
	 *         
	 * @post 	...
	 * 			| new.getUpperBoundCoordinate() == upperBoundCoordinate
	 * @throws 	IllegalArgumentException
	 *         	...
	 *         	| ! isValidUpperBoundCoordinate(upperBoundCoordinate)
	 */
	private static void setUpperBoundCoordinate(double upperBoundCoordinate)
			throws IllegalArgumentException {
		if (!isValidUpperBoundCoordinate(upperBoundCoordinate))
			throw new IllegalArgumentException();
		World.upperBoundCoordinate = upperBoundCoordinate;

	}
	
	/**
	 * 
	 * @param 	spaceObject
	 * 
	 * @pre		...
	 * 			| (spaceObject != null) && (spaceObject.getWorld() == this)
	 * @effect	...
	 * 			| (new this).getSpaceObjects() == this.getSpaceObjects().add(spaceObject)
	 * @effect	...
	 * 			| (new this).getPossibleCollisions() == this.getPossibleCollisions()
	 */
	@Raw
	public void addSpaceObject(SpaceObject spaceObject)
	{
		assert (spaceObject != null) && (spaceObject.getWorld() == this);
		
		Set<SpaceObject> objects = this.getSpaceObjects();
		objects.add(spaceObject);
		this.setSpaceObjects(objects);
		this.addCollisions(spaceObject);
	}
	
	/**
	 * 
	 * @param spaceObject
	 * @return	...
	 * 			| result ==
	 * 			| 	(spaceObjects.contains(spaceObject))
	 */
	@Basic
	public boolean containsSpaceObject(SpaceObject spaceObject) {
		if(spaceObject == null)
			return false;
		return(this.spaceObjects.contains(spaceObject));
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| this.spaceObjects
	 */
	@Basic
	public Set<SpaceObject> getSpaceObjects() {
		return new HashSet<SpaceObject>(this.spaceObjects);
	}
	
	public void setSpaceObjects(Set<SpaceObject> objects)
	{
		this.spaceObjects = new HashSet<SpaceObject>(objects);
	}
	
	/**
	 * 
	 * @param spaceObjectClass
	 * @return	...
	 * 			| result == (setSpaceObjects == new HashSet<SpaceObject>())
	 * 				&& for each spaceObject in setSpaceObjects: spaceObjectClass.isInstance(spaceObject)
	 */
	/*public Set<? extends SpaceObject> getSpaceObjects(Class<?> spaceObjectClass)
	{
		Set<SpaceObject> setSpaceObjects = new HashSet<>();
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(spaceObjectClass.isInstance(spaceObject))
				setSpaceObjects.add(spaceObject);
		}
		return setSpaceObjects;
	}*/
	
	public Set<Ship> getShips() 
	{
		Set<Ship> setShips = new HashSet<Ship>();
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(Ship.class.isInstance(spaceObject))
				setShips.add((Ship)spaceObject);
		}
		return setShips;
	}
	
	public Set<Asteroid> getAsteroids() 
	{
		Set<Asteroid> setAsteroids = new HashSet<Asteroid>();
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(Asteroid.class.isInstance(spaceObject))
				setAsteroids.add((Asteroid)spaceObject);
		}
		return setAsteroids;
	}
	
	public Set<Bullet> getBullets() 
	{
		Set<Bullet> setBullets = new HashSet<Bullet>();
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(Bullet.class.isInstance(spaceObject))
				setBullets.add((Bullet)spaceObject);
		}
		return setBullets;
	}
	
	private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
	
	/**
	 * 
	 * @param 	spaceObject
	 * 
	 * @pre		...
	 * 			| (spaceObject != null) && (spaceObject.getWorld()==this) && this.containsSpaceObject(spaceObject)
	 * @effect	...
	 * 			| (new this).getPossibleCollisions() == 
	 * @throws 	IllegalArgumentException
	 * 			...
	 * 			| (spaceObject != null) && (spaceObject.getWorld()==this) && this.containsSpaceObject(spaceObject)
	 */
		// TODO
	@Raw
	public void removeSpaceObject(SpaceObject spaceObject) throws IllegalArgumentException{
		assert (spaceObject != null) && (spaceObject.getWorld() == this) && containsSpaceObject(spaceObject);
		
		Set<SpaceObject> objects = this.getSpaceObjects();
		objects.remove(spaceObject);
		this.removeCollisions(spaceObject);
		this.setSpaceObjects(objects);
		
	}
	
	public boolean hasProperSpaceObjects()
	{
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(!spaceObject.canHaveAsWorld(this)){
				return false;
			}
			if(spaceObject.getWorld() != this){
				return false;
			}
		}
		return true;
	}
	
	private ArrayList<Collision> possibleCollisions;
	
	@Basic
	public ArrayList<Collision> getPossibleCollisions()
	{
		if(this.possibleCollisions== null)
			return null;
		return new ArrayList<Collision>(possibleCollisions);
	}
	
	public void setPossibleCollisions(List<Collision> collisions)
	{
		if(collisions == null)
			this.possibleCollisions = new ArrayList<Collision>();
		else
			this.possibleCollisions = new ArrayList<Collision>(collisions);
	}
	
	public void addCollisions(SpaceObject spaceObject)
	{
		if(spaceObject == null)
			throw new IllegalArgumentException();
		
		List<Collision> collisions;
		if(this.getPossibleCollisions() == null)
			collisions = new ArrayList<Collision>();
		else
			collisions = new ArrayList<Collision>(this.getPossibleCollisions());
		
		List<SpaceObject> objects = new ArrayList<SpaceObject>(this.getSpaceObjects());
		
		Collision collisionWithBorder = new Collision(spaceObject);
		collisions.add(collisionWithBorder);
		
		for(int i = 0; i < objects.size(); i++)
		{
			if(Collision.areValidObjects(spaceObject, objects.get(i)))
			{
				Collision collision = new Collision(spaceObject, objects.get(i));
				collisions.add(collision);
			}			
					
		}		
		
		this.setPossibleCollisions(collisions);
	}
	
	public void removeCollisions(SpaceObject spaceObject)
	{
		List<Collision> collisions;
		if(this.getPossibleCollisions() == null)
			collisions = new ArrayList<Collision>();
		else
			collisions = new ArrayList<Collision>(this.getPossibleCollisions());
		
		Iterator<Collision> it = collisions.iterator(); 
		
		while(it.hasNext())
		{
			Collision collision = it.next();
			if(collision.contains(spaceObject))
				it.remove();					
		}		
		
		this.setPossibleCollisions(collisions);
	}
	
	public Collision getFirstCollision()
	{
		List<Collision> collisions = this.getPossibleCollisions();
		
		Collision firstCollision = null;
		double time = Double.POSITIVE_INFINITY;
		
		for(Collision collision: collisions)
		{
			double timeToCollision = collision.getTimeToCollision();
			if(time > timeToCollision)
			{
				time = timeToCollision;
				firstCollision = collision;
			}
		}
		
		return firstCollision;
		
	}
	
	public void advanceObjects(double time) throws IllegalArgumentException
	{
		for(SpaceObject spaceObject: this.getSpaceObjects())
		{
			if(spaceObject != null)
			{
				spaceObject.move(time);
				if(Ship.class.isInstance(spaceObject))
				{
					((Ship)spaceObject).thrust(time);
				}
			}
		}
	}
	
	public double evolveBeforeCollision(double dt) throws IllegalArgumentException
	{
		double tc = Double.POSITIVE_INFINITY;
		double collisionTime;
		Collision firstCollision;
		
		try{
			firstCollision = this.getFirstCollision();
			collisionTime = firstCollision.getTimeToCollision();
		}
		catch(NullPointerException npe)
		{
			return tc;
		}
				
		tc = collisionTime;
		
		if(tc < dt)
		{
			advanceObjects(tc);
			firstCollision.execute();
		}		
		
		return tc;
	}
	
	public void evolve(double dt) throws IllegalArgumentException
	{
		if(Double.isNaN(dt) || Double.isInfinite(dt) || dt < 0)
			throw new IllegalArgumentException();
		
		double tc = evolveBeforeCollision(dt);
		double newdt = dt;
		
		while(tc <= newdt)
		{
			newdt = newdt - tc;
			tc = evolveBeforeCollision(newdt);
		}
		
		advanceObjects(newdt);
	}
}
