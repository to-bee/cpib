package vm;

import virtualmachineFS2015.*;

/**
 * Created by Malin on 15.01.2017.
 */
public enum Compiler {
    COMPILER;

    private IVirtualMachine vm = null;
    private ICodeArray cArr = null;

    public void initializeVm(int codeArraySize, int storeSize) throws IVirtualMachine.ExecutionError {
        this.cArr = new CodeArray(codeArraySize);
        vm = new VirtualMachine(this.cArr, storeSize);
    }


    public IVirtualMachine getVM() {
        return this.vm;
    }

    public ICodeArray getCodeArray() {
        return cArr;
    }

}