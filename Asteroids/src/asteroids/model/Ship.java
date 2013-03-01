package asteroids.model;

import asteroids.IShip;
import be.kuleuven.cs.som.annotate.*;

public class Ship implements IShip {

	// Position
	private double xPosition;
	private double yPosition;
	
	@Basic 
	public double getX()
	{
		return this.xPosition;
	}
	
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
		//this.xPosition = x;
	}
	
	private void setY(double y)
	{
		//this.yPosition = y;
	}
}
