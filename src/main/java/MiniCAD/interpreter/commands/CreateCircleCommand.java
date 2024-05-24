package MiniCAD.interpreter.commands;

import MiniCAD.util.GeneratoreId;

public class CreateCircleCommand extends CreateCommand{
    private String id;
    public CreateCircleCommand(TypeConstraint tc, Posizione pos) {
        super(tc, pos);
    }

    @Override
    public void interpreta() {
        id = GeneratoreId.generaId();
        System.out.println(id);
    }

    @Override
    public String toString() {
        return "Cerchio{" +
                "id='" + id + '\'' +
                "raggio= " + typeConstraint.getParameter().toString() +
                "posizione= " + posizione +
                '}';
    }
}
