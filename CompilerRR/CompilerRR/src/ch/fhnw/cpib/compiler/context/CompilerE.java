package ch.fhnw.cpib.compiler.context;

import ch.fhnw.cpib.compiler.vm.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.compiler.ast.interfaces.IAbsSyn.IProgram;
import ch.fhnw.cpib.compiler.vm.CodeArray;
import ch.fhnw.cpib.compiler.vm.ICodeArray;
import ch.fhnw.cpib.compiler.vm.IVirtualMachine;
import ch.fhnw.cpib.compiler.vm.IVirtualMachine.ExecutionError;
import ch.fhnw.cpib.compiler.vm.VirtualMachine;

// From Infboard
public enum CompilerE {
  COMPILER;

  /**
   * Global Context. Following the parent-path of any other context will lead to
   * this one. The contexts are built like a one-headed linked list, and this is
   * its head.
   */
  private Context         global  = null;
  /** Current context. */
  private Context         context = null;

  private ICodeArray Carr 		  = null;

  public void compile(final IProgram program) throws CodeTooSmallError, ExecutionError {
    this.global = new Context();
    this.context = this.global;
    this.Carr = new CodeArray(10000);
    program.check();
    program.code(0);
  }
  
  public ICodeArray getCodeArray() {
	  return Carr;
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

}
