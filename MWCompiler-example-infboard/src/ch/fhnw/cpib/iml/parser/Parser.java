package ch.fhnw.cpib.iml.parser;

import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.lib.enums.ArrFun;
import ch.fhnw.cpib.iml.lib.enums.BoolOpr;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.lib.enums.MultOpr;
import ch.fhnw.cpib.iml.lib.enums.RelOpr;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.ArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.BlockCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdCall;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdExclaMark;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdIf;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdQuestMark;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdSkip;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CmdWhile;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.ConcSyn;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.CpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Expr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.ExprList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FactorArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FactorIdent;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FactorLiteral;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FactorLparen;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FactorMonadicOpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.FunDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.GlobImp;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.GlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.GlobInitList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.IConcSyn;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.MonadicOprAddOpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.MonadicOprNot;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptArrFactorEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptChangeMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptChangeModeEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptExprEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFactorIdentArrFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFactorIdentArrFun;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFactorIdentEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFactorIdentExprList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFactorIdentInit;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFlowMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptFlowModeEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobImpList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobImpListEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobInitList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobInitListEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobalCpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptGlobalCpsDeclEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptLocalCpsDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptLocalCpsDeclEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptMechMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptMechModeEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptParamEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptRelOprTerm2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.OptRelOprTerm2Epsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Param;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.ParamList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.ProcDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Program;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepAddOprTerm3;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepAddOprTerm3Epsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepBoolOprTerm1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepBoolOprTerm1Epsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepCmd;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepCmdEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepDecl;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepDeclEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepExpr;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepExprEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepGlobImp;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepGlobImpEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepIdent;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepIdentEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepMultOprFactor;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepMultOprFactorEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepParam;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.RepParamEpsilon;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.StoreDeclChangeMode;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.StoreDeclIdent;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Term1;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Term2;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.Term3;
import ch.fhnw.cpib.iml.parser.exception.GrammarException;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.TokenList;
import ch.fhnw.cpib.iml.scanner.attr.AttrLiteral;

public enum Parser {
  PARSER;

  private Token<?> token;
  private Terminal             terminal;
  private TokenList            tokenList;
  private ConcSyn              concSyn;

  private Parser() {
  }

  public ConcSyn getConcSyn() {
    return this.concSyn;
  }

  /**
   * Method to prepare Parser for parsing
   *
   * @param tokenLst
   *          reseted {@link TokenList} from {@link Scanner}
   */
  public void init(final TokenList tokenLst) {
    this.tokenList = tokenLst;
    this.token = tokenLst.nextToken();
    this.terminal = this.token.getTerminal();
    this.concSyn = new ConcSyn();
  }

  /**
   * Consume next {@link Terminal} and return it or throw a
   * {@link GrammarException} if given {@link Terminal} is not the expected
   * {@link Terminal}
   *
   * @param expectedTerminal
   * @return
   */
  private Token<?> consume(final Terminal expectedTerminal) {
    if (this.terminal == expectedTerminal) {
      final Token<?> consumedToken = this.token;
      if (this.terminal != Terminal.SENTINEL) {
        this.token = this.tokenList.nextToken();
        this.terminal = this.token.getTerminal();
      }
      return consumedToken;
    }

    throw new GrammarException(this.token, expectedTerminal);
  }

  @SuppressWarnings("unchecked")
  private Token<String> consumeIdent() {
    final Token<?> t = this.consume(Terminal.IDENT);
    if (t.getAttribute().getValue() instanceof String)
      return (Token<String>) t;
    throw new GrammarException(this.token, Terminal.IDENT);
  }

  @SuppressWarnings("unchecked")
  private Token<RelOpr> consumeRelOpr() {
    final Token<?> t = this.consume(Terminal.RELOPR);
    if (t.getAttribute().getValue() instanceof RelOpr)
      return (Token<RelOpr>) t;
    throw new GrammarException(this.token, Terminal.RELOPR);
  }

  @SuppressWarnings("unchecked")
  private Token<MultOpr> consumeMultOpr() {
    final Token<?> t = this.consume(Terminal.MULTOPR);
    if (t.getAttribute().getValue() instanceof MultOpr)
      return (Token<MultOpr>) t;
    throw new GrammarException(this.token, Terminal.MULTOPR);
  }

