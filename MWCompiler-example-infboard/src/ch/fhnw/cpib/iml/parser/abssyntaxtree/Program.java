package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IProgram;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class Program implements IProgram {

  private final Token<String> ident;
  private final IList<IDecl>  globCpsDecl;
  private final ICmd                      blockCmd;

  public Program(final Token<String> ident,
 final IList<IDecl> globCpsDecl,
      final ICmd blockCmd) {
    this.ident = ident;
    this.globCpsDecl = globCpsDecl;
    this.blockCmd = blockCmd;
  }

  @Override
  public void check() throws ContextException {
    this.globCpsDecl.check();
    this.blockCmd.check();
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.globCpsDecl.code(loc);
    loc = this.blockCmd.code(loc);
    vm.Stop(loc++);
    return loc;
  }

}
