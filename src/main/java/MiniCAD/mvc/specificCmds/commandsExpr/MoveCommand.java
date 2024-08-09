package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.Token;
import MiniCAD.mvc.specificCmds.utilExpr.PosizioneExpr;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.awt.geom.Point2D;

public class MoveCommand implements UndoableCmdExprIF {
    private Token objectId;
    private PosizioneExpr newPos;
    private boolean offset;

    public MoveCommand(Token objectId, PosizioneExpr posizione, boolean offset){
        this.objectId= objectId;
        this.newPos = posizione;
        this.offset = offset;
    }

    public Token getObjectId() {
        return objectId;
    }


    public Point2D parsePosizioneToPoint2D(){
        double x = Double.parseDouble(Float.toString(newPos.getParam1()));
        double y = Double.parseDouble(Float.toString(newPos.getParam2()));
        return new Point2D.Double(x, y);
    }
    @Override
    public String interpreta(Context context) {
        String res;
        GraphicObject object = context.getObjectbyId(objectId.interpreta(context));
        String idStr = objectId.interpreta(context);
        PosizioneExpr pos= newPos.interpreta(context);

        if( object != null ){
            if(offset){
                context.moveOffObject(idStr, parsePosizioneToPoint2D());
                Point2D newPos = object.getPosition();
                res = "New position: (" + newPos.getX() + ","+ newPos.getY() + ")";
            }else{
                context.moveObject(idStr, parsePosizioneToPoint2D());
                res = "Object with ID= " + getObjectId().getValore() + " is moved in position " + pos ;
            }
        } else {
            res= "Object with ID= "+ objectId.getValore().toString() + " is not found";
        }
        System.out.println(res);
        return res;
    }


    @Override
    public String toString() {
        return "MoveCommand{" +
                "objectId=" + objectId +
                ", posizione=" + newPos +
                '}';
    }

    @Override
    public boolean undo(Context context) {
        String id = objectId.interpreta(context);
        context.undoMove(id);
        return true;
    }
}
