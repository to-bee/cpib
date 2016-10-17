package scanner.token;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TokenList implements ITokenList {
	private List<IToken> tokens;
	private Iterator<IToken> iterator;

	public TokenList() {
		tokens = new LinkedList<>();
	}

	@Override
	public void add(IToken token) {
		tokens.add(token);

	}

	@Override
	public void reset() {
		tokens.clear();
		iterator = null;
	}

	@Override
	public IToken nextToken() {
		if (iterator == null)
			iterator = tokens.iterator();
		return iterator.next();
	}
	
	@Override
	public String toString(){
		String output ="[";
		for( IToken t : tokens){
			System.out.println(t.toString());
			output += t.toString()+", ";
		}
		return output+"]";
	}

}
