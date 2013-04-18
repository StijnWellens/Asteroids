package asteroids.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import asteroids.Util;
import asteroids.model.*;

public class WorldTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testConstructorWorld() {
		World world = new World(150,150);
		assertNotNull(world);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testConstructorWorld_IllegalCase(){
		World world = new World(Double.NaN, Double.NaN);
	}
	
	@Test
	public void testGetHeight(){
		World world = new World(150,150);
		assertEquals(150, world.getHeight(),Util.EPSILON);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetHeight_IllegalCase(){
		World world = new World(150,Double.NaN);
		world.getHeight();
	}
	
	@Test
	public void testGetWidth(){
		World world = new World(150, 150);
		assertEquals(150, world.getWidth(),Util.EPSILON);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetWidth_IllegalCase(){
		World world = new World(Double.NaN,150);
		world.getWidth();
	}
	
	@Test 
	public void testIsValidHeight(){
		World world = new World();
		assertTrue(world.isValidHeight(150));
	}
	
	@Test
	public void testIsValidHeight_IllegalCase(){
		World world = new World(150,150);
		assertFalse(world.isValidHeight(Double.NaN));
	}
	
	@Test
	public void testIsValidHeight_Infinity(){
		World world = new World(150,150);
		assertFalse(world.isValidHeight(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testIsValidHeight_NegativeAmount(){
		World world = new World(150,150);
		assertFalse(world.isValidHeight(-50));
	}
	
	@Test
	public void testIsValidWidth(){
		World world = new World(150,150);
		assertTrue(world.isValidWidth(150));
	}
	
	@Test
	public void testIsValidWidth_IllegalCase(){
		World world = new World(150,150);
		assertFalse(world.isValidWidth(Double.NaN));
	}
	
	@Test
	public void testIsValidWidth_Infinity(){
		World world = new World(150,150);
		assertFalse(world.isValidWidth(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testIsValidWidth_NegativeAmount(){
		World world = new World(150,150);
		assertFalse(world.isValidWidth(-50));
	}
	
	 @Test
	 public void testGetUpperBoundCoordinate(){
		assertEquals(Double.MAX_VALUE,World.getUpperBoundCoordinate(),Util.EPSILON);	
	 }
	 
	 @Test
	 public void testIsValidUpperBoundCoordinate(){
		 assertTrue(World.isValidUpperBoundCoordinate(500));
	 }
	 
	 @Test
	 public void testIsValidUpperBoundCoordinate_IllegalCase(){
		 assertFalse(World.isValidUpperBoundCoordinate(Double.NaN));
	 }
	 
	 @Test
	 public void testIsValidUpperBoundCoordinate_Infinity(){
		 assertFalse(World.isValidUpperBoundCoordinate(Double.POSITIVE_INFINITY));
	 }
	 
	 @Test
	 public void testIsValidUpperBoundCoordinate_NegativeAmount(){
		 assertFalse(World.isValidUpperBoundCoordinate(-500));
	 }
	 
	 @Test
	 public void testContainsSpaceObject(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 ship.flyIntoWorld(world);
		 assertTrue(world.containsSpaceObject(ship));
	 }
	 
	 @Test
	 public void testContainsSpaceObject_ObjectIsNull(){
		 World world = new World();
		 SpaceObject ship = null;
		 assertFalse(world.containsSpaceObject(ship));
	 }
	 
	 @Test (expected = AssertionError.class)
	 public void testAddSpaceObject_IllegalCase(){
		 World world = new World();
		 SpaceObject ship = null;
		 world.addSpaceObject(ship);
	 }
	 
	 @Test (expected = AssertionError.class)
	 public void testAddSpaceObject_NotInWorld(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 world.addSpaceObject(ship);
	 }
	 
	 @Test
	 public void testRemoveSpaceObject(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 ship.flyIntoWorld(world);
		 ship.die(world);
		 assertFalse(world.getSpaceObjects().contains(ship));
	 }

	 @Test
	 public void testGetShips(){
		 World world = new World();
		 SpaceObject ship1 = new Ship(20, 20, 0, 20, 11, 0, 5);
		 SpaceObject ship2 = new Ship(40, 40, 0, 20, 11, 0, 5);
		 SpaceObject asteroid = new Asteroid(60,60,0,20,5);
		 assertTrue(world.getShips().isEmpty());
		 ship1.flyIntoWorld(world);
		 assertTrue(world.getShips().contains(ship1));
		 ship2.flyIntoWorld(world);
		 asteroid.flyIntoWorld(world);
		 assertTrue(world.getShips().contains(ship2));
		 assertTrue(world.getShips().contains(ship1));
		 assertFalse(world.getShips().contains(asteroid));
		 assertEquals(2,world.getShips().size());
	 }
	 
	 @Test
	 public void testGetAsteroids(){
		 World world = new World();
		 SpaceObject asteroid1 = new Asteroid(20, 20, 0, 20,5);
		 SpaceObject asteroid2 = new Asteroid(40, 40, 0, 20, 5);
		 SpaceObject ship = new Ship(60,60,0,20,11,0,5);
		 assertTrue(world.getAsteroids().isEmpty());
		 asteroid1.flyIntoWorld(world);
		 assertTrue(world.getAsteroids().contains(asteroid1));
		 asteroid2.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 assertTrue(world.getAsteroids().contains(asteroid2));
		 assertTrue(world.getAsteroids().contains(asteroid1));
		 assertFalse(world.getAsteroids().contains(ship));
		 assertEquals(2,world.getAsteroids().size());
	 }
	 
	 @Test
	 public void testGetBullets(){
		 World world = new World();
		 SpaceObject bullet1 = new Bullet(20, 20, 0, 20);
		 SpaceObject bullet2 = new Bullet(40, 40, 0, 20);
		 SpaceObject ship = new Ship(60,60,0,20,11,0,5);
		 assertTrue(world.getBullets().isEmpty());
		 bullet1.flyIntoWorld(world);
		 assertTrue(world.getBullets().contains(bullet1));
		 bullet2.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 assertTrue(world.getBullets().contains(bullet2));
		 assertTrue(world.getBullets().contains(bullet1));
		 assertFalse(world.getBullets().contains(ship));
		 assertEquals(2,world.getBullets().size());
	 }
	 
	 @Test
	 public void testHasProperSpaceObjects(){
		 World world = new World();
		 SpaceObject asteroid1 = new Asteroid(10, 10, 0, 20,5);
		 SpaceObject asteroid2 = new Asteroid(40, 40, 0, 20, 5);
		 SpaceObject ship = new Ship(70,70,0,20,11,0,5);
		 asteroid1.flyIntoWorld(world);
		 asteroid2.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 assertTrue(world.hasProperSpaceObjects());
	 }
	 
	 @Test
	 public void testSetPossibleCollisions(){
		 World world = new World();
		 List<Collision> collisions = null;
		 world.setPossibleCollisions(collisions);
		 assertTrue(world.getPossibleCollisions().isEmpty());
	 }
	 
	 @Test
	 public void testGetFirstCollision(){
		 World world = new World();
		 SpaceObject ship1 = new Ship(60,60,0,20,5,0,5);
		 SpaceObject ship2 = new Ship(60,80,0,-20,5,0,5);
		 ship1.flyIntoWorld(world);
		 ship2.flyIntoWorld(world);
		 assertTrue(world.getFirstCollision().contains(ship1));
		 assertTrue(world.getFirstCollision().contains(ship2));
	 }
	 
	 @Test
	 public void testAdvanceObjects(){
		 World world = new World(1000,1000);
		 SpaceObject ship = new Ship(60,60,0,20,5,0,5);
		 SpaceObject asteroid = new Asteroid(10, 10, 0,20,5);
		 ship.flyIntoWorld(world);
		 asteroid.flyIntoWorld(world);
		 ((Ship) ship).getThruster().setEnabled(true);
		 world.advanceObjects(20);
		 assertEquals(20*20+10,asteroid.getY(),Util.EPSILON);
		 assertEquals(20*20+60,ship.getY(),Util.EPSILON);
		 assertEquals(20,ship.getYVelocity(),Util.EPSILON);
	 }
}
