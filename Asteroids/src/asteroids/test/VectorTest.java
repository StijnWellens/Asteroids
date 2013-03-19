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
	
	@Test
	  public void testSumOfComponents_ZeroComp() {
	    assertEquals(-4,Vector.sumOfComponents(-4,0), Util.EPSILON);
	   
	}
	
	@Test
	  public void testSumOfComponents_Max_Value() {
		assertEquals( Double.MAX_VALUE,Vector.sumOfComponents(Double.MAX_VALUE,5), Util.EPSILON);
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSumOfComponents_IllegalCase() {
		Vector.sumOfComponents(Double.NaN,5);
	 }
	
	@Test
	  public void testMultiplyComponents() {
	    assertEquals(-33.78,Vector.multiplyComponents(5.63, -6), Util.EPSILON);
	   
	}
	
	@Test
	  public void testMultiplyComponentsWithCompLowerThan1() {
	    assertEquals(-3.78,Vector.multiplyComponents(0.63, -6), Util.EPSILON);
	   
	}
	
	@Test
	  public void testMultiplyComponents_Max_Value() {
		assertEquals(Double.MAX_VALUE, Vector.multiplyComponents(5,Double.MAX_VALUE), Util.EPSILON);
	 }
	
	@Test
	  public void testMultiplyComponents_Min_Value() {
		assertEquals(Double.MIN_VALUE, Vector.multiplyComponents(0.10,Double.MIN_VALUE), Util.EPSILON);
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testMultiplyComponents_IllegalCase() {
		Vector.multiplyComponents(5,Double.NaN);
	 }
	
	@Test
	  public void testGetModulus() {
	    assertEquals( Math.sqrt(41),Vector.getModulus(-5, 4),Util.EPSILON);
	   
	}
	
	@Test
	  public void testGetModulus_Infinity() {
		assertEquals(Double.POSITIVE_INFINITY, Vector.getModulus(Double.MAX_VALUE, Double.MAX_VALUE), Util.EPSILON);
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testGetModulus_IllegalCase() {
		Vector.getModulus(Double.NaN, Double.MAX_VALUE);
	 }
	
	@Test
	  public void testDotProduct() {
		Vector vector1 = new Vector(0.333,-2);
		Vector vector2 = new Vector(3,12);
	    assertEquals(-23.001,Vector.dotProduct(vector1,vector2), Util.EPSILON);
	   
	}
	
	@Test
	  public void testDotProduct_Max_Value() {
		Vector vector1 = new Vector(Double.MAX_VALUE,-2);
		Vector vector2 = new Vector(3,12);
	    assertEquals(Double.MAX_VALUE, Vector.dotProduct(vector1,vector2), Util.EPSILON);
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testDotProduct_IllegalCase() {
		Vector vector1 = null;
		Vector vector2 = new Vector(3,12);
	    Vector.dotProduct(vector1,vector2);
	 }
	
	@Test
	  public void testMultiplyScalar() {
		Vector vector1 = new Vector(0.333,-2);
		double scalar = 3;
		Vector vector2 = new Vector(0.999, -6);
	    assertEquals(vector2.getXComp(),Vector.multiplyScalar(vector1,scalar).getXComp(), Util.EPSILON);
	    assertEquals(vector2.getYComp(),Vector.multiplyScalar(vector1,scalar).getYComp(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testMultiplyScalar_IllegalCase_ScalarNaN() {
		Vector vector1 = new Vector(0.333,-2);
		double scalar = Double.NaN ;
		Vector.multiplyScalar(vector1,scalar);
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testMultiplyScalar_IllegalCase_VectorNull() {
		Vector vector1 = null;
		double scalar = 3 ;
		Vector.multiplyScalar(vector1,scalar);
	 }
	
	@Test
	  public void testSubtraction() {
		Vector vector1 = new Vector(0.333,-2);
		Vector vector2 = new Vector(3,12);
		Vector vector3 = new Vector(-2.667, -14);
		assertEquals(vector3.getXComp(),Vector.subtraction(vector1,vector2).getXComp(), Util.EPSILON);
	    assertEquals(vector3.getYComp(),Vector.subtraction(vector1,vector2).getYComp(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSubtraction_IllegalCase() {
		Vector vector1 = null;
		Vector vector2 = new Vector(3,12);
	    Vector.subtraction(vector1,vector2);
	 }
	@Test
	  public void testSum() {
		Vector vector1 = new Vector(0.333,-2);
		Vector vector2 = new Vector(3,12);
		Vector vector3 = new Vector(3.333, 10);
		assertEquals(vector3.getXComp(),Vector.sum(vector1,vector2).getXComp(), Util.EPSILON);
	    assertEquals(vector3.getYComp(),Vector.sum(vector1,vector2).getYComp(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSum_IllegalCase() {
		Vector vector1 = null;
		Vector vector2 = new Vector(3,12);
	    Vector.sum(vector1,vector2);
	 }
}
