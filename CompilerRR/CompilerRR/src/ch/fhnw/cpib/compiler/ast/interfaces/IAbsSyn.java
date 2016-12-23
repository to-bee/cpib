package ch.fhnw.cpib.compiler.ast.interfaces;

public interface IAbsSyn {
	
	String toString();
	
	public interface IProgram extends IAbsSyn {

	}
	
	public interface IProgramParameter extends IAbsSyn {

	}

	public interface IDeclaration extends IAbsSyn {

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

	}
	
	public interface ICase extends IAbsSyn {

	}
	
	public interface IGlobalImport extends IAbsSyn {

	}
	
	public interface IParameter extends IAbsSyn {

	}
	

	public interface IExpression extends IAbsSyn {

	}

	public interface IBOOLOPRterm1 extends IAbsSyn {

	}
	
	
	public interface IFactor extends IAbsSyn {

	}
	
	public interface ITerm1 extends IAbsSyn {

	}
	
	public interface IRELOPRterm2 extends IAbsSyn {

	}

	public interface ITerm2 extends IAbsSyn {

	}

	public interface IADDOPRterm3 extends IAbsSyn {

	}
	
	public interface ITerm3 extends IAbsSyn {

	}
	
	public interface IMULTOPRfactor extends IAbsSyn {

	}
	
	public interface IOptionalIdent extends IAbsSyn {

	}

	public interface IMonadicOperator extends IAbsSyn {

	}

}
