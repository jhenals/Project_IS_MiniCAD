package MiniCAD.interpreter.commands;

import MiniCAD.util.GeneratoreId;

public class CreateImageCommand extends CreateCommand {

    private String id;

    public CreateImageCommand(TypeConstraint tc, Posizione pos) {
        super(tc, pos);
    }

    @Override
    public void interpreta() {
        id = GeneratoreId.generaId();
        System.out.println(id);
        //logic here
    }

    @Override
    public String toString() {
        return "CreateImageCommand{" +
                "id='" + id + '\'' +
                ", typeConstraint=" + typeConstraint +
                ", posizione=" + posizione +
                '}';
    }
}
