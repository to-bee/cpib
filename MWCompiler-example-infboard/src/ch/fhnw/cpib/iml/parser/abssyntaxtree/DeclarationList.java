package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class DeclarationList implements IList<IDecl> {

  private final IDecl        decl;
  private final IList<IDecl> declarationList;

  public DeclarationList(final IDecl decl, final IList<IDecl> declarationList) {
    this.decl = decl;
    this.declarationList = declarationList;
  }

  @Override
  public void check() throws ContextException {
    this.decl.check();
    this.declarationList.check();
  }

  @Override
  public Token<?> getToken() {
    return this.decl.getToken();
  }

  @Override
  public IDecl getItem() {
    return this.decl;
  }

  @Override
  public IList<IDecl> next() {
    return this.declarationList;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    vm.DebugInfo(location, this.getClass().getSimpleName(), this.getToken());
    final int loc = this.decl.code(location + 1);
    return this.declarationList.code(loc);
  }

  @Override
  public String toString() {
    return ListUtils.toString(this);
  }
}
