package ch.fhnw.cpib.iml.scanner;

import static ch.fhnw.cpib.iml.scanner.Scanner.SCANNER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.exception.IllegalChrException;
import ch.fhnw.cpib.iml.scanner.exception.SyntaxException;

public class ScannerTest {

  private static String scan(final String string, final int index) {
    return SCANNER.scan(string).get(index).toString();
  }

  @Test
  public void testIdent() {
    assertEquals("(IDENT,\"SiMpLE\")", ScannerTest.scan("SiMpLE", 0));

    assertEquals("(IDENT,\"d1md1\")", ScannerTest.scan("d1md1", 0));
  }

  @Test
  public void testLiteral() {
    // Integer literals:
    assertEquals("(LITERAL,IntVal 1234)", ScannerTest.scan("1234", 0));
    assertEquals("(LITERAL,IntVal 0)", ScannerTest.scan("0", 0));
    assertEquals("(LITERAL,IntVal 0)", ScannerTest.scan("00000", 0));
    assertEquals("(LITERAL,IntVal 7)", ScannerTest.scan("007", 0));

    // boolean literals:
    assertEquals("(LITERAL,BoolVal TRUE)", ScannerTest.scan("true", 0));
    assertEquals("(LITERAL,BoolVal FALSE)", ScannerTest.scan("FALSE", 0));

    // string literals:
    assertEquals("(LITERAL,StrVal \"Bla\")", ScannerTest.scan("\"Bla\"", 0));
    // A String representing one Backslash:
    assertEquals("(LITERAL,StrVal \"\\\\\")", ScannerTest.scan("\"\\\\\"", 0));

    // String with surrogate pair - 'DESERET SMALL LETTER EN' (U+1044C):
    assertEquals("(LITERAL,StrVal \"\uD801\uDC4C\")", ScannerTest.scan(
        "\"\uD801\uDC4C\"", 0));

    // String with \0-Termination inside string:
    assertEquals("(LITERAL,StrVal \"\\0\")", ScannerTest.scan("\"\\0\"", 0));
    assertEquals("(LITERAL,StrVal \"A\\0B\")", ScannerTest.scan("\"A\\0B\"", 0));

    {
      // Content of src: LF A \ B TAB " FORM_FEED
      final String src = "\"\\nA\\\\B\\t\\\"\\f\"";
      final IToken<?> strVal = SCANNER.scan(src).get(0);
      assertEquals("(LITERAL,StrVal " + src + ")", strVal.toString());
      final AttrStrVal attr = (AttrStrVal) strVal.getAttribute();
      final int[] expected = new int[] { 7, // = maxlen
          '\n', 'A', '\\', 'B', '\t', '\"', '\f' };
      assertArrayEquals(expected, attr.getValue());
    }
    {
      // str := "\R" -> \r|\n|\r\n
      final String src = "\"\\R\"";
      final IToken<?> strVal = SCANNER.scan(src).get(0);
      assertEquals("(LITERAL,StrVal " + src + ")", strVal.toString());
      final AttrStrVal attr = (AttrStrVal) strVal.getAttribute();
      int[] expected = null;
      final String sep = System.getProperty("line.separator");
// @formatter:off
      if(sep.length() == 1)
        expected = new int[] {
           1,//.maxlen
           sep.charAt(0)
        };
      else if(sep.length() == 2)
        expected = new int[] {
          2,//.maxlen
          sep.charAt(0),
          sep.charAt(1)
         };
      else fail();
// @formatter:on
      assertArrayEquals(expected, attr.getValue());
    }
    try {
      // missing end delimiter:
      ScannerTest.scan("A\nX\"Bla", 0);
      fail();
    } catch (final SyntaxException e) {
      // Points to starting delimiter of string:
      assertEquals(2, e.getLineNumber());
      assertEquals(2, e.getColumn());
    }
    try {
      // Line break inside String:
      ScannerTest.scan("\"A\nB\"", 0);
      fail();
    } catch (final IllegalChrException e) {
    }
  }

