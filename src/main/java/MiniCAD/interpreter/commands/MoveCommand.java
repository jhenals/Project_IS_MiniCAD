package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.interpreter.dataClasses.Posizione;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class MoveCommand implements  Command{
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

    public void setObjectId(Token objectId) {
        this.objectId = objectId;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public Point2D parsePosizione(){
        Double x = Double.parseDouble(Float.toString(posizione.getParam1()));
        Double y = Double.parseDouble(Float.toString(posizione.getParam2()));
        Point2D p = new Point2D.Double(x, y);
        return p;
    }
    @Override
    public void interpreta() {
        ObjectManager objectManager = ObjectManager.getInstance();
        GraphicObject object = objectManager.getObjectbyId(objectId.getValore().toString());

        if( object != null ){
            if(offset){
                Double newX = object.getPosition().getX() + Double.parseDouble(Float.toString(posizione.getParam1()));
                Double newY = object.getPosition().getY() +  Double.parseDouble(Float.toString(posizione.getParam2()));
                object.moveTo(newX, newY);
                System.out.println( "Oggetto con id=" + getObjectId().getValore() + " viene spostato da " + getPosizione() + " a (" +
                        String.format("%.2f", newX) + ";"+ String.format("%.2f", newY) + ")");
            }else{
                object.moveTo(parsePosizione());
                System.out.println("Oggetto con id " + getObjectId().getValore() + " viene spostato " +
                        " alla posizione " + getPosizione() );

            }
        } else {
            System.out.println("Oggetto con ID "+ objectId.getValore().toString() + " non trovato");
        }

    }


    @Override
    public String toString() {
        return "MoveCommand{" +
                "objectId=" + objectId +
                ", posizione=" + posizione +
                '}';
    }
}
