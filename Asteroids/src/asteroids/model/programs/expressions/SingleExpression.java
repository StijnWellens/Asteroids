package asteroids.model.programs.expressions;

import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Stijn
 * @invar	...
 * 			| hasProperParts()
 */
public abstract class SingleExpression extends ComplexExpression {

	protected SingleExpression(Expression e) throws IllegalArgumentException{
		if(!canHaveAsPartAt(1,e))
			throw new IllegalArgumentException();
		setPartAt(1,e);
	}
	
	private Expression part;
	
	public Expression getPart() 
	{
		return this.part;
	}
	
	@Override
	public Expression getPartAt(int i) throws IndexOutOfBoundsException {
		if(i != 1)
			throw new IndexOutOfBoundsException();
		return getPart();
	}
	
	@Override
	public boolean canHaveAsPartAt(int i, Expression expression) {
		return super.canHaveAsPartAt(i, expression) && i == 1;
	}
	
	@Override @Raw
	protected void setPartAt(int i, Expression expression){
		this.part = expression;
	}

	@Override
	public int getNmbOfParts() {
		return 1;
	}	
	
	@Override
	public boolean hasProperParts() {
		return canHaveAsPartAt(1,getPart());
	}
}
