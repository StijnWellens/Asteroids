package asteroids.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;


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
	 * @param spaceObject
	 * @return	...
	 * 			| spaceObjects.contains(spaceObject)
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
			if(Ship.class.isInstance(spaceObject))
				setShips.add((Ship)spaceObject);
		return setShips;
	}
	
	public Set<Asteroid> getAsteroids() 
	{
		Set<Asteroid> setAsteroids = new HashSet<Asteroid>();
		for(SpaceObject spaceObject: getSpaceObjects())
			if(Asteroid.class.isInstance(spaceObject))
				setAsteroids.add((Asteroid)spaceObject);
		return setAsteroids;
	}
	
	public Set<Bullet> getBullets() 
	{
		Set<Bullet> setBullets = new HashSet<Bullet>();
		for(SpaceObject spaceObject: getSpaceObjects())
			if(Bullet.class.isInstance(spaceObject))
				setBullets.add((Bullet)spaceObject);
		return setBullets;
	}
	
	public void addSpaceObject(SpaceObject spaceObject)throws IllegalArgumentException
	{
		if(!this.canHaveAsObject(spaceObject))
			throw new IllegalArgumentException();
		this.getSpaceObjects().add(spaceObject);
		this.addCollisions(spaceObject);
	}
	
	public boolean canHaveAsObject(SpaceObject spaceObject){
		return (spaceObject!=null) && (spaceObject.canHaveAsWorld(this));
	}
	
	private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
	
	public void removeSpaceObject(SpaceObject spaceObject) throws IllegalArgumentException{
		if(containsSpaceObject(spaceObject))
		{
			this.getSpaceObjects().remove(spaceObject);
			this.removeCollisions(spaceObject);
		}
		else
			throw new IllegalArgumentException();
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
		this.possibleCollisions = new ArrayList<Collision>(collisions);
	}
	
	/*
	public void createCollisions()
	{
		List<Collision> collisions = new ArrayList<Collision>();
		
		List<SpaceObject> objects = new ArrayList<SpaceObject>(this.getSpaceObjects());
		
		for(int i = 0; i < objects.size()-1; i++)
		{
			SpaceObject spaceObject = objects.get(i);
			
			for(int j = i+1; j < objects.size(); j++)
			{
				Collision collision = new Collision(spaceObject, objects.get(j));
				collisions.add(collision);
			}
			
		}
		
		
	}*/
	

	public void addCollisions(SpaceObject spaceObject)
	{
		List<Collision> collisions;
		if(this.getPossibleCollisions() == null)
			collisions = new ArrayList<Collision>();
		else
			collisions = new ArrayList<Collision>(this.getPossibleCollisions());
		
		List<SpaceObject> objects = new ArrayList<SpaceObject>(this.getSpaceObjects());
		
		for(int i = 0; i < objects.size(); i++)
		{
			try{
				Collision collision = new Collision(spaceObject, objects.get(i));
				collisions.add(collision);
			}
			catch(Exception e)
			{}
					
		}		
		
		this.setPossibleCollisions(collisions);
	}
	
	public void removeCollisions(SpaceObject spaceObject)
	{
		List<Collision> collisions = new ArrayList<Collision>(this.getPossibleCollisions());
				
		for(Collision collision: collisions)
		{
			if(collision.contains(spaceObject))
				collisions.remove(collision);
					
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
	
	public SpaceObject getFirstObjectToCollideWithBorder()
	{
		Set<SpaceObject> objects = this.getSpaceObjects();
		Iterator<SpaceObject> it = objects.iterator();
		
		SpaceObject firstObject = null;
		double time = Double.POSITIVE_INFINITY;
		
		while(it.hasNext())
		{
			double timeToCollision = it.next().getTimeToCollisionWithBorder();
			if(time > timeToCollision)
			{
				time = timeToCollision;
				firstObject = it.next();
			}
		}
		
		return firstObject;
	}
	
	public void advanceObjects(double time)
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
	
	public double evolveBeforeCollision(double dt)
	{
		double tc = Double.POSITIVE_INFINITY;
		double collisionWithBorder;
		double collisionTime;
		
		try{
			collisionWithBorder = this.getFirstObjectToCollideWithBorder().getTimeToCollisionWithBorder();
			collisionTime = this.getFirstCollision().getTimeToCollision();
		}
		catch(NullPointerException npe)
		{
			return tc;
		}
				
		if(collisionWithBorder < collisionTime)
		{
			tc = collisionWithBorder;
			if(tc < dt)
			{
				advanceObjects(tc);
				this.getFirstObjectToCollideWithBorder().collisionWithBorder();
			}
		}
		else
		{
			tc = collisionTime;
			if(tc < dt)
			{
				advanceObjects(tc);
				this.getFirstCollision().execute();
			}
		}
		
		return tc;
	}
	
	public void evolve(double dt)
	{
		double tc = evolveBeforeCollision(dt);
		double newdt = dt;
		
		while(tc <= newdt)
		{
			newdt = newdt - tc;
			tc = evolveBeforeCollision(dt);
		}
		
		advanceObjects(newdt);
	}
}
