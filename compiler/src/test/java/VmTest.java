import absSyn.IAbsSyn;
import conSyn.IConcSyn;
import context.TokenVm;
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
public class VmTest {
    @Test
    public void testComplex() {
        IAbsSyn absSyn;
        String complexAddProgram;

        // Multiple assignement of a variable
        complexAddProgram = "program ComplexTest()\n" +
                "global\n" +
                "fun addVar() returns s:Int32\n" +
                "local\n" +
                "var bsp1:Compl;\n" + // init/alloc
                "var bsp2:Compl;\n" + // init/alloc
                "var result:Compl\n" + // init/allow
                "do\n" +
                "bsp1 := (5+I*4)+I*4;\n" + // assignment
                "bsp1 := bsp1+33;\n" +
                "bsp2 := bsp1*5;\n" +
                "bsp2 := 4-I*5;\n" +
                "result := bsp1 + bsp2 + 4-I*5\n" +
                "endfun\n" +
                "do\n" +
                "call addVar()\n" +
                "endprogram";
        try {
            absSyn = checkProgram(complexAddProgram);
        } catch (ContextError e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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

        TokenVm vm = new TokenVm(tokenList);

        IAbsSyn absSyn = parseTree.toAbsSyn();
        absSyn.check();
        return absSyn;

    }


}