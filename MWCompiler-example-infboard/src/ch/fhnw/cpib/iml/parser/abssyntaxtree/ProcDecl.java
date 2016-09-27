package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import static ch.fhnw.cpib.iml.parser.PseudoToken.EXTREME_POINTER;
import static ch.fhnw.cpib.iml.parser.PseudoToken.FRAME_POINTER_OLD;
import static ch.fhnw.cpib.iml.parser.PseudoToken.PROGRAM_COUNTER_OLD;
import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.compiler.Context;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.ListUtils;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IDecl;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IGlobalImport;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IList;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IParameter;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class ProcDecl implements IDecl {

  private final Token<String>        ident;
  private final IList<IParameter>    paramList;
  private final IList<IGlobalImport> globImpList;
  private final IList<IDecl>         localDeclList;
  private final ICmd                 blockCmd;
  private Context                    context = null;
  private int                        location = -1;

  public ProcDecl(final Token<String> ident, final IList<IParameter> paramList,
      final IList<IGlobalImport> globImpList, final IList<IDecl> localDeclList,
      final ICmd blockCmd) {
    this.ident = ident;
    this.paramList = paramList;
    this.globImpList = globImpList;
    this.localDeclList = localDeclList;
    this.blockCmd = blockCmd;
  }

  @Override
  public void check() throws ContextException {
    if (COMPILER.getCurrentContext().getParent() != null)
      throw new ContextException(
          "Procedures can only be declared in global context", this.ident);

    if (COMPILER.getCurrentContext().getProcedure(this.ident) != null)
      throw new ContextException("PROC " + this.ident + " exists already.",
          this.ident);

    COMPILER.getCurrentContext().addProcedure(this);
    assert this.context == null;
    this.context = COMPILER.switchContext();

    /**
     * These Variables are set on vm.Enter to store the old pointers to return
     * to after function call. They are never used in the code, the declaration
     * here serves only the purpose of having the right relativePosition of
     * variables after vm.Enter
     */
    this.context.addVariable(FRAME_POINTER_OLD, Type.INT32, ChangeMode.CONST);
    this.context.addVariable(EXTREME_POINTER, Type.INT32, ChangeMode.CONST);
    this.context.addVariable(PROGRAM_COUNTER_OLD, Type.INT32, ChangeMode.CONST);

    this.paramList.check();
    this.globImpList.check();
    this.localDeclList.check();
    this.blockCmd.check();
    COMPILER.returnContext();
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public Type getType() {
    return null; // Procedure has no return type!
  }

  public IList<IParameter> getParamList() {
    return this.paramList;
  }

  @Override
  public int code(@SuppressWarnings("hiding") final int location)
      throws CodeTooSmallError,
      HeapTooSmallError {
    int loc = location;
    final int jumpLoc = loc++;

    COMPILER.switchToContext(this.context);
    final IVirtualMachine vm = COMPILER.getVM();

    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    this.setLocation(loc);

    vm.Enter(loc++, 0, 0);

    loc = this.paramList.code(loc);
    loc = this.globImpList.code(loc);
    loc = this.localDeclList.code(loc);
    loc = this.blockCmd.code(loc);

    final int size = this.context.getVariableCount() - 3
        - ListUtils.length(this.globImpList);

    vm.Return(loc++, size);
    COMPILER.returnContext();

    // To jump over procDecl since it should not be executed until a Call to it
    vm.UncondJump(jumpLoc, loc);

    return loc;
  }

  /**
   * Location as set by the {@link Compiler}. This is where the code for this
   * function is.
   */
  public int getLocation() {
    return this.location;
  }

  /**
   * Location to be set by the {@link Compiler}. This is where the code for this
   * function is.
   */
  public void setLocation(final int location) {
    assert this.location == -1;
    this.location = location;
  }

  public IList<IGlobalImport> getGlobalImportList() {
    return this.globImpList;
  }

  public Context getContext() {
    return this.context;
  }

}
