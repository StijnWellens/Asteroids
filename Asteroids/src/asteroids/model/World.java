package asteroids.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;


public class World {
	
	public World() {
		setUpperBoundCoordinate(Double.MAX_VALUE);
		this.height = 10;
		this.width = 10;		
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
	 * @throws	IllegalArgumentException
	 * 			| (spaceObject == null)
	 */
	@Basic
	public boolean containsSpaceObject(SpaceObject spaceObject) {
		if(spaceObject == null)
			throw new IllegalArgumentException();
		return(spaceObjects.contains(spaceObject));
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| this.spaceObjects
	 */
	@Basic
	public Set<SpaceObject> getSpaceObjects() {
		return new HashSet<SpaceObject>(spaceObjects);
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
	
	public void addSpaceObject(SpaceObject spaceObject)
	{
		spaceObjects.add(spaceObject);
	}
	
	//public boolean canHaveAsObject(SpaceObject)
	
	private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
	
}
