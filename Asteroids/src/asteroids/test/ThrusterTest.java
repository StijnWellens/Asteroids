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
	

}
