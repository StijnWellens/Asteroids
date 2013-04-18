package asteroids.test;

import static org.junit.Assert.*;

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
	 public void testAddSpaceObject_ShipIsNull(){
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
		 assertTrue(world.getSpaceObjects().contains(ship));
	 }
	 
	 
}
