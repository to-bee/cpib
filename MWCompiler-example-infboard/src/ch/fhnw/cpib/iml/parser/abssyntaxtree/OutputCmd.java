package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.ICmd;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.scanner.attr.AttrStrVal;
import ch.fhnw.cpib.iml.scanner.attr.Attribute;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class OutputCmd implements ICmd {

  private final IExpr expr;

  public OutputCmd(final IExpr expr) {
    this.expr = expr;
  }

  @Override
  public void check() throws ContextException {
    this.expr.check();
  }

  @Override
  public Token<?> getToken() {
    return this.expr.getToken();
  }

  @Override
  public int code(final int location) throws CodeTooSmallError,
      HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    vm.DebugInfo(loc++, this.getClass().getSimpleName(), this.getToken());

    loc = this.expr.code(loc);

    final Attribute<?> attribute = this.expr.getToken().getAttribute();
    switch (this.expr.getType()) {
    case BOOL:
      vm.BoolOutput(loc++, attribute.getLexem());
      break;
    case STRING:
      if (attribute instanceof AttrStrVal) vm.StringOutput(loc++, "(literal)");
      else vm.StringOutput(loc++, attribute.getLexem()); // Identifier
      break;
    case INT32:
      vm.IntOutput(loc++, attribute.getLexem());
      break;
    default:
      assert false : "Unknown type!";
    }

    return loc;
  }

}
