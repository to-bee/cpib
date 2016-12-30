package ch.fhnw.cpib.compiler.ast.interfaces;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.enums.operators.FlowMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.MechMode;
import ch.fhnw.cpib.compiler.scanner.enums.operators.Type;

public interface IAbsSyn {
	
	String toString();
	
	public interface IProgram extends IAbsSyn {

		void code(int i);

	}
	
	public interface IProgramParameter extends IAbsSyn {

	}

	public interface IDeclaration extends IAbsSyn {
		
		Token getToken();

		Type getType();

	}
	
	public interface ITypedIdent extends IAbsSyn {

	}
	
	public interface ITypeDeclaration extends IAbsSyn {

	}
	
	public interface IStorageDeclaration extends IAbsSyn {

	}
	
	public interface IFunctionDeclaration extends IAbsSyn {

	}
	
	public interface IProcedureDeclaration extends IAbsSyn {

	}
	
	public interface ICommand extends IAbsSyn {

		Token getToken();

	}
	
	public interface ICase extends IAbsSyn {

	}
	
	public interface IGlobalImport extends IAbsSyn {

		Token getToken();

	}
	
	public interface IParameter extends IAbsSyn {

		void setLocationInParamList(int location);

		FlowMode getFlowMode();

		MechMode getMechMode();

		Token getToken();

		Type getType();

	}
	

	public interface IExpression extends IAbsSyn {
		Token getToken();

		ch.fhnw.cpib.compiler.scanner.enums.operators.Type getType();

	}

	public interface IRoutineCall {
		Token getToken();

		void setFunc(boolean b);

		void check();
	}
	
	public interface IMULTOPRfactor extends IAbsSyn {

	}
	
	public interface IOptionalIdent extends IAbsSyn {

	}

	public interface IMonadicOperator extends IAbsSyn {

	}

	void check();

}
