package context;

import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.*;

/**
 * Created by tobi on 07.01.17.
 */
public class TupleVar extends Var {
    /**
     * Type on the left side which is defined
     * For example tuple1:(int
     */
    private final List<IToken> leftSideTokens = new ArrayList<>();

    public TupleVar(Ident ident) throws ContextError {
        super(ident);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TupleVar))
            return false;
        TupleVar variable = (TupleVar) o;
        return getIdent().equals(variable.getIdent()) && getContext().equals(variable.getContext());
    }

    @Override
    public String toString() {
        return String.format("%s:(%s)", this.getIdent().getValue(), this.joinTokens(getLeftSideTokens()));
    }

    public List<IToken> getLeftSideTokens() {
        return leftSideTokens;
    }

    public String joinTokens(List<IToken> tokens) {
        StringBuilder sb = new StringBuilder();

        Iterator<IToken> iterator = tokens.iterator();
        while(iterator.hasNext())
        {
            sb.append(iterator.next().getTerminal().getValue());
            if(iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public void addLeftSideToken(IToken rightSideToken) throws ContextError {
        this.leftSideTokens.add(rightSideToken);
    }

    public void checkAssignmentEquality() throws ContextError {
        //TODO maybe move
        if (isConst() == false){
            throw new ContextError(String.format("Tuple: %s must be const", toString()));
        }
        if(leftSideTokens.size() > getRightSideTokens().size()) {
            throw new ContextError(String.format("Tuple: %s cannot be assigned with: %s", toString(), joinTokens(getRightSideTokens())));
        } else {
            Iterator<IToken> rightSideIterator = getRightSideTokens().iterator();
            IToken rightSideToken;
            for(IToken leftSideToken : leftSideTokens) {
                rightSideToken = rightSideIterator.next();
                checkRightSideTypeMatch(leftSideToken.getTerminal(), rightSideToken);
            }
        }
    }

    public void addRightSideToken(IToken rightSideToken) throws ContextError {
        //TODO unterschied bereits initialisiert vs. zu viele parameter rechts
        //case1 c:(bool,int32)  c:(true,2,2)                Tuple %s cannot take this many parameters
        //case2 c:(bool,int32)  c:(true,2); c:(false,1)     Tuple %s is constant and can only be initialized once

        if(isConst() && this.getRightSideTokens().size() >= this.leftSideTokens.size()) {
            throw new ContextError(String.format("Tuple %s is constant and can only be initialized once", toString()));
        }
        super.addRightSideToken(rightSideToken);
    }
}
