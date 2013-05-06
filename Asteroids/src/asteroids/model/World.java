package asteroids.model;


import java.util.*;

import asteroids.CollisionListener;
import asteroids.Util;
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
 * @invar   The space objects attached to each world must be proper
 * 			space objects for that world.
 * 			| hasProperSpaceObjects()
 * @author 	Julie Wouters & Stijn Wellens
 */

public class World {
	
	/**
	 * @post	...
	 * 			| (new this).getHeight = 100
	 * @post	...
	 * 			| (new this).getWidth = 100
	 */
	public World() {
		setUpperBoundCoordinate(Double.MAX_VALUE);
		this.height = 100;
		this.width = 100;		
	}
	
	/**
	 * 
	 * @param 	width
	 * @param 	height
	 * @throws 	IllegalArgumentException
	 * 			| !isValidHeight(height)
	 * @throws	IllegalArgumentException
	 * 			| !isValidWidth(width)
	 * @post	...
	 * 			| (new this).getHeight = height
	 * @post	...
	 * 			| (new this).getWidth = width
	 */
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
	
	/**
	 * 
	 * @param 	objects
	 * 
	 * @post	...
	 * 			| (new this).getSpaceObjects() == objects
	 */
	public void setSpaceObjects(Set<SpaceObject> objects)
	{
		this.spaceObjects = new HashSet<SpaceObject>(objects);
	}
	
	
	
	/**
	 * 
	 * @param	clazz
	 * 			The subclass from SpaceObject from which a set must be returned.
	 * @return	...
	 * 			| result == (setObjects == new HashSet<(clazz.getClass())>())
	 * 			|	&& for each spaceObject in setObjects: 
	 * 			|		(clazz.getClass()).isInstance(spaceObject)
	 */
	public Set<? extends SpaceObject> getObjects(Class<? extends SpaceObject> clazz) 
	{
		Set<SpaceObject> setObjects = new HashSet<SpaceObject>();
		for(SpaceObject spaceObject: getSpaceObjects())
		{
			if(((clazz)).isInstance(spaceObject))
				setObjects.add(spaceObject);
		}
		return setObjects;
	}
	
	private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
	
