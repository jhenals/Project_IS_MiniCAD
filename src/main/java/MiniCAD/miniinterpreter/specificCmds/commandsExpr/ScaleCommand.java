package MiniCAD.miniinterpreter.specificCmds.commandsExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.Token;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

public class ScaleCommand implements UndoableCmdExprIF {
    private Token objectId;
    private Token scaleFactor; //POS_FLOAT

    private Double previousScaleFactor;
    private GraphicObject object;

    public ScaleCommand(Token objectId, Token scaleFactor) {
        this.objectId = objectId;
        this.scaleFactor = scaleFactor;
    }

    @Override
    public String interpreta(Context context) {
        String res;
        String idStr = objectId.interpreta(context);
        String sfStr = scaleFactor.interpreta(context);

        object = context.getObjectbyId(idStr);
        previousScaleFactor = Double.parseDouble(sfStr);

        double newDim;

        if( object != null ){
            context.ridimensiona(idStr, sfStr);
            res = "New dimension= ";
            if( object instanceof CircleObject){
                newDim = ((CircleObject) object).getRadius();
                res = res + newDim;
            }else{
                double newDimX = object.getDimension().getWidth();
                double newDimY = object.getDimension().getHeight();
                res = res + "("+ newDimX +","+ newDimY +")";            }
        }else{
            res = "Object with ID="+idStr+" is not found.";
        }
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
        object.scale(1.0 / previousScaleFactor);
        return true;
    }
}
