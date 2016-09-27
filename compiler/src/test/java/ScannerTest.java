import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tobi on 27/09/16.
 */
public class ScannerTest {
    private static String text;

    @Before
    public static void init() {
        text = "while x36 <= 67 do\nx := x-1\nendwhile";
    }

    @Test
    public static void testCreateToken() {
        Scanner scanner = new Scanner();
        List<String> tokens = scanner.createTokens(text);
    }
}