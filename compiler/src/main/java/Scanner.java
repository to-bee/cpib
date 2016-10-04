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
import tokens.Opr;

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
            terminal = Terminals.getTerminalFromString(word);
            if(terminal == Terminals.WHILE
                    || terminal == Terminals.DO
                    || terminal == Terminals.ENDWHILE) {
                token = new BaseToken(terminal);
            }
            // Will filter Unary operators (TODO: ++, --, !)
            else if(isNumeric(word)) {
                double d = Double.parseDouble(word);
                token = new Literal(d);
            }
            else if((operator = Operators.getOperatorFromString(word)) != Operators.UNDEFINED) {
                switch(operator.getOperatorType()) {
                    case ARITHMOPR:
                        token = new Opr(operator);
                        break;
                    case ASSIGNOPR:
                        token = new Opr(operator);
                        break;
                    case CONDOPR:
                        token = new Opr(operator);
                        break;
                    case RELOPR:
                        token = new Opr(operator);
                        break;
                    case UNARYOPR:
                        token = new Opr(operator);
                        break;
                    default:
                        token = new BaseToken(Terminals.UNDEFINED);
                        break;
                }

            }
            else {
                token = new Ident(word);
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