package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.Util;
import asteroids.model.Vector;

public class VectorTest {

	@Test
	  public void testSumOfComponents() {
	    assertEquals(11.63,Vector.sumOfComponents(5.63, 6), Util.EPSILON);
	   
	}
	
	@Test
	  public void testSumOfComponents_NegativeComp() {
	    assertEquals(0.81,Vector.sumOfComponents(-4.19, 5), Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testSumOfComponents_IllegalCase() {
		assertEquals( Double.MAX_VALUE,Vector.sumOfComponents(Double.MAX_VALUE,5), Util.EPSILON);
	 }
	
	@Test
	  public void testMultiplyComponents() {
	    assertEquals(-33.78,Vector.multiplyComponents(5.63, -6), Util.EPSILON);
	   
	}
	
	@Test
	  public void testMultiplyComponentsWithCompLowerThan1() {
	    assertEquals(-3.78,Vector.multiplyComponents(0.63, -6), Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testMultiplyComponents_IllegalCase() {
		Vector.multiplyComponents(5,Double.MAX_VALUE);
	 }
	
	@Test
	  public void testGetModulus() {
	    assertEquals( Math.sqrt(41),Vector.getModulus(-5, 4),Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testGetModulus_IllegalCase() {
		Vector.getModulus(Double.MAX_VALUE, Double.MAX_VALUE);
	 }
	
	@Test
	  public void testDotProduct() {
		Vector vector1 = new Vector(0.333,-2);
		Vector vector2 = new Vector(3,12);
	    assertEquals(-23.001,Vector.dotProduct(vector1,vector2), Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testDotProduct_IllegalCase() {
		Vector vector1 = new Vector(Double.MAX_VALUE,-2);
		Vector vector2 = new Vector(3,12);
	    Vector.dotProduct(vector1,vector2);
	 }
}
