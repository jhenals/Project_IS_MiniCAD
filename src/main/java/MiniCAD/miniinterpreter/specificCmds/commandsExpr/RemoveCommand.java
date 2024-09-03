package MiniCAD.miniinterpreter.specificCmds.commandsExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.Token;
import MiniCAD.ui.MiniCadGUI;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;

import javax.swing.*;
import java.awt.*;
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
        return idStr;
    }


    @Override
    public boolean undo(Context context) {
        for( Map.Entry<String, AbstractGraphicObject> entry: removedObjects.entrySet() ){

            if( entry.getValue() instanceof ImageObject){
                newObject = new ImageObject(new ImageIcon(Objects.requireNonNull(MiniCadGUI.class.getResource("NyaNya.gif"))),
                        (new Point(0, 0)));
            }
            context.addObject(entry.getKey(), newObject);
        }
        if (context.getObjectTypeById(idStr).equals("Group")) {
            context.createGroup(removedObjects.keySet().stream().toList());
        }
        System.out.println(idStr);
        return true;
    }
}
