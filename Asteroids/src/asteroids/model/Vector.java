package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class representing a vector with a x and y component.
 * @author Julie Wouters & Stijn Wellens
 *
 */
public class Vector {

	/**
	 * The default constructor to create a vector.
	 * @effect	The x and y component are set to zero.
	 * 			| setComp(0,0)
	 */
	public Vector()
	{
		this.setComp(0, 0);
	}
	
	/**
	 * The constructor to create a vector with a given x and y component.
	 * @param 	xComp
	 * 			The given x component for this vector.
	 * @param 	yComp
	 * 			The given y component for this vector.
	 */
	public Vector(double xComp, double yComp) 
	{
		this.setComp(xComp, yComp);
	}
	
	private double xComp;
	private double yComp;
	
	/**
	 * Return the x component of this vector.
	 * 
	 */
	@Basic
	public double getXComp() {
		return this.xComp;
	}
	
	/**
	 * Return the y component of this vector.
	 * 
	 */
	@Basic
	public double getYComp() {
		return this.yComp;
	}
	
	/**
	 * Set the x and y component of this vector to a given x and y component. 
	 * @param 	x
	 * 			The given x component.
	 * @param 	y
	 * 			The given y component.
	 * @post	The new x and y component of this vector are equal to the given x and y component.
	 * 			| (new this).getXComp() == x
	 * 			| (new this).getYComp() == y
	 * @throws	IllegalArgumentException
	 * 			The given x or y component is not a number.
	 * 			| Double.isNaN(x) || Double.isNaN(y)
	 */
	public void setComp(double x, double y) throws IllegalArgumentException
	{
		if(Double.isNaN(x) || Double.isNaN(y))
			throw new IllegalArgumentException();
		this.xComp = x;
		this.yComp = y;
	}
	
	/**
	 * Calculate the modulus of this vector.
	 * 
	 * @return	The modulus of the vector.
	 * 			| result == Math.hypot(getXComp(),getYComp())
	 */
	public double getModulus() 
	{
		double modulus = Math.hypot(getXComp(), getYComp()) ;
				
		return modulus;
	}
	
	/**
	 * Calculate the modulus of the given vector components.
	 * 
	 * @param 	x
	 * 			The x component of a vector.
	 * @param 	y
	 * 			The y component of a vector.
	 * @return	The modulus of the vector. 
	 * 			| result == Math.hypot(x,y)
	 * @return	Positive infinity if one of the components is infinity.
	 * 			| if(((Double)x).isInfinite() || ((Double)y).isInfinite())
				|	then result == Double.POSITIVE_INFINITY;
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given doubles is not a number.
	 * 			| Double.isNaN(x) || Double.isNaN(y)
	 */
	public static double getModulus(double x, double y) throws IllegalArgumentException
	{
		if(Double.isNaN(x) || Double.isNaN(y))
			throw new IllegalArgumentException();
		
		if(((Double)x).isInfinite() || ((Double)y).isInfinite())
			return Double.POSITIVE_INFINITY;
		double modulus = Math.hypot(x, y);
				
		return modulus;
	}
	
	/**
	 * Calculate the sum of two given components of a vector.
	 * 
	 * @param 	comp1
	 * 			The first given vector component.
	 * @param 	comp2
	 * 			The second given vector component.
	 * @return	The sum of the two given vector components.
	 * 			| result == comp1 + comp2
	 * @return	The maximum value of a double, when the sum exceeds the maximum value.
	 * 			| if(comp1+comp2 > Double.MAX_VALUE)
	 * 			|	then result == Double.MAX_VALUE
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given doubles is not a number.
	 * 			| Double.isNaN(comp1) || Double.isNaN(comp2)
	 */
	public static double sumOfComponents(double comp1, double comp2) throws IllegalArgumentException
	{
		if(Double.isNaN(comp1) || Double.isNaN(comp2))
			throw new IllegalArgumentException();
		
		if( (Math.signum(comp1)==Math.signum(comp2)) && ( (Math.abs(comp1) > Double.MAX_VALUE - Math.abs(comp2)) || (Math.abs(comp2) > Double.MAX_VALUE - Math.abs(comp1))) )
			return Double.MAX_VALUE;
		
		return (comp1 + comp2);
		
	
	}
	
