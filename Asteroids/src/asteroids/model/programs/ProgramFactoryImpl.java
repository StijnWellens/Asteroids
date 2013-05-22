package asteroids.model.programs;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;

import asteroids.model.programs.expressions.*;
import asteroids.model.programs.parsing.ProgramFactory;
import asteroids.model.programs.statements.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type> {

	public ProgramFactoryImpl() {
		this.programController = new ProgramController();
	}
	
	@Basic @Immutable
	public ProgramController getProgramController()
	{
		return this.programController;
	}
	
	private final ProgramController programController;
	
	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(line,column,d);
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(line,column,b);
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new And(line, column, e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return new Or(line,column,e1,e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new Negation(line,column,e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new Null(line, column);
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new Self(line,column, this.getProgramController());
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetXOperator(line, column, e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetYOperator(line, column, e);
	}

	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return new GetVxOperator(line, column, e);
	}

	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return new GetVyOperator(line, column, e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadiusOperator(line, column, e);
	}

	@Override
	public Expression createVariable(int line, int column, String name) {
		return new Variable(line, column, name, this.getProgramController());
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new LargerThan(line, column, e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualTo(line, column, e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LargerThanOrEqualTo(line, column, e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new EqualTo(line, column, e1, e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new NotEqualTo(line, column,e1,e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Addition(line, column, e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Subtraction(line, column, e1, e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(line, column, e1, e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(line, column, e1, e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new SquareRoot(line, column, e);
	}

	@Override
	public Expression createGetDirection(int line, int column) {
		Expression e = new Self(line,column, this.getProgramController());
		return new GetDirectionOperator(line, column, e);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new Sine(line, column, e);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new Cosine(line, column, e);
	}

	@Override
	public Statement createEnableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createDisableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createFire(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(int line, int column, String variable,
			Expression rhs) {
		return new Assignment(line,column,variable,rhs,this.getProgramController());
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForeach(int line, int column,
			asteroids.model.programs.parsing.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip(line, column);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(line,column,statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new PrintStatement(line,column,e);
	}

	@Override
	public Type createDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type createBooleanType() {
		return Type.BOOL;
	}

	@Override
	public Type createEntityType() {
		return Type.ENTITY;
	}

	
}