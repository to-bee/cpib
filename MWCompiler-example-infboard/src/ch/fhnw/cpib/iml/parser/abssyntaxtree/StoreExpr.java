package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.ChangeMode;
import ch.fhnw.cpib.iml.lib.enums.FlowMode;
import ch.fhnw.cpib.iml.lib.enums.MechMode;
import ch.fhnw.cpib.iml.lib.enums.Scope;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

/**
 * Acces to store, either write and maybe init or read
 */
public class StoreExpr implements IExpr {

  private final Token<String> ident;
  private final boolean       isInit;
  private final IExpr         arrSizeExpr;

  private Scope               scope;
  private MechMode            mechMode;
  private ChangeMode          changeMode;
  private FlowMode            flowMode;

  /** Used in code generation to distinguish between read and write */
  private boolean             isWrite = false;

  public StoreExpr(final Token<String> ident, final boolean isInit,
      final IExpr arrSizeExpr) {
    this.ident = ident;
    this.isInit = isInit;
    this.arrSizeExpr = arrSizeExpr;
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  private Type type = null;

  @Override
  public void check() throws ContextException {
    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);

    if (var == null)
      throw new ContextException("Variable '" + this.ident.getAttribute()
          + "' does not exist.", this.ident);

    /**
     * If variable has not been initialized check if this is initialization.
     * Only in global context, not in context of Proc- or FunDecl
     */
    if (COMPILER.getCurrentContext().getParent() == null
        || var.getFlowMode() == FlowMode.OUT) {
      if (!var.isInitialized()) {
        if (this.isInit) {
          var.setInitialized(true);
        } else {
          throw new ContextException("Variable has not been initialized yet",
              this.ident);
        }
      } else if (this.isInit) {
        /** If variable already initialized ensure this is no initialization. */
        throw new ContextException(
            "Initialisation on already initialised variable", this.ident);
      }
    }

    this.type = var.getType();
    this.scope = var.getScope();
    this.mechMode = var.getMechMode();
    this.changeMode = var.getChangeMode();
    this.flowMode = var.getFlowMode();

    if (this.isWrite) {
      if (this.changeMode == ChangeMode.CONST && !this.isInit) {
        throw new ContextException("No write access to const allowed",
            this.ident);
      } else if (this.flowMode == FlowMode.IN) { throw new ContextException(
          "No write acces to variable marked as IN allowed", this.ident); }
    }

    if (!(this.arrSizeExpr instanceof Empty)) {
      this.arrSizeExpr.check();
      if (this.arrSizeExpr.getType() != Type.INT32)
        throw new ContextException(
            "Expression for the size of an array has to be of type INT32",
            this.ident);
    }
  }

  @Override
  public Type getType() {
    if (this.hasArrSizeExpr()) return Type.INT32;
    return this.type;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;

    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);
    assert var != null;
    vm.DebugInfo(loc++, this.getClass().getSimpleName() + ": " + var.getName(),
        this.getToken());

    if (this.scope == Scope.GLOBAL) {
      vm.IntLoad(loc++, var.getAbsLocation());
    } else {
      vm.LoadRel(loc++, var.getRelLocation());
    }

    // If MechMode.REF, get the address of the referenced variable
    if (var.getMechMode() == MechMode.REF) vm.Deref(loc++);

    /**
     * If this is a read access, load the variable value for string this is the
     * address of the heap cell, for write access the address of this variable
     * is already on the stack
     */

    if (this.hasArrSizeExpr()) {
      assert this.type == Type.STRING;
      vm.Deref(loc++);
      loc = this.arrSizeExpr.code(loc);
      vm.IntAdd(loc++);
    }

    if (!this.isWrite) {
      vm.Deref(loc++);
    }

    return loc;
  }

  public boolean isInit() {
    return this.isInit;
  }

  public boolean isWrite() {
    return this.isWrite;
  }

  /**
   * Mark this StoreExpr as a left expr to write to
   */
  public void setWrite(final boolean isWrite) {
    this.isWrite = isWrite;
  }

  public boolean hasArrSizeExpr() {
    return !(this.arrSizeExpr instanceof Empty);
  }
}
