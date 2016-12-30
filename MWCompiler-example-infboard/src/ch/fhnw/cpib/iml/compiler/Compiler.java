package ch.fhnw.cpib.iml.compiler;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IProgram;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;
import ch.fhnw.cpib.iml.vm.VirtualMachine;

// From Infboard
public enum Compiler {
  COMPILER;

  /**
   * Global Context. Following the parent-path of any other context will lead to
   * this one. The contexts are built like a one-headed linked list, and this is
   * its head.
   */
  private Context         global  = null;
  /** Current context. */
  private Context         context = null;

  private IVirtualMachine vm      = null;

  public Compilation compile(final IProgram program) throws CodeTooSmallError,
      HeapTooSmallError {
    this.global = new Context();
    this.context = this.global;
    this.vm = new VirtualMachine(10000, 10000);
    final Compilation c = new Compilation(this.vm);
    program.check();
    program.code(0);
    return c;
  }

  /**
   * Global Context. Following the parent-path of any other context will lead to
   * this one.
   */
  public Context getGlobalContext() {
    return this.global;
  }

  /** Current context. */
  public Context getCurrentContext() {
    return this.context;
  }

  public void returnContext() {
    if (this.context.getParent() != null) {
      this.context = this.context.getParent();
    } else {
      throw new RuntimeException("Cannot return to context.");
    }
  }

  public Context switchContext() {
    return this.context = new Context(this.context);
  }

  public void switchToContext(@SuppressWarnings("hiding") final Context context) {
    this.context = context;
  }

  public IVirtualMachine getVM() {
    return this.vm;
  }
}