  @SuppressWarnings("unchecked")
  private Token<BoolOpr> consumeBoolOpr() {
    final Token<?> t = this.consume(Terminal.BOOLOPR);
    if (t.getAttribute().getValue() instanceof BoolOpr)
      return (Token<BoolOpr>) t;
    throw new GrammarException(this.token, Terminal.BOOLOPR);
  }

  @SuppressWarnings("unchecked")
  private Token<AddOpr> consumeAddOpr() {
    final Token<?> t = this.consume(Terminal.ADDOPR);
    if (t.getAttribute().getValue() instanceof AddOpr)
      return (Token<AddOpr>) t;
    throw new GrammarException(this.token, Terminal.ADDOPR);
  }

  @SuppressWarnings("unchecked")
  private Token<ArrFun> consumeArrFun(final Terminal expectedTerminal) {
    final Token<?> t = this.consume(expectedTerminal);
    switch (t.getTerminal()) {
    case ARRFUN:
      return (Token<ArrFun>) t;
    default:
      throw new GrammarException(this.token, expectedTerminal);
    }
  }

  @SuppressWarnings("unchecked")
  private Token<int[]> consumeLiteral() {
    final Token<?> t = this.consume(Terminal.LITERAL);
    if (t.getAttribute() instanceof AttrLiteral)
      return (Token<int[]>) t;
    throw new GrammarException(this.token, Terminal.LITERAL);
  }

  @SuppressWarnings("unchecked")
  private Token<MechMode> consumeMechMode() {
    final Token<?> t = this.consume(Terminal.MECHMODE);
    if (t.getAttribute().getValue() instanceof MechMode)
      return (Token<MechMode>) t;
    throw new GrammarException(this.token, Terminal.MECHMODE);
  }

  @SuppressWarnings("unchecked")
  private Token<FlowMode> consumeFlowMode() {
    final Token<?> t = this.consume(Terminal.FLOWMODE);
    if (t.getAttribute().getValue() instanceof FlowMode)
      return (Token<FlowMode>) t;
    throw new GrammarException(this.token, Terminal.FLOWMODE);
  }

  @SuppressWarnings("unchecked")
  private Token<ChangeMode> consumeChangeMode() {
    final Token<?> t = this.consume(Terminal.CHANGEMODE);
    if (t.getAttribute().getValue() instanceof ChangeMode)
      return (Token<ChangeMode>) t;
    throw new GrammarException(this.token, Terminal.CHANGEMODE);
  }

  @SuppressWarnings("unchecked")
  private Token<Type> consumeType() {
    final Token<?> t = this.consume(Terminal.TYPE);
    if (t.getAttribute().getValue() instanceof Type)
      return (Token<Type>) t;
    throw new GrammarException(this.token, Terminal.TYPE);
  }

  public IConcSyn.IProgram parse() {
    final IConcSyn.IProgram program = this.program();
    this.consume(Terminal.SENTINEL);
    this.concSyn.setProgram(program);
    return program;
  }

  private IConcSyn.IProgram program() {
    if (this.terminal == Terminal.PROGRAM)
      return new Program(this.consume(Terminal.PROGRAM), this.consumeIdent(),
          this.optGlobalCpsDecl(), this.blockCmd());

    throw new GrammarException(this.token, Terminal.PROGRAM);
  }

