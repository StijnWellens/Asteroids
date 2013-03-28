package asteroids.model;

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
}
