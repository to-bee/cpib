package ch.fhnw.cpib.compiler.cst.interfaces;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.classes.FunctionDeclaration;
import ch.fhnw.cpib.compiler.ast.classes.ProcedureDeclaration;
import ch.fhnw.cpib.compiler.ast.classes.StorageDeclaration;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.scanner.Token;



public interface IConcSyn {
	
	String toString();
	
	public interface IOptionalGlobalInits extends IConcSyn {
		List<Token> toAbs();
	}
	
	public interface IBlockCmd extends IConcSyn {
		List<IAbsSyn.ICommand> toAbs();
	}
	
	public interface ICmd extends IConcSyn {
		IAbsSyn.ICommand toAbs();
	}

	public interface IDeclaration extends IConcSyn {
		IAbsSyn.IDeclaration toAbs();
	}

	public interface IDeclarations extends IConcSyn {
		List<IAbsSyn.IDeclaration> toAbs();
	}

	public interface IExpression extends IConcSyn {
		IAbsSyn.IExpression toAbs();
	}

	public interface IExpressionList extends IConcSyn {
		List<IAbsSyn.IExpression> toAbs();
	}
	
	public interface IFactor extends IConcSyn {
		IAbsSyn.IFactor toAbs();
	}
	
	public interface IGlobalImport extends IConcSyn {
		IAbsSyn.IGlobalImport toAbs();
	}

	public interface IIdents extends IConcSyn {
		List<Token> toAbs();
	}

	public interface IMonadicOperator extends IConcSyn {
		IAbsSyn.IMonadicOperator toAbs();
	}

	public interface IOptionalCHANGEMODE extends IConcSyn {
		Token toAbs();
	}

	public interface IOptionalExpressions extends IConcSyn {
		List<IAbsSyn.IExpression> toAbs();
	}

	public interface IOptionalFLOWMODE extends IConcSyn {
		Token toAbs();
	}

	public interface IOptionalGlobalDeclarations extends IConcSyn {
		List<IAbsSyn.IDeclaration> toAbs();
	}

	public interface IOptionalGlobalImports extends IConcSyn {
		List<IAbsSyn.IGlobalImport> toAbs();
	}

	public interface IOptionalIdent extends IConcSyn {
		IAbsSyn.IOptionalIdent toAbs();
	}

	public interface IOptionalLocalStorageDeclarations extends IConcSyn {
		List<IAbsSyn.IStorageDeclaration> toAbs();
	}

	public interface IOptionalMECHMODE extends IConcSyn {
		Token toAbs();
	}

	public interface IOptionalParameters extends IConcSyn {
		List<IAbsSyn.IParameter> toAbs();
	}

	public interface IOptionalProgramParameters extends IConcSyn {
		List<IAbsSyn.IProgramParameter> toAbs();
	}

	public interface IParameter extends IConcSyn {
		IAbsSyn.IParameter toAbs();
	}

	public interface IParameterList extends IConcSyn {
		List<IAbsSyn.IParameter> toAbs();
	}

	public interface IProgamParameterList  extends IConcSyn{
		List<IAbsSyn.IProgramParameter> toAbs();
	}

	public interface IProgram extends IConcSyn {
		IAbsSyn.IProgram toAbs();
	}

	public interface IRepADDOPRterm3 extends IConcSyn {
		List<IAbsSyn.IADDOPRterm3> toAbs();
	}

	public interface IRepBOOLOPRterm1 extends IConcSyn {
		List<IAbsSyn.IBOOLOPRterm1> toAbs();
	}

	public interface IRepeatingOptionalCase extends IConcSyn {
		List<IAbsSyn.ICase> toAbs();
	}

	public interface IRepeatingOptionalCmds extends IConcSyn {
		List<IAbsSyn.ICommand> toAbs();
	}

	public interface IRepeatingOptionalDeclarations extends IConcSyn {
		List<IAbsSyn.IDeclaration> toAbs();
	}

	public interface IRepeatingOptionalExpressions extends IConcSyn {
		List<IAbsSyn.IExpression> toAbs();
	}

	public interface IRepeatingOptionalGlobalImports extends IConcSyn {
		List<IAbsSyn.IGlobalImport> toAbs();
	}

	public interface IRepeatingOptionalIdents extends IConcSyn {
		List<Token> toAbs();
	}
	
	public interface IRepeatingOptionalParameters extends IConcSyn {
		List<IAbsSyn.IParameter> toAbs();
	}
	
	public interface IRepeatingOptionalProgramParameters extends IConcSyn {
		List<IAbsSyn.IProgramParameter> toAbs();
	}

	public interface IRepeatingOptionalStorageDeclarations extends IConcSyn {
		List<IAbsSyn.IStorageDeclaration> toAbs();
	}

	public interface IRepMULTOPRfactor extends IConcSyn {
		List<IAbsSyn.IMULTOPRfactor> toAbs();
	}

	public interface IRepRELOPRterm2 extends IConcSyn{
		List<IAbsSyn.IRELOPRterm2> toAbs();
	}

	public interface IProcedureDeclaration extends IConcSyn, IDeclaration {
		@Override
		ProcedureDeclaration toAbs();
	}
	
	public interface IFunctionDeclaration extends IConcSyn, IDeclaration {
		@Override
		FunctionDeclaration toAbs();
	}
	
	public interface IStorageDeclaration  extends IConcSyn, IDeclaration{
		@Override
		StorageDeclaration toAbs();
	}

	public interface ITerm1 extends IConcSyn {
		IAbsSyn.ITerm1 toAbs();
	}

	public interface ITerm2 extends IConcSyn {
		IAbsSyn.ITerm2 toAbs();
	}

	public interface ITerm3 extends IConcSyn {
		IAbsSyn.ITerm3 toAbs();
	}

	public interface ITypeDeclaration extends IConcSyn {
		IAbsSyn.ITypeDeclaration toAbs();
	}

	public interface ITypedIdent extends IConcSyn {
		IAbsSyn.ITypedIdent toAbs();
	}

}
