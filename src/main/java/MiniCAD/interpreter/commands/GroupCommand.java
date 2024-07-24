package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.GroupObject;
import MiniCAD.interpreter.dataClasses.ListId;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.interpreter.dataClasses.TokenType;
import MiniCAD.util.GeneratoreId;

import java.util.List;

public class GroupCommand implements  Command{
    private ListId ids ;

    public GroupCommand(ListId listId) {
        ids=listId;
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
        System.out.println(grpId);
        return grpId;
    }


    @Override
    public String toString() {
        return "GroupCommand{" +
                "objectIds=" + ids.toString() +
                '}';
    }
}