  @Test
  public void testBrackets() {
    assertEquals("LBRACKET", scan("[", 0));
    assertEquals("RBRACKET", scan("]", 0));
  }

  @Test
  public void testWithoutWhiteSpace() {
    assertEquals("(MULTOPR,TIMES)", scan("var Blubb:=14*13DiV17;", 4));
  }

  @Test(expected = SyntaxException.class)
  public void testException() {
    ScannerTest.scan("var test := 2 / 3", 4);
  }

  @Test
  public void testLineNumber() {
    // A is Token 0 on line 1.
    // B is Token 1 on line 2. Et cetera...
    final String source = "A\rB\nC true \r\n E\r\rG\n\nI\n\rK\n \nM\n";
    final TokenList tokenList = SCANNER.scan(source);

    final Token<?> A = tokenList.get(0);
    assertEquals("A", A.getAttribute().getLexem());
    assertEquals(1, A.getLineNumber());
    assertEquals(1, A.getColumn());

    final Token<?> B = tokenList.get(1);
    assertEquals("B", B.getAttribute().getLexem());
    assertEquals(2, B.getLineNumber());
    assertEquals(1, B.getColumn());

    final Token<?> C = tokenList.get(2);
    assertEquals("C", C.getAttribute().getLexem());
    assertEquals(3, C.getLineNumber());
    assertEquals(1, C.getColumn());

    final Token<?> _true = tokenList.get(3);
    assertEquals("TRUE", _true.getAttribute().getLexem());
    assertEquals(3, _true.getLineNumber());
    assertEquals(3, _true.getColumn());

    final Token<?> E = tokenList.get(4);
    assertEquals("E", E.getAttribute().getLexem());
    assertEquals(4, E.getLineNumber());
    assertEquals(2, E.getColumn());

    final Token<?> G = tokenList.get(5);
    assertEquals("G", G.getAttribute().getLexem());
    assertEquals(6, G.getLineNumber());
    assertEquals(1, G.getColumn());

    final Token<?> I = tokenList.get(6);
    assertEquals("I", I.getAttribute().getLexem());
    assertEquals(8, I.getLineNumber());
    assertEquals(1, I.getColumn());

    final Token<?> K = tokenList.get(7);
    assertEquals("K", K.getAttribute().getLexem());
    assertEquals(10, K.getLineNumber());
    assertEquals(1, K.getColumn());

    final Token<?> M = tokenList.get(8);
    assertEquals("M", M.getAttribute().getLexem());
    assertEquals(12, M.getLineNumber());
    assertEquals(1, M.getColumn());

  }

  @Test
  public void testCombination() {
    // @formatter:off
    final String[] lexems = {
      " ", "\n", "\r", "\t", "\"string\"", "\"\\\\\"",
      "if", "IF", " X ", "\"ä耀\"", "07", "\"\"", " 0 ", "0",
      ":=", "(false)", "/=", "\"۞\"",
      "+", "?", "!", "*"
    };
    // @formatter:on
    final StringBuilder sb = new StringBuilder();
    for (final String l1 : lexems) {
      sb.append(l1);
      for (final String l2 : lexems) {
        sb.append(l2);
      }
    }
    sb.append(" EndOfCode\n");
    final TokenList tokenList = SCANNER.scan(sb.toString());
    assertEquals("EndOfCode", tokenList.get(tokenList.size() - 2)
        .getAttribute().getLexem());
    assertEquals(Terminal.SENTINEL, tokenList.get(tokenList.size() - 1)
        .getTerminal());
  }

  @Test
  public void testAlias() throws Exception {
    for (final Alias alias : Alias.values()) {
      final String token = scan(alias.getUnicode() + "\n", 0);
      assertTrue(token + " ≠ " + alias.name(), token.contains(alias.name()));
    }
  }

  @Test
  public void testArrFun() throws Exception {
    assertEquals("(ARRFUN,STRLEN)", ScannerTest.scan("bla.strlen", 1));
    assertEquals("(ARRFUN,MAXLEN)", ScannerTest.scan("\"x\".MAXLEN", 1));
  }
}
