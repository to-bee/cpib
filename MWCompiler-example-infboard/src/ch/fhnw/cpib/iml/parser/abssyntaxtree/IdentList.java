package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.concsyntaxtree.GlobInitList;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

/**
 * A list of identifiers (names of variables).
 * This is really a {@link GlobInitList global INIT list}.
 */
public class IdentList implements IList<IIdent> {

  private final IIdent        ident;
  private final IList<IIdent> identList;

  public IdentList(final IIdent ident, final IList<IIdent> identList) {
    this.ident = ident;
    this.identList = identList;
  }

  @Override
  public void check() throws ContextException {
    if (COMPILER.getCurrentContext() != COMPILER.getGlobalContext())
      throw new ContextException("Global inits only allowed in global context",
          this.ident.getToken());

    this.ident.check();
    this.identList.check();
  }

  @Override
  public Token<String> getToken() {
    return this.ident.getToken();
  }

  @Override
  public IIdent getItem() {
    return this.ident;
  }

  @Override
  public IList<IIdent> next() {
    return this.identList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.ident.code(loc);
    loc = this.identList.code(loc);
    return loc;
  }

  @Override
  public String toString() {
    return ListUtils.toString(this);
  }
}
