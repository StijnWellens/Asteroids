package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.Util;
import asteroids.model.Ship;

public class ThrusterTest {

	@Test
	public void testThrust(){
		Ship ship = new Ship(0, 0, 10, 20, 10, -(Math.PI)/2, 1);
		ship.getThruster().setEnabled(true);
		ship.thrust(10);
		assertEquals(10,ship.getXVelocity(),Util.EPSILON);
		assertEquals(20*(-1.1E20),ship.getYVelocity(),Util.EPSILON);
	}
	
	@Test
	public void testThrust_IllegalCase(){
		Ship ship = new Ship();
		ship.setDirection(3.14/4);
		ship.thrust(Double.NaN);
		assertEquals(0,ship.getXVelocity(),Util.EPSILON);
		assertEquals(0,ship.getYVelocity(),Util.EPSILON);
	}
	
	@Test
	public void testThrust_NegativeAmount(){
		Ship ship = new Ship();
		ship.setDirection(3.14/4);
		ship.thrust(-200000);
		assertEquals(0,ship.getXVelocity(),Util.EPSILON);
		assertEquals(0,ship.getYVelocity(),Util.EPSILON);
	}
	

}
