package ch.fhnw.cpib.compiler.context;

import ch.fhnw.cpib.compiler.vm.IVirtualMachine;
import ch.fhnw.cpib.compiler.vm.IVirtualMachine.ExecutionError;

public class Compilation {
  private final IVirtualMachine vm;

  public Compilation(final IVirtualMachine vm) {
    this.vm = vm;
  }

  public IVirtualMachine getVM() {
    return this.vm;
  }

  public void execute() throws ExecutionError {
    //this.vm.execute();
  }
}
