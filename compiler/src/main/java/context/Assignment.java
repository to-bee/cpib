package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.List;

import static scanner.datatypes.Terminal.COMPL;

/**
 * Created by tobi on 16.01.17.
 */
public class Assignment {
    private Assignment parent;
    private List<Object> components = new ArrayList<>();

    public List<Object> getComponents() {
        return components;
    }



//    private final Var var;

    public Assignment() {
//        this.var = var;
    }

    public Assignment(Assignment parent) {
        this.parent = parent;
    }

    public void addComponent(Object comp) {
        this.components.add(comp);
    }


//    public void checkRightSideTypeMatch(Terminal leftSideType, IToken rightSideType) throws ContextError {
//        List<Terminal> allowedTypes = new ArrayList<>();
//        switch (leftSideType) {
//            case COMPL:
//                allowedTypes.add(COMPL);
//                allowedTypes.add(Terminal.IMAGINARY_PART);
//                allowedTypes.add(Terminal.INT32);
//                allowedTypes.add(Terminal.LITERAL);
//                break;
//            case INT32:
//                allowedTypes.add(Terminal.LITERAL);
//                allowedTypes.add(Terminal.INT32);
//                break;
//            case BOOL:
//                allowedTypes.add(Terminal.IDENT);
//
//                if (rightSideType instanceof Ident) {
//                    Ident ident = (Ident) rightSideType;
//                    if (!ident.getValue().toLowerCase().equals("true") && !ident.getValue().toLowerCase().equals("false"))
//                        throw new ContextError(String.format("LType and RType mismatch for variable: %s. Cannot assign: %s to %s", toString(), leftSideType.toString(), rightSideType.toString()));
//                }
//                break;
//        }
//
//        if (!allowedTypes.contains(rightSideType.getTerminal())) {
//            throw new ContextError(String.format("LType and RType mismatch for variable: %s. Cannot assign: %s to %s", toString(), leftSideType.toString(), rightSideType.toString()));
//
//        }
//    }

//    protected abstract void checkAssignmentEquality() throws ContextError;

    // Default var
//    public void checkAssignmentEquality() throws ContextError {
//        for (IToken rightSideToken : getRightSideTokens()) {
//            checkRightSideTypeMatch(getLeftSideType(), rightSideToken);
//        }
//    }

    public Assignment getParent() {
        return parent;
    }
}
