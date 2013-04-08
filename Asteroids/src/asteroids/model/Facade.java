package asteroids.model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;

public class Facade implements IFacade<World,Ship,Asteroid,Bullet> {
	
	public Facade() {
		
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle, double mass) throws ModelException{
		try{ 
			Ship newShip = new Ship(x,y, xVelocity, yVelocity, radius, angle, mass);
			return newShip;
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
		
	}

	@Override
	public double getShipX(Ship ship) {
		return ship.getX();
	}

	@Override
	public double getShipY(Ship ship) {
		return ship.getY();
	}

	@Override
	public double getShipXVelocity(Ship ship) {
		return ship.getXVelocity();
	}

	@Override
	public double getShipYVelocity(Ship ship) {
		return ship.getYVelocity();
	}

	@Override
	public double getShipRadius(Ship ship) {
		return ship.getRadius();
	}

	@Override
	public double getShipDirection(Ship ship) {
		return ship.getDirection();
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		try{
			angle = Ship.makeAngleValid(angle);
			ship.turn(angle);
		}
		catch(Exception e) {
			throw new ModelException(e);			
		}
		
	}
	
	@Override
	public World createWorld(double width, double height) throws ModelException {
		try{
			return new World(width,height);
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
	}

	@Override
	public double getWorldWidth(World world) {
		return world.getWidth();
	}

	@Override
	public double getWorldHeight(World world) {
		return world.getHeight();
	}

	@Override
	public Set<Ship> getShips(World world) {
		return world.getShips();
	}
	

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		return world.getAsteroids();
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		return world.getBullets();
	}

	@Override
	public void addShip(World world, Ship ship) throws ModelException{
		try{
			world.addSpaceObject(ship);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) throws ModelException {
		try{
			world.addSpaceObject(asteroid);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeShip(World world, Ship ship) throws ModelException{
		try{
			world.removeSpaceObject(ship);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		try{
			world.removeSpaceObject(asteroid);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isShip(Object o) {
		if(o == null)
			return false;
		if(Ship.class.isInstance(o))
			return true;
		else
			return false;
	}

	@Override
	public double getShipMass(Ship ship) {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) {
		return ship.getThruster().isThrusterEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active)throws ModelException {
		try{
			ship.getThruster().setEnabled(active);
		}
		catch(Exception e) {
			throw new ModelException(e);
		}
	}

	@Override
	public void fireBullet(Ship ship)throws ModelException {
		try{
			ship.fireBullet();
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius)throws ModelException {
		try{
		return new Asteroid(x, y, xVelocity, yVelocity, radius);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		if(o == null)
			return false;
		if(Asteroid.class.isInstance(o))
			return true;
		else
			return false;
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		return asteroid.getX();
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		return asteroid.getY();
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		return asteroid.getXVelocity();
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		return asteroid.getYVelocity();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) {
		return asteroid.getWorld();
	}

	@Override
	public boolean isBullets(Object o) {
		if(o == null)
			return false;
		if(Bullet.class.isInstance(o))
			return true;
		else
			return false;
	}

	@Override
	public double getBulletX(Bullet bullet) {
		return bullet.getX();
	}

	@Override
	public double getBulletY(Bullet bullet) {
		return bullet.getY();
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		return bullet.getXVelocity();
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		return bullet.getYVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		return bullet.getShip();
	}

}
