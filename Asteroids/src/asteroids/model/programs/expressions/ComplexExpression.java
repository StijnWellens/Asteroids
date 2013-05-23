package asteroids.model.programs.expressions;

/**
 * 
 * @author Stijn
 * @invar	...
 * 			| hasProperParts()
 */
public abstract class ComplexExpression extends Expression {

	protected ComplexExpression(int line, int column) {
		super(line, column);
	}

	public abstract Expression getPartAt(int i) throws IndexOutOfBoundsException;
	
	public boolean canHaveAsPartAt(int i, Expression expression) {
		return (i>0) && (expression != null) && (!expression.containsExpression(this));
	}
	
	protected abstract void setPartAt(int i, Expression expression);
	
	public abstract int getNmbOfParts();
	
	public abstract boolean hasProperParts();
	
	public abstract String getSymbol();
	
	@Override
	public boolean containsExpression(Expression expression) {
		if(expression == this)
			return true;
		if(expression == null)
			return false;
		for(int i=1; i<= this.getNmbOfParts(); i++)
			if(this.getPartAt(i).containsExpression(expression) )
				return true;
		return false;
		
	}	
	
	@Override
	public boolean equals(Object other){
		if ((other == null) || (this.getClass() != other.getClass()))
			return false;
		ComplexExpression otherEx = (ComplexExpression) other;
		if (this.getNmbOfParts() != otherEx.getNmbOfParts())
			return false;
		for (int pos = 1; pos <= this.getNmbOfParts(); pos++)
			if (!getPartAt(pos).equals(otherEx.getPartAt(pos)))
				return false;
		return true;
	}
}
