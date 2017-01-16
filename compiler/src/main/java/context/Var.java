package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by tobi on 15.01.17.
 */
public abstract class Var {
    private static List<Var> variables = new ArrayList<>();

    private int relLocation;
    private int absLocation;

    public boolean isConst() {
        return isConst;
    }

    private boolean isConst;

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
        if (!(o instanceof DefaultVar))
            return false;
        DefaultVar variable = (DefaultVar) o;
        return getIdent().equals(variable.getIdent()) && getContext().equals(variable.getContext());
    }

    public Var(Ident ident) {

        this.ident = ident;
    }

    public static void addVariable(Var var) {
        var.setContext(Context.getCurrentContext());
        if (!variables.contains(var)) {
            variables.add(var);
        }
    }

    public static List<Var> getVariables() {
        return variables;
    }

    public static void clearVariables() {
        variables.clear();
    }

    public static Var getCurrentVariable() {
        return currentVariable;
    }

    public static void setCurrentVariable(IToken token) {
        Var var = getVar(token);
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

    private static Var currentVariable;


    public static Var getVar(IToken token) {
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

    public void setConst(boolean isConst) {
        this.isConst = isConst;
    }

    /**
     * Check for forbidden types
     *
     * @param mustContainTypes
     * @return
     */
    public boolean rightSideContainsOnly(Terminal[] mustContainTypes) {
        List<Terminal> copy = getRightSideTokens().stream().map(token -> token.getTerminal()).collect(Collectors.toList());
        copy.removeAll(Arrays.asList(mustContainTypes));
        return copy.size() == 0;
    }

    /**
     * Type on the right side which is calculated
     */
    private List<IToken> rightSideTokens = new ArrayList<>();

    public List<IToken> getRightSideTokens() {
        List<IToken> rightSideTypes = new ArrayList<>(this.rightSideTokens);
        return rightSideTypes;
    }

    public void addRightSideToken(IToken rightSideToken) throws ContextError {
        if(isConst() && this.rightSideTokens.size() > 0) {
            throw new ContextError(String.format("Variable %s is const", toString()));
        }
        this.rightSideTokens.add(rightSideToken);
    }

    public boolean rightSideTypeContains(Terminal terminal) {
        return rightSideTokens.contains(terminal);
    }
}
