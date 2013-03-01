package asteroids.model;

import asteroids.IShip;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip {

	// Position
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
	
	// Velocity
	
	private double vx;
	private double vy;
	private final double maxV = 300000; // in km/s
	
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
	 * 			| result == (maxV >= 0) && (maxV <= LIGHTSPEED)
	 */
	public boolean isValidMaxVelocity(double maxV)
	{
		return ((maxV >= 0) && (maxV <= LIGHTSPEED));
	}
	
	/**
	 * 
	 * @param 	vx
	 * 			The x component of the velocity to be checked.
	 * @param	vy
	 * 			The y component of the velocity to be checked.
	 * @return	True if and only if the velocity is equal or less than the maximum speed of the ship.
	 */
	public boolean isValidVelocity(double vx, double vy)
	{
		
	}
	
}
