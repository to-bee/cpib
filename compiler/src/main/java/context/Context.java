package context;

import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobi on 07.01.17.
 */
public class Context {
    private final Scope scope;

    public Context(Scope scope) {
        this.scope = scope;
    }

    public static Context getCurrentContext() {
        return currentContext;
    }

    public static void setCurrentContext(Context currentContext) {
        Context.currentContext = currentContext;
    }

    private static Context currentContext;

    private static VmVar currentVmVariable;
    public static VmVar getCurrentVmVariable() {
        return currentVmVariable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Context context = (Context) o;

        return scope == context.scope;
    }

    @Override
    public int hashCode() {
        return scope != null ? scope.hashCode() : 0;
    }




    private static Map<Ident, VmVar> vmVariables = new HashMap<>();

    public static Map<Ident, VmVar> getVmVariablesMap() {
        return vmVariables;
    }

    public static VmVar setCurrentVmVariable(IToken token) {
        VmVar vmVar = getVar(token);
        if (vmVar != null) {
            currentVmVariable = vmVar;
        }
        return vmVar;

    }

    public static Collection<VmVar> getVmVariables() {
        return vmVariables.values();
    }

    public static VmVar addVmVariable(Ident ident) throws ContextError {
        if (vmVariables.containsKey(ident)) {
            throw new ContextError(String.format("Cannot define multiple variables: %s", vmVariables));
        }

        VmVar vmVar = new VmVar(ident);
        vmVariables.put(ident, vmVar);
        return vmVar;
    }

    public static VmVar getVar(IToken token) {
        if (!(token instanceof Ident)) {
            return null;
        }
        Ident ident = (Ident) token;
        if (vmVariables.containsKey(ident)) {
            return vmVariables.get(ident);
        } else {
            return null;
        }
    }

    public static void clearVmVariables() {
        vmVariables.clear();
    }
}
