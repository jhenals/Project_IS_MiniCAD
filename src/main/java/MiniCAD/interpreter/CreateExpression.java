package MiniCAD.interpreter;

import java.awt.geom.Point2D;

public class CreateExpression extends Expression{
    private String tipo;  //tipo di oggetto da creare : cerchio, rettangolo, image
    private double[] parametri;
    private Point2D posizione; //posizione iniziale

    public CreateExpression( String tipo, double[] parametri, Point2D posizione){
        this.tipo = tipo;
        this.parametri = parametri;
        this.posizione = posizione;
    }
    @Override
    void interpret() {
      

    }
}
