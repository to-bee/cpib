package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static scanner.datatypes.Terminal.COMPL;
import static scanner.datatypes.Terminal.INT32;

/**
 * Created by tobi on 15.01.17.
 */
public class VmVar {
    private final Ident ident;
    private int relLocation;
    private int absLocation;
    private boolean isConst;
    private Context context;
    private List<IToken> types = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList();
    private Assignment currentAssignment;

    public VmVar(Ident ident) {
        this.ident = ident;
    }

    public boolean isConst() {
        return isConst;
    }

    public void setConst(boolean isConst) {
        this.isConst = isConst;
    }

    public int getRelLocation() {
        return relLocation;
    }

    public void setRelLocation(int relLocation) {
        this.relLocation = relLocation;
    }

    public int getAbsLocation() {
        return absLocation;
    }

    public void setAbsLocation(int absLocation) {
        this.absLocation = absLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DefaultVar))
            return false;
        DefaultVar variable = (DefaultVar) o;
        return getIdent().equals(variable.getIdent()) && getContext().equals(variable.getContext());
    }

    @Override
    public String toString() {
        return String.format("%s", this.ident.getValue());
    }

    public Ident getIdent() {
        return ident;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void addAssignment() {
        Assignment assignment = new Assignment();
        this.assignments.add(assignment);
        this.setCurrentAssignment(assignment);
    }

    public Assignment getCurrentAssignment() {
        return currentAssignment;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }

    public void addType(IToken type) {
        this.types.add(type);
    }

    public void checkForbiddenOperations() throws ContextError {
        /**
         * Default var
         */
        if (getTypes().size() == 1) {
            IToken type = getTypes().get(0);
//            List<Object> components = getAssignments().stream().flatMap(a -> Arrays.stream(a.getComponents().toArray())).collect(Collectors.toList());

            List<Terminal> forbiddenTypes = new ArrayList<>();
            forbiddenTypes.add(Terminal.DIVOPR);
            forbiddenTypes.add(Terminal.COMPLEMENT);
            forbiddenTypes.add(Terminal.GT);
            forbiddenTypes.add(Terminal.LT);
            forbiddenTypes.add(Terminal.GE);
            forbiddenTypes.add(Terminal.LE);
            forbiddenTypes.add(Terminal.CAND);
            forbiddenTypes.add(Terminal.COR);

            List<Terminal> complTypes = new ArrayList<>();
            complTypes.add(Terminal.COMPL);
            complTypes.add(Terminal.IMAGINARY_PART);

            for(Assignment assi : getAssignments()) {
                boolean containsforbiddenOperators = assi.rValueContains(forbiddenTypes);
                boolean containsCompl = assi.rValueContains(complTypes);
                if(containsforbiddenOperators && containsCompl
                        || type.getTerminal() == Terminal.COMPL && containsforbiddenOperators) {
                    throw new ContextError(String.format("A variable with type: %s must not contain an RValue with type: %s", Terminal.COMPL, join(forbiddenTypes)));
                }
            }
        }
        /**
         * Tuple
         */
        else if (getTypes().size() > 0) {
            if(getAssignments().size() > 1) {
                throw new ContextError("Tuple is const and cannot be assigned several times");
            }
        } else {
            throw new ContextError(String.format("%s: No Type Assigned", getIdent()));
        }
    }

    public List<IToken> getTypes() {
        return new ArrayList<>(types);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Check whether all rightSideTokens match in type with left side.
     */
    public void checkAssignmentEquality() throws ContextError {
        /**
         * Default var
         */
        if (getTypes().size() == 1) {
            IToken type = getTypes().get(0);
            for (Assignment assi : getAssignments()) {
                Terminal rValueType = getRValueType();
                if (type.getTerminal() != rValueType) {
                    throw new ContextError(String.format("LType and RType mismatch for variable: %s. Cannot assign: %s to %s", toString(), type.getTerminal(), rValueType));
                }
            }
        }
        /**
         * Tuple
         */
        else if (getTypes().size() > 0) {

        } else {
            throw new ContextError(String.format("%s: No Type Assigned", getIdent()));
        }

//        for (IToken rightSideToken : getRightSideTokens()) {
//        }
    }

    public Terminal getRValueType() throws ContextError {
        List<Terminal> types = new ArrayList<>();
        for (Assignment assi : getAssignments()) {
            types.add(assi.getRValueType());
        }

        return getBestMatchingType(types);
    }

    public static Terminal getBestMatchingType(List<Terminal> types) throws ContextError {
        if (types.size() > 0) {
            // Check if all types are the same
            for (int i = 0; i < types.size(); i++) {
                List<Terminal> allowedTypes = getAllowedTypes(types.get(i));
                // All the other types must be one type of allowedTypes
                for (int j = i; j < types.size(); j++) {
                    if (!allowedTypes.contains(types.get(j))) {
                        throw new ContextError(String.format("Rtype mismatch detected. Variable:%s must not be assigned by a variable of the following type(s): (%s)", types.get(j).getValue(), join(allowedTypes)));
                    }
                }
            }

            if (types.contains(Terminal.COMPL)) {
                return Terminal.COMPL;
            } else {
                return types.get(0);
            }
        } else {
            throw new ContextError("No assignment not allowed");
        }
    }

    private static List<Terminal> getAllowedTypes(Terminal terminal) {
        List<Terminal> allowedTypes = new ArrayList<>();
        switch (terminal) {
            case COMPL:
                allowedTypes.add(COMPL);
                allowedTypes.add(INT32);
//                allowedTypes.add(Terminal.IMAGINARY_PART);
//                allowedTypes.add(Terminal.LITERAL);
                break;
            case INT32:
                allowedTypes.add(COMPL);
                allowedTypes.add(INT32);
//                allowedTypes.add(Terminal.LITERAL);
                break;
            case BOOL:
                allowedTypes.add(Terminal.BOOL);
                break;
        }

        return allowedTypes;
    }

    public static String join(List<Terminal> terminals) {
        StringBuilder sb = new StringBuilder();

        Iterator<Terminal> iterator = terminals.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getValue());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public boolean isTuple() {
        return getTypes().size() > 1;
    }


}
