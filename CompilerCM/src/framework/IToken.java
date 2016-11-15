package scanner.token;

import types.Terminals;

public interface IToken {
	public Terminals getTerminal();
	public String toString();
}
