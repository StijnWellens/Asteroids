package asteroids.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.model.*;
import asteroids.model.SpaceObject.State;

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
	  public void testGetPosition() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    assertEquals(100,ship.getPosition().getXComp(), Util.EPSILON);
	    assertEquals(200,ship.getPosition().getYComp(), Util.EPSILON);
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
	  public void testGetVelocityVector() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    assertEquals(10,ship.getVelocity().getXComp(), Util.EPSILON);
	    assertEquals(-10,ship.getVelocity().getYComp(), Util.EPSILON);
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
	public void testIsValidVelocityComponent_Infinity(){
		Ship ship = new Ship();
		assertFalse(ship.isValidVelocityComp(Double.POSITIVE_INFINITY));
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
	  public void testSetVelocity_ExceedsMaxV() {
	    Ship ship = new Ship();
	    ship.setVelocity(600000, 200000);
	    assertEquals((ship.getMaxVelocity()/Math.sqrt(6E6*6E6+2E6*2E6))*6E6,ship.getXVelocity(), Util.EPSILON);
	    assertEquals((ship.getMaxVelocity()/Math.sqrt(6E6*6E6+2E6*2E6))*2E6,ship.getYVelocity(), Util.EPSILON);
	 }
	
	@Test
	  public void testSetVelocity_NotValid() {
	    Ship ship = new Ship();
	    ship.setVelocity(Double.NaN, 20);
	    assertEquals(0,ship.getXVelocity(), Util.EPSILON);
	    assertEquals(0,ship.getYVelocity(), Util.EPSILON);
	 }
	
	@Test
	  public void testSetVelocity() {
	    Ship ship = new Ship(100, 200, 10, -10, 20, -Math.PI,10);
	    Vector velocity = new Vector(20,20);
	    ship.setVelocity(velocity);
	    assertEquals(20,ship.getXVelocity(), Util.EPSILON);
	    assertEquals(20,ship.getYVelocity(), Util.EPSILON);
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
	public void testSetDirection_IllegalCase1(){
		Ship ship = new Ship();
		ship.setDirection(Double.NaN);	
		assertEquals(Double.NaN, ship.getDirection(), Util.EPSILON);
	}
	
	@Test (expected=AssertionError.class)
	public void testSetDirection_IllegalCase2(){
		Ship ship = new Ship();
		ship.setDirection((5*Math.PI/2));		
	}
	
	@Test (expected=AssertionError.class)
	public void testSetDirection_IllegalCase3(){
		Ship ship = new Ship();
		ship.setDirection((-Math.PI/2));		
	}
	
	@Test
	public void testMakeAngleValid_NaN(){
		Ship ship = new Ship();
		double angle = Ship.makeAngleValid(Double.NaN);
		ship.setDirection(angle);
		assertEquals(0,ship.getDirection(),Util.EPSILON);	
	}
	
	@Test
	public void testMakeAngleValid_Infinity(){
		Ship ship = new Ship();
		double angle = Ship.makeAngleValid(Double.POSITIVE_INFINITY);
		ship.setDirection(angle);
		assertEquals(0,ship.getDirection(),Util.EPSILON);	
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
	public void testOverlapWithWorldObject(){
		Ship ship1 = new Ship(20,20,0,0,11,3.14/2,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		Ship ship2 = new Ship(30,30,0,0,11,3.14/2,10);
		assertTrue(ship2.overlapWithWorldObject(world));
	}
	
	@Test 
	public void testOverlapWithWorldObject_WorldIsNull(){
		World world = null;
		Ship ship2 = new Ship(30,30,0,0,11,3.14/2,10);
		assertFalse(ship2.overlapWithWorldObject(world));
	}
	
	@Test 
	public void testOverlapWithWorldObject_Itself(){
		Ship ship1 = new Ship(20,20,0,0,11,3.14/2,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		assertFalse(ship1.overlapWithWorldObject(world));
	}
	
	@Test
	public void testOverlapWithWorldObject_Bullet(){
		SpaceObject bullet = new Bullet(20,20,0,0);
		World world = new World(100,100);
		bullet.flyIntoWorld(world);
		SpaceObject ship2 = new Ship(30,30,0,0,11,3.14/2,10);
		assertFalse(ship2.overlapWithWorldObject(world));
	}
	
	@Test
	public void testGetState(){
		Ship ship = new Ship();
		assertEquals(State.CREATED, ship.getState());	
	}
	
	@Test
	public void testIsValidState(){
		Ship ship = new Ship();
		assertTrue(ship.isValidState(State.ACTIVE));	
	}
	
	@Test
	public void testIsValidState_IllegalCase(){
		Ship ship = new Ship();
		assertFalse(ship.isValidState(null));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetState_IllegalCase(){
		Ship ship = new Ship();
		ship.setState(null);
	}
	
	@Test
	public void testGetWorld(){
		Ship ship = new Ship();
		assertEquals(null,ship.getWorld());
	}
	
	@Test
	public void testCanHaveAsWorld(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		Bullet bullet = new Bullet(20, 20, 0, 20);
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
	public void testCanHaveAsWorld_OutBorderX2(){
		Ship ship = new Ship(-10, 0, 0, 20, 11, 0,10);
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
	public void testCanHaveAsWorld_OutBorderY2(){
		Ship ship = new Ship(30, -10, 0, 20, 11, 0,10);
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
	public void testCanHaveAsWorld_WorldIsNull(){
		Ship ship1 = new Ship(20, 40, 0, 20, 11, 0, 10);
		World world = null;
		assertTrue(ship1.canHaveAsWorld(world));	
	}
	
	@Test
	public void testHasProperWorld(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		Bullet bullet = new Bullet(20, 20, 0, 20);
		Ship ship2 = new Ship(80, 80, 0, 20, 11, 0, 10);
		World world = new World(100,100);
		bullet.flyIntoWorld(world);
		ship2.flyIntoWorld(world);
		ship1.flyIntoWorld(world);
		assertTrue(ship1.hasProperWorld());	
	}
	
	@Test 
	public void testHasProperWorld_WorldIsNull(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		assertTrue(ship1.hasProperWorld());	
	}
	
	@Test
	public void testHasProperWorld_NotInWorld(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		world.removeSpaceObject(ship1);
		assertFalse(ship1.hasProperWorld());	
	}
	
	@Test
	public void testHasProperWorld_CantHaveAsWorld(){
		Ship ship = new Ship(30, 30, 0, 20, 11, 0,10);
		World world = new World(50,50);
		ship.flyIntoWorld(world);
		ship.setPosition(-10, 20);
		assertFalse(ship.hasProperWorld());	
	}
		
	@Test
	public void testFlyIntoWorld(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		assertEquals(world, ship1.getWorld());	
		assertEquals(State.ACTIVE, ship1.getState());	
		assertTrue(world.containsSpaceObject(ship1));	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFlyIntoWorld_WorldIsNull(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = null;
		ship1.flyIntoWorld(world);
	}
	

	@Test (expected = IllegalStateException.class)
	public void testFlyIntoWorld_AlreadyInAWorld(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = new World(100,100);
		World world1 = new World(100,100);
		ship1.flyIntoWorld(world);
		ship1.flyIntoWorld(world1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFlyIntoWorld_CantHaveAsWorld(){
		Ship ship = new Ship(30, 60, 0, 20, 11, 0,10);
		World world = new World(50,50);
		ship.flyIntoWorld(world);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testFlyIntoWorld_WrongState(){
		Ship ship = new Ship(30, 30, 0, 20, 11, 0,10);
		ship.setState(State.TERMINATED);
		World world = new World(100,100);
		ship.flyIntoWorld(world);
	}
	
	@Test
	public void testDie(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		ship1.die();
		assertEquals(null, ship1.getWorld());	
		assertEquals(State.TERMINATED, ship1.getState());	
		assertFalse(world.containsSpaceObject(ship1));	
	}
	
	@Test (expected = IllegalStateException.class)
	public void testDie_WrongState(){
		Ship ship1 = new Ship(20, 20, 0, 20, 11, 0,10);
		World world = new World(100,100);
		ship1.flyIntoWorld(world);
		ship1.setState(State.TERMINATED);
		ship1.die();
	}
	
	@Test
	public void testIsValidThruster_IllegalCase(){
		Ship ship = new Ship();
		Thruster thruster = null;
		assertFalse(ship.isValidThruster(thruster));
	}
	
	@Test
	public void testThrust(){
		Ship ship = new Ship(0, 0, 10, 20, 10, (3*Math.PI)/2, 1);
		ship.getThruster().setEnabled(true);
		ship.thrust(1E-14);
		assertEquals(10,ship.getXVelocity(),Util.EPSILON);
		assertEquals(20+(-1.1E4),ship.getYVelocity(),Util.EPSILON);
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
	public void testFireBullet(){
		Ship ship = new Ship(50, 50, 10, 20, 10, (3*Math.PI)/2, 1);
		World world = new World(100,100);
		ship.flyIntoWorld(world);
		ship.fireBullet();
		assertFalse(world.getObjects(Bullet.class).isEmpty());
		}
	
	@Test (expected = IllegalStateException.class)
	public void testFireBullet_IllegalCase(){
		Ship ship = new Ship(50, 50, 10, 20, 10, (3*Math.PI)/2, 1);
		World world = new World(100,100);
		ship.flyIntoWorld(world);
		ship.setState(State.TERMINATED);
		ship.fireBullet();
		
	}
	
	@Test (expected = IllegalStateException.class)
	public void testFireBullet_WorldIsNull(){
		Ship ship = new Ship();
		ship.setState(State.ACTIVE);
		ship.fireBullet();
	}
	
	
	
}
