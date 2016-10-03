import java.util.StringTokenizer;

import datatypes.Operators;
import datatypes.Terminals;
import datatypes.TokenList;
import dictionary.Dictionary;
import errors.LexicalError;
import interfaces.IToken;
import interfaces.ITokenList;
import tokens.BaseToken;
import tokens.Ident;
import tokens.Literal;
import tokens.RelOpr;

/**
 * Created by tobi on 27/09/16.
 */
public class Scanner {

    public Scanner() {

    }
    
    public ITokenList scan(CharSequence cs) throws LexicalError {
		TokenList tl = new TokenList(); // might become linkedlist
		
    	
    	
    	return null;
    	
    }

    public ITokenList createTokenList(String text) {
//        List<String> wordList = new ArrayList<>();
        ITokenList tokenList = new TokenList();

        Dictionary dictionary = new Dictionary();

        String delim = " \n\r\t,.;"; //insert here all delimitators
        StringTokenizer st = new StringTokenizer(text, delim);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            IToken token = null;
            Terminals terminal = null;
            Operators operator = null;
            if((terminal = Terminals.getTerminalFromString(word)) == Terminals.WHILE) {
                token = new BaseToken(terminal);
            }
            else if((operator = Operators.getOperatorFromString(word)) != Operators.UNDEFINED) {
                token = new RelOpr(operator);
            }
            else if(isNumeric(word)) {
                double d = Double.parseDouble(word);
                token = new Literal(d);
            }
            else {
                token = new Ident(word); // Var name
            }

            tokenList.add(token);
//            wordList.add(word);
        }

        return tokenList;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}