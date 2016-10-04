import datatypes.OperatorPosition;
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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by tobi on 27/09/16.
 */
public class Scanner {

    public Scanner() {

    }

    public ITokenList scan(String text) throws LexicalError {
        List<String> wordList = this.createWordList(text);
        ITokenList tokenList = this.createTokenList(wordList);
        return tokenList;
    }

    /**
     * Create a complete wordlist
     * Splits words by delimitators ( \n\r\t,.;)
     * Splits articulated operators like (5+2 -> 5, +, 2) and determines their type
     * @param text
     * @return
     */
    public List<String> createWordList(String text) {
        List<String> wordList = new ArrayList<>();

        String delim = " \n\r\t,.;"; //insert here all delimitators
        StringTokenizer st = new StringTokenizer(text, delim);
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            boolean articulatedOperatorFound = false;

            /**
             * Token is may not be splitted completely
             * Checks whether an operator is articulated
             */
            for (Operators op : Operators.getAllSorted()) {
                String opIdentifier = op.getIdentifier();
                /**
                 * Operator contains more than one sign -> abort check
                 * Example: <= Operator
                 */
                if (word.equals(opIdentifier)) {
                    articulatedOperatorFound = false;
                    break;
                }
                /**
                 * Example: RelOpr without spaces between (2<=4)
                 */
                else if (word.contains(opIdentifier)) {
                    OperatorPosition oprPos = getOprPosition(word, opIdentifier);
                    if(oprPos == OperatorPosition.PREFIX) {
                        wordList.add(opIdentifier);
                        wordList.add(word.replace(opIdentifier, ""));
                        articulatedOperatorFound = true;
                        break;
                    }
                    else if(oprPos == OperatorPosition.POSTFIX) {
                        wordList.add(word.replace(opIdentifier, ""));
                        wordList.add(opIdentifier);
                        articulatedOperatorFound = true;
                        break;
                    }
                    else if(oprPos == OperatorPosition.INFIX) {
                        String[] words = word.split(opIdentifier);
                        wordList.add(words[0]);
                        wordList.add(opIdentifier);
                        wordList.add(words[1]);
                        articulatedOperatorFound = true;
                        break;
                    }
                    else if(oprPos == OperatorPosition.UNDEFINED) {
                        throw new IllegalArgumentException("Given opIdentifier is not ambigous. Please update the programming code!");
                    }

                }
            }

            // Token is already splitted completely
            if (!articulatedOperatorFound) {
                wordList.add(word);
            }
        }

        return wordList;
    }

    /**
     * Uses a completly splitted word list and creates some token from it
     *
     * @param wordList
     * @return
     */
    public ITokenList createTokenList(List<String> wordList) {
        ITokenList tokenList = new TokenList();
        for(String word : wordList) {
            tokenList.add(getTokenByWord(word));
        }

        return tokenList;
    }

    public OperatorPosition getOprPosition(String word, String opIdentifier) {
        if(word.indexOf(opIdentifier) == -1) {
            return OperatorPosition.UNDEFINED;
        }
        // Token is at the beginning (++i)
        else if(word.indexOf(opIdentifier) == 0) {
            return OperatorPosition.PREFIX;
        }
        // Token is at the end (i++)
        else if(word.indexOf(opIdentifier) == word.length()-opIdentifier.length()) {
            return OperatorPosition.POSTFIX;
        }
        // Token is in between (i+5)
        else {
            return OperatorPosition.INFIX;
        }
    }

    private IToken getTokenByWord(String word) {
        IToken token;
        Terminals terminal;
        Operators operator;
        terminal = Terminals.getTerminalFromString(word);
        if (terminal == Terminals.WHILE
                || terminal == Terminals.DO
                || terminal == Terminals.ENDWHILE) {
            token = new BaseToken(terminal);
        } else if (isNumeric(word)) {
            double d = Double.parseDouble(word);
            token = new Literal(d);
        } else if ((operator = Operators.getOperatorFromString(word)) != Operators.UNDEFINED) {
            if (operator.getOperatorTypes().stream().anyMatch(Operators.getAllOperatorTerminals()::contains)) {
                token = new Opr(operator);
            } else {
                token = new BaseToken(Terminals.UNDEFINED);
            }

        } else {
            token = new Ident(word);
        }
        return token;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}