package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.*;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import ObserverCommandFlyweight.is.shapes.model.RectangleObject;

import javax.swing.*;
import java.awt.geom.Point2D;

public class CreateCommand implements UndoableCommand {
    protected TypeConstructor typeConstructor;
    protected Posizione posizione;
    private String objId;

    public CreateCommand(TypeConstructor tc, Posizione pos) {
        typeConstructor = tc;
        posizione = pos;
    }

    private Point2D getPosizione(){
        float x= posizione.getParam1();
        float y= posizione.getParam2();
        return new Point2D.Double(x,y);
    }


    @Override
    public String interpreta(Context context) {
        GraphicObject object;

        if( typeConstructor instanceof TypeConstructor.CircleConstructor){
            TypeConstructor.CircleConstructor circleConstructor = (TypeConstructor.CircleConstructor) typeConstructor.interpreta(context);
            object = new CircleObject( getPosizione(),circleConstructor.getRaggio());
        } else if ( typeConstructor instanceof TypeConstructor.RectangleConstuctor){
            TypeConstructor.RectangleConstuctor rectangleConstuctor = ((TypeConstructor.RectangleConstuctor) typeConstructor).interpreta(context);
            double base = rectangleConstuctor.getParam1();
            double altezza =  rectangleConstuctor.getParam2();
            object = new RectangleObject(getPosizione(), base, altezza);
        } else if ( typeConstructor instanceof TypeConstructor.ImageConstructor) {
            TypeConstructor.ImageConstructor imageConstructor = ((TypeConstructor.ImageConstructor) typeConstructor).interpreta(context);
            String path = imageConstructor.getPath();
            object = new ImageObject(new ImageIcon(path), getPosizione());
        } else {
            throw new IllegalArgumentException("Tipo di oggetto sconosciuto.");
        }


        if( object != null) {
            String objectId = context.generaId();
            context.addObject(objectId, object);
            objId  =  objectId;
        }
        System.out.println(objId);
        return objId;
    }

    @Override
    public boolean undo(Context context) {
        context.removeObjectById (objId);
        System.out.println("Rimosso oggetto con id=" + objId);
        return true;
    }
}
