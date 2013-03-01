package asteroids.model;

import asteroids.IShip;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip {

	// Position
	private double xPosition;
	private double yPosition;
	
	/**
	 * 
	 * @return
	 */
	@Basic 
	public double getX()
	{
		return this.xPosition;
	}
	
	/**
	 * 
	 * @return
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
		return x >= 0;
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
		return y >= 0;
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
}
