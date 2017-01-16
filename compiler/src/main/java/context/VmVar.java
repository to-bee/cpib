package context;

import scanner.datatypes.Terminal;
import scanner.errors.ContextError;
import scanner.token.IToken;
import scanner.token.Ident;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static scanner.datatypes.Terminal.COMPL;

/**
 * Created by tobi on 15.01.17.
 */
public class VmVar {
    private int relLocation;
    private int absLocation;
    private boolean isConst;
    private Context context;

    private List<Assignment> assignments = new ArrayList();

    public VmVar(Ident ident) {
        this.ident = ident;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    private final Ident ident;


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

    private Assignment currentAssignment;
}
