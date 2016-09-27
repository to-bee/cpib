package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class CmdList implements IList<ICmd>, ICmd {

  private final ICmd     cmd;
  private final IList<ICmd> cmdList;

  public CmdList(final ICmd cmd, final IList<ICmd> cmdList) {
    this.cmd = cmd;
    this.cmdList = cmdList;
  }

  @Override
  public void check() throws ContextException {
    this.cmd.check();
    this.cmdList.check();
  }

  @Override
  public Token<?> getToken() {
    return this.cmd.getToken();
  }

  @Override
  public ICmd getItem() {
    return this.cmd;
  }

  @Override
  public IList<ICmd> next() {
    return this.cmdList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.cmd.code(loc);
    loc = this.cmdList.code(loc);
    return loc;
  }

  @Override
  public String toString() {
    return ListUtils.toString(this);
  }
}
