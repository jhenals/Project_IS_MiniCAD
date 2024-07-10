package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.utils.Token;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

public class ScaleCommand implements  Command{
    private Token objectId;
    private Token scaleFactor; //POS_FLOAT

    public ScaleCommand(Token objectId, Token scaleFactor) {
        this.objectId = objectId;
        this.scaleFactor = scaleFactor;
    }

    public Token getObjectId() {
        return objectId;
    }

    public void setObjectId(Token objectId) {
        this.objectId = objectId;
    }

    public Float getScaleFactor() {
        return Float.parseFloat(scaleFactor.getValore().toString());
    }

    public void setScaleFactor(Token sf) {
        scaleFactor = sf;
    }

    @Override
    public void interpreta() {
        ObjectManager objectManager = ObjectManager.getInstance();
        GraphicObject object = objectManager.getObject(objectId.getValore().toString());
        if( object != null ){
            object.scale(getScaleFactor());
        }else{
            System.out.println("Oggetto con id="+objectId+" non trovato.");
        }
        System.out.println("Oggetto con id="+ objectId.getValore().toString() + " viene ridimensionato con un fattore di scale pari a " + getScaleFactor());
    }


    @Override
    public String toString() {
        return "ScaleCommand{" +
                "objectId=" + objectId +
                ", scale factor=" + scaleFactor +
                '}';
    }
}
