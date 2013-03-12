package asteroids.model;

import asteroids.IShip;
import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class representing a ship with a position, velocity, direction and radius
 * 
 * @invar The x position of the ship must always be a valid x position. |
 *        isValidX(getX())
 * @invar The y position of the ship must always be a valid y position. |
 *        isValidY(getY())
 * @invar The maximum velocity of the ship must always be a valid maximum
 *        velocity. | isValidMaxVelocity(getMaxVelocity())
 * @invar The velocity of the ship must always be a valid velocity. |
 *        isValidVelocity(getXVelocity(), getYVelocity())
 * @invar The direction of the ship must always be a valid direction. |
 *        isValidDirection(getDirection())
 * @invar The lowerBoundRadius of the ship must always be a valid
 *        lowerBoundRadius. | isValidLowerBoundRadius(getLowerBoundRadius())
 * @invar The radius of the ship must always be a valid radius. |
 *        isValidRadius(getRadius())
 * @author Julie Wouters & Stijn Wellens
 * 
 */
public class Ship implements IShip {

	// TODO default Constructor documentatie
	/**
	 * 
	 */
	public Ship() throws IllegalArgumentException {
		setMaxVelocity(300000);
		
		setX(0);
		setY(0);
		setVelocity(0, 0);
		setDirection(PI / 2);
		setRadius(1);

	}

	// TODO Constructor documentatie
	/**
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity,
			double radius, double angle) throws IllegalArgumentException {
		setMaxVelocity(300000);
		
		setX(x);
		setY(y);
		setVelocity(xVelocity, yVelocity);
		setDirection(angle);
		setRadius(radius);

	}

	// Position: defensive programming

	private double xPosition;
	private double yPosition;

	/**
	 * Returns the x Position of the ship.
	 * 
	 * @return xPosition the x Position of the ship
	 */
	@Basic
	public double getX() {
		return this.xPosition;
	}

	/**
	 * Returns the y Position of the ship.
	 * 
	 * @return yPosition the y Position of the ship
	 */
	@Basic
	public double getY() {
		return this.yPosition;
	}

	/**
	 * Checks whether the given x position is a valid x position for a ship.
	 * 
	 * @param x
	 *            The x position to check.
	 * @return True if and only if the given x position is a positive number. |
	 *         result == (x>=0)
	 */
	public boolean isValidX(double x) {
		return !Double.isNaN(x);
	}

	/**
	 * Checks whether the given y position is a valid y position for a ship.
	 * 
	 * @param y
	 *            The y position to check.
	 * @return True if and only if the given y position is a positive number. |
	 *         result == (y>=0)
	 */
	public boolean isValidY(double y) {
		return !Double.isNaN(y);
	}

	/**
	 * 
	 * @param x
	 *            The new x position of this ship.
	 * @post The new x position of this ship is equal to the given x position of
	 *       this ship. | (new this).getX() == x
	 * @throws IllegalArgumentException
	 *             The given x position is not a valid x position. | !
	 *             isValidX(x)
	 */
	private void setX(double x) throws IllegalArgumentException {
		if (!isValidX(x))
			throw new IllegalArgumentException();
		this.xPosition = x;
	}

	/**
	 * 
	 * @param y
	 *            The new y position of this ship.
	 * @post The new y position of this ship is equal to the given y position of
	 *       this ship. | (new this).getY() == y
	 * @throws IllegalArgumentException
	 *             The given y position is not a valid y position. | !
	 *             isValidY(y)
	 */
	private void setY(double y) throws IllegalArgumentException {
		if (!isValidY(y))
			throw new IllegalArgumentException();
		this.yPosition = y;
	}

	// Velocity: total programming

	private double vx;
	private double vy;
	private double maxV = LIGHTSPEED; // in km/s

	private static final double LIGHTSPEED = 300000;

	/**
	 * Returns the x component of the velocity of the ship.
	 */
	@Basic
	public double getXVelocity() {
		return this.vx;
	}

	/**
	 * Returns the y component of the velocity of the ship.
	 */
	@Basic
	public double getYVelocity() {
		return this.vy;
	}

	/**
	 * Returns the maximum velocity of the ship.
	 * 
	 */
	@Basic
	@Immutable
	public double getMaxVelocity() {
		return this.maxV;
	}

