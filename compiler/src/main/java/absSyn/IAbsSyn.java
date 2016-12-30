package absSyn;

import scanner.errors.ContextError;
import virtualmachineFS2015.ICodeArray;

public interface IAbsSyn {
    void check() throws ContextError;

    int code(final int location) throws ICodeArray.CodeTooSmallError;

    String toString(int counter);
}