  private IConcSyn.IOptGlobalCpsDecl optGlobalCpsDecl() {
    switch (this.terminal) {
    case GLOBAL:
      return new OptGlobalCpsDecl(this.consume(Terminal.GLOBAL), this.cpsDecl());
    case LBRACE:
      return new OptGlobalCpsDeclEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.GLOBAL, Terminal.LBRACE);
    }
  }

  private IConcSyn.IDecl decl() {
    switch (this.terminal) {
    case IDENT:
    case CHANGEMODE:
      return this.storeDecl();
    case FUN:
      return this.funDecl();
    case PROC:
      return this.procDecl();
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.FUN, Terminal.PROC);
    }

  }

  private IConcSyn.IStoreDecl storeDecl() {
    switch (this.terminal) {
    case CHANGEMODE:
      return new StoreDeclChangeMode(this.consumeChangeMode(), this
          .consumeIdent(), this.consume(Terminal.COLON), this.consumeType());
    case IDENT:
      return new StoreDeclIdent(this.consumeIdent(), this
          .consume(Terminal.COLON), this.consumeType());
    default:
      throw new GrammarException(this.token, Terminal.CHANGEMODE,
          Terminal.IDENT);
    }

  }

  private IConcSyn.IFunDecl funDecl() {
    if (this.terminal == Terminal.FUN)
      return new FunDecl(this.consume(Terminal.FUN), this.consumeIdent(), this
          .paramList(), this.consume(Terminal.RETURNS), this.storeDecl(), this
          .optGlobImpList(), this.optLocalCpsDecl(), this.blockCmd());

    throw new GrammarException(this.token, Terminal.FUN);
  }

  private IConcSyn.IOptGlobImpList optGlobImpList() {
    switch (this.terminal) {
    case GLOBAL:
      return new OptGlobImpList(this.consume(Terminal.GLOBAL), this
          .globImpList());
    case LBRACE:
    case LOCAL:
      return new OptGlobImpListEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.GLOBAL, Terminal.LBRACE,
          Terminal.LOCAL);
    }

  }

  private IConcSyn.IOptLocalCpsDecl optLocalCpsDecl() {
    switch (this.terminal) {
    case LOCAL:
      return new OptLocalCpsDecl(this.consume(Terminal.LOCAL), this.cpsDecl());
    case LBRACE:
      return new OptLocalCpsDeclEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.LOCAL, Terminal.LBRACE);
    }
  }

  private IConcSyn.IProcDecl procDecl() {
    if (this.terminal == Terminal.PROC)
      return new ProcDecl(this.consume(Terminal.PROC), this.consumeIdent(),
          this.paramList(), this.optGlobImpList(), this.optLocalCpsDecl(), this
              .blockCmd());

    throw new GrammarException(this.token, Terminal.PROC);
  }

  private IConcSyn.ICpsDecl cpsDecl() {
    switch (this.terminal) {
    case PROC:
    case FUN:
    case IDENT:
    case CHANGEMODE:
      return new CpsDecl(this.decl(), this.repDecl());
    default:
      throw new GrammarException("Error on line " + this.token.getLineNumber());
    }
  }

  private IConcSyn.IRepDecl repDecl() {
    switch (this.terminal) {
    case SEMICOLON:
      return new RepDecl(this.consume(Terminal.SEMICOLON), this.decl(), this
          .repDecl());
    case LBRACE:
      return new RepDeclEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.SEMICOLON,
          Terminal.LBRACE);
    }
  }

  private IConcSyn.IParamList paramList() {
    if (this.terminal == Terminal.LPAREN)
      return new ParamList(this.consume(Terminal.LPAREN), this.optParam(), this
          .consume(Terminal.RPAREN));

    throw new GrammarException(this.token, Terminal.LPAREN);
  }

  private IConcSyn.IOptParam optParam() {
    switch (this.terminal) {
    case IDENT:
    case CHANGEMODE:
    case MECHMODE:
    case FLOWMODE:
      return new OptParam(this.param(), this.repParam());
    case RPAREN:
      return new OptParamEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.MECHMODE, Terminal.FLOWMODE,
          Terminal.RPAREN);
    }

  }

  private IConcSyn.IRepParam repParam() {
    switch (this.terminal) {
    case COMMA:
      return new RepParam(this.consume(Terminal.COMMA), this.param(), this
          .repParam());
    case RPAREN:
      return new RepParamEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.COMMA, Terminal.RPAREN);
    }

  }

  private IConcSyn.IParam param() {
    switch (this.terminal) {
    case IDENT:
    case CHANGEMODE:
    case MECHMODE:
    case FLOWMODE:
      return new Param(this.optFlowMode(), this.optMechMode(), this.storeDecl());
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.MECHMODE, Terminal.FLOWMODE);
    }

  }

  private IConcSyn.IOptFlowMode optFlowMode() {
    switch (this.terminal) {
    case FLOWMODE:
      return new OptFlowMode(this.consumeFlowMode());
    case IDENT:
    case CHANGEMODE:
    case MECHMODE:
      return new OptFlowModeEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.FLOWMODE, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.MECHMODE);
    }

  }

  private IConcSyn.IOptMechMode optMechMode() {
    switch (this.terminal) {
    case MECHMODE:
      return new OptMechMode(this.consumeMechMode());
    case IDENT:
    case CHANGEMODE:
      return new OptMechModeEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.MECHMODE, Terminal.IDENT,
          Terminal.CHANGEMODE);
    }

  }

  private IConcSyn.IGlobImpList globImpList() {
    switch (this.terminal) {
    case IDENT:
    case CHANGEMODE:
    case FLOWMODE:
      return new GlobImpList(this.globImp(), this.repGlobImp());
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.FLOWMODE);
    }

  }

  private IConcSyn.IRepGlobImp repGlobImp() {
    switch (this.terminal) {
    case COMMA:
      return new RepGlobImp(this.consume(Terminal.COMMA), this.globImp(), this
          .repGlobImp());
    case LBRACE:
    case LOCAL:
      return new RepGlobImpEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.COMMA, Terminal.LBRACE,
          Terminal.LOCAL);
    }
  }

  private IConcSyn.IGlobImp globImp() {
    switch (this.terminal) {
    case IDENT:
    case CHANGEMODE:
    case FLOWMODE:
      return new GlobImp(this.optFlowMode(), this.optChangeMode(), this
          .consumeIdent());
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE, Terminal.FLOWMODE);
    }
  }

  private IConcSyn.IOptChangeMode optChangeMode() {
    switch (this.terminal) {
    case CHANGEMODE:
      return new OptChangeMode(this.consumeChangeMode());
    case IDENT:
      return new OptChangeModeEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.IDENT,
          Terminal.CHANGEMODE);
    }
  }

  private IConcSyn.ICmd cmd() {
    switch (this.terminal) {
    case SKIP:
      return new CmdSkip(this.consume(Terminal.SKIP));
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new CmdExpr(this.expr(), this.consume(Terminal.BECOMES), this
          .expr());
    case IF:
      return new CmdIf(this.consume(Terminal.IF),
          this.consume(Terminal.LPAREN), this.expr(), this
              .consume(Terminal.RPAREN), this.blockCmd(), this
              .consume(Terminal.ELSE), this.blockCmd());
    case WHILE:
      return new CmdWhile(this.consume(Terminal.WHILE), this
          .consume(Terminal.LPAREN), this.expr(),
          this.consume(Terminal.RPAREN), this.blockCmd());
    case CALL:
      return new CmdCall(this.consume(Terminal.CALL), this.consumeIdent(), this
          .exprList(), this.optGlobInitList());
    case QUESTMARK:
      return new CmdQuestMark(this.consume(Terminal.QUESTMARK), this.expr());
    case EXCLAMARK:
      return new CmdExclaMark(this.consume(Terminal.EXCLAMARK), this.expr());
    default:
      throw new GrammarException(this.token, Terminal.SKIP, Terminal.LPAREN,
          Terminal.ADDOPR, Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET,
          Terminal.LITERAL, Terminal.IF, Terminal.WHILE, Terminal.CALL,
          Terminal.QUESTMARK, Terminal.EXCLAMARK);
    }
  }

  private IConcSyn.IOptGlobInitList optGlobInitList() {
    switch (this.terminal) {
    case INIT:
      return new OptGlobInitList(this.consume(Terminal.INIT), this
          .globInitList());
    case RBRACE:
    case SEMICOLON:
      return new OptGlobInitListEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.INIT, Terminal.RBRACE,
          Terminal.SEMICOLON);
    }

  }

  private IConcSyn.IBlockCmd blockCmd() {
    switch (this.terminal) {
    case LBRACE:
      return new BlockCmd(this.consume(Terminal.LBRACE), this.cmd(), this
          .repCmd(), this.consume(Terminal.RBRACE));
    default:
      throw new GrammarException(this.token, Terminal.LBRACE);
    }

  }

  private IConcSyn.IRepCmd repCmd() {
    switch (this.terminal) {
    case SEMICOLON:
      return new RepCmd(this.consume(Terminal.SEMICOLON), this.cmd(), this
          .repCmd());
    case RBRACE:
      return new RepCmdEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.SEMICOLON,
          Terminal.RBRACE);
    }

  }

  private IConcSyn.IGlobInitList globInitList() {
    switch (this.terminal) {
    case IDENT:
      return new GlobInitList(this.consumeIdent(), this.optArrFactor(), this
          .repIdent());
    default:
      throw new GrammarException(this.token, Terminal.IDENT);
    }
  }

  private IConcSyn.IRepIdent repIdent() {
    switch (this.terminal) {
    case COMMA:
      return new RepIdent(this.consume(Terminal.COMMA), this.consumeIdent(),
          this.optArrFactor(), this.repIdent());
    case RBRACE:
    case SEMICOLON:
      return new RepIdentEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.COMMA, Terminal.RBRACE,
          Terminal.SEMICOLON);
    }

  }

  private IConcSyn.IExpr expr() {
    switch (this.terminal) {
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new Expr(this.term1(), this.repBoolOprTerm1());
    default:
      throw new GrammarException(this.token, Terminal.LPAREN, Terminal.ADDOPR,
          Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET, Terminal.LITERAL);
    }
  }

  private IConcSyn.IRepBoolOprTerm1 repBoolOprTerm1() {
    switch (this.terminal) {
    case BOOLOPR:
      return new RepBoolOprTerm1(this.consumeBoolOpr(), this.term1(), this
          .repBoolOprTerm1());
    case COMMA:
    case RBRACKET:
    case RPAREN:
    case RBRACE:
    case SEMICOLON:
    case BECOMES:
      return new RepBoolOprTerm1Epsilon();
    default:
      throw new GrammarException(this.token, Terminal.BOOLOPR, Terminal.COMMA,
          Terminal.RBRACKET, Terminal.RPAREN, Terminal.RBRACE,
          Terminal.SEMICOLON, Terminal.BECOMES);
    }

  }

  private IConcSyn.ITerm1 term1() {
    switch (this.terminal) {
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new Term1(this.term2(), this.optRelOprTerm2());
    default:
      throw new GrammarException(this.token, Terminal.LPAREN, Terminal.ADDOPR,
          Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET, Terminal.LITERAL);
    }

  }

  private IConcSyn.IOptRelOprTerm2 optRelOprTerm2() {
    switch (this.terminal) {
    case RELOPR:
      return new OptRelOprTerm2(this.consumeRelOpr(), this.term2());
    case COMMA:
    case RBRACKET:
    case RPAREN:
    case RBRACE:
    case SEMICOLON:
    case BECOMES:
    case BOOLOPR:
      return new OptRelOprTerm2Epsilon();
    default:
      throw new GrammarException(this.token, Terminal.RELOPR, Terminal.COMMA,
          Terminal.RBRACKET, Terminal.RPAREN, Terminal.RBRACE,
          Terminal.SEMICOLON, Terminal.BECOMES, Terminal.BOOLOPR);
    }
  }

  private IConcSyn.ITerm2 term2() {
    switch (this.terminal) {
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new Term2(this.term3(), this.repAddOprTerm3());
    default:
      throw new GrammarException(this.token, Terminal.LPAREN, Terminal.ADDOPR,
          Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET, Terminal.LITERAL);
    }
  }

  private IConcSyn.IRepAddOprTerm3 repAddOprTerm3() {
    switch (this.terminal) {
    case ADDOPR:
      return new RepAddOprTerm3(this.consumeAddOpr(), this.term3(), this
          .repAddOprTerm3());
    case COMMA:
    case RBRACKET:
    case RPAREN:
    case RBRACE:
    case SEMICOLON:
    case BECOMES:
    case BOOLOPR:
    case RELOPR:
      return new RepAddOprTerm3Epsilon();
    default:
      throw new GrammarException(this.token, Terminal.ADDOPR, Terminal.COMMA,
          Terminal.RBRACKET, Terminal.RPAREN, Terminal.RBRACE,
          Terminal.SEMICOLON, Terminal.BECOMES, Terminal.BOOLOPR,
          Terminal.RELOPR);
    }
  }

  private IConcSyn.ITerm3 term3() {
    switch (this.terminal) {
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new Term3(this.factor(), this.repMultOprFactor());
    default:
      throw new GrammarException(this.token, Terminal.LPAREN, Terminal.ADDOPR,
          Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET, Terminal.LITERAL);
    }
  }

  private IConcSyn.IRepMultOprFactor repMultOprFactor() {
    switch (this.terminal) {
    case MULTOPR:
      return new RepMultOprFactor(this.consumeMultOpr(),
          this.factor(), this.repMultOprFactor());
    case COMMA:
    case RBRACKET:
    case RPAREN:
    case RBRACE:
    case SEMICOLON:
    case BECOMES:
    case BOOLOPR:
    case RELOPR:
    case ADDOPR:
      return new RepMultOprFactorEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.COMMA, Terminal.RBRACKET,
          Terminal.RPAREN, Terminal.RBRACE, Terminal.SEMICOLON,
          Terminal.BECOMES, Terminal.BOOLOPR, Terminal.RELOPR, Terminal.ADDOPR);
    }
  }

  private IConcSyn.IFactor factor() {
    switch (this.terminal) {
    case LITERAL:
      return new FactorLiteral(this.consumeLiteral());
    case LBRACKET:
      return new FactorArrFactor(this.arrFactor());
    case IDENT:
      return new FactorIdent(this.consumeIdent(), this.optFactorIdent());
    case ADDOPR:
    case NOT:
      return new FactorMonadicOpr(this.monadicOpr(), this.factor());
    case LPAREN:
      return new FactorLparen(this.consume(Terminal.LPAREN), this.expr(), this
          .consume(Terminal.RPAREN));
    default:
      throw new GrammarException(this.token, Terminal.LITERAL,
          Terminal.LBRACKET, Terminal.IDENT, Terminal.ADDOPR, Terminal.NOT,
          Terminal.LPAREN);
    }
  }

  private IConcSyn.IArrFactor arrFactor() {
    if (this.terminal == Terminal.LBRACKET)
      return new ArrFactor(this.consume(Terminal.LBRACKET), this.expr(), this
          .consume(Terminal.RBRACKET));

    throw new GrammarException(this.token, Terminal.LBRACKET);
  }

  private IConcSyn.IOptFactorIdent optFactorIdent() {
    switch (this.terminal) {
    case INIT:
      return new OptFactorIdentInit(this.consume(Terminal.INIT), this
          .optArrFactor());
    case LPAREN:
      return new OptFactorIdentExprList(this.exprList());
    case LBRACKET:
      return new OptFactorIdentArrFactor(this.arrFactor());
    case ARRFUN:
      return new OptFactorIdentArrFun(this.consumeArrFun(Terminal.ARRFUN));
    case COMMA:
    case RBRACKET:
    case RPAREN:
    case RBRACE:
    case SEMICOLON:
    case BECOMES:
    case BOOLOPR:
    case RELOPR:
    case ADDOPR:
    case MULTOPR:
      return new OptFactorIdentEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.INIT, Terminal.LPAREN,
          Terminal.LBRACKET, Terminal.ARRFUN, Terminal.COMMA,
          Terminal.RBRACKET, Terminal.RPAREN, Terminal.RBRACE,
          Terminal.SEMICOLON, Terminal.BECOMES, Terminal.BOOLOPR,
          Terminal.RELOPR, Terminal.ADDOPR, Terminal.MULTOPR);
    }
  }

  private IConcSyn.IOptArrFactor optArrFactor() {
    switch (this.terminal) {
    case LBRACKET:
      return new OptArrFactor(this.arrFactor());
    case RBRACKET:
    case RPAREN:
    case BECOMES:
    case BOOLOPR:
    case RELOPR:
    case ADDOPR:
    case MULTOPR:
    case RBRACE:
    case SEMICOLON:
    case COMMA:
      return new OptArrFactorEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.LBRACKET, Terminal.COMMA,
          Terminal.RBRACKET, Terminal.RPAREN, Terminal.RBRACE,
          Terminal.SEMICOLON, Terminal.BECOMES, Terminal.BOOLOPR,
          Terminal.RELOPR, Terminal.ADDOPR, Terminal.MULTOPR);
    }
  }

  private IConcSyn.IExprList exprList() {
    if (this.terminal == Terminal.LPAREN)
      return new ExprList(this.consume(Terminal.LPAREN), this.optExpr(), this
          .consume(Terminal.RPAREN));

    throw new GrammarException(this.token, Terminal.LPAREN);
  }

  private IConcSyn.IOptExpr optExpr() {
    switch (this.terminal) {
    case LPAREN:
    case ADDOPR:
    case NOT:
    case IDENT:
    case LBRACKET:
    case LITERAL:
      return new OptExpr(this.expr(), this.repExpr());
    case RPAREN:
      return new OptExprEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.LPAREN, Terminal.ADDOPR,
          Terminal.NOT, Terminal.IDENT, Terminal.LBRACKET, Terminal.LITERAL,
          Terminal.RPAREN);
    }
  }

  private IConcSyn.IRepExpr repExpr() {
    switch (this.terminal) {
    case COMMA:
      return new RepExpr(this.consume(Terminal.COMMA), this.expr(), this
          .repExpr());
    case RPAREN:
      return new RepExprEpsilon();
    default:
      throw new GrammarException(this.token, Terminal.COMMA, Terminal.RPAREN);
    }
  }

  @SuppressWarnings("unchecked")
  private IConcSyn.IMonadicOpr monadicOpr() {
    switch (this.terminal) {
    case NOT:
      return new MonadicOprNot(this.token);
    case ADDOPR:
      return new MonadicOprAddOpr((Token<AddOpr>) this.token);
    default:
      throw new GrammarException(this.token, Terminal.NOT, Terminal.ADDOPR);
    }
  }

}
