package context;

import scanner.datatypes.Terminal;
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

    public TupleVar(Ident ident) {
        super(ident);
        // Tuple is always const
        setConst(true);
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
        return String.format("%s:(%s)", this.getIdent().getValue(), this.joinLeftSideTokens());
    }

    public List<IToken> getLeftSideTokens() {
        return leftSideTokens;
    }

    private String joinLeftSideTokens() {
        StringBuilder sb = new StringBuilder();

        Iterator<IToken> iterator = this.getLeftSideTokens().iterator();
        while(iterator.hasNext())
        {
            sb.append(iterator.next().getTerminal());
            if(iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
