package context;

import scanner.token.Ident;

/**
 * Created by tobi on 07.01.17.
 */
public class Context {
    private final Scope scope;
//    private final Ident ident;

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
}
