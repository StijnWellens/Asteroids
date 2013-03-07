package asteroids.model;

import asteroids.IShip;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a ship with a position, velocity, direction/
 * 
 * @invar	The x position of the ship must always be a valid x position.
 * 			| isValidX(getX())
 * @invar	The y position of the ship must always be a valid y position.
 * 			| isValidY(getY())
 * @invar	The maximum velocity of the ship must always be a valid maximum velocity.
 * 			| isValidMaxVelocity(getMaxVelocity())
 * @invar	The velocity of the ship must always be a valid velocity.
 * 			| isValidVelocity(getXVelocity(), getYVelocity())
 * @invar	The direction of the ship must always be a valid direction.
 * 			| isValidDirection(getDirection())
 * @author Stijn
 *
 */
public class Ship implements IShip {

	// Position: defensive programming
	
	private double xPosition;
	private double yPosition;
	
	/**
	 * Returns the x Position of the ship.
	 * 
	 * @return 	xPosition
	 * 			the x Position of the ship			
	 */
	@Basic 
	public double getX()
	{
		return this.xPosition;
	}
	
	/**
	 * Returns the y Position of the ship.
	 * 
	 * @return 	yPosition
	 * 			the y Position of the ship			
	 */
	@Basic
	public double getY()
	{
		return this.yPosition;
	}
	
	/**
	 * Checks whether the given x position is a valid x position for a ship.
	 * 
	 * @param 	x
	 * 			The x position to check.
	 * @return	True if and only if the given x position is a positive number.
	 * 			| result == (x>=0)
	 */
	public boolean isValidX(double x)
	{
		return ! Double.isNaN(x);
	}
	
	/**
	 * Checks whether the given y position is a valid y position for a ship.
	 * 
	 * @param 	y
	 * 			The y position to check.
	 * @return	True if and only if the given y position is a positive number.
	 * 			| result == (y>=0)
	 */
	public boolean isValidY(double y)
	{
		return ! Double.isNaN(y);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The new x position of this ship.
	 * @post	The new x position of this ship is equal to the given x position of this ship.
	 * 			| (new this).getX() == x
	 * @throws	IllegalArgumentException
	 * 			The given x position is not a valid x position.
	 * 			| ! isValidX(x)	
	 */
	private void setX(double x)
	{
		if(! isValidX(x))
			throw new IllegalArgumentException();
		this.xPosition = x;
	}
	
	/**
	 * 
	 * @param 	y
	 * 			The new y position of this ship.
	 * @post	The new y position of this ship is equal to the given y position of this ship.
	 * 			| (new this).getY() == y
	 * @throws	IllegalArgumentException
	 * 			The given y position is not a valid y position.
	 * 			| ! isValidY(y)	
	 */
	private void setY(double y)
	{
		if(! isValidY(y))
			throw new IllegalArgumentException();
		this.yPosition = y;
	}
	
	// Velocity: total programming
	
	private double vx;
	private double vy;
	private double maxV = LIGHTSPEED; // in km/s
	
	private static final double LIGHTSPEED = 300000;
	
	/**
	 *  Returns the x component of the velocity of the ship.
	 */
	@Basic
	public double getXVelocity()
	{
		return this.vx;
	}
	
	/**
	 *  Returns the y component of the velocity of the ship.
	 */
	@Basic
	public double getYVelocity()
	{
		return this.vy;
	}
	
	/**
	 * Returns the maximum velocity of the ship.
	 * 
	 */
	@Basic @Immutable
	public double getMaxVelocity()
	{
		return this.maxV;
	}
	
	/**
	 * Checks whether the given maximum velocity is valid.
	 * 
	 * @param 	maxV
	 * 			The given maximum velocity.
	 * @return	True if and only if the given maximum velocity is positive or 0 and not exceeding the speed of light.
	 * 			| if((maxV >= 0) && (maxV <= LIGHTSPEED))
	 * 			|	then result == true
	 */
	public boolean isValidMaxVelocity(double maxV)
	{
		return ((maxV >= 0) && (maxV <= LIGHTSPEED));
	}
	
	/**
	 * 
	 * @param 	maxV
	 * 			The given maximum velocity of the ship.
	 * @post	If the absolute value of the given maximum velocity is valid, the maximum velocity will be the absolute value of the given maximum velocity.
	 * 			| if (isValidMaxVelocity(Math.abs(maxV)))
	 * 			|	then (new this).getMaxVelocity() == Math.abs(maxV)
	 * @post	If the absolute value of the given maximum velocity exceeds the speed of light, then the maximum velocity will be the speed of light.
	 * 			| if (Math.abs(maxV) > LIGHTSPEED)
	 * 			|	then (new this).getMaxVelocity() == LIGHTSPEED
	 * 			 			
	 */
	public void setMaxVelocity(double maxV)
	{
		if (isValidMaxVelocity(Math.abs(maxV)))
		{
			this.maxV = Math.abs(maxV);
		}
		else 
		{
			this.maxV = LIGHTSPEED;
		}
		
	}
	
	/**
	 * Checks if the given velocity components are valid.
	 * 
	 * @param 	vx
	 * 			The x component of the velocity to be checked.
	 * @param	vy
	 * 			The y component of the velocity to be checked.
	 * @return	True if and only if the velocity is equal or less than the valid maximum speed of the ship.
	 * 			| if (vx <0 && vy<0)
	 * 			|	then result == false
	 * 			| else if( isValidMaxVelocity(this.maxV))&&(Math.sqrt(vx*vx+vy*vy) <= this.maxV) )
	 * 			|	then result == true
	 * 			| else
	 * 			|	then result == false
	 */
	public boolean isValidVelocity(double vx, double vy)
	{
		if(vx >=0 && vy >=0 && isValidMaxVelocity(this.maxV))
		{
			return Math.sqrt(vx*vx+vy*vy) <= this.maxV ;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the velocity of the ship.
	 * 
	 * @param 	vx
	 * 			The given x component of the velocity.
	 * @param 	vy
	 * 			The given y component of the velocity.
	 * @post	If the given velocity components are valid, then the velocity components will be the given componentsd.
	 * 			if(isValidVelocity(vx,vy))
	 * 				then (new this).getXVelocity() == vx && (new this).getYVelocity() == vy
	 */
	@Basic
	private void setVelocity(double vx, double vy)
	{	
		if(isValidVelocity(vx,vy))
		{
			this.vx = vx;
			this.vy = vy;
		}
	}
	
	
	//Direction: nominal programming
	
	private double direction;
	
	/**
	 * Returns the angle of the direction of this ship.
	 * @return	the angle of the direction of the ship
	 * 			| this.direction
	 */
	@Basic
	public double getDirection()
	{
		return this.direction;
	}
	
	/**
	 * Returns whether the given direction is valid or not.
	 * @param 	angle
	 * 			The angle in which the ship moves.
	 * @return	True if and only if
	 * 			| result == (Util.fuzzyEquals(angle,Direction.getAngle()) 
	 */
	public boolean isValidDirection(double angle)
	{
		
	}
	
}
