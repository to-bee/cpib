package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Context;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IRoutineCall;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class ProcCallCmd implements ICmd {

  private final IRoutineCall  routineCall;
  private final IList<IIdent> globalInitList;

  public ProcCallCmd(final IRoutineCall routineCall,
      final IList<IIdent> identList) {
    this.routineCall = routineCall;
    this.globalInitList = identList;
  }

  @Override
  public void check() throws ContextException {
    final Context globalContext = COMPILER.getGlobalContext();
    final Token<String> ident = this.routineCall.getToken();
    final ProcDecl procedure = globalContext.getProcedure(ident);

    if (procedure == null)
      throw new ContextException("Procedure does not exist.", ident);

    this.routineCall.check();
    this.globalInitList.check();
  }

  @Override
  public Token<?> getToken() {
    return this.routineCall.getToken();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.globalInitList.code(loc);
    loc = this.routineCall.code(loc);
    return loc;
  }

}
