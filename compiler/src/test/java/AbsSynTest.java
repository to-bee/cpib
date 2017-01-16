import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class AbsSynTest {
    @Test
    public void testComplex() {
        IAbsSyn absSyn;
        /**
         * scope checking
         * global storage delcaration, local storage declaration not the same name
         * fun parameter not the same name as local storage declarations
         * local storage declarations not two variables with the same name
         *
         * type checking
         * string not assigneable to int etc.
         */
        String complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar(param1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4);\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        absSyn = checkProgram(complexAddProgram);
        Scanner scanner = new Scanner();
        ITokenList tokenList;
        tokenList = scanner.scan(complexAddProgram);
        System.out.println(tokenList.toString());
        System.out.println(absSyn.toString());
    }

    private IAbsSyn checkProgram(String addProgram) {
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(addProgram);
//            scanner.printResult(addProgram, addProgram, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        IConcSyn parseTree = null;
        try {
            parseTree = new Parser(tokenList);
            parseTree.parse();
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
            Assert.fail();
        }

        try {
            IAbsSyn absSyn = parseTree.toAbsSyn();
            return absSyn;
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }

        return null;
    }


}