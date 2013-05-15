package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Stijn
 *
 * @invar	...
 * 			| canHaveAsPartAt(1,getLeftPart())
 * @invar	...
 * 			| canHaveAsPartAt(2,getRightPart())
 * @invar	...
 * 			| hasProperParts()
 */
public abstract class BinaryExpression extends ComplexExpression {

	protected BinaryExpression(int line, int column, Expression left, Expression right) throws IllegalArgumentException {
		super(line, column);
		if(!canHaveAsPartAt(1,left))
			throw new IllegalArgumentException();
		if(!canHaveAsPartAt(2,right))
			throw new IllegalArgumentException();
		if(left.getType() != right.getType())
			throw new IllegalArgumentException();
		
		setPartAt(1,left);
		setPartAt(2,right);
		
	}
	@Basic
	public Expression getLeftPart()
	{
		return this.leftPart;
	}
	
	@Basic 
	public Expression getRightPart()
	{
		return this.rightPart;
	}
	
	@Override
	public boolean canHaveAsPartAt(int i, Expression expression)
	{
		super.canHaveAsPartAt(i,expression);
		if(i!=1 || i!=2)
			return false;
		return false;
		
	}
		
	@Override @Raw
	protected void setPartAt(int i, Expression expression) throws IllegalArgumentException
	{
		if(i == 1)
			this.leftPart = expression;
		if(i == 2)
			this.rightPart = expression;
	}
	
	private Expression leftPart;
	private Expression rightPart;
	
	
	@Override
	public Expression getPartAt(int i) throws IndexOutOfBoundsException {
		if(i != 1 || i!= 2)
			throw new IndexOutOfBoundsException();
		if(i == 1)
			return this.getLeftPart();
		else
			return this.getRightPart();
	}
	
	@Override
	public boolean hasProperParts() {
		if(!canHaveAsPartAt(1,this.getLeftPart()))
			return false;
		if(!canHaveAsPartAt(2,this.getRightPart()))
			return false;
		return (this.getLeftPart().getType() == this.getRightPart().getType());
	}
	
	@Override
	public int getNmbOfParts() {
		return 2;
	}


}
