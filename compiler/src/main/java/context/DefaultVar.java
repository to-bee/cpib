package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tobi on 07.01.17.
 */
public class DefaultVar extends Var {
    /**
     * Type on the left side which is defined
     * For example
     * tuple1:(int32,bool)
     * compl1:Compl
     */
    private final Terminal leftSideType;
    private Set<DefaultVar> exprVariables = new HashSet<>();
    private IToken opr;

    public DefaultVar(Ident ident, IToken leftSideType) {
        super(ident);
        this.leftSideType = leftSideType.getTerminal();
    }

    /*
     * Tuple Type Checker
     */
    public DefaultVar(Ident ident) {
        super(ident);
        this.leftSideType = null;
    }

    @Override
    public void checkAssignmentEquality() throws ContextError {
        for(IToken rightSideToken : getRightSideTokens()) {
            checkRightSideTypeMatch(getLeftSideType(), rightSideToken);
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.getIdent().getValue(), this.getLeftSideType().getValue());
    }

    public Terminal getLeftSideType() {
        return leftSideType;
    }

    public void addExprVariable(IToken token) throws ContextError {
        DefaultVar var = (DefaultVar) getVar(token);
        if (var != null) {
            exprVariables.add(var);
        }
    }

    public IToken getOpr() {
        return opr;
    }

    public void setExprOpr(IToken relOpr) {
        this.opr = relOpr;
    }

    public List<DefaultVar> getExprVariables() {
        List<DefaultVar> exprVariables = new ArrayList<DefaultVar>(this.exprVariables);
        return exprVariables;
    }

    public void addRightSideToken(IToken rightSideToken) throws ContextError {
        if(isConst() && this.getRightSideTokens().size() > 0) {
            throw new ContextError(String.format("Variable %s is const", toString()));
        }
        super.addRightSideToken(rightSideToken);
    }

    public boolean exprVariableContains(Terminal terminal) {
        return this.exprVariables.stream().filter(v -> v.leftSideType == terminal).count() > 0;
    }

}
