import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class ParserTest {
    private static String text;
    private static String result;
    private ITokenList tokenList;

    @Before
    public void init() {

    }


    @Test
    public void testParser() {
        text = "program add() global\n" +
                "var bsp1:compl; var bsp2:compl; var result:compl;\n" +
                "do\n" +
                "bsp1 := 5+i*4;\n" +
                "bsp2 := 4-i*5;\n" +
                "result := bsp1 + bsp2;\n" +
                "endprogram\n" +
                "programm subtract() global\n" +
                "var bsp1:compl; var bsp2:compl; var result:compl;\n" +
                "do\n" +
                "bsp1 := 2+i*3;\n" +
                "bsp2 := 3-i*4;\n" +
                "result := bsp1 - bsp2;\n" +
                "endprogram\n" +
                "programm multiply() global\n" +
                "var bsp1:compl; var bsp2:compl; var result:compl;\n" +
                "do\n" +
                "bsp1 := 5+i*4;\n" +
                "bsp2 := 4-i*5;\n" +
                "result := bsp1 * bsp2;\n" +
                "endprogram";

        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(text);
            scanner.printResult(text, result, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        try {
            Parser parser = new Parser(tokenList);
            IConcSyn concSyn = parser.parseProgram();
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
            Assert.fail();
        }
    }


}