import datatypes.OperatorPosition;
import datatypes.TokenList;
import interfaces.ITokenList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tobi on 27/09/16.
 */
public class ScannerTest {
    private static String text;
    private static String result;

    @Before
    public void init() {
        text = "while x36 <= 67 do\nx := x-1\nendwhile";
        result = "[WHILE, (IDENT, \"x36\"), (RELOPR, LE), (LITERAL, 67), DO, (IDENT, \"x\"), BECOMES, (IDENT, \"x\"), (ADDOPR, MINUS), (LITERAL, 1), ENDWHILE, SENTINEL]";
    }

    /**
     * Presentations result:
     * [WHILE, (IDENT, "x36"), (RELOPR, LE), (LITERAL, 67), DO, (IDENT, "x"), BECOMES, (IDENT, "x"), (ADDOPR, MINUS), (LITERAL, 1), ENDWHILE, START_ROUTINE]
     *
     * Our result:
     * [WHILE, (IDENT, "x36"), (RELOPR, LE), (LITERAL, 67.0), DO, (IDENT, "x"), (ASSIGNOPR, BECOMES), (IDENT, "x"), MINUS, (LITERAL, 1.0), ENDWHILE],
     *
     * Questions:
     * - Should the scanner determine the type of Operator (+ can be arithmetic or unary e.g.)
     * - Why do we need sentinell when we use ENDWHILE (is sentinel marking the end of the program?)
     * - What is the operator TIMES for?
     *
     * Tobi, 4.10
     */
    @Test
    public void testCreateToken() {
        Scanner scanner = new Scanner();
        List<String> wordList = scanner.createWordList(text);
        ITokenList tokenList = scanner.createTokenList(wordList);
    }

    @Test
    public void testOperatorPosition() {
        Scanner scanner = new Scanner();
        assertEquals(scanner.getOprPosition("22--", "+"), OperatorPosition.UNDEFINED);
        assertEquals(scanner.getOprPosition("22+5", "+"), OperatorPosition.INFIX);
        assertEquals(scanner.getOprPosition("++5", "++"), OperatorPosition.PREFIX);
        assertEquals(scanner.getOprPosition("22--", "--"), OperatorPosition.POSTFIX);
    }
}