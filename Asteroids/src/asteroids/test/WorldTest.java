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
		 SpaceObject ship = new Ship(20, 20, 0, 20, 11, 0, 5);
		 ship.flyIntoWorld(world);
		 assertTrue(world.containsSpaceObject(ship));
	 }
	 
	 @Test
	 public void testContainsSpaceObject_ObjectIsNull(){
		 World world = new World();
		 SpaceObject ship = null;
		 assertFalse(world.containsSpaceObject(ship));
	 }
	 
	 @Test (expected = IllegalArgumentException.class)
	 public void testAddSpaceObject_IllegalCase(){
		 World world = new World();
		 SpaceObject ship = null;
		 world.addSpaceObject(ship);
	 }
	 
	 @Test 
	 public void testAddSpaceObject_NotInWorld(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 world.addSpaceObject(ship);
	 }
	 
	 @Test
	 public void testRemoveSpaceObject(){
		 World world = new World();
		 SpaceObject ship = new Ship(20, 20, 0, 20, 11, 0, 5);
		 ship.flyIntoWorld(world);
		 world.removeSpaceObject(ship);
		 assertFalse(world.getSpaceObjects().contains(ship));
	 }
	 
	 @Test
	 public void testRemoveSpaceObject_ObjectIsNull(){
		 World world = new World();
		 world.removeSpaceObject(null);
	 }
	 
	 @Test 
	 public void testRemoveSpaceObject_NotThisWorld(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 world.removeSpaceObject(ship);
	 }

	 @Test
	 public void testGetShips(){
		 World world = new World();
		 SpaceObject ship1 = new Ship(20, 20, 0, 20, 11, 0, 5);
		 SpaceObject ship2 = new Ship(40, 40, 0, 20, 11, 0, 5);
		 SpaceObject asteroid = new Asteroid(60,60,0,20,5);
		 assertTrue(world.getObjects(Ship.class).isEmpty());
		 ship1.flyIntoWorld(world);
		 assertTrue(world.getObjects(Ship.class).contains(ship1));
		 ship2.flyIntoWorld(world);
		 asteroid.flyIntoWorld(world);
		 assertTrue(world.getObjects(Ship.class).contains(ship2));
		 assertTrue(world.getObjects(Ship.class).contains(ship1));
		 assertFalse(world.getObjects(Ship.class).contains(asteroid));
		 assertEquals(2,world.getObjects(Ship.class).size());
	 }
	 
	 @Test
	 public void testGetAsteroids(){
		 World world = new World();
		 SpaceObject asteroid1 = new Asteroid(20, 20, 0, 20,5);
		 SpaceObject asteroid2 = new Asteroid(40, 40, 0, 20, 5);
		 SpaceObject ship = new Ship(60,60,0,20,11,0,5);
		 assertTrue(world.getObjects(Asteroid.class).isEmpty());
		 asteroid1.flyIntoWorld(world);
		 assertTrue(world.getObjects(Asteroid.class).contains(asteroid1));
		 asteroid2.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 assertTrue(world.getObjects(Asteroid.class).contains(asteroid2));
		 assertTrue(world.getObjects(Asteroid.class).contains(asteroid1));
		 assertFalse(world.getObjects(Asteroid.class).contains(ship));
		 assertEquals(2,world.getObjects(Asteroid.class).size());
	 }
	 
	 @Test
	 public void testGetBullets(){
		 World world = new World();
		 SpaceObject bullet1 = new Bullet(20, 20, 0, 20);
		 SpaceObject bullet2 = new Bullet(40, 40, 0, 20);
		 SpaceObject ship = new Ship(60,60,0,20,11,0,5);
		 assertTrue(world.getObjects(Bullet.class).isEmpty());
		 bullet1.flyIntoWorld(world);
		 assertTrue(world.getObjects(Bullet.class).contains(bullet1));
		 bullet2.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 assertTrue(world.getObjects(Bullet.class).contains(bullet2));
		 assertTrue(world.getObjects(Bullet.class).contains(bullet1));
		 assertFalse(world.getObjects(Bullet.class).contains(ship));
		 assertEquals(2,world.getObjects(Bullet.class).size());
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
		 SpaceObject ship = new Ship(10,10,10,20,10,(3*Math.PI)/2,1);
		 SpaceObject asteroid = new Asteroid(30, 30, 0,20,5);
		 ship.flyIntoWorld(world);
		 asteroid.flyIntoWorld(world);
		 ((Ship) ship).getThruster().setEnabled(true);
		 world.advanceObjects(1E-14);
		 assertEquals(1E-14*20+30,asteroid.getY(),Util.EPSILON);
		 assertEquals(1E-14*10+10,ship.getX(),Util.EPSILON);
		 assertEquals(1E-14*20+10,ship.getY(),Util.EPSILON);
		 assertEquals(20+(-1.1E4),ship.getYVelocity(),Util.EPSILON);
	 }
	 
	 @Test
	 public void testEvolve(){
		 World world = new World();
		 SpaceObject ship = new Ship(60,60,0,10,5,Math.PI/2,5);
		 SpaceObject asteroid1 = new Asteroid(10, 10,10,0,2);
		 SpaceObject asteroid2 = new Asteroid(20, 10, 0,0, 2);
		 asteroid2.flyIntoWorld(world);
		 asteroid1.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 world.evolve(0.6,null);
		 assertEquals(16,asteroid1.getX(),Util.EPSILON);
		 assertEquals(20,asteroid2.getX(),Util.EPSILON);
		 world.evolve(5.4,null);
		 assertEquals(70,ship.getY(),Util.EPSILON);
		 assertEquals(60,ship.getX(),Util.EPSILON);
	 }
	 
	 @Test (expected = IllegalArgumentException.class)
	 public void testEvolve_IllegalCase(){
		 World world = new World();
		 SpaceObject ship = new Ship(60,60,0,10,5,Math.PI/2,5);
		 SpaceObject asteroid1 = new Asteroid(10, 10,10,0,2);
		 SpaceObject asteroid2 = new Asteroid(20, 10, 0,0, 2);
		 asteroid2.flyIntoWorld(world);
		 asteroid1.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 world.evolve(Double.NaN,null);
	 }
	 
	 @Test (expected = IllegalArgumentException.class)
	 public void testEvolve_Infinity(){
		 World world = new World();
		 SpaceObject ship = new Ship(60,60,0,10,5,Math.PI/2,5);
		 SpaceObject asteroid1 = new Asteroid(10, 10,10,0,2);
		 SpaceObject asteroid2 = new Asteroid(20, 10, 0,0, 2);
		 asteroid2.flyIntoWorld(world);
		 asteroid1.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 world.evolve(Double.POSITIVE_INFINITY,null);
	 }
	 
	 @Test (expected = IllegalArgumentException.class)
	 public void testEvolve_NegativeAmount(){
		 World world = new World();
		 SpaceObject ship = new Ship(60,60,0,10,5,Math.PI/2,5);
		 SpaceObject asteroid1 = new Asteroid(10, 10,10,0,2);
		 SpaceObject asteroid2 = new Asteroid(20, 10, 0,0, 2);
		 asteroid2.flyIntoWorld(world);
		 asteroid1.flyIntoWorld(world);
		 ship.flyIntoWorld(world);
		 world.evolve(-50,null);
	 }
	 
	 @Test (expected = IllegalArgumentException.class)
	 public void testAddCollisions_ObjectIsNull(){
		 World world = new World();
		 world.addCollisions(null);
	 }
	 
	 @Test 
	 public void testRemoveCollisions_CollisionsNull(){
		 World world = new World();
		 SpaceObject ship = new Ship();
		 world.removeCollisions(ship);
		 assertTrue(world.getPossibleCollisions().isEmpty());
	 }
	 
	 
}
