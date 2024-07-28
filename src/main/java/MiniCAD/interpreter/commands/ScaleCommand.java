package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

public class ScaleCommand implements UndoableCmdExprIF {
    private Token objectId;
    private Token scaleFactor; //POS_FLOAT

    public ScaleCommand(Token objectId, Token scaleFactor) {
        this.objectId = objectId;
        this.scaleFactor = scaleFactor;
    }

    @Override
    public String interpreta(Context context) {
        String res;
        String idStr = objectId.interpreta(context);
        String sfStr = scaleFactor.interpreta(context);

        GraphicObject object = context.getObjectbyId(idStr);
        double newDim;
        if( object != null ){
            context.ridimensiona(idStr, sfStr);
            res = "Oggetto con id="+ idStr + " viene ridimensionato con un fattore di scala pari a " + sfStr+". Nuova dimensione= ";
            if( object instanceof CircleObject){
                newDim = ((CircleObject) object).getRadius();
                res = res + newDim;
            }else{
                double newDimX = object.getDimension().getWidth();
                double newDimY = object.getDimension().getHeight();
                res = res + "("+ newDimX +","+ newDimY +")";            }
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

    @Override
    public boolean undo(Context context) {
        return false; //TODO
    }
}
