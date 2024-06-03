package MiniCAD.interpreter.commands;

import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.List;

public abstract class CreateCommand implements Command {
    protected TypeConstraint typeConstraint;
    protected Posizione posizione;
    protected List<GraphicObject> oggetti;

    public CreateCommand(TypeConstraint tc, Posizione pos) {
        typeConstraint = tc;
        posizione = pos;
    }


}
