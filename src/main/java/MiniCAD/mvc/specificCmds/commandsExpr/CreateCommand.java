package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.PosizioneExpr;
import MiniCAD.mvc.specificCmds.utilExpr.TypeConstructorExpr;
import MiniCAD.ui.MiniCadGUI;
import ObserverCommandFlyweight.is.shapes.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

public class CreateCommand implements UndoableCmdExprIF {
    protected TypeConstructorExpr<?> typeConstructor;
    protected PosizioneExpr posizione;
    private String objId;

    public CreateCommand(TypeConstructorExpr<?> tc, PosizioneExpr pos) {
        typeConstructor = tc;
        posizione = pos;
    }

    private Point2D getPosInPoint2D(){
        float x= posizione.getParam1();
        float y= posizione.getParam2();
        return new Point2D.Double(x,y);
    }


    @Override
    public String interpreta(Context context) {
        String objectId = context.generaId();

        AbstractGraphicObject object = (AbstractGraphicObject) getGraphicObject(typeConstructor, context);
        if( object instanceof ImageObject){
            object = new ImageObject(new ImageIcon(Objects.requireNonNull(MiniCadGUI.class.getResource("NyaNya.gif"))),
                    (new Point(240, 290)));
        }

        GraphicObject go = object.clone();
        context.addObject(objectId, go);
        objId  =  objectId;
        return objId;
    }

    private GraphicObject getGraphicObject(TypeConstructorExpr<?> tc, Context context) {
        if( tc instanceof TypeConstructorExpr.CircleConstructor){
            TypeConstructorExpr.CircleConstructor circleConstructor = (TypeConstructorExpr.CircleConstructor) tc.interpreta(context);
            return new CircleObject( getPosInPoint2D(),circleConstructor.getRaggio());
        } else if ( tc instanceof TypeConstructorExpr.RectangleConstructor){
            TypeConstructorExpr.RectangleConstructor rectangleConstuctor = ((TypeConstructorExpr.RectangleConstructor) tc).interpreta(context);
            double base = rectangleConstuctor.getBase();
            double altezza =  rectangleConstuctor.getAltezza();
            return new RectangleObject(getPosInPoint2D(), base, altezza);
        } else if ( tc instanceof TypeConstructorExpr.ImageConstructor) {
            TypeConstructorExpr.ImageConstructor imageConstructor = ((TypeConstructorExpr.ImageConstructor) tc).interpreta(context);
            String path = imageConstructor.getPath();
            return new ImageObject(new ImageIcon(path), getPosInPoint2D());
        } else {
            throw new IllegalArgumentException("Object type is unknown.");
        }
    }

    @Override
    public boolean undo(Context context) {
        context.removeObjectById (objId);
        return true;
    }
}
