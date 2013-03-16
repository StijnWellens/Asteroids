package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;

public class Vector {

	/**
	 * The default constructor to create a vector.
	 * @effect	The x and y component are set to zero.
	 * 			| setComp(0,0)
	 */
	public Vector()
	{
		setComp(0, 0);
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
		setComp(xComp, yComp);
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
	public void setComp(double x, double y) 
	{
		this.xComp = x;
		this.yComp = y;
	}
	
	/**
	 * 
	 * @return
	 * @throws ArithmeticException
	 */
	public double getModulus() throws ArithmeticException
	{
		double modulus = Math.hypot(getXComp(), getYComp()) ;
		//if(modulus == Double.POSITIVE_INFINITY )
			//throw new ArithmeticException();
		
		return modulus;
	}
	
	public static double getModulus(double x, double y) throws ArithmeticException
	{
		if(((Double)x).isInfinite() || ((Double)y).isInfinite())
			return Double.POSITIVE_INFINITY;
		double modulus = Math.hypot(x, y);
		//if(modulus == Double.POSITIVE_INFINITY)
			//throw new ArithmeticException();
		
		return modulus;
	}
	
	public static double sumOfComponents(double comp1, double comp2) throws ArithmeticException
	{
		
		/*if(Math.signum(comp1) == Math.signum(comp2) && Math.abs(comp1) > Double.MAX_VALUE - Math.abs(comp2))
		{
			return Double.MAX_VALUE;
		}
		else if(Math.signum(comp1) == Math.signum(comp2) && Math.abs(comp1) > Double.MIN_VALUE + Math.abs(comp2))
		{
			return Double.MIN_VALUE;
		}
		else
		{
			return comp1 + comp2;
		}*/
		
		if( (Math.signum(comp1)==Math.signum(comp2)) && ( (Math.abs(comp1) > Double.MAX_VALUE - Math.abs(comp2)) || (Math.abs(comp2) > Double.MAX_VALUE - Math.abs(comp1))) )
			return Double.MAX_VALUE;
			//throw new ArithmeticException();
		
		/*if( (comp1>0 && comp2<0) && ( comp1 < Double.MIN_VALUE - comp2) )
				throw new ArithmeticException();
		if( (comp2>0 && comp1<0) && ( comp2 < Double.MIN_VALUE - comp1) )
			throw new ArithmeticException();*/
		
		return comp1 + comp2;
		
	
	}
	
	public static double multiplyComponents(double comp1, double comp2) throws ArithmeticException
	{
		if((Math.abs(comp1) < 1 && Math.abs(comp2) < 1) && (Math.abs(comp1) < Double.MIN_VALUE / Math.abs(comp2) || Math.abs(comp2) < Double.MIN_VALUE / Math.abs(comp1) ) )
			return Double.MIN_VALUE;
			//throw new ArithmeticException();
		if((Math.abs(comp1) > 1 && Math.abs(comp2) > 1) && (Math.abs(comp1) > Double.MAX_VALUE / Math.abs(comp2) || Math.abs(comp2) > Double.MAX_VALUE / Math.abs(comp1) ))
			return Double.MAX_VALUE;
			//throw new ArithmeticException();
		
		return comp1 * comp2;
		/*if(((Double)comp1).isInfinite() || ((Double)comp2).isInfinite())
			return Double.POSITIVE_INFINITY;
		float comp1float = ((Double)comp1).floatValue();
		float comp2float = ((Double)comp2).floatValue();
		
		return comp1float * comp2float;*/
	}
		
	public static double dotProduct(Vector vector1, Vector vector2) throws ArithmeticException
	{
		double newX = multiplyComponents(vector1.getXComp(),vector2.getXComp());
		double newY = multiplyComponents(vector1.getYComp(),vector2.getYComp());
		
				
		return (sumOfComponents(newX,newY));
	}
	
	public static Vector multiplyScalar(Vector vector1, double scalar) throws ArithmeticException, IllegalArgumentException
	{
		double newX = multiplyComponents(vector1.getXComp(),scalar);
		double newY = multiplyComponents(vector1.getYComp(),scalar);
		
		return new Vector(newX,newY);
	}
	
	public static Vector subtraction(Vector vector1, Vector vector2) throws ArithmeticException, IllegalArgumentException
	{
		double newX = sumOfComponents(vector1.getXComp(),-vector2.getXComp());
		double newY = sumOfComponents(vector1.getYComp(),-vector2.getYComp());
		
		return new Vector(newX,newY);
	}
	
	public static Vector sum(Vector vector1, Vector vector2) throws ArithmeticException
	{
		double newX = sumOfComponents(vector1.getXComp(),vector2.getXComp());
		double newY = sumOfComponents(vector1.getYComp(),vector2.getYComp());
		
		return new Vector(newX,newY);
		
	}
	
	
}
