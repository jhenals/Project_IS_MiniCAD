package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.*;
import MiniCAD.util.GeneratoreId;
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
    public String interpreta() {
        ObjectManager objectManager= ObjectManager.getInstance();
        GraphicObject object;
        if( typeConstructor instanceof TypeConstructor.CircleConstructor){
            object = new CircleObject( getPosizione(), ((TypeConstructor.CircleConstructor) typeConstructor).getRaggio());
        } else if ( typeConstructor instanceof TypeConstructor.RectangleConstuctor){
            double base = ((TypeConstructor.RectangleConstuctor) typeConstructor).getParam1();
            double altezza =  ((TypeConstructor.RectangleConstuctor) typeConstructor).getParam2();
            object = new RectangleObject(getPosizione(), base, altezza);
        } else if ( typeConstructor instanceof TypeConstructor.ImageConstructor) {
            String path = ((TypeConstructor.ImageConstructor) typeConstructor).getPath();
            object = new ImageObject(new ImageIcon(path),
                    getPosizione());
        } else {
            throw new IllegalArgumentException("Tipo di oggetto sconosciuto.");
        }

        if( object != null) {
            String objectId = GeneratoreId.generaId();
            objectManager.addObject(objectId, object);
            objId  =  objectId;
        }
        System.out.println(objId);
        return objId;
    }

    @Override
    public boolean undo() {
        ObjectManager.getInstance().removeObject(objId);
        System.out.println("Rimosso oggetto con id=" + objId);
        return true;
    }
}
