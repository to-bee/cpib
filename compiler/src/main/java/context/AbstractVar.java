package context;

import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by tobi on 15.01.17.
 */
public abstract class AbstractVar {
    private static List<AbstractVar> variables = new ArrayList<>();

    private int relLocation;
    private int absLocation;

    public int getRelLocation() {
        return relLocation;
    }

    public void setRelLocation(int relLocation) {
        this.relLocation = relLocation;
    }

    public int getAbsLocation() {
        return absLocation;
    }

    public void setAbsLocation(int absLocation) {
        this.absLocation = absLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DefaultVariable))
            return false;
        DefaultVariable variable = (DefaultVariable) o;
        return getIdent().equals(variable.getIdent()) && getContext().equals(variable.getContext());
    }

    public AbstractVar(Ident ident) {

        this.ident = ident;
    }

    public static void addVariable(AbstractVar var) {
        var.setContext(Context.getCurrentContext());
        if (!variables.contains(var)) {
            variables.add(var);
        }
    }

    public static List<AbstractVar> getVariables() {
        return variables;
    }

    public static void clearVariables() {
        variables.clear();
    }

    public static AbstractVar getCurrentVariable() {
        return currentVariable;
    }

    public static void setCurrentVariable(IToken token) {
        AbstractVar var = getVar(token);
        if (var != null) {
            currentVariable = var;
        }
    }

    private final Ident ident;

    public Ident getIdent() {
        return ident;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private static AbstractVar currentVariable;


    public static AbstractVar getVar(IToken token) {
        if (!(token instanceof Ident)) {
            return null;
        }
        Ident _ident = (Ident) token;
        try {
            return variables.stream().filter(v -> v.getContext().equals(Context.getCurrentContext()) && v.getIdent().getValue().equals(_ident.getValue())).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
