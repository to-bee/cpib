import org.junit.Assert;
import scanner.Scanner;
import org.junit.Before;
import org.junit.Test;
import scanner.errors.LexicalError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class ScannerTest {
    private static String text;
    private static String result;

    @Before
    public void init() {

    }

    /**
     * Presentations result:
     * [WHILE, (IDENT, "x36"), (RELOPR, LE), (LITERAL, 67), DO, (IDENT, "x"), BECOMES, (IDENT, "x"), (ADDOPR, MINUS), (LITERAL, 1), ENDWHILE, PROGRAM]
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
        ITokenList tokenList;

//        text = "4+i*2";
//        scanner.model.tokenList = scanner.scan(text);
//        scanner.printResult(text, result, scanner.model.tokenList);


        // Remove spaces
//        text = "a := (67 + 31) - 2";
//        tokenList = scanner.scan(text);
//        scanner.printResult(text, result, tokenList);

        text = "while x36 <= 67 do\nx := x-1\nendwhile";
        result = "[WHILE, (IDENT, \"x36\"), (RELOPR, LE), (LITERAL, 67), DO, (IDENT, \"x\"), BECOMES, (IDENT, \"x\"), (ADDOPR, MINUS), (LITERAL, 1), ENDWHILE, SENTINEL]";
        tokenList = scanner.scan(text);

        text = "Liebe Grossmutter!\nZu Deinem 67-ten Geburtstag";
        result = "[(IDENT, „Liebe\"), (IDENT, „Grossmutter\"), EXCLAMARK, (IDENT, „Zu\"), (IDENT, „Deinem\"), (LITERAL, 67), (ADDOPR, MINUS), (IDENT, „ten\"), (IDENT, „Geburtstag\"), ...]";
        tokenList = scanner.scan(text);

        text = "div63";
        tokenList = scanner.scan(text);

        text = "%63";
        tokenList = scanner.scan(text);

        text = "while632";
        tokenList = scanner.scan(text);

        text = "72while";
        try {
            tokenList = scanner.scan(text);
            Assert.fail();
        }
        catch(LexicalError e) {

        }

        text = "abc23";
        tokenList = scanner.scan(text);

        text = "23abc";
        try {
            tokenList = scanner.scan(text);
            Assert.fail();
        }
        catch(LexicalError e) {

        }

        text = "whilea23abc";
        tokenList = scanner.scan(text);

        text = "a23abcwhile";
        tokenList = scanner.scan(text);

        text = "a23whilecde";
        tokenList = scanner.scan(text);

        text = "whilewhilea23abc";
        tokenList = scanner.scan(text);
    }
}