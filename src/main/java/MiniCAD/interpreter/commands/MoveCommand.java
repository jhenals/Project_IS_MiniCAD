package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.interpreter.utilExpr.Posizione;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class MoveCommand implements UndoableCmdExprIF {
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
        double x = Double.parseDouble(Float.toString(posizione.getParam1()));
        double y = Double.parseDouble(Float.toString(posizione.getParam2()));
        return new Point2D.Double(x, y);
    }
    @Override
    public String interpreta(Context context) {
        String res;
        GraphicObject object = context.getObjectbyId(objectId.interpreta(context));
        String idStr = objectId.interpreta(context);
        Posizione pos= posizione.interpreta(context);

        if( object != null ){
            if(offset){
                Point2D oldPos = object.getPosition();
                context.moveOffObject(idStr, parsePosizioneToPoint2D());
                Point2D newPos = object.getPosition();

                res = "Oggetto con id=" + getObjectId().getValore() + " viene spostato da " + oldPos + " a (" +
                        newPos + ")";
            }else{

                context.moveObject(idStr, parsePosizioneToPoint2D());
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

    @Override
    public boolean undo(Context context) {
        return false; //TODO
    }
}
