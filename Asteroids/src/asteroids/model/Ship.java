package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Ship {

	// Position
	private double xPosition;
	private double yPosition;
	
	@Basic 
	private double getXPosition()
	{
		return this.xPosition;
	}
	
	@Basic
	private double getYPosition()
	{
		return this.yPosition;
	}
	
	private void setXPosition(int x)
	{
		
	}
	
}
