package MiniCAD.interpreter.commands;

public abstract class CreateCommand implements Command {
    protected TypeConstraint typeConstraint;
    protected Posizione posizione;

    public CreateCommand(TypeConstraint tc, Posizione pos) {
        typeConstraint = tc;
        posizione = pos;
    }


}
