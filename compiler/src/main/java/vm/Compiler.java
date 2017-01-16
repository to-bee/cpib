package vm;

import virtualmachineFS2015.*;

/**
 * Created by Malin on 15.01.2017.
 */
public enum Compiler {
    COMPILER;

    private IVirtualMachine vm = null;

    public IVirtualMachine getVM() {
        return this.vm;
    }

    private ICodeArray cArr = null;

    public void initialize() throws ICodeArray.CodeTooSmallError, IVirtualMachine.ExecutionError {
        this.cArr = new CodeArray(10000);
    }

    public ICodeArray getCodeArray() {
        return cArr;
    }
}