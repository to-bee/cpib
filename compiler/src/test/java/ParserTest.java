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
        // TODO: add
        //        "programm subtract() global\n" +
//                "var bsp1:compl; var bsp2:compl; var result:compl;\n" +
//                "do\n" +
//                "bsp1 := 2+I*3;\n" +
//                "bsp2 := 3-I*4;\n" +
//                "result := bsp1 - bsp2;\n" +
//                "endprogram\n" +
//                "programm multiply() global\n" +
//                "var bsp1:compl; var bsp2:compl; var result:compl;\n" +
//                "do\n" +
//                "bsp1 := 5+I*4;\n" +
//                "bsp2 := 4-I*5;\n" +
//                "result := bsp1 * bsp2;\n" +

        // without complex
        text = "program ComplexTest()\n" +
                "global\n" +
                "fun add(bsp1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := 5*4;\n" +
                "bsp2 := 4*5;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "s init := 1\n" +
                "endprogram";

//        text = "program ComplexTest()\n" +
//                "global\n" +
//                "fun add(bsp1:Compl) returns s:Int32\n" +
//                    "local\n" +
//                    "var bsp1:Compl;\n" +
//                    "var bsp2:Compl;\n" +
//                    "var result:Compl\n" +
//                    "do\n" +
//                        "bsp1 := 5+I*4;\n" +
//                        "bsp2 := 4-I*5;\n" +
//                        "result := bsp1 + bsp2\n" +
//                "endfun\n" +
//                "do\n" +
//                    "call add()\n" +
//                    "s init := 1\n" +
//                "endprogram";

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