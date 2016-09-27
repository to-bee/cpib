package ch.fhnw.cpib.iml.compiler;

import ch.fhnw.cpib.iml.vm.IVirtualMachine;
import ch.fhnw.cpib.iml.vm.IVirtualMachine.ExecutionError;

public class Compilation {
  private final IVirtualMachine vm;

  public Compilation(final IVirtualMachine vm) {
    this.vm = vm;
  }

  public IVirtualMachine getVM() {
    return this.vm;
  }

  public void execute() throws ExecutionError {
    this.vm.execute();
  }
}
