package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.lexerparser.Token;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;

import java.awt.geom.Point2D;

public class MoveOffCommand implements  Command{
    private Token objectId;
    private Posizione posizione;

    public MoveOffCommand(Token objectId, Posizione posizione) {
        this.objectId = objectId;
        this.posizione = posizione;
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

    /*
    public Posizione nuovaPosizione(Posizione pos){
        GraphicObject go = new ImageObject();
        TODO: Posizione corrente dell'oggetto + nuova posizione fornita dall'utente


    }

     */

    public Posizione getNuovaPosizione(Point2D np){
     return null;
    }

    @Override
    public void interpreta() {
        System.out.println("Ogetto con id "+ getObjectId().getValore() +" viene spostato ad una nuova posizione " + "getNuovaP"  );
    }

    @Override
    public void undo() {

    }
    @Override
    public String toString() {
        return "MoveOffCommand{" +
                "objectId=" + objectId +
                ", posizione=" + posizione +
                '}';
    }
}
