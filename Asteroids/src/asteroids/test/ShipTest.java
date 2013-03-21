package asteroids.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import asteroids.IShip;
import asteroids.ModelException;
import asteroids.Util;
import asteroids.model.Ship;

public class ShipTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	  public void testConstructorShip() {
	    IShip ship = new Ship(100, 200, 10, -10, 20, -Math.PI);
	    assertNotNull(ship);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testConstructorShip_IllegalCase() {
	    Ship ship = new Ship(Double.NaN, 200, 10, -10, 20, -Math.PI);
	 }
	
	@Test
	  public void testGetCoordinate() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI);
	    assertEquals(100,ship.getX(), Util.EPSILON);
	    assertEquals(200,ship.getY(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testGetCoordinate_IllegalCase() {
	    Ship ship = new Ship(Double.NaN, Double.NaN, 10, -10, 20, -Math.PI);
	 }
	
	@Test
	  public void testSetCoordinate() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI);
	    ship.setPosition(150,150);
	    assertEquals(150,ship.getX(), Util.EPSILON);
	    assertEquals(150,ship.getY(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSetCoordinate_IllegalCase() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI);
	    ship.setPosition(Double.NaN, Double.NaN);
	}
	
	@Test
	  public void testGetVelocity() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI);
	    assertEquals(10,ship.getXVelocity(), Util.EPSILON);
	    assertEquals(-10,ship.getYVelocity(), Util.EPSILON);
	}
	
	@Test
	  public void testGetVelocity_IllegalCase() {
	    Ship ship = new Ship(100, 200, Double.NaN, Double.NaN, 20, -Math.PI);
	    assertEquals(0,ship.getXVelocity(), Util.EPSILON);
	    assertEquals(0,ship.getYVelocity(), Util.EPSILON);
	 }
	
	@Test
	public void testIsValidVelocityComp(){
		Ship ship = new Ship();
		assertTrue(ship.isValidVelocityComp(50000));
	}
	
	@Test
	public void testIsValidVelocityComp_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidVelocityComp(Double.NaN));
	}
	
	@Test
	public void testIsValidVelocity(){
		Ship ship = new Ship();
		assertTrue(ship.isValidVelocity(150000, 150000));
	}
	
	@Test
	public void testIsValidVelocity_ExceedsMaxVelocity(){
		Ship ship = new Ship();
		assertFalse(ship.isValidVelocity(300000,300000));
	}
	
	@Test
	public void testIsValidVelocity_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidVelocity(Double.NaN, Double.NaN));
	}
	
	@Test
	  public void testGetMaxVelocity() {
	    Ship ship = new Ship();
	    assertEquals(Ship.LIGHTSPEED,ship.getMaxVelocity(), Util.EPSILON);
	 }
	
	@Test
	  public void testSetMaxVelocity() {
	    Ship ship = new Ship();
	    ship.setMaxVelocity(Ship.LIGHTSPEED-5000);
	    assertEquals(Ship.LIGHTSPEED-5000,ship.getMaxVelocity(), Util.EPSILON);
	 }
	
	@Test
	  public void testSetMaxVelocity_IllegalCase() {
	    Ship ship = new Ship();
	    ship.setMaxVelocity(Ship.LIGHTSPEED+20000);	
	    assertEquals(Ship.LIGHTSPEED, ship.getMaxVelocity(), Util.EPSILON);
	 }
	
	@Test
	public void testIsValidMaxVelocity() {
		Ship ship = new Ship();
		assertTrue(ship.isValidMaxVelocity(120000));
	}
	
	@Test
	public void testIsValidMaxVelocity_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMaxVelocity(Double.NaN));
	}
	
	@Test
	public void testIsValidMaxVelocity_NegativeAmount(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMaxVelocity(-25));
	}
	
	@Test
	public void testIsValidMaxVelocity_ExceedsLightspeed(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMaxVelocity(Ship.LIGHTSPEED + 20000));
	}
	
	@Test
	public void testThrust(){
		Ship ship = new Ship();
		ship.setDirection(3.14/4);
		ship.thrust(100000);
		assertEquals(100000*Math.cos(3.14/4),ship.getXVelocity(),Util.EPSILON);
		assertEquals(100000*Math.sin(3.14/4),ship.getYVelocity(),Util.EPSILON);
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
	
	@Test
	public void testThrust_ExceedsLightspeed(){
		Ship ship = new Ship();
		ship.setDirection(3.14/4);
		ship.thrust(Ship.LIGHTSPEED);
		double tempVx =Ship.LIGHTSPEED * Math.cos(3.14/4);
		double tempVy =Ship.LIGHTSPEED * Math.sin(3.14/4);
		double tempAmount = (ship.getMaxVelocity())
				/ Math.sqrt(tempVx * tempVx + tempVy * tempVy);
		assertEquals(tempVx*tempAmount,ship.getXVelocity(),Util.EPSILON);		
		assertEquals(tempVy*tempAmount,ship.getYVelocity(),Util.EPSILON);	
	}
	
	@Test
	public void testGetDirection(){
		Ship ship = new Ship(0,0,0,0,1,3.14/4);
		assertEquals(3.14/4,ship.getDirection(),Util.EPSILON);
	}
	
	@Test
	public void testIsValidDirection(){
		Ship ship = new Ship();
		assertTrue(ship.isValidDirection(3.14/4));	
	}
	
	@Test
	public void testIsValidDirection_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDirection(Double.NaN));
	}
	
	@Test
	public void testIsValidDirection_NegativeAmount(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDirection(-3.14/4));
	}
	
	@Test
	public void testIsValidDirection_ExceedsMaximumValue(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDirection(3*3.14));
	}
	
	@Test
	public void testSetDirection(){
		Ship ship = new Ship();
		ship.setDirection(3.14/4);
		assertEquals(3.14/4, ship.getDirection(),Util.EPSILON);
	}
	
	@Test (expected=AssertionError.class)
	public void testSetDirection_IllegalCase(){
		Ship ship = new Ship();
		ship.setDirection(Double.NaN);
		assertEquals(3.14/2,ship.getDirection(),Util.EPSILON);
	}
	
	@Test
	public void testTurn(){
		Ship ship = new Ship();
		ship.setDirection(3.14/2);
		ship.turn(3.14/4);
		assertEquals((3*3.14)/4,ship.getDirection(),Util.EPSILON);	
	}
	
	@Test (expected=AssertionError.class)
	public void testTurn_IllegalCase(){
		Ship ship = new Ship();
		ship.setDirection(3.14/2);
		ship.turn(2*3.14);
		assertEquals(3.14/2,ship.getDirection(),Util.EPSILON);
	}
	
	@Test
	public void testGetLowerBoundRadius(){
		Ship ship = new Ship();
		assertEquals(0,ship.getLowerBoundRadius(),Util.EPSILON);
	}
	
	@Test
	public void testIsValidLowerBoundRadius(){
		assertTrue(Ship.isValidLowerBoundRadius(5));
	}
	
	@Test
	public void testIsValidLowerBoundRadius_IllegalCase(){
		assertFalse(Ship.isValidLowerBoundRadius(Double.NaN));
	}
	
	@Test
	public void testIsValidLowerBoundRadius_NegativeAmount(){
		assertFalse(Ship.isValidLowerBoundRadius(-25));
	}
	
	@Test
	public void testGetRadius(){
		Ship ship = new Ship(0,0,0,0,10,3.14/2);
		assertEquals(10, ship.getRadius(),Util.EPSILON);
	}
	
	@Test
	public void testIsValidRadius(){
		Ship ship = new Ship();
		assertTrue(ship.isValidRadius(5));
	}
	
	@Test
	public void testIsValidRadius_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidRadius(Double.NaN));
	}
	
	@Test
	public void testIsValidRadius_BelowLowerBound(){
		Ship ship = new Ship();
		assertFalse(ship.isValidRadius(-20));
	}
	
	@Test
	public void testSetRadius(){
		Ship ship = new Ship();
		ship.setRadius(30);
		assertEquals(30,ship.getRadius(),Util.EPSILON);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetRadius_IllegalCase(){
		Ship ship = new Ship();
		ship.setRadius(Double.NaN);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetRadius_BelowLowerBound(){
		Ship ship = new Ship();
		ship.setRadius(-10);
	}
	
	@Test
	public void testIsValidDuration(){
		Ship ship = new Ship();
		assertTrue(ship.isValidDuration(20));
	}
	
	@Test
	public void testIsValidDuration_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(Double.NaN));
	}
	
	@Test
	public void testIsValidDuration_NegativeAmount(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(-20));
	}
	
	@Test
	public void testMove(){
		Ship ship = new Ship(0,0,10000,10000,10,3.14/2);
		ship.move(20);
		assertEquals(10000 * 20, ship.getX(),Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMove_IllegalCase(){
		Ship ship = new Ship();
		ship.move(Double.NaN);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMove_NegativeAmount(){
		Ship ship = new Ship();
		ship.move(-20);
	}
	
	@Test
	public void testGetDistanceBetween(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2);
		Ship ship2 = new Ship(40,40,0,0,10,3.14/2);
		assertEquals(Math.sqrt(800)-10-10,Ship.getDistanceBetween(ship1, ship2),Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_ShipIsNull(){
		Ship ship = new Ship();
		Ship ship2 = null;
		Ship.getDistanceBetween(ship,ship2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_IllegalCase(){
		Ship ship1 = new Ship(Double.NaN,Double.NaN,0,0,10,3.14/2);
		Ship ship2 = new Ship(40,40,0,0,10,3.14/2);
		Ship.getDistanceBetween(ship1,ship2);
	}
	
	@Test
	public void testOverlap(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2);
		Ship ship2 = new Ship(30,30,0,0,10,3.14/2);
		assertTrue(Ship.overlap(ship1, ship2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOverlap_ShipIsNull(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2);
		Ship ship2 = null;
		Ship.overlap(ship1, ship2);
	}
	
	@Test
	public void testOverlap_False(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2);
		Ship ship2 = new Ship(40,40,0,0,10,3.14/2);
		assertFalse(Ship.overlap(ship1, ship2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetTimeToCollision_ShipIsNull(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2);
		Ship ship2 = null;
		Ship.getTimeToCollision(ship1, ship2);
	}
	
	@Test 
	  public void testGetTimeToCollision() {
	    Ship ship1 = new Ship(0, 0, 0, 0, 10, 0);
	    Ship ship2 = new Ship(20, 20, -20,-20 , 10, (5* Math.PI)/4);
	    
	    assertEquals((-(-800+ Math.sqrt(320000))/(800)),Ship.getTimeToCollision(ship1,ship2), Util.EPSILON);
	 }
	
	@Test
	public void testGetTimeToCollision_NoCollision(){
		Ship ship1 = new Ship(0, 0, 0, 0, 10, 0);
	    Ship ship2 = new Ship(200, 200, 200, 200 , 100, Math.PI/4);
	    assertEquals(Double.POSITIVE_INFINITY,Ship.getTimeToCollision(ship1, ship2),Util.EPSILON);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetCollisionPosition_ShipIsNull(){
		Ship ship1 = new Ship();
	    Ship ship2 = null;
	    Ship.getCollisionPosition(ship1, ship2);
	}
	
	@Test
	public void testGetCollisionPosition(){
		Ship ship1 = new Ship(0, 0, 0, 0, 10, 0);
	    Ship ship2 = new Ship(20, 20, -20, -20 , 10, Math.PI/4);
	    double[] collision = new double[2];
	    collision[0] = 5*Math.sqrt(2);
	    collision[1] = 5*Math.sqrt(2);
	    assertArrayEquals(collision, Ship.getCollisionPosition(ship1, ship2), Util.EPSILON);
	}
	
	

}
