package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Token;
import MiniCAD.interpreter.utilExpr.TokenType;

public class UngroupCommand implements CommandIF {
    private Token groupId;

    public UngroupCommand(Token groupId) {
        this.groupId = groupId;
    }

    @Override
    public String interpreta(Context context) {
        String res = "";
        String gid = groupId.getValore().toString();
        if( context.getAllGroups().keySet().contains(gid) ){
            context.unGroup(gid);
            context.removeObjectById(gid);
            res = "Gruppo con id=" + gid + " è stato rimosso.";
        }else{
            res = "Gruppo con id=" + gid + " è inesistente.";
        }
        System.out.println(res);
        return res;
    }


    @Override
    public String toString() {
        return "UngroupCommand{" +
                "groupId=" + groupId +
                '}';
    }

    @Override
    public boolean undo(Context context) {
        return false; //TODO
    }
}
