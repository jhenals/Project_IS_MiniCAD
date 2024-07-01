package MiniCAD.interpreter.commands;

import MiniCAD.util.GeneratoreId;

import java.awt.geom.Point2D;

public class CreateCircleCommand extends CreateCommand{
    private String id;
    public CreateCircleCommand(TypeConstraint tc, Posizione pos) {
        super(tc, pos);
    }

    @Override
    public void interpreta() {
        double x = Double.parseDouble(posizione.getParam1().getValore().toString());
        double y = Double.parseDouble(posizione.getParam2().getValore().toString());
        Point2D position = new Point2D.Double(x,y);
        double raggio = Double.parseDouble(typeConstraint.getParameter().toString());
        id = GeneratoreId.generaId();
        System.out.println("Id del Cerchio : " + id);
    }

    @Override
    public String toString() {
        return "Cerchio{" +
                "id='" + id + '\'' +
                "raggio= " + typeConstraint.getParameter().toString() +
                "posizione= " + posizione +
                '}';
    }


    @Override
    public void undo() {

    }
}
