package MiniCAD.miniinterpreter.specificCmds.commandsExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.Token;
import MiniCAD.ui.MiniCadGUI;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RemoveCommand implements UndoableCmdExprIF {
    private CommandExprIF<?> id;
    private AbstractGraphicObject previousGraphicObjectOfId;
    private GraphicObject newObject;
    private Map<String, AbstractGraphicObject> removedObjects;
    private String idStr;

    public RemoveCommand(Token id) {
        this.id = id;
        this.removedObjects = new HashMap<>();
    }

    @Override
    public String interpreta(Context context) {
        idStr = (String) id.interpreta(context);

        previousGraphicObjectOfId = (AbstractGraphicObject)context.getObjectbyId(idStr);
        newObject = previousGraphicObjectOfId.clone();

        if (context.getObjectTypeById(idStr).equals( "Group")){
            for( String objId : context.getObjectIDsOfGroup(idStr)){
                removedObjects.put(objId, (AbstractGraphicObject)context.getObjectbyId(objId));
                context.removeObjectById(objId);
            }
            context.unGroup(idStr);
            context.removeObjectById(idStr);
        }else{
            removedObjects.put(idStr,previousGraphicObjectOfId);
            context.removeObjectById(idStr);
        }
        return idStr + " is removed.";
    }


    @Override
    public boolean undo(Context context) {
        for (Map.Entry<String, AbstractGraphicObject> entry : removedObjects.entrySet()) {
            String objectId = entry.getKey();
            AbstractGraphicObject graphicObject = entry.getValue();

            if (graphicObject instanceof ImageObject) {    // Special handling for ImageObject
                ImageObject restoredImage = new ImageObject(new ImageIcon(Objects.requireNonNull(
                        MiniCadGUI.class.getResource("NyaNya.gif"))),
                        new Point2D.Double(graphicObject.getPosition().getX(), graphicObject.getPosition().getY()));
                context.addObject(objectId, restoredImage.clone());
            } else {
                context.addObject(objectId, graphicObject.clone());
            }
        }

        if (newObject.getType().equals("Group")) {
            context.createGroup(removedObjects.keySet().stream().toList());
        }

        return true;
    }
}
