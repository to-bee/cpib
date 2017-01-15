package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.*;

/**
 * Created by tobi on 07.01.17.
 */
public class Variable {
    private static Set<Variable> variables = new HashSet<>();
    private static Variable currentVariable;
    private Set<Variable> exprVariables = new HashSet<>();
    private IToken opr;
    private final Ident ident;

    private int relLocation;

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

    private int absLocation;
    /**
     * Type on the left side which is defined
     */
    private final Terminal leftSideType;
    /**
     * Type on the right side which is calculated
     */
    private Set<Terminal> rightSideTypes = new HashSet<>();
    private Context context;

    public Variable(Ident ident, IToken leftSideType) {
        this.ident = ident;
        this.leftSideType = leftSideType.getTerminal();
    }

    /*
     * Tuple Type Checker
     */
    public Variable(Ident ident) {
        //TODO
        this.ident = ident;
        this.leftSideType = null;
    }

    public static Variable getCurrentVariable() {
        return currentVariable;
    }

    public static void setCurrentVariable(IToken token) {
        Variable var = getVar(token);
        if (var != null) {
            currentVariable = var;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Variable))
            return false;
        Variable variable = (Variable) o;
        return ident.equals(variable.ident) && context.equals(variable.context);
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.ident.getValue(), this.getLeftSideType().getValue());
    }

    public static Variable getVar(IToken token) {
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

    public Context getContext() {
        return context;
    }

    public Ident getIdent() {
        return ident;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addExprVariable(IToken token) throws ContextError {
        Variable var = getVar(token);
        if (var != null) {
            exprVariables.add(var);
        }
    }

    public IToken getOpr() {
        return opr;
    }

    public static void addVariable(Variable var) {
        if(!variables.contains(var)) {
            var.setContext(Context.getCurrentContext());
            variables.add(var);
        }
    }

    public static Set<Variable> getVariables() {
        return variables;
    }

    public void setExprOpr(IToken relOpr) {
        this.opr = relOpr;
    }

    public void resetExpr() {
        exprVariables.clear();
        opr = null;
    }

    public List<Variable> getExprVariables() {
        List<Variable> exprVariables = new ArrayList<Variable>(this.exprVariables);
        return exprVariables;
    }

    public boolean exprVariableContains(Terminal terminal) {
        return this.exprVariables.stream().filter(v -> v.leftSideType == terminal).count() > 0;
    }

    public Terminal getLeftSideType() {
        return leftSideType;
    }

    public List<Terminal> getRightSideTypes() {
        List<Terminal> rightSideTypes = new ArrayList<Terminal>(this.rightSideTypes);
        return rightSideTypes;
    }

    public boolean rightSideTypeContains(Terminal terminal) {
        return rightSideTypes.contains(terminal);
    }

    /**
     * Check for forbidden types
     * @param mustContainTypes
     * @return
     */
    public boolean rightSideContainsOnly(Terminal[] mustContainTypes) {
        List<Terminal> copy = getRightSideTypes();
        copy.removeAll(Arrays.asList(mustContainTypes));
        return copy.size() == 0;
    }



    public void addRightSideType(Terminal rightSideType) {
        this.rightSideTypes.add(rightSideType);
    }

    public static void clearVariables() {
        variables.clear();
    }
}
