package MiniCAD.shapes.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.utilExpr.PosizioneExpr;
import MiniCAD.shapes.interpreter.utilExpr.TypeConstructorExpr;
import MiniCAD.ui.MiniCADUI;
import ObserverCommandFlyweight.is.shapes.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

public class CreateCommand implements UndoableCmdExprIF {
    protected TypeConstructorExpr typeConstructor;
    protected PosizioneExpr posizione;
    private String objId;

    public CreateCommand(TypeConstructorExpr tc, PosizioneExpr pos) {
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
        AbstractGraphicObject object = (AbstractGraphicObject) getGraphicObject(typeConstructor, context);
        String objectId = context.generaId();
        if( object instanceof ImageObject){
            object = new ImageObject(new ImageIcon(Objects.requireNonNull(MiniCADUI.class.getResource("NyaNya.gif"))),
                    (new Point(240, 290)));
        }
        GraphicObject go = object.clone();
        context.addObject(objectId, go);
        objId  =  objectId;
        System.out.println(objId);
        return objId;
    }

    private GraphicObject getGraphicObject(TypeConstructorExpr typeConstructor, Context context) {
        if( typeConstructor instanceof TypeConstructorExpr.CircleConstructor){
            TypeConstructorExpr.CircleConstructor circleConstructor = (TypeConstructorExpr.CircleConstructor) typeConstructor.interpreta(context);
            return new CircleObject( getPosInPoint2D(),circleConstructor.getRaggio());
        } else if ( typeConstructor instanceof TypeConstructorExpr.RectangleConstructor){
            TypeConstructorExpr.RectangleConstructor rectangleConstuctor = ((TypeConstructorExpr.RectangleConstructor) typeConstructor).interpreta(context);
            double base = rectangleConstuctor.getBase();
            double altezza =  rectangleConstuctor.getAltezza();
            return new RectangleObject(getPosInPoint2D(), base, altezza);
        } else if ( typeConstructor instanceof TypeConstructorExpr.ImageConstructor) {
            TypeConstructorExpr.ImageConstructor imageConstructor = ((TypeConstructorExpr.ImageConstructor) typeConstructor).interpreta(context);
            String path = imageConstructor.getPath();
            return new ImageObject(new ImageIcon(path), getPosInPoint2D());
        } else {
            throw new IllegalArgumentException("TipoExpr di oggetto sconosciuto.");
        }
    }

    @Override
    public boolean undo(Context context) {
        context.removeObjectById (objId);
        //System.out.println("Rimosso oggetto con id=" + objId);
        return true;
    }
}
