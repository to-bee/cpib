package ch.fhnw.cpib.iml.compiler;

import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IProgram;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;
import ch.fhnw.cpib.iml.vm.VirtualMachine;

/*
 * TODO:
 *
 * - Methodenaufrufe:
 * 1. Schritt: symbolisch
 * Bei Methodenaufrufe mit Namen als Calls in Code abfüllen.
 * 2. Schritt: numerisch
 * Namen durch absolute Adressen ersezten.
 *
 * - Stop
 * Stop am Ende vom Programm-Code (vor den Subroutinen).
 *
 * - int IExpr.code(int loc) :
 * `- loc: Wo beginnt erzeugter Code?
 * `- return value: nächste freie Adresse.
 * `- if(loc >= code.length) -> throws CodeTooSmallException
 * `- int loc1 = e1.code(loc); int loc2 = e2.code(loc1);
 * `- Fallunterscheidung bei COR und CAND: e2 ist conditional!
 * `- vm.intAdd(loc2); // Zwei Werte sind auch Stack.
 * `- return loc2+1;
 *
 * - Framepointer:
 * . Da werden die organisational Infos ausgelesen um zurückzuspringen.
 *
 * - Stack:
 * . Normale lokale Variablen.
 * . Werte von Referenzen.
 * . pc_old
 * . (extreme pointer)
 * . fp_old
 * . bei OUT, INOUT und REF werden Adressen abgelegt.
 * . Lokale variablen sind über dem Rückgabewert.
 * . Rückgabewert einer Funktion immer als erstes (unterstes) Element.
 * . Zuunterst: alle globalen Veriablen (beginnt bei 0).
 */

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

  public Compilation compile(final IProgram program)
 throws CodeTooSmallError,
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
