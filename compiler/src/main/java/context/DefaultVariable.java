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
public class DefaultVariable extends AbstractVar {
    /**
     * Type on the left side which is defined
     * For example
     * tuple1:(int32,bool)
     * compl1:Compl
     */
    private final Terminal leftSideType;
    private Set<DefaultVariable> exprVariables = new HashSet<>();
    private IToken opr;

    /**
     * Type on the right side which is calculated
     */
    private Set<IToken> rightSideTokens = new HashSet<>();

    public DefaultVariable(Ident ident, IToken leftSideType) {
        super(ident);
        this.leftSideType = leftSideType.getTerminal();
    }

    /*
     * Tuple Type Checker
     */
    public DefaultVariable(Ident ident) {
        super(ident);
        this.leftSideType = null;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.getIdent().getValue(), this.getLeftSideType().getValue());
    }

    public Terminal getLeftSideType() {
        return leftSideType;
    }

    public void addExprVariable(IToken token) throws ContextError {
        DefaultVariable var = (DefaultVariable) getVar(token);
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

    public List<DefaultVariable> getExprVariables() {
        List<DefaultVariable> exprVariables = new ArrayList<DefaultVariable>(this.exprVariables);
        return exprVariables;
    }

    public boolean exprVariableContains(Terminal terminal) {
        return this.exprVariables.stream().filter(v -> v.leftSideType == terminal).count() > 0;
    }

    public boolean rightSideTypeContains(Terminal terminal) {
        return rightSideTokens.contains(terminal);
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

    public List<IToken> getRightSideTokens() {
        List<IToken> rightSideTypes = new ArrayList<>(this.rightSideTokens);
        return rightSideTypes;
    }

    public void addRightSideToken(IToken rightSideToken) {
        this.rightSideTokens.add(rightSideToken);
    }
}
