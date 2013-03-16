package asteroids.model;

import asteroids.IFacade;
import asteroids.IShip;
import asteroids.ModelException;

public class Facade implements IFacade {

	private static final double PI = Math.PI;
	
	public Facade() {
		
	}

	@Override
	public IShip createShip() throws ModelException {
		try{
			IShip newShip = new Ship();
			return newShip;
		}
		catch(IllegalArgumentException iae){
			throw new ModelException(iae);
		}
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) throws ModelException{
		try{ 
			IShip newShip = new Ship(x,y, xVelocity, yVelocity, radius, angle);
			return newShip;
		}
		catch(IllegalArgumentException iae)
		{
			throw new ModelException(iae);
		}
		
	}

	@Override
	public double getX(IShip ship) {
		return ((Ship)ship).getX();
	}

	@Override
	public double getY(IShip ship) {
		return ((Ship)ship).getY();
	}

	@Override
	public double getXVelocity(IShip ship) {
		return ((Ship)ship).getXVelocity();
	}

	@Override
	public double getYVelocity(IShip ship) {
		return ((Ship)ship).getYVelocity();
	}

	@Override
	public double getRadius(IShip ship) {
		return ((Ship)ship).getRadius();
	}

	@Override
	public double getDirection(IShip ship) {
		return ((Ship)ship).getDirection();
	}

	@Override
	public void move(IShip ship, double dt) throws ModelException {
		try{
			((Ship)ship).move(dt);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);
		}
		
	}

	@Override
	public void thrust(IShip ship, double amount) {
		try{
			((Ship)ship).thrust(amount);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
		
		
	}

	@Override
	public void turn(IShip ship, double angle) {
		try{
			angle = makeAngleValid(angle);
			((Ship)ship).turn(angle);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
		
	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) throws ModelException {
		try{
			return (Ship.getDistanceBetween((Ship)ship1,(Ship)ship2));
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) throws ModelException {
		try{
			return Ship.overlap((Ship)ship1,(Ship)ship2);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) throws ModelException {
		try{
			return Ship.getTimeToCollision((Ship)ship1,(Ship)ship2);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		try{
			return Ship.getCollisionPosition((Ship)ship1,(Ship)ship2);
		}
		catch(IllegalArgumentException iae) {
			throw new ModelException(iae);			
		}
	}
	
	public double makeAngleValid(double angle)
	{
		if(Double.isNaN(angle))
		{
			angle = 0;
		}
		
		double tempAngle = angle%(2*PI);
		if(tempAngle < 0)
		{
			return tempAngle + (2*PI);
		}
		else
		{
			return tempAngle;
		}
	}

}
