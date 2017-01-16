package context;

import scanner.datatypes.Terminal;
import scanner.datatypes.TerminalType;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;
import scanner.token.Literal;

import java.util.ArrayList;
import java.util.List;

import static scanner.datatypes.Terminal.COMPL;
import static scanner.datatypes.Terminal.INT32;

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

    public Terminal getRValueType() throws ContextError {
        List<Terminal> types = new ArrayList<>();
        for (Object obj : getComponents()) {
            if (obj instanceof IToken) {
                IToken token = (IToken) obj;
                if(token.getTerminal().getType() == TerminalType.RELOPR
                        || token.getTerminal().getType() == TerminalType.BOOLOPR
                        || token.getTerminal().getType() == TerminalType.UNARYOPR) {
                    types.add(Terminal.BOOL);
                }
                else if(token.getTerminal().getType() == TerminalType.ARITHMOPR) {
                    types.add(Terminal.INT32);
                }
                else if(token.getTerminal() == Terminal.IMAGINARY_PART) {
                    types.add(Terminal.COMPL);
                }
                else if (token instanceof Ident) {
                    Ident ident = (Ident) token;
                    if (ident.getValue().toLowerCase().equals("true") || ident.getValue().toLowerCase().equals("false")) {
                        types.add(Terminal.BOOL);
                    }
                }
                else if (token instanceof Literal) {
                    Literal literal = (Literal) token;
                    if (literal.getValue() % 1 == 0) {
                        types.add(Terminal.INT32);
                    } else {
                        throw new ContextError(String.format("Floating points not allowed (%s)", literal));
                    }
                }
                else if (token.getTerminal() == Terminal.SEMICOLON) {
                    // ignore
                }
                else {
                    throw new ContextError("Unknown type");
                }

            } else if (obj instanceof VmVar) {
                VmVar vmVar = (VmVar) obj;
                if (vmVar.isTuple()) {
                    throw new ContextError(String.format("Cannot assign tuple %s to variable", vmVar.toString()));
                } else {
                    types.add(vmVar.getTypes().get(0).getTerminal());
                }
            } else if (obj instanceof Assignment) {
                Assignment assi = (Assignment) obj;
                types.add(assi.getRValueType());
            } else {
                throw new ContextError("Unknown type");
            }
        }

        return VmVar.getBestMatchingType(types);
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
