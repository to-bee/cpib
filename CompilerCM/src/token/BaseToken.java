package token;

import framework.IToken;
import terminal.Terminals;

public class BaseToken implements IToken {
	private final Terminals terminal;
	
	public BaseToken(Terminals terminal){
		this.terminal = terminal;
	}
	
	public Terminals getTerminal(){
		return terminal;
	}
	
	public String toString() {
		return terminal.toString();
	}
}
