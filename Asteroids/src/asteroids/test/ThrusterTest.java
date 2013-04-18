package asteroids.test;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.Util;
import asteroids.model.*;

public class ThrusterTest {

	@Test
	public void testGenerateAcceleration(){
		Thruster thruster = new Thruster(true, 1.1E18);
		Vector acceleration = thruster.generateAcceleration((3*(Math.PI)/2), 1, 10);
				
		assertEquals(0,acceleration.getXComp(),Util.EPSILON);
		assertEquals((-1.1E19),acceleration.getYComp(),Util.EPSILON);
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
