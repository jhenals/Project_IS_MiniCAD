package MiniCAD.interpreter.commands;

import MiniCAD.util.GeneratoreId;

public class CreateRectangleCommand extends CreateCommand{

    private String id;

    public CreateRectangleCommand(TypeConstraint tc, Posizione pos) {
        super(tc, pos);
    }

    @Override
    public void interpreta() {
        id = GeneratoreId.generaId();
        System.out.println(id);
    }

    @Override
    public String toString() {
        return "CreateRectangleCommand{" +
                "id='" + id + '\'' +
                ", typeConstraint=" + typeConstraint +
                ", posizione=" + posizione +
                '}';
    }
}
