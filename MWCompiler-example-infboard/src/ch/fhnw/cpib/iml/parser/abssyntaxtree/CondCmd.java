package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class CondCmd implements ICmd {

  private final IExpr expr;
  private final ICmd  thenCmd;
  private final ICmd  elseCmd;

  public CondCmd(final IExpr expr, final ICmd thenCmd, final ICmd elseCmd) {
    this.expr = expr;
    this.thenCmd = thenCmd;
    this.elseCmd = elseCmd;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
    this.thenCmd.check();
    this.elseCmd.check();
  }

  @Override
  public Token<?> getToken() {
    return this.expr.getToken();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.expr.code(loc);
    final int locJump1 = loc++;
    vm.DebugInfo(loc++, "TRUE -> THEN", this.thenCmd.getToken());
    loc = this.thenCmd.code(loc);
    final int locJump2 = loc++;

    final int locElse = loc;
    vm.DebugInfo(loc++, "FALSE -> ELSE", this.elseCmd.getToken());
    loc = this.elseCmd.code(loc);

    vm.CondJump(locJump1, locElse);
    vm.UncondJump(locJump2, loc);

    return loc;
  }
}
