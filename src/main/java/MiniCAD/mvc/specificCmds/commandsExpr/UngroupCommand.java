package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.Token;

import java.util.List;

public class UngroupCommand implements UndoableCmdExprIF {
    private final Token groupId;
    private List<String> idList;

    public UngroupCommand(Token groupId) {
        this.groupId = groupId;
    }

    @Override
    public String interpreta(Context context) {
        String res;
        String gid = groupId.interpreta(context);
        if( !context.getObjectTypeById(gid).equals("Group")){
            throw new IllegalArgumentException("Object with ID="+ gid + " is not of type GROUP.");
        }
        if( context.getAllGroups().containsKey(gid) ){
            idList = context.getObjectIDsOfGroup(gid);
            context.unGroup(gid);
            context.removeObjectById(gid);
            res = "Group with ID=" + gid + " is removed.";
        }else{
            res = "Group with ID=" + gid + " doesn't exist.";
        }
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
        context.createGroup(idList);
        return true;
    }
}
