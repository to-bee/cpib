package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class InputCmd implements ICmd {

  private final IExpr expr;

  public InputCmd(final IExpr expr) {
    this.expr = expr;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
    if (!(this.expr instanceof StoreExpr))
      throw new ContextException("Cannot store input into given Expression",
          this.expr.getToken());

    ((StoreExpr) this.expr).setWrite(true);
  }

  @Override
  public Token<?> getToken() {
    return this.expr.getToken();
  }

  /**
   * This is the address of a cell on the heap which is used to store an address
   * of a string which is also on the heap.
   */
  private int str = -1;

  /**
   * These cells are used like registers, since the VM does not have them.
   *
   * @throws HeapTooSmallError
   */
  private void initHeapCells() throws HeapTooSmallError {
    if (this.str > -1) return;
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    this.str = vm.IntInitHeapCell();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    int loc = location;
    final IVirtualMachine vm = COMPILER.getVM();
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());

    // String Pointer is now on Stack.

    final Token<?> token = this.expr.getToken();
    switch (this.expr.getType()) {
    case BOOL:
      loc = this.expr.code(loc);
      vm.BoolInput(loc++, token.getAttribute().toString());
      break;
    case STRING:
      // Hack: Create AssiCmd to get Code.
      if (((StoreExpr) this.expr).isInit()) {// INIT:

        // Allocate empty String:
        this.initHeapCells();
        vm.DebugInfo(loc++, "? ident INIT", token);
        vm.IntLoad(loc++, 254); // MAXLEN
        vm.IntLoad(loc++, 255); // Array Size
        // Creates the array on the heap and leaves the address on the stack.
        vm.IntArrayInitHeap(loc++);

        vm.IntLoad(loc++, this.str);
        vm.Store(loc++); // save address
        vm.Alloc(loc++, 1); // string address again
        vm.Store(loc++); // save MAXLEN
        vm.IntLoad(loc++, this.str);
        vm.Deref(loc++);
        loc = this.expr.code(loc);
        vm.Store(loc++);
        vm.Alloc(loc++, 1); // string address again
      } else { // No INIT: String exists already
        loc = this.expr.code(loc);
        vm.Deref(loc++); // actual address of String
      }
      vm.StringInput(loc++, token.getAttribute().getLexem());
      break;

    default:
      loc = this.expr.code(loc);
      vm.IntInput(loc++, token.getAttribute().toString());
      break;
    }

    return loc;
  }
}