	/**
	 * Calculate the multiplication of two given components of a vector.
	 * 
	 * @param 	comp1
	 * 			The first given vector component.
	 * @param 	comp2
	 * 			The second given vector component.
	 * @return	The multiplication of the two given components.
	 * 			| result == comp1 * comp2
	 * @return	The minimum value of a double when the multiplication is lower than the minimum value of a double.
	 * 			| result == Double.MIN_VALUE
	 * @return	The maximum value of a double when the multiplication exceeds the maximum value of a double.
	 * 			| result == Double.MAX_VALUE
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given doubles is not a number.
	 * 			| Double.isNaN(comp1) || Double.isNaN(comp2) 			
	 */
	public static double multiplyComponents(double comp1, double comp2) throws IllegalArgumentException
	{
		if(Double.isNaN(comp1) || Double.isNaN(comp2))
			throw new IllegalArgumentException();
		
		try{
			if((Math.abs(comp1) < 1 && Math.abs(comp2) < 1) && (Math.abs(comp1) < Double.MIN_VALUE / Math.abs(comp2) || Math.abs(comp2) < Double.MIN_VALUE / Math.abs(comp1) ) )
				return Double.MIN_VALUE;
				
			if((Math.abs(comp1) > 1 && Math.abs(comp2) > 1) && (Math.abs(comp1) > Double.MAX_VALUE / Math.abs(comp2) || Math.abs(comp2) > Double.MAX_VALUE / Math.abs(comp1) ))
				return Double.MAX_VALUE;
		}
		catch(ArithmeticException ae)
		{
			return 0;
		}
			
		return comp1 * comp2;
		
	}
	
	/**
	 * Calculate the dot product of two vectors.
	 * 
	 * @param 	vector1
	 * 			The first of the two vectors.
	 * @param 	vector2
	 * 			The second of the two vectors.
	 * @return	The dot product of two vectors.
	 * 			| result == vector1.getXComp()*vector2.getXComp() + vector1.getYComp()*vector2.getYComp()
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given vectors is null.
	 * 			| vector1 == null || vector2 == null		
	 */
	public static double dotProduct(Vector vector1, Vector vector2) throws IllegalArgumentException
	{
		if(vector1 == null || vector2 == null)
			throw new IllegalArgumentException();
		
		double newX = multiplyComponents(vector1.getXComp(),vector2.getXComp());
		double newY = multiplyComponents(vector1.getYComp(),vector2.getYComp());
		
				
		return (sumOfComponents(newX,newY));
	}
	
	/**
	 * Calculate a multiplication of a scalar with a vector.
	 * 
	 * @param 	vector1
	 * 			The given vector.
	 * @param 	scalar
	 * 			The given scalar.
	 * @return	The multiplication of a scalar with a vector.
	 * 			| result == vector1 * scalar
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when the given vector is null or the scalar is not a number.
	 * 			| vector1 == null || Double.isNaN(scalar)	
	 */
	public static Vector multiplyScalar(Vector vector1, double scalar) throws IllegalArgumentException
	{
		if(vector1 == null || Double.isNaN(scalar))
			throw new IllegalArgumentException();
		
		double newX = multiplyComponents(vector1.getXComp(),scalar);
		double newY = multiplyComponents(vector1.getYComp(),scalar);
		
		return new Vector(newX,newY);
	}
	
	/**
	 * Calculate the subtraction of two vectors.
	 * 
	 * @param 	vector1
	 * 			The first vector given.
	 * @param 	vector2
	 * 			The second vector given.
	 * @return	The subtraction of the two given vectors.
	 * 			| result == vector1 - vector2
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given vectors is null.
	 * 			| vector1 == null || vector2 == null	
	 */
	public static Vector subtraction(Vector vector1, Vector vector2) throws IllegalArgumentException
	{
		if(vector1 == null || vector2 == null)
			throw new IllegalArgumentException();
		
		double newX = sumOfComponents(vector1.getXComp(),-(vector2.getXComp()));
		double newY = sumOfComponents(vector1.getYComp(),-(vector2.getYComp()));
		
		return new Vector(newX,newY);
	}
	
	/**
	 * Calculate the sum of two vectors.
	 * 
	 * @param 	vector1
	 * 			The first vector given.
	 * @param 	vector2
	 * 			The second vector given.
	 * @return	The sum of the two given vectors.
	 * 			| result == vector1 + vector2
	 * @throws 	IllegalArgumentException
	 * 			Throws exception when one of the given vectors is null.
	 * 			| vector1 == null || vector2 == null	
	 */
	public static Vector sum(Vector vector1, Vector vector2) throws IllegalArgumentException 
	{
		if(vector1 == null || vector2 == null)
			throw new IllegalArgumentException();
		
		double newX = sumOfComponents(vector1.getXComp(),vector2.getXComp());
		double newY = sumOfComponents(vector1.getYComp(),vector2.getYComp());
		
		return new Vector(newX,newY);
		
	}
	
	
}
