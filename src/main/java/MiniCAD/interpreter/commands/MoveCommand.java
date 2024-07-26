package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.interpreter.utilExpr.Posizione;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class MoveCommand implements CommandIF {
    private Token objectId;
    private Posizione posizione;
    private boolean offset;

    public MoveCommand(Token objectId, Posizione posizione, boolean offset){
        this.objectId= objectId;
        this.posizione = posizione;
        this.offset = offset;
    }

    public Token getObjectId() {
        return objectId;
    }


    public Point2D parsePosizioneToPoint2D(){
        Double x = Double.parseDouble(Float.toString(posizione.getParam1()));
        Double y = Double.parseDouble(Float.toString(posizione.getParam2()));
        Point2D p = new Point2D.Double(x, y);
        return p;
    }
    @Override
    public String interpreta(Context context) {
        String res = "";
        GraphicObject object = context.getObjectbyId(objectId.interpreta(context));
        Posizione pos= posizione.interpreta(context);

        if( object != null ){
            if(offset){
                Double newX = object.getPosition().getX() + Double.parseDouble(Float.toString(posizione.getParam1()));
                Double newY = object.getPosition().getY() +  Double.parseDouble(Float.toString(posizione.getParam2()));
                object.moveTo(newX, newY);
                res = "Oggetto con id=" + getObjectId().getValore() + " viene spostato da " + pos + " a (" +
                        String.format("%.2f", newX) + ";"+ String.format("%.2f", newY) + ")";
            }else{
                object.moveTo(parsePosizioneToPoint2D());
                res = "Oggetto con id= " + getObjectId().getValore() + " viene spostato " +
                        " alla posizione " + pos ;

            }
        } else {
            res= "Oggetto con ID "+ objectId.getValore().toString() + " non trovato";
        }
        System.out.println(res);
        return res;
    }


    @Override
    public String toString() {
        return "MoveCommand{" +
                "objectId=" + objectId +
                ", posizione=" + posizione +
                '}';
    }
}
