package context;

import virtualmachineFS2015.*;

/**
 * Created by Malin on 15.01.2017.
 */
public enum Compiler {
    COMPILER;

    private IVirtualMachine vm = null;
    private ICodeArray Carr = null;

    public void initializeVm() throws IVirtualMachine.ExecutionError {

        this.Carr = new CodeArray(10000);
        vm = new VirtualMachine(null, 10000);
    }


    public IVirtualMachine getVM() {
        return this.vm;
    }

}