import datatypes.TokenList;
import interfaces.ITokenList;
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

    @Test
    public void testCreateToken() {
        Scanner scanner = new Scanner();
        ITokenList tokenList = scanner.createTokenList(text);
    }
}