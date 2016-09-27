package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import ch.fhnw.cpib.iml.compiler.Compiler;
import ch.fhnw.cpib.iml.lib.enums.AddOpr;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Terminal;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class MonadicExpr implements IExpr {

  private final Token<?> operator;
  private final IExpr    expr;

  public MonadicExpr(final Token<?> operator, final IExpr expr) {
    this.operator = operator;
    this.expr = expr;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
  }

  @Override
  public Token<?> getToken() {
    return this.operator;
  }

  Type type = null;

  @Override
  public Type getType() {
    if (this.operator.getTerminal() == Terminal.NOT) this.type = Type.BOOL;
    else this.type = Type.INT32; // ADDOPR
    return this.type;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = Compiler.COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());
    loc = this.expr.code(loc);
    final Terminal terminal = this.operator.getTerminal();
    if (terminal == Terminal.NOT) {
      vm.IntLoad(loc++, 0);
      vm.IntEQ(loc++);
      return loc;
    } else if (terminal == Terminal.ADDOPR) {
      final Object value = this.operator.getAttribute().getValue();
      if (value == AddOpr.MINUS) {
        vm.IntInv(loc++);
        return loc;
      } else if (value == AddOpr.PLUS) {
        // Does nothing...
        return loc;
      }
    }
    throw new RuntimeException("MonadicExpr: Unknown operator.");
  }
}
