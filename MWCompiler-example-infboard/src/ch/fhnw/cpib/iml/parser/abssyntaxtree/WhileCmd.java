package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class WhileCmd implements ICmd {

  private final IExpr expr;
  private final ICmd  cmd;

  public WhileCmd(final IExpr expr, final ICmd cmd) {
    this.expr = expr;
    this.cmd = cmd;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
    if(this.expr.getType() != Type.BOOL)
      throw new ContextException(
          "The condition of this while is not of type BOOL", this.expr
              .getToken());
    this.cmd.check();
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

    final int loopLoc = loc; // Jumplocation where the cmd to loop starts
    vm.DebugInfo(loc++, "WHILE: condition follows", this.expr.getToken());
    loc = this.expr.code(loc);

    final int middleLoc = loc++; // Jumplocation for first jump over cmd to expr
    vm.DebugInfo(loc++, "WHILE: body follows", this.cmd.getToken());
    loc = this.cmd.code(loc);
    vm.DebugInfo(loc++, "WHILE: body finished", this.cmd.getToken());
    vm.UncondJump(loc++, loopLoc);

    vm.CondJump(middleLoc, loc);
    vm.DebugInfo(loc++, "WHILE: condition was false", this.expr.getToken());

    return loc;
  }
}
