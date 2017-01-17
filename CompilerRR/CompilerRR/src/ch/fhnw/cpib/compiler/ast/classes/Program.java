package ch.fhnw.cpib.compiler.ast.classes;

import java.util.List;

import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn;
import ch.fhnw.cpib.compiler.context.CompilerE;
import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.vm.IInstructions;

public class Program implements IAbsSyn.IProgram{

	Token ident;
	List<IAbsSyn.IProgramParameter> paramList;
	List<IAbsSyn.IDeclaration> declarationList;
	List<IAbsSyn.ICommand> commandList;
	
	public Program(Token ident, List<IProgramParameter> paramList,
			List<IDeclaration> declarationList, List<ICommand> commandList) {
		super();
		this.ident = ident;
		this.paramList = paramList;
		this.declarationList = declarationList;
		this.commandList = commandList;
	}

	@Override
	public void check() {
		for (IDeclaration iDeclaration : declarationList) {
			iDeclaration.check();
		}
		for (ICommand iCommand : commandList) {
			iCommand.check();
		}
	}

	@Override
	public void code(int i) throws CodeTooSmallError {
	    final ICodeArray codeArray = CompilerE.COMPILER.getCodeArray();
	    int loc = i;
	    
	    for(IProgramParameter par : paramList)
	    	loc = par.code(loc);
	    
	    for(IDeclaration dec : declarationList)
	    	loc = dec.code(loc);
	    
	    for(ICommand cmd : commandList)
	    	loc = cmd.code(loc);
	    
	    codeArray.put(loc++, new IInstructions.Stop());
	    
	    //return loc;	
	}
	
	@Override
	public void print(String prefix){
		
		ident.print(prefix);
		
		for(IProgramParameter p : paramList){
			p.print(prefix + "-");
		}
		
		for(IDeclaration d : declarationList){
			d.print(prefix + "-");
		}
		
		for(ICommand c : commandList){
			c.print(prefix + "-");
		}
	}
	
}