	/**
	 * Checks whether the given maximum velocity is valid.
	 * 
	 * @param maxV
	 *            The given maximum velocity.
	 * @return False if the double maxV is not a number. | if(Double.isNaN(maxV)
	 *         == true) | then result == false
	 * @return True if and only if the given maximum velocity is positive or 0
	 *         and not exceeding the speed of light. | if((maxV >= 0) && (maxV
	 *         <= LIGHTSPEED) | then result == true
	 */
	public boolean isValidMaxVelocity(double maxV) {
		if (Double.isNaN(maxV) == true) {
			return false;
		} else {
			return ((maxV >= 0) && (maxV <= LIGHTSPEED));
		}
	}

	/**
	 * 
	 * @param maxV
	 *            The given maximum velocity of the ship.
	 * @post If the absolute value of the given maximum velocity is valid, the
	 *       maximum velocity will be the absolute value of the given maximum
	 *       velocity. | if (isValidMaxVelocity(Math.abs(maxV))) | then (new
	 *       this).getMaxVelocity() == Math.abs(maxV)
	 * @post If the absolute value of the given maximum velocity exceeds the
	 *       speed of light, then the maximum velocity will be the speed of
	 *       light. | if (Math.abs(maxV) > LIGHTSPEED) | then (new
	 *       this).getMaxVelocity() == LIGHTSPEED
	 * 
	 */
	private void setMaxVelocity(double maxV) {
		if (isValidMaxVelocity(Math.abs(maxV))) {
			this.maxV = Math.abs(maxV);
		} else {
			this.maxV = LIGHTSPEED;
		}

	}

	/**
	 * Check if the x velocity component is valid.
	 * 
	 * @param vx
	 *            The x velocity component to be checked.
	 * @post True if and only x velocity component is a number. | result == | (
	 *       (!Double.isNaN(vx))
	 */
	public boolean isValidXVelocity(double vx) {
		return (!Double.isNaN(vx));
	}

	/**
	 * Check if the y velocity component is valid.
	 * 
	 * @param vy
	 *            The y velocity component to be checked.
	 * @post True if and only if the y velocity component is a number. | result
	 *       == | ( (!Double.isNaN(vy))
	 */
	public boolean isValidYVelocity(double vy) {
		return (!Double.isNaN(vy));
	}

	/**
	 * Checks if the given velocity components are valid.
	 * 
	 * @param vx
	 *            The x component of the velocity to be checked.
	 * @param vy
	 *            The y component of the velocity to be checked.
	 * @return False if the double vx or vy is not a number. |
	 *         if((Double.isNaN(vx) == true)|| (Double.isNaN(vy) == true)) |
	 *         then result == false
	 * @return True if and only if the velocity is equal or less than the valid
	 *         maximum speed of the ship. | if (vx <0 && vy<0) | then result ==
	 *         false | else if(
	 *         isValidMaxVelocity(this.maxV))&&(Math.sqrt(vx*vx+vy*vy) <=
	 *         this.maxV) ) | then result == true | else | then result == false
	 */
	public boolean isValidVelocity(double vx, double vy) {
		if ((!isValidXVelocity(vx)) || (!isValidYVelocity(vy))) {
			return false;
		}
		if (vx >= 0 && vy >= 0 && isValidMaxVelocity(this.maxV)) {
			return Util.fuzzyLessThanOrEqualTo(Math.sqrt(vx * vx + vy * vy),
					this.maxV);
		} else {
			return false;
		}
	}

	/**
	 * Set the x velocity of the ship.
	 * 
	 * @param vx
	 *            The given x component of the velocity.
	 * @post If the given x velocity component is valid, then the new x velocity
	 *       component will be the given component. if(isValidXVelocity(vx))
	 *       then (new this).getXVelocity() == vx
	 */
	@Raw
	private void setXVelocity(double vx) {
		if (isValidXVelocity(vx)) {
			this.vx = vx;
		}
	}

	/**
	 * Set the y velocity of the ship.
	 * 
	 * @param vy
	 *            The given y component of the velocity.
	 * @post If the given y velocity component is valid, then the new y velocity
	 *       component will be the given component. if(isValidXVelocity(vy))
	 *       then (new this).getYVelocity() == vy
	 */
	@Raw
	private void setYVelocity(double vy) {
		if (isValidYVelocity(vy)) {
			this.vy = vy;
		}
	}

