package asteroids.model;

public class Vector {

	public Vector()
	{
		setComp(0, 0);
	}
	
	public Vector(double xComp, double yComp)
	{
		setComp(xComp, yComp);
	}
	
	private double xComp;
	private double yComp;
	
	public double getXComp() {
		return this.xComp;
	}
	
	public double getYComp() {
		return this.yComp;
	}
	
	public void setComp(double x, double y)
	{
		this.xComp = x;
		this.yComp = y;
	}
	
	public double getModulus()
	{
		return Math.hypot(getXComp(), getYComp()) ;
	}
	
	public static double getModulus(double x, double y)
	{
		return Math.hypot(x, y);
	}
	
	public static double dotProduct(Vector vector1, Vector vector2)
	{
		return (vector1.getXComp()*vector2.getXComp()) + (vector1.getYComp()*vector2.getYComp());
	}
	
	public static Vector subtraction(Vector vector1, Vector vector2)
	{
		return new Vector(vector1.getXComp()-vector2.getXComp(), vector1.getYComp()-vector2.getYComp());
	}
	
	
}
