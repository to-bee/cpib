package context;

import scanner.datatypes.Terminal;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.*;

/**
 * Created by tobi on 07.01.17.
 */
public class TupleVar extends AbstractVar {
    /**
     * Type on the left side which is defined
     * For example tuple1:(int
     */
    private final List<IToken> leftSideTokens = new ArrayList<>();
    /**
     * Type on the right side which is calculated
     */
    private List<Terminal> rightSideTypes = new ArrayList<>();

    public TupleVar(Ident ident) {
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

    public List<Terminal> getRightSideTypes() {
        List<Terminal> rightSideTypes = new ArrayList<Terminal>(this.rightSideTypes);
        return rightSideTypes;
    }

    public void addRightSideType(Terminal rightSideType) {
        this.rightSideTypes.add(rightSideType);
    }
}
