import conSyn.IConcSyn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import scanner.Scanner;
import scanner.errors.GrammarError;
import scanner.tokenList.ITokenList;

public class ParserTupleTest {

    @Before
    public void init() {

    }

    @Test
    public void testParser() {
        IConcSyn parseTree;
        String TupleDeclaration = "program TupleTest()\n" +
                "global\n" +
                "fun add(mytuple1:(int32,int32)) returns s:Int32\n" +
                "local\n" +
                "const mytuple1:(int32,int32)\n" +
                "do\n" +
                "mytuple1 := (1,2);\n" +
                "result := 2\n" +
                "endfun\n" +
                "do\n" +
                "call add()\n" +
                "endprogram";
        parseTree = checkProgram(TupleDeclaration);
        parseTree.toString();
    }

    private IConcSyn checkProgram(String addProgram) {
        ITokenList tokenList = null;
        try {
            Scanner scanner = new Scanner();
            tokenList = scanner.scan(addProgram);
//            scanner.printResult(addProgram, addProgram, tokenList);
        } catch (Exception lexicalError) {
            lexicalError.printStackTrace();
        }

        try {
            Parser parseTree = new Parser(tokenList);
            parseTree.parse();
            return parseTree;
        } catch (GrammarError grammarError) {
            grammarError.printStackTrace();
            Assert.fail();
        }

        return null;
    }


}