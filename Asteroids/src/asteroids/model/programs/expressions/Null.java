package asteroids.model.programs.expressions;

public class Null extends EntityLiteral {

	public Null(int line, int column) {
		super(line, column, null);
	}

	@Override
	public int hashCode() {
		return this.getValue().hashCode();
	}	
}