	/**
	 * 
	 * @param 	spaceObject
	 * 
	 * @pre		...
	 * 			| (spaceObject != null) && (spaceObject.getWorld()==this) && this.containsSpaceObject(spaceObject)
	 * @effect	...
	 * 			| this.removeCollisions(spaceObject)
	 * @effect	...
	 * 			| (new this).getSpaceObjects() == this.getSpaceObjects().remove(spaceObject)
	 */
	@Raw
	public void removeSpaceObject(SpaceObject spaceObject) {
		assert (spaceObject != null) && (spaceObject.getWorld() == this) && containsSpaceObject(spaceObject);
		
		Set<SpaceObject> objects = this.getSpaceObjects();
		objects.remove(spaceObject);
		this.removeCollisions(spaceObject);
		this.setSpaceObjects(objects);
		
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result ==
	 * 			|	for each spaceObject in getSpaceObjects():
	 * 			|		( spaceObject.canHaveAsWorld(this) 
	 * 			|			&& spaceObject.getWorld()==this)
	 */
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
	
	/**
	 * 
	 * @return	...
	 * 			| this.possibleCollisions
	 */
	@Basic
	public ArrayList<Collision> getPossibleCollisions()
	{
		if(this.possibleCollisions== null)
			return null;
		return new ArrayList<Collision>(possibleCollisions);
	}
	
	/**
	 * 
	 * @param 	collisions
	 * @post	...
	 * 			| if(collisions == null) 
	 * 			|   then( (new this).possibleCollisions == new ArrayList<Collision>())
	 * @post	...
	 * 			| (new this).possibleCollisions == collisions	
	 */
	public void setPossibleCollisions(List<Collision> collisions)
	{
		if(collisions == null)
			this.possibleCollisions = new ArrayList<Collision>();
		else
			this.possibleCollisions = new ArrayList<Collision>(collisions);
	}
	
	/**
	 * 
	 * @param 	spaceObject
	 * @effect	...
	 * 			| if( this.getPossibleCollisions == null )
	 * 				then ( List<Collision> collisions = new ArrayList<Collision>())
	 * 			| 		else (List<Collision> collisions = new ArrayList<Collision>(this.getPossibleCollisions()))
	 * 			|		 Collision collisionWithBorder = new Collision(spaceObject)
	 * 			| 	 		collisions.add(collisionWithBorder)
	 * 			|			 for each object in this.getSpaceObjects():
	 * 			|				(Collision collision = new Collision(spaceObject, object)
	 * 			|							with collision.areValidObjects(spaceObject,object) == true
	 *			|						collisions.add(collision))
	 *			|						this.setPossibleCollisions(collisions)
	 * @throws	IllegalArgumentException
	 * 			| spaceObject == null
	 */
	public void addCollisions(SpaceObject spaceObject) throws IllegalArgumentException
	{
		if(spaceObject == null)
			throw new IllegalArgumentException();
		
		List<Collision> collisions;
		if(this.getPossibleCollisions() == null)
			collisions = new ArrayList<Collision>();
		else
			collisions = new ArrayList<Collision>(this.getPossibleCollisions());
		
		List<SpaceObject> objects = new ArrayList<SpaceObject>(this.getSpaceObjects());
		
		try{
			Collision collisionWithBorder = new Collision(spaceObject);
			collisions.add(collisionWithBorder);
		}
		catch(IllegalArgumentException iae) {}
				
		for(int i = 0; i < objects.size(); i++)
		{
			
			try
			{
				Collision collision = new Collision(spaceObject, objects.get(i));
				collisions.add(collision);
			}
			catch(IllegalArgumentException iae) // !collision.areValidObjects(spaceObject, objects.get(i))
			{}
				
		}		
		
		this.setPossibleCollisions(collisions);
	}
	
	/**
	 * 
	 * @param 	spaceObject
	 * @post	...
	 * 			| for each collision in (new this).getPossibleCollisions():
	 * 			|	!collision.contains(spaceObject)
	 */
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
	
	/**
	 * 
	 * @return	...
	 * 			| for each collision in this.getPossibleCollisions():
	 * 			|	collision.getTimeToCollision() >= result.getTimeToCollision()
	 */
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
	
	/**
	 * 
	 * @param 	time
	 * @effect	...
	 * 			| for each spaceObject in this.getSpaceObjects():
	 * 			|	if( spaceObject != null)
	 * 			|	 then ( spaceObject.move(time)
	 * 			|				if(Ship.class.isInstance(spaceObject))
	 * 			|					then ( ((Ship)spaceObject).thrust(time)))
	 */
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
	
	/**
	 * 
	 * @param 	dt
	 * @param	collisionListener
	 * @return	...
	 * 			| if( result == Double.POSITIVE_INFINITY )
	 * 			|	then ( this.getFirstCollision() == null)
	 * @return	...
	 * 			| if ( result != Double.POSITIVE_INFINITY )
	 * 			|	then (result == this.getFirstCollision().getTimeToCollision() )
	 * @effect	...
	 * 			| if( this.getFirstCollision() != null)
	 * 			|	then ( Collision firstCollision = this.getFirstCollision()
	 * 			|			double tc = firstCollision.getTimeToCollision()
	 * 			|	 if (tc < dt)
	 * 			|		then (if(!Util.fuzzyLessThanOrEqualTo(tc,0) || Util.fuzzyEquals(tc,0))
	 * 			|		      then ( this.advanceObjects(tc) )
	 * 			|				firstCollision.execute()
	 * 			|				if(collisionListener != null)
	 *	    	| 				  then(if(firstCollision.getObject2()==null)
	 *	    	|	 				   then( collisionListener.boundaryCollision(firstCollision.getObject1(), 
	 *	    	|			 				firstCollision.getCollisionPosition()[0],
	 *	    	|			 				firstCollision.getCollisionPosition()[1]))
	 *	   		|						else 
	 *	    	|	 						(collisionListener.objectCollision(firstCollision.getObject1(), 
	 *	    	|			 				firstCollision.getObject2(), 
	 *	    	|			 				firstCollision.getCollisionPosition()[0],
	 *	    	|			 				firstCollision.getCollisionPosition()[1]))))
	 */
	public double evolveBeforeCollision(double dt, CollisionListener collisionListener) throws IllegalArgumentException
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
		
		if(tc <= dt)
		{
			if(!Util.fuzzyLessThanOrEqualTo(tc,0))
		     {
		           advanceObjects(tc);
		     }
			
			if(collisionListener != null){
		    	 if(firstCollision.getObject2()==null){
		    		 collisionListener.boundaryCollision(firstCollision.getObject1(), 
		    				 firstCollision.getCollisionPosition()[0],
		    				 	firstCollision.getCollisionPosition()[1]);
		    	 }
		    	 else {
		    		 collisionListener.objectCollision(firstCollision.getObject1(), 
		    				 firstCollision.getObject2(), 
		    				 	firstCollision.getCollisionPosition()[0],
		    				 		firstCollision.getCollisionPosition()[1]);
		    	 }
		     }
			
			 firstCollision.execute();
			
		}		
		
		return tc;
	}
	
	/**
	 * 
	 * @param 	dt
	 * @param   collisionListener
	 * @effect	...
	 * 			| double tc = this.evolveBeforeCollision(dt,collisionListener)
	 *			| double newdt = dt;
	 *			|	while(tc <= newdt)
	 *			|		then (newdt = newdt - tc
	 *			|				tc = this.evolveBeforeCollision(newdt,collisionListener))
	 *			|	advanceObjects(newdt)
	 * @throws 	IllegalArgumentException
	 * 			| (Double.isNaN(dt) || Double.isInfinite(dt) || dt < 0)
	 */
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException
	{
		if(Double.isNaN(dt) || Double.isInfinite(dt) || dt < 0)
			throw new IllegalArgumentException();
		
		double tc = evolveBeforeCollision(dt,collisionListener);
		double newdt = dt;
		
		while(tc <= newdt)
		{
			newdt = newdt - tc;
			tc = evolveBeforeCollision(newdt,collisionListener);
		}
		
		advanceObjects(newdt);
	}
}
