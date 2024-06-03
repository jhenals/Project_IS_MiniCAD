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
        System.out.println("Id del rettangolo : " +id);
        System.out.println("------------");
        System.out.println("CreateRectangleCommand{" +
                "\n id='" + id + '\'' +
                "\n typeConstraint=" + typeConstraint +
                "\n in posizione=" + posizione +
                '}');
    }

    @Override
    public String toString() {
        return "CreateRectangleCommand{" +
                "id='" + id + '\'' +
                ", typeConstraint=" + typeConstraint +
                ", in posizione=" + posizione +
                '}';
    }
}
