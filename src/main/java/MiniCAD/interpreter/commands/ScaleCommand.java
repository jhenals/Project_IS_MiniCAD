package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

public class ScaleCommand implements  Command{
    private Token objectId;
    private Token scaleFactor; //POS_FLOAT

    public ScaleCommand(Token objectId, Token scaleFactor) {
        this.objectId = objectId;
        this.scaleFactor = scaleFactor;
    }

    public Float getScaleFactor() {
        return Float.parseFloat(scaleFactor.getValore().toString());
    }

    @Override
    public String interpreta() {
        String res = "";
        ObjectManager objectManager = ObjectManager.getInstance();
        GraphicObject object = objectManager.getObjectbyId(objectId.getValore().toString());
        if( object != null ){
            object.scale(getScaleFactor());
            res = "Oggetto con id="+ objectId.getValore().toString() + " viene ridimensionato con un fattore di scala pari a " + getScaleFactor()+". Nuova dimensione="+ object.getDimension().toString();
        }else{
            res = "Oggetto con id="+objectId+" non trovato.";
        }
        System.out.println(res);
        return res;
    }

    @Override
    public String toString() {
        return "ScaleCommand{" +
                "objectId=" + objectId +
                ", scale factor=" + scaleFactor +
                '}';
    }
}
