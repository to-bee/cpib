package ch.fhnw.cpib.compiler.ast.interfaces;

import ch.fhnw.cpib.compiler.scanner.Token;

import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public interface IAbsSyn {
	
	String toString();
	
	void check();
	void print(String prefix);
	
	public interface IProgram extends IAbsSyn {

		void code(int i) throws CodeTooSmallError;

	}
	
	public interface IProgramParameter extends IAbsSyn {

		int code(int i) throws CodeTooSmallError;
	}

	public interface IDeclaration extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Token getToken();

		Type getType();

	}
	
	public interface ITypedIdent extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Token getToken();

		Type getType();
	}
	
	public interface ITypeDeclaration extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Type getType();
		
		Token getToken();

	}
	
	public interface IStorageDeclaration extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
	}
	
	public interface IFunctionDeclaration extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
	}
	
	public interface IProcedureDeclaration extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
	}
	
	public interface ICommand extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Token getToken();

	}
	
	public interface ICase extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Token getToken();

	}
	
	public interface IGlobalImport extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		Token getToken();

	}
	
	public interface IParameter extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
		void setLocationInParamList(int location);

		FlowMode getFlowMode();

		MechMode getMechMode();

		Token getToken();

		Type getType();

	}
	

	public interface IExpression extends IAbsSyn {
		Token getToken();

		ch.fhnw.cpib.compiler.scanner.enums.operators.Type getType();
		int code(int i) throws CodeTooSmallError;
	}

	public interface IRoutineCall extends IAbsSyn{
		Token getToken();

		void setFunc(boolean b);

		void check();
		int code(int i) throws CodeTooSmallError;
	}
	
	public interface IMULTOPRfactor extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
	}
	
	public interface IOptionalIdent extends IAbsSyn {
		int code(int i) throws CodeTooSmallError;
	}

	public interface IMonadicOperator extends IAbsSyn {

	}

	

}
