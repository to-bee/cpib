import org.junit.Assert;
import scanner.Scanner;
import org.junit.Before;
import org.junit.Test;
import scanner.errors.LexicalError;
import scanner.tokenList.ITokenList;

/**
 * Created by ylaub on 19.12.2016.
 */
public class ScannerTupleTest {
    private static String text;
    private static String result;

    @Before
    public void init() {

    }

    @Test
    public void testCreateToken() {
        Scanner scanner = new Scanner();
        ITokenList tokenList;

        text = "const mytuple1:(int32,bool,int32)";
        tokenList = scanner.scan(text);

        text = "const mytuple1 : ( int32 , bool , int32 )";
        tokenList = scanner.scan(text);

        text = "mytuple1[0]";
        tokenList = scanner.scan(text);
    }
}
