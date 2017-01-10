import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.ContextError;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

/**
 * Created by tobi on 27/09/16.
 */
public class TypeCheckTest {
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
         *
         * typechecker
         * <=, >=, contexterror,
         * ==, != funktioniert
         * division, evt. modulo contexterror
         * NOT contexterror
         * &&, || contexterror
         *
         *
         */

        String complexAddProgram;

        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testGt() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isEquals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isEquals := bsp1 >= bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            Assert.fail();
        }
        catch(ContextError e) {
            // thats ok
        }

        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testEquals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isEquals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isEquals := bsp1 == bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }

        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun testUnequals() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var isUnequals:Bool\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "isUnequals := bsp1 != bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }

        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun add(bsp1:Compl) returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" +
                "var bsp2:Compl;\n" +
                "var result:Compl\n" +
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 + bsp2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
            System.out.println(absSyn.toString());
        } catch (ContextError contextError) {
            contextError.printStackTrace();
            Assert.fail();
        }
    }

    private IAbsSyn checkProgram(String addProgram) throws ContextError {
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

        IAbsSyn absSyn = parseTree.toAbsSyn();
        absSyn.check();
        return absSyn;

    }


}