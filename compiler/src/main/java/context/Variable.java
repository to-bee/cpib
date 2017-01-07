package context;

import absSyn.ExpressionAbsSyn;
import absSyn.IAbsSyn;
import absSyn.TypeDeclarationAbsSyn;
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
    private final Ident ident;
    private Context context;
    /**
     * Whether the value of variable contains imaginary terminal
     */
    private boolean imaginary;

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