	/**
	 * Set the velocity of the ship.
	 * 
	 * @param vx
	 *            The given x component of the velocity.
	 * @param vy
	 *            The given y component of the velocity.
	 * @post If the given velocity components are valid, then the new velocity
	 *       components will be the given components. if(isValidVelocity(vx,vy))
	 *       then ((new this).getXVelocity() == vx) && ((new
	 *       this).getYVelocity() == vy)
	 */
	private void setVelocity(double vx, double vy) {
		if (isValidVelocity(vx, vy)) {
			setXVelocity(vx);
			setYVelocity(vy);
		}
	}

	/**
	 * Check if the amount that has to be added to a velocity is valid.
	 * 
	 * @param amount
	 *            The amount to be checked.
	 * @post True if and only if the amount is a number and is higher than or
	 *       equal to zero. | result == | ( (!Double.isNaN(amount)) | && (amount
	 *       >= 0) )
	 */
	private boolean isValidAmount(double amount) {
		return ((!Double.isNaN(amount)) && (amount >= 0));
	}

	/**
	 * Changes the ship's velocity based on the current velocity, its direction
	 * and on a given amount.
	 * 
	 * @param amount
	 *            The amount that needs to be added to the current velocity.
	 * @effect If the given amount is valid, the new x velocity will be set to
	 *         the sum of the old x velocity and the amount that has been
	 *         multiplied with the cosine of the direction. |
	 *         if(!isValidAmount(amount)) | then (new this).getXVelocity() ==
	 *         this.getXVelocity() + amount*Math.sin(this.direction);
	 * 
	 */
	// TODO Afwerken documentatie!
	public void thrust(double amount) {

		if (!isValidAmount(amount)) {
			amount = 0;
		}

		double tempVx = vx + amount * Math.cos(direction);
		double tempVy = vy + amount * Math.sin(direction);

		if (isValidVelocity(tempVx, tempVy)) {
			setVelocity(tempVx, tempVy);
		} else {
			double tempAmount = (this.maxV)
					/ Math.sqrt(tempVx * tempVx + tempVy * tempVy);
			tempVx = tempVx * tempAmount;
			tempVy = tempVy * tempAmount;

			setVelocity(tempVx, tempVy);
		}
	}

	// Direction: nominal programming

	private double direction;
	private static final double PI = 3.14;

	/**
	 * Returns the angle of the direction of this ship.
	 * 
	 * @return the angle of the direction of the ship | this.direction
	 */
	@Basic
	@Raw
	public double getDirection() {
		return this.direction;
	}

	/**
	 * Returns whether the given direction is valid or not.
	 * 
	 * @param angle
	 *            The angle in which the ship moves.
	 * @return True if and only if the given angle is a number and is between
	 *         zero and 2*Pi. | result == ((!Double.isNaN(angle)) && (angle >=
	 *         0) && (angle < 2*PI))
	 */
	public boolean isValidDirection(double angle) {
		return ((!Double.isNaN(angle)) && (angle >= 0) && (angle < 2 * PI));
	}

	/**
	 * Set the direction of the ship to the given direction.
	 * 
	 * @param angle
	 *            The angle of the given direction.
	 * @pre The given direction must be a valid direction. |
	 *      isValidDirection(angle)
	 * @post The new direction of the ship is equal to the modulo 2*Pi of the
	 *       given direction of the ship. | (new this).getDirection() == angle
	 */
	public void setDirection(double angle) {
		assert isValidDirection(angle);

		this.direction = angle;

	}

	/**
	 * Turns the ship with a given angle.
	 * 
	 * @param angle
	 *            The angle that needs to be added to the current direction.
	 * @pre The sum of the old direction and the given angle must be a valid
	 *      direction. | isValidDirection(this.getDirection()+angle)
	 * @effect The direction of the ship is set to the sum of the current
	 *         direction and the given angle. | (new
	 *         this).setDirection(this.getDirection() + angle)
	 */
	public void turn(double angle) {
		assert isValidDirection(this.direction + angle);

		setDirection(this.direction + angle);
	}

	// Radius

	private double radius;
	private static double lowerBoundRadius;

