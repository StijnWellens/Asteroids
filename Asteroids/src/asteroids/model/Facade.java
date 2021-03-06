package asteroids.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import org.antlr.v4.runtime.RecognitionException;

import asteroids.CollisionListener;
import asteroids.IFacade;
import asteroids.ModelException;
import asteroids.model.programs.*;
import asteroids.model.programs.expressions.*;
import asteroids.model.programs.parsing.*;
import asteroids.model.programs.statements.*;

public class Facade implements IFacade<World,Ship,Asteroid,Bullet,Program> {
	
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

	@SuppressWarnings("unchecked") // This is not a problem in this case, we want all the objects from the Ship class so we will also only get these.
	@Override
	public Set<Ship> getShips(World world) {
		try{
			Set<Ship> objects = (Set<Ship>) world.getObjects(Ship.class);
			return new HashSet<Ship>(objects);
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
	}
	

	@SuppressWarnings("unchecked") // This is not a problem in this case, we want all the objects from the Asteroid class so we will also only get these.
	@Override
	public Set<Asteroid> getAsteroids(World world) {
		try{
			Set<Asteroid> objects = (Set<Asteroid>) world.getObjects(Asteroid.class);
			return new HashSet<Asteroid>(objects);
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
	}

	@SuppressWarnings("unchecked") // This is not a problem in this case, we want all the objects from the Bullet class so we will also only get these.
	@Override
	public Set<Bullet> getBullets(World world) {
		try{
			Set<Bullet> objects = (Set<Bullet>) world.getObjects(Bullet.class);
			return new HashSet<Bullet>(objects);
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
	}

	@Override
	public void addShip(World world, Ship ship) throws ModelException{
		try{
			ship.flyIntoWorld(world);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) throws ModelException {
		try{
			asteroid.flyIntoWorld(world);
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public void removeShip(World world, Ship ship) throws ModelException{
		try{
			ship.die();
		}
		catch(Exception e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		try{
			asteroid.die();
		}
		catch(Exception e){
			throw new ModelException(e);
		}
		
	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) throws ModelException {
		try{
			world.evolve(dt,collisionListener);
		}
		catch(Exception e)
		{
			throw new ModelException(e);
		}
		
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
		Asteroid newAsteroid = new Asteroid(x, y, xVelocity, yVelocity, radius);
		return newAsteroid;
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		try{
			Asteroid newAsteroid = new Asteroid(x, y, xVelocity, yVelocity, radius,random);
			return newAsteroid;
			}
			catch(Exception e){
				throw new ModelException(e);
			}
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
		return bullet.getSource();
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> parseProgram(String text) {
		ProgramFactoryImpl factory = new ProgramFactoryImpl();
	    ProgramParser<Expression, Statement, Type> parser = new ProgramParser<>(factory);
	    try {
	        parser.parse(text);
	        List<String> errors = parser.getErrors();
	        if(! errors.isEmpty()) {
	          return ParseOutcome.failure(errors.get(0));
	        } else {
	          return ParseOutcome.success(new Program(parser.getGlobals(), parser.getStatement(), factory.getProgramController())); 
	        }
	    } catch(RecognitionException e) {
	      return ParseOutcome.failure(e.getMessage());
	    }
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromStream(
			InputStream stream) throws IOException {
		Scanner scanner = new Scanner(stream);
		Scanner scanner2 = scanner.useDelimiter("\\A");
		String string = "";
		while(scanner.hasNext()){
			string = string + scanner.next();
		}
		scanner.close();
		scanner2.close();
		return this.parseProgram(string);
	}

	@Override
	public asteroids.IFacade.ParseOutcome<Program> loadProgramFromUrl(URL url)
			throws IOException {
		InputStream stream = url.openStream();
		return this.loadProgramFromStream(stream);
	}

	@Override
	public boolean isTypeCheckingSupported() {
		return true;
	}

	@Override
	public asteroids.IFacade.TypeCheckOutcome typeCheckProgram(Program program) {
		if(program.typeCheck())
			return new asteroids.IFacade.TypeCheckOutcome(true, "Type! Check! :)");
		else 
			return new asteroids.IFacade.TypeCheckOutcome(false, "TypeCheckError.. :(");
	}

	@Override
	public void setShipProgram(Ship ship, Program program) {
		try{
			ship.setProgram(program);		
		}
		catch(Exception e){
			throw new ModelException(e);
		}
	}

}
