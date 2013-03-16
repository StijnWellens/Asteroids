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
	
	@Test(expected=ArithmeticException.class)
	  public void testSumOfComponents_IllegalCase() {
		assertEquals( Double.MAX_VALUE,Vector.sumOfComponents(Double.MAX_VALUE,5), Util.EPSILON);
	 }
	
	@Test
	  public void testMultiplyComponents() {
	    assertEquals(33.78,Vector.multiplyComponents(5.63, 6), Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testMultiplyComponents_IllegalCase() {
		Vector.multiplyComponents(5,Double.MAX_VALUE);
	 }
	
	@Test
	  public void testgetModulus() {
	    assertEquals(Math.sqrt(41),Vector.getModulus(5, 4), Util.EPSILON);
	   
	}
	
	@Test(expected=ArithmeticException.class)
	  public void testGetModulus_IllegalCase() {
		Vector.getModulus(Double.MAX_VALUE, Double.MAX_VALUE);
	 }
}
