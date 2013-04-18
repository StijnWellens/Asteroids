package asteroids.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.model.*;

public class ShipTest { 

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	  public void testConstructorShip() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI, 10);
	    assertNotNull(ship);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testConstructorShip_IllegalCase() {
	    new Ship(Double.NaN, 200, 10, -10, 20, -Math.PI,10 );
	 }
	
	@Test(expected=IllegalArgumentException.class)
	  public void testConstructorShip_IllegalCaseRadius() {
	    new Ship(100, 200, 10, -10, -20, -Math.PI,10 );
	 }
	
	@Test
	  public void testGetCoordinate() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    assertEquals(100,ship.getX(), Util.EPSILON);
	    assertEquals(200,ship.getY(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testGetCoordinate_IllegalCase() {
	    new Ship(Double.NaN, Double.NaN, 10, -10, 20, -Math.PI,10);
	 }
	
	@Test
	  public void testSetCoordinate() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    ship.setPosition(150,150);
	    assertEquals(150,ship.getX(), Util.EPSILON);
	    assertEquals(150,ship.getY(), Util.EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSetCoordinate_InvalidYPosition() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    ship.setPosition(20, Double.NaN);
	}
	
	@Test(expected=IllegalArgumentException.class)
	  public void testSetCoordinate_InvalidXYPositiion() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    ship.setPosition(Double.NaN, Double.NaN);
	}
	
	@Test
	  public void testGetVelocity() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    assertEquals(10,ship.getXVelocity(), Util.EPSILON);
	    assertEquals(-10,ship.getYVelocity(), Util.EPSILON);
	}
	
	@Test
	  public void testGetVelocity_IllegalCase() {
	    Ship ship = new Ship(100, 200, Double.NaN, Double.NaN, 20, -Math.PI,10);
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
	public void testIsValidVelocity_IllegalCase2(){
		Ship ship = new Ship();
		assertFalse(ship.isValidVelocity(10, Double.NaN));
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
	public void testGetDirection(){
		Ship ship = new Ship(0,0,0,0,11,3.14/4,10);
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
		assertEquals(0,Ship.getLowerBoundRadius(),Util.EPSILON);
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
	public void testIsValidLowerBoundRadius_Infinity(){
		assertFalse(Ship.isValidLowerBoundRadius(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testGetRadius(){
		Ship ship = new Ship(0,0,0,0,11,3.14/2,10);
		assertEquals(11, ship.getRadius(),Util.EPSILON);
	}
	
	@Test
	public void testIsValidRadius(){
		Ship ship = new Ship();
		assertTrue(ship.isValidRadius(11));
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
	public void testIsValidRadius_Infinity(){
		Ship ship = new Ship();
		assertFalse(ship.isValidRadius(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testIsValidDuration(){
		Ship ship = new Ship();
		assertTrue(ship.isValidDuration(20.));
	}
	
	@Test
	public void testIsValidDuration_IllegalCase2(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testIsValidDuration_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(Double.NaN));
	}
	
	@Test
	public void testIsValidDuration_NegativeAmount(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(-20.));
	}
	
	@Test
	public void testIsValidDuration_Infinite(){
		Ship ship = new Ship();
		assertFalse(ship.isValidDuration(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testMove(){
		Ship ship = new Ship(0,0,10000,10000,11,3.14/2,10);
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
	public void testGetMass(){
		Ship ship = new Ship(0,0,0,0,11,3.14/4,10);
		assertEquals(10,ship.getMass(),Util.EPSILON);
	}
	
	@Test
	public void testIsValidMass(){
		Ship ship = new Ship();
		assertTrue(ship.isValidMass(10));	
	}
	
	@Test
	public void testIsValidMass_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMass(Double.NaN));
	}
	
	@Test
	public void testIsValidMass_NegativeAmount(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMass(-10));
	}
	
	@Test
	public void testIsValidMass_Infinity(){
		Ship ship = new Ship();
		assertFalse(ship.isValidMass(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testSetMass(){
		Ship ship = new Ship();
		ship.setMass(20);
		assertEquals(20, ship.getMass(),Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetMass_IllegalCase(){
		Ship ship = new Ship();
		ship.setMass(Double.NaN);
	}
	
	@Test
	public void testGetDistanceBetween(){
		Ship ship1 = new Ship(20,20,0,0,11,3.14/2,10);
		Ship ship2 = new Ship(40,40,0,0,11,3.14/2,10);
		assertEquals(Math.sqrt(800)-11-11,Ship.getDistanceBetween(ship1, ship2),Util.EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_ShipIsNull(){
		Ship ship = new Ship();
		Ship ship2 = null;
		Ship.getDistanceBetween(ship,ship2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_ShipsAreNull(){
		Ship ship = null;
		Ship ship2 = null;
		Ship.getDistanceBetween(ship,ship2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetDistanceBetween_IllegalCase(){
		Ship ship1 = new Ship(Double.NaN,Double.NaN,0,0,10,3.14/2,10);
		Ship ship2 = new Ship(40,40,0,0,10,3.14/2,10);
		Ship.getDistanceBetween(ship1,ship2);
	}
	
	@Test
	public void testOverlap(){
		Ship ship1 = new Ship(20,20,0,0,11,3.14/2,10);
		Ship ship2 = new Ship(30,30,0,0,11,3.14/2,10);
		assertTrue(Ship.overlap(ship1, ship2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOverlap_ShipIsNull(){
		Ship ship1 = new Ship(20,20,0,0,10,3.14/2,10);
		Ship ship2 = null;
		Ship.overlap(ship1, ship2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testOverlap_ShipsAreNull(){
		Ship ship1 = null;
		Ship ship2 = null;
		Ship.overlap(ship1, ship2);
	}
	
	@Test
	public void testOverlap_False(){
		Ship ship1 = new Ship(20,20,0,0,11,3.14/2,10);
		Ship ship2 = new Ship(40,40,0,0,11,3.14/2,10);
		assertFalse(Ship.overlap(ship1, ship2));
	}
	
	
	
	@Test
	public void testGetWorld(){
		Ship ship = new Ship();
		assertEquals(null,ship.getWorld());
	}
	
	@Test
	public void testCanHaveAsWorld(){
		Ship ship1 = new Ship(20, 0, 0, 20, 11, 0,10);
		Bullet bullet = new Bullet(20, 0, 0, 20);
		Ship ship2 = new Ship(80, 80, 0, 20, 11, 0, 10);
		World world = new World(100,100);
		bullet.flyIntoWorld(world);
		ship2.flyIntoWorld(world);
		assertTrue(ship1.canHaveAsWorld(world));	
	}
	
	
	@Test
	public void testCanHaveAsWorld_OutBorderX(){
		Ship ship = new Ship(60, 0, 0, 20, 11, 0,10);
		World world = new World(50,50);
		assertFalse(ship.canHaveAsWorld(world));	
	}
	
	@Test
	public void testCanHaveAsWorld_OutBorderY(){
		Ship ship = new Ship(20, 60, 0, 20, 11, 0,10);
		World world = new World(50,50);
		assertFalse(ship.canHaveAsWorld(world));	
	}
	
	@Test
	public void testCanHaveAsWorld_Overlap(){
		Ship ship1 = new Ship(20, 40, 0, 20, 11, 0, 10);
		Ship ship2 = new Ship(40, 40, 0, 20, 11, 0, 10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		assertFalse(ship2.canHaveAsWorld(world));	
	}
	
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
	

}