	/**
	 * Returns the lower bound of the radius of all ships.
	 * 
	 * @return lower bound of the radius The lower bound of the radius of the
	 *         ship
	 */
	@Basic
	@Raw
	public double getLowerBoundRadius() {
		return lowerBoundRadius;
	}

	/**
	 * Check whether the lower bound of the radius is valid for all ships.
	 * 
	 * @param lowerBoundRadius
	 *            the lower bound to check
	 * @return True if and only if the lower bound of the radius is not zero or
	 *         below zero and if is a number. | result == | ( (lowerBoundRadius
	 *         >= 0) | && (!Double.isNaN(lowerBoundRadius)) )
	 */
	public static boolean isValidLowerBoundRadius(double lowerBoundRadius) {
		return ((!Double.isNaN(lowerBoundRadius)) && (lowerBoundRadius >= 0));
	}

	/**
	 * Set the lowerBoundRadius of all the ships to the given lowerBoundRadius.
	 * 
	 * @param lowerBoundRadius
	 *            The new lowerBoundRadius for all the ships.
	 * @post The new lowerBoundRadius of all the ships is equal to the given
	 *       lowerBoundRadius. | new.getLowerBoundRadius() == lowerBoundRadius
	 * @throws IllegalArgumentException
	 *             The given lowerBoundRadius is not a valid lowerBoundRadius
	 *             for any ship. | ! isValidLowerBoundRadius(lowerBoundRadius)
	 */
	private static void setLowerBoundRadius(double lowerBoundRadius)
			throws IllegalArgumentException {
		if (!isValidLowerBoundRadius(lowerBoundRadius))
			throw new IllegalArgumentException();
		Ship.lowerBoundRadius = lowerBoundRadius;

	}

	/**
	 * Returns the radius of this ship.
	 * 
	 * @return radius The radius of the ship
	 */
	@Basic
	@Raw
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check whether the given radius is a valid radius for this ship.
	 * 
	 * @param radius
	 *            The radius to check.
	 * @return True if and only if the given radius is higher than the
	 *         lowerBoundRadius of the ships and it is a number. | result == | (
	 *         (!Double.isNaN(radius)) | && (radius > lowerBoundRadius) )
	 */
	public boolean isValidRadius(double radius) {
		return ((!Double.isNaN(radius)) && (radius > lowerBoundRadius));
	}

	/**
	 * Set the radius of the ship to the given radius.
	 * 
	 * @param radius
	 *            The new radius for the ship.
	 * @post The new radius of the ship is equal to the given radius. | (new
	 *       this).getRadius() == radius
	 * @throws IllegalArgumentException
	 *             The given radius is not a valid radius for this ship. | !
	 *             isValidRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
	}

	/**
	 * Check whether the given duration is a valid duration.
	 * 
	 * @param duration
	 *            The duration to check.
	 * @return True if and only if the given duration is not below zero. |
	 *         result == | duration >= 0
	 */
	public boolean isValidDuration(double duration) {
		return duration >= 0;
	}

	/**
	 * Changes the position of the ship based on the current position, current
	 * orientation, velocity and a given time duration.
	 * 
	 * @param duration
	 *            How long the ship needs to move.
	 * @post The new x-coordinate of this ship is equal to the old x-coordinate
	 *       of this ship incremented with the product of the x-velocity of the
	 *       ship with the cosine of the direction of the ship with the given
	 *       time duration. * |(new this).getX() == this.getX() +
	 *       this.getXVelocity()*Math.cos(this.getDirection())*duration
	 * @post The new y-coordinate of this ship is equal to the old y-coordinate
	 *       of this ship incremented with the product of the y-velocity of the
	 *       ship with the sine of the direction of the ship with the given time
	 *       duration. |(new this).getY() == this.getY() +
	 *       this.getYVelocity()*Math.sin(this.getDirection())*duration
	 * @throws IllegalArgumentException
	 *             The given duration is not a valid duration. |
	 *             !isValidDuration(duration)
	 */
	public void move(double duration) {
		if (!isValidDuration(duration))
			throw new IllegalArgumentException();
		setX(xPosition + vx * Math.cos(direction) * duration);
		setY(yPosition + vy * Math.sin(direction) * duration);
	}

}
