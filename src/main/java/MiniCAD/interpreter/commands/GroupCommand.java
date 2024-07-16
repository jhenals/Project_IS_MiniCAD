package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.GroupObject;
import MiniCAD.interpreter.dataClasses.ListId;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.interpreter.dataClasses.TokenType;
import MiniCAD.util.GeneratoreId;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupCommand implements  Command{
    private ListId ids ;

    public GroupCommand(ListId listId) {
        ids=listId;
    }

    public ListId getObjectIds() {
        return ids;
    }

    public void setObjectIds(ListId objectIds) {
        this.ids = ids;
    }

    @Override
    public String interpreta() {
        String res = "";
        ObjectManager objectManager = ObjectManager.getInstance();

        String grpId = GeneratoreId.generaIdGruppo();
        GroupObject groupObject = new GroupObject(grpId);
        for( Token id : ids.getIds() ){
            if( id.getTipo()== TokenType.OBJ_ID ){
                groupObject.addObject(objectManager.getObjectbyId(id.getValore().toString()));
            }else if( id.getTipo() == TokenType.GRP_ID){
                groupObject.addGroup(id.getValore().toString());
            }
        }
        List<String> objectIds = groupObject.getObjectIds();

        objectManager.addGroup(grpId, objectIds);
        objectManager.addObject(grpId, groupObject);
        return grpId;
        /*
        StringBuilder sb = new StringBuilder();
        System.out.println("Oggeti nel gruppo: ");
        for(Map.Entry<String, GraphicObject> entry : objectManager.getObjectsOfGroup(grpId).entrySet()  ){
            sb.append(" ").append(entry.getKey()).append(":").append(entry.getValue().getType()).append("\n");
        }
        System.out.println(sb.toString());

         */

    }


    @Override
    public String toString() {
        return "GroupCommand{" +
                "objectIds=" + ids.toString() +
                '}';
    }
}
