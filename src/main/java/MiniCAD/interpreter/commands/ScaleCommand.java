package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

public class ScaleCommand implements CommandIF {
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
    public String interpreta(Context context) {
        String res = "";
        String idStr = objectId.interpreta(context);
        GraphicObject object = context.getObjectbyId(idStr);
        if( object != null ){
            object.scale(getScaleFactor());
            res = "Oggetto con id="+ idStr + " viene ridimensionato con un fattore di scala pari a " + getScaleFactor()+". Nuova dimensione="+ object.getDimension().toString();
        }else{
            res = "Oggetto con id="+idStr+" non trovato.";
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
