package asteroids.model.programs.expressions;

/**
 * 
 * @author Stijn
 * @invar	...
 * 			| hasProperParts()
 */
public abstract class ComplexExpression extends Expression {

	public abstract Expression getPartAt(int i) throws IndexOutOfBoundsException;
	
	public boolean canHaveAsPartAt(int i, Expression expression) {
		return (i>0) && (expression != null) && (!expression.containsExpression(this));
	}
	
	protected abstract void setPartAt(int i, Expression expression);
	
	public abstract int getNmbOfParts();
	
	public abstract boolean hasProperParts();
	
	@Override
	public boolean containsExpression(Expression expression) {
		if(expression == this)
			return true;
		if(expression == null)
			return false;
		for(int i=0; i<= this.getNmbOfParts(); i++)
			if(this.getPartAt(i).containsExpression(expression) )
				return true;
		return false;
		
	}	
}
