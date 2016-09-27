package ch.fhnw.cpib.iml.parser.abssyntaxtree;

import static ch.fhnw.cpib.iml.compiler.Compiler.COMPILER;
import ch.fhnw.cpib.iml.compiler.Variable;
import ch.fhnw.cpib.iml.lib.enums.Type;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IExpr;
import ch.fhnw.cpib.iml.parser.abssyntaxtree.IAbsSyn.IIdent;
import ch.fhnw.cpib.iml.parser.exception.ContextException;
import ch.fhnw.cpib.iml.scanner.Token;
import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.CodeTooSmallError;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.HeapTooSmallError;

public class Ident implements IIdent {

  private final Token<String> ident;
  private final IExpr         arrSizeExpr;

  public Ident(final Token<String> ident, final IExpr arrSizeExpr) {
    this.ident = ident;
    this.arrSizeExpr = arrSizeExpr;
  }

  private Type type;

  @Override
  public void check() throws ContextException {
    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);

    if (var == null)
      throw new ContextException("Variable " + this.ident.getAttribute()
          + " not found", this.ident);

    if (var.isInitialized())
      throw new ContextException("Variable " + this.ident.getAttribute()
          + " already initialized", this.ident);

    this.type = var.getType();

    if (!(this.arrSizeExpr instanceof Empty)) {
      if (this.type != Type.STRING)
        throw new ContextException(
            "Expression to init a String only allowed on variables of type string",
            this.ident);

      this.arrSizeExpr.check();
      if (this.arrSizeExpr.getType() != Type.INT32)
        throw new ContextException(
            "Expression to init a String has to be of type int32", this.ident);
    }

    var.setInitialized(true);
  }

  @Override
  public Token<String> getToken() {
    return this.ident;
  }

  @Override
  public int code(final int location) throws CodeTooSmallError, HeapTooSmallError {
    final IVirtualMachine vm = COMPILER.getVM();
    int loc = location;
    final Variable var = COMPILER.getCurrentContext().getVariable(this.ident);

    if (var.getType() == Type.STRING) {
      if (!(this.arrSizeExpr instanceof Empty)) {
        loc = this.arrSizeExpr.code(loc);
      } else {
        vm.IntLoad(loc++, 255);
      }

      vm.IntArrayInitHeap(loc++);
    } else {
      vm.IntLoad(loc++, 0);
    }

    // Load address of Variable onto the stack
    vm.LoadRel(loc++, var.getRelLocation());
    vm.Store(loc++);

    return loc;
  }

  @Override
  public Type getType() {
    return this.type;
  }

}
