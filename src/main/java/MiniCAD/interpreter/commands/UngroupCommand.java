package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.ObjectManager;
import MiniCAD.interpreter.dataClasses.Token;
import MiniCAD.interpreter.dataClasses.TokenType;

public class UngroupCommand implements  Command{
    private Token groupId;

    public UngroupCommand(Token groupId) {
        if( groupId.getTipo() == TokenType.GRP_ID ){
            this.groupId = groupId;
        }else{
            throw new IllegalArgumentException("Parametro passato non è un tipo OBJ_ID");
        }
    }

    public Token getGroupId() {
        return groupId;
    }

    public void setGroupId(Token groupId) {
        this.groupId = groupId;
    }

    @Override
    public String interpreta() {
        String res = "";
        ObjectManager objectManager = ObjectManager.getInstance();
        String gid = groupId.getValore().toString();
        if( objectManager.getAllGroupIds().contains(gid) ){
            objectManager.unGroup(gid);
            objectManager.removeObject(gid);
            res = "Gruppo con id=" + gid + " è stato rimosso.";
        }else{
            res = "Gruppo con id=" + gid + " è inesistente.";
        }
        return res;
    }


    @Override
    public String toString() {
        return "UngroupCommand{" +
                "groupId=" + groupId +
                '}';
    }
}
