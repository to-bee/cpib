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
                //TODO: TupleTest here
                "global\n" +
                "fun bla(mytuple1:(int32,bool,int32)) returns s:Int32\n" +
                "local\n" +
                "const mytuple1:(int32,bool,int32)\n" +
                "do\n" +
                "myTuple1 init := (1,True,23412);\n" +
                "result := 1\n" +
                "endfun\n" +
                "do\n" +
                "call bla()\n" +
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