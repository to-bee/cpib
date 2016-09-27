package ch.fhnw.cpib.iml.lib;

import ch.fhnw.cpib.iml.scanner.Symbol;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.attr.AttrBoolOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrBoolVal;
import ch.fhnw.cpib.iml.scanner.attr.AttrChangeMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrFlowMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrMechMode;
import ch.fhnw.cpib.iml.scanner.attr.AttrMultOpr;
import ch.fhnw.cpib.iml.scanner.attr.AttrType;
import ch.fhnw.cpib.iml.scanner.state.ScannerState;

/**
 * A complete list of all reserved keywords of IML that consist of alphabetic
 * characters only.
 * Operators consisting of a single non-alphabetic character can be found in
 * {@link Symbol}.
 * Others are handled by the {@link ScannerState states} of the state machine.
 */
public enum Keyword {
  /** Token: ({@linkplain Terminal#TYPE TYPE}, {@linkplain AttrType BOOL}) */
  BOOL,
  /** Token: ({@linkplain Terminal#TYPE TYPE}, {@linkplain AttrType STRING}) */
  STRING,
  /** Token: {@linkplain Terminal#CALL CALL} */
  CALL,
  /**
   * Token: ({@linkplain Terminal#BOOLOPR BOOLOPR, {@linkplain AttrBoolOpr
   * CAND})
   */
  CAND,
  /**
   * Token:
   * ({@linkplain Terminal#CHANGEMODE CHANGEMODE}, {@linkplain AttrChangeMode
   * CONST})
   */
  CONST,
  /**
   * Token:
   * ({@linkplain Terminal#MECHMODE MECHMODE}, {@linkplain AttrMechMode COPY})
   */
  COPY,
  /**
   * Token:
   * ({@linkplain Terminal#BOOLOPR BOOLOPR}, {@linkplain AttrBoolOpr COR})
   */
  COR,
  /**
   * Token:
   * ({@linkplain Terminal#MULTOPR MULTOPR}, {@linkplain AttrMultOpr DIV})
   */
  DIV,
  /** Token: {@linkplain Terminal#ELSE ELSE} */
  ELSE,
  /**
   * Token:
   * ({@linkplain Terminal#LITERAL LITERAL}, {@linkplain AttrBoolVal BoolVal
   * false})
   */
  FALSE,
  /** Token: {@linkplain Terminal#FUN FUN} */
  FUN,
  /** Token: {@linkplain Terminal#GLOBAL GLOBAL} */
  GLOBAL,
  /** Token: {@linkplain Terminal#IF IF} */
  IF,
  /**
   * Token: ({@linkplain Terminal#FLOWMODE FLOWMODE}, {@linkplain AttrFlowMode
   * IN})
   */
  IN,
  /** Token: {@linkplain Terminal#INIT INIT} */
  INIT,
  /**
   * Token: ({@linkplain Terminal#FLOWMODE FLOWMODE}, {@linkplain AttrFlowMode
   * INOUT})
   */
  INOUT,
  /** Token: ({@linkplain Terminal#TYPE TYPE}, {@linkplain AttrType INT}) */
  INT32,
  /** Token: {@linkplain Terminal#LOCAL LOCAL} */
  LOCAL,
  /**
   * Token: ({@linkplain Terminal#MULTOPR MULTOPR}, {@linkplain AttrMultOpr MOD}
   * )
   */
  MOD,
  /** Token: {@linkplain Terminal#NOT NOT} */
  NOT,
  /**
   * Token: ({@linkplain Terminal#FLOWMODE FLOWMODE}, {@linkplain AttrFlowMode
   * OUT})
   */
  OUT,
  /** Token: {@linkplain Terminal#PROC PROC} */
  PROC,
  /** Token: {@linkplain Terminal#PROGRAM PROGRAM} */
  PROGRAM,
  /**
   * Token: ({@linkplain Terminal#MECHMODE MECHMODE}, {@linkplain AttrMechMode
   * REF})
   */
  REF,
  /** Token: {@linkplain Terminal#RETURNS RETURNS} */
  RETURNS,
  /** Token: {@linkplain Terminal#SKIP SKIP } */
  SKIP,
  /**
   * Token:
   * ({@linkplain Terminal#LITERAL LITERAL}, {@linkplain AttrBoolVal BoolVal
   * TRUE})
   */
  TRUE,
  /**
   * Token:
   * ({@linkplain Terminal#CHANGEMODE CHANGEMODE}, {@linkplain AttrChangeMode
   * VAR})
   */
  VAR,
  /** Token: {@linkplain Terminal#WHILE WHILE} */
  WHILE,
  /**
   * Token: ({@linkplain Terminal#ARRFUN ARRFUN, {@linkplain AttrArrFun
   * STRLEN}
   */
  STRLEN,
  /**
   * Token: ({@linkplain Terminal#ARRFUN ARRFUN, {@linkplain AttrArrFun
   * MAXLEN}
   */
  MAXLEN;

  /**
   * Determines whether a lexem is or is not a keyword.
   *
   * @param lexem
   *          the lexem to be tested.
   * @return <code>true</code> if it is reserved.
   */
  public static boolean isKeyword(final String lexem) {
    if (lexem == null) return false;
    try {
      return valueOf(lexem.toUpperCase()) != null;
    } catch (final Throwable t) {
      return false;
    }
  }
}
