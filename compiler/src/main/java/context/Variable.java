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
    private static Variable exprVariableLeft;
    private static Variable exprVariableRight;
    private static IToken opr;
    private final Ident ident;
    private Context context;
    /**
     * Whether the value of variable contains imaginary terminal
     */
    private boolean imaginary;

    public static Variable getExprVariableLeft() {
        return exprVariableLeft;
    }

    public static Variable getExprVariableRight() {
        return exprVariableRight;
    }

    public static IToken getOpr() {
        return opr;
    }

    public Ident getIdent() {
        return ident;
    }

    public Context getContext() {
        return context;
    }

    public Variable(Ident ident, IToken type) {

        this.ident = ident;
        this.type = type.getTerminal();

        //        if(exprL.getType() != Type.IDENT) {
//            throw new ContextError("LType must be Ident");
//        }
//        if(exprR.getType() != Type.LITERAL && exprR.getType() != Type.BOOL) {
//            throw new ContextError("RType must be Literal or Bool");
//        }
    }

    public static void addVariable(Variable var) {
        var.setContext(Context.getCurrentContext());
        variables.add(var);
    }

    public static void setExprVariableLeft(IToken token) throws ContextError {
        Variable var = getVar(token);
        if(var != null) {
            exprVariableLeft = var;
        }
    }

    public static void setExprVariableRight(IToken token) throws ContextError {
        Variable var = getVar(token);
        if(var != null) {
            exprVariableRight = var;
        }
    }

    public static void setExprOpr(IToken relOpr) {
        Variable.opr = relOpr;
    }

    public static void resetExpr() {
        exprVariableLeft = null;
        opr = null;
        exprVariableRight = null;
    }

    public Terminal getType() {
        return type;
    }

    public void setType(Terminal type) {
        this.type = type;
    }

    private Terminal type;

    public void setContext(Context context) {
        this.context = context;
    }

    public static Variable getVar(IToken token) {
        if(!(token instanceof Ident)) {
            return null;
        }
        Ident _ident = (Ident)token;
        try {
            return variables.stream().filter(v -> v.getContext().equals(Context.getCurrentContext()) && v.getIdent().getValue().equals(_ident.getValue())).findFirst().get();
        }
        catch(NoSuchElementException e) {
            return null;
        }
    }

    public void setImaginary(boolean imaginary) {
        this.imaginary = imaginary;
    }

    public boolean isImaginary() {
        return imaginary;
    }
}
