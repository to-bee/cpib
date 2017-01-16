package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static scanner.datatypes.Terminal.COMPL;

/**
 * Created by tobi on 15.01.17.
 */
public abstract class Var {
    private static List<Var> variables = new ArrayList<>();
    private static Var currentVariable;
    private final Ident ident;
    private int relLocation;
    private int absLocation;
    private boolean isConst;
    private Context context;
    /**
     * Type on the right side which is calculated
     */
    private List<IToken> rightSideTokens = new ArrayList<>();

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

    public boolean isConst() {
        return isConst;
    }

    public void setConst(boolean isConst) {
        this.isConst = isConst;
    }

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

    public Ident getIdent() {
        return ident;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<IToken> getRightSideTokens() {
        return new ArrayList<>(this.rightSideTokens);
    }

    public void addRightSideToken(IToken rightSideToken) throws ContextError {
        this.rightSideTokens.add(rightSideToken);
    }

    public boolean rightSideTypeContains(Terminal terminal) {
        return rightSideTokens.contains(terminal);
    }

    public void checkRightSideTypeMatch(Terminal leftSideType, IToken rightSideType) throws ContextError {
        List<Terminal> allowedTypes = new ArrayList<>();
        switch (leftSideType) {
            case COMPL:
                allowedTypes.add(COMPL);
                allowedTypes.add(Terminal.IMAGINARY_PART);
                allowedTypes.add(Terminal.INT32);
                allowedTypes.add(Terminal.LITERAL);
                break;
            case INT32:
                allowedTypes.add(Terminal.LITERAL);
                allowedTypes.add(Terminal.INT32);
                break;
            case BOOL:
                allowedTypes.add(Terminal.IDENT);

                if (rightSideType instanceof Ident) {
                    Ident ident = (Ident) rightSideType;
                    if (!ident.getValue().toLowerCase().equals("true") && !ident.getValue().toLowerCase().equals("false"))
                        throw new ContextError(String.format("LType and RType mismatch for variable: %s. Cannot assign: %s to %s", toString(), leftSideType.toString(), rightSideType.toString()));
                }
                break;
        }

        if (!allowedTypes.contains(rightSideType.getTerminal())) {
            throw new ContextError(String.format("LType and RType mismatch for variable: %s. Cannot assign: %s to %s", toString(), leftSideType.toString(), rightSideType.toString()));

        }
    }

    protected abstract void checkAssignmentEquality() throws ContextError;
}
