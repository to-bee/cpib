package ch.fhnw.cpib.iml.scanner;

import static ch.fhnw.cpib.iml.scanner.Scanner.SCANNER;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cpib.iml.lib.Chr;

public class TokenTest {

  private static String scan(final String string) {
    return SCANNER.scan(string).get(0).toString();
  }

  @Before
  public void before() {
    Scanner.SCANNER.hashCode(); // To initialize the Scanner.
  }

  @Test
  public void testAllSymbols() {
    assertEquals("LPAREN", TokenTest.scan("("));
    assertEquals("RPAREN", TokenTest.scan(")"));
    assertEquals("COMMA", TokenTest.scan(","));
    assertEquals("SEMICOLON", TokenTest.scan(";"));
    assertEquals("COLON", TokenTest.scan(":"));
    assertEquals("QUESTMARK", TokenTest.scan("?"));
    assertEquals("EXCLAMARK", TokenTest.scan("!"));
    assertEquals("BECOMES", TokenTest.scan(":="));
    assertEquals("LBRACE", TokenTest.scan("{"));
    assertEquals("RBRACE", TokenTest.scan("}"));
    assertEquals("(MULTOPR,TIMES)", TokenTest.scan("*"));
    assertEquals("(ADDOPR,PLUS)", TokenTest.scan("+"));
    assertEquals("(ADDOPR,MINUS)", TokenTest.scan("-"));
    assertEquals("(RELOPR,EQ)", TokenTest.scan("="));
    assertEquals("(RELOPR,NE)", TokenTest.scan("/="));
    assertEquals("(RELOPR,LT)", TokenTest.scan("<"));
    assertEquals("(RELOPR,GT)", TokenTest.scan(">"));
    assertEquals("(RELOPR,LE)", TokenTest.scan("<="));
    assertEquals("(RELOPR,GE)", TokenTest.scan(">="));
  }

  @Test
  public void testAllKeywords() {
    assertEquals("(TYPE,BOOL)", TokenTest.scan("bool"));
    assertEquals("CALL", TokenTest.scan("call"));
    assertEquals("(BOOLOPR,CAND)", TokenTest.scan("cand"));
    assertEquals("(CHANGEMODE,CONST)", TokenTest.scan("const"));
    assertEquals("(MECHMODE,COPY)", TokenTest.scan("copy"));
    assertEquals("(BOOLOPR,COR)", TokenTest.scan("cor"));
    assertEquals("(MULTOPR,DIV)", TokenTest.scan("div"));
    assertEquals("ELSE", TokenTest.scan("else"));
    assertEquals("(LITERAL,BoolVal FALSE)", TokenTest.scan("false"));
    assertEquals("FUN", TokenTest.scan("fun"));
    assertEquals("GLOBAL", TokenTest.scan("global"));
    assertEquals("IF", TokenTest.scan("if"));
    assertEquals("(FLOWMODE,IN)", TokenTest.scan("in"));
    assertEquals("INIT", TokenTest.scan("init"));
    assertEquals("(FLOWMODE,INOUT)", TokenTest.scan("inout"));
    assertEquals("(TYPE,INT32)", TokenTest.scan("int32"));
    assertEquals("LOCAL", TokenTest.scan("local"));
    assertEquals("(MULTOPR,MOD)", TokenTest.scan("mod"));
    assertEquals("NOT", TokenTest.scan("not"));
    assertEquals("(FLOWMODE,OUT)", TokenTest.scan("out"));
    assertEquals("PROC", TokenTest.scan("proc"));
    assertEquals("PROGRAM", TokenTest.scan("program"));
    assertEquals("(MECHMODE,REF)", TokenTest.scan("ref"));
    assertEquals("RETURNS", TokenTest.scan("returns"));
    assertEquals("SKIP", TokenTest.scan("skip"));
    assertEquals("(LITERAL,BoolVal TRUE)", TokenTest.scan("true"));
    assertEquals("(CHANGEMODE,VAR)", TokenTest.scan("var"));
    assertEquals("WHILE", TokenTest.scan("while"));
  }

  @Test
  public void testUniqueness() throws Exception {
    final IToken<?> t1 = DistinctToken.getToken("TRUE");
    final IToken<?> t2 = DistinctToken.getToken("true");
    final IToken<?> t3 = DistinctToken.getToken(Arrays.asList(new Chr[] { Chr.get('t'),
        Chr.get('R'), Chr.get('u'), Chr.get('E') }));

    assertSame(t1, t2);
    assertSame(t1, t3);
    assertSame(t2, t3);

    assertSame(DistinctToken.getToken("*"), DistinctToken.getToken("*"));

    assertSame(DistinctToken.getToken("MyVar"), DistinctToken.getToken("MYVAR"));
  }
}
