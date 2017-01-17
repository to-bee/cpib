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

    public boolean rValueContains(List<Terminal> searchTypes) throws ContextError {
        List<Terminal> types = new ArrayList<>();
        int i = 0;
        for (Object obj : getComponents()) {
            if (obj instanceof IToken) {
                IToken token = (IToken) obj;

//                if(token.getTerminal() == Terminal.IMAGINARY_PART) {
//                    if(getComponents().get(i-1) instanceof IToken) {
//                        IToken lefToken = (IToken) getComponents().get(i-1);
//                        if(lefToken.getTerminal() != Terminal.ADDOPR
//                                && lefToken.getTerminal() != Terminal.MINOPR) {
//                            throw new ContextError(String.format("%s is not allowed here", Terminal.IMAGINARY_PART));
//                        }
//                    }
//
//                    if(getComponents().get(i+1) instanceof IToken) {
//                        IToken rightToken = (IToken) getComponents().get(i+1);
//                        if(rightToken.getTerminal() != Terminal.MULTOPR
//                                && rightToken.getTerminal() != Terminal.MODOPR) {
//                            throw new ContextError(String.format("%s is not allowed here", Terminal.IMAGINARY_PART));
//                        }
//                    }
//                }

                if(searchTypes.contains(token.getTerminal())) {
                    return true;
                }

            } else if (obj instanceof VmVar) {
                VmVar vmVar = (VmVar) obj;
                if(searchTypes.contains(vmVar.getTypes().get(0).getTerminal())) {
                    return true;
                }
            } else if (obj instanceof Assignment) {
                Assignment assi = (Assignment) obj;
                if(searchTypes.contains(assi.rValueContains(searchTypes))) {
                    return true;
                }
            }
            i++;
        }

        return false;
    }

    public Terminal getRValueType() throws ContextError {
        List<Terminal> types = new ArrayList<>();
        for (Object obj : getComponents()) {
            if (obj instanceof IToken) {
                IToken token = (IToken) obj;
                if(token.getTerminal().getType() == TerminalType.RELOPR
                        || token.getTerminal().getType() == TerminalType.BOOLOPR
                        || token.getTerminal().getType() == TerminalType.UNARYOPR) {
//                    types.add(Terminal.BOOL);
                    // Left type must be boolean type
                    return Terminal.BOOL;
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

    public void resolveIdentifierAndAddComponent(IToken token) throws ContextError {
        boolean added = false;
        if (token instanceof Ident) {
            VmVar subVmVar = Context.getVar(token);
            if (subVmVar != null) {
                added = true;
                this.addComponent(subVmVar);
            } else {
                Ident ident = ((Ident) token);
                if(!ident.getValue().equals("true") && !ident.getValue().equals("false")) {
                    throw new ContextError(String.format("Unknown variable: %s", ident.getValue()));
                }
            }
        }

        if (!added) {
            this.addComponent(token);
        }
    }

    public void addComponent(Object comp) {
        this.components.add(comp);
    }

    public Assignment getParent() {
        return parent;
    }

}
