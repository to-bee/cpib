package ch.fhnw.cpib.iml.parser;

import static ch.fhnw.cpib.iml.parser.Parser.PARSER;
import static ch.fhnw.cpib.iml.scanner.Scanner.SCANNER;

import java.io.File;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IProgram;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Program;
import ch.fhnw.cpib.iml.scanner.FileSourceCode;
import ch.fhnw.cpib.iml.scanner.Scanner;
import ch.fhnw.cpib.iml.scanner.TokenList;

public class ParserTest {

  @Test
  public void testMain() {
    final File file = new File("docs/IML/testProg.iml");

    try {
      PARSER.init(SCANNER.scan(new FileSourceCode(file)));
    } catch (final Exception e) {
      e.printStackTrace();
    }

    PARSER.parse();
    PARSER.getConcSyn().printTree();
  }

  @Test
  public void testParse() {
    final String src =// @formatter:off
        "program TEST\n" +     //1
    		"global\n" +           //2
    		"var a : int32\n" +    //3
    		"{\n" +                //4
    		"a init := 1 + 2\n" +  //5
    		"}\n";// @formatter:on //6
    final Program program = (Program) parse(src);
    Assert.assertEquals(program.ident.toString(), "(IDENT,\"TEST\")");
    program.toAbsSyn();
  }

  @Test
  public void testAbsSyn() {
    final File file = new File("docs/IML/testProg.iml");

    try {
      PARSER.init(SCANNER.scan(new FileSourceCode(file)));
    } catch (final Exception e) {
      e.printStackTrace();
    }

    PARSER.parse();
    PARSER.getConcSyn().getProgram().toAbsSyn();
  }

  static IConcSyn.IProgram parse(final String code) {
    final TokenList tokenList = Scanner.SCANNER.scan(code);
    Parser.PARSER.init(tokenList);
    return Parser.PARSER.parse();
  }

  @Test
  public void testGROSSBUCHSTABEN() throws Exception {
    final File file = new File("docs/IML/GROSSBUCHSTABEN.iml");

    final Charset utf8 = Charset.forName("UTF-8");
    try (FileSourceCode src = new FileSourceCode(file, utf8)) {
      PARSER.init(SCANNER.scan(src));
    } catch (final Exception e) {
      e.printStackTrace();
    }

    PARSER.parse();
    final IProgram absSyn = PARSER.getConcSyn().getProgram().toAbsSyn();
    absSyn.check();
    PARSER.getConcSyn().printTree();
  }

}
