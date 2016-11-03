import conSyn.IConcSyn;
import model.errors.GrammarError;
import tokenList.ITokenList;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;

/**
 * Created by tobi on 27/09/16.
 */
public class ParserTest {
    private static String text;
    private static String result;
    private ITokenList tokenList;

    @Before
    public void init() {
        Scanner scanner = new Scanner();
//        text = "a := (67 + 31) - 2";



        text = "while x36 <= 67 do\nx := x-1\nendwhile";
        result = "[WHILE, (IDENT, \"x36\"), (RELOPR, LE), (LITERAL, 67), DO, (IDENT, \"x\"), BECOMES, (IDENT, \"x\"), (ADDOPR, MINUS), (LITERAL, 1), ENDWHILE, SENTINEL]";
        tokenList = scanner.scan(text);
        scanner.printResult(text, result, tokenList);
    }


    @Test
    public void testParser() {
        try {
            Parser parser = new Parser(tokenList);
            IConcSyn concSyn = parser.parse();
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
        }
    }


}