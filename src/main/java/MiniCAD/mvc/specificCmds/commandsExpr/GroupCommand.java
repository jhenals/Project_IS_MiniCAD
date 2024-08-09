package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.model.GroupObject;
import MiniCAD.mvc.specificCmds.utilExpr.ListIdExpr;

import java.util.List;

public class GroupCommand implements UndoableCmdExprIF {
    private ListIdExpr ids ;
    private String gid;

    public GroupCommand(ListIdExpr listId) {
        ids=listId;
    }

    @Override
    public String interpreta(Context context) {
        List<String> idList = ids.interpreta(context);
        GroupObject groupObject = context.createGroup(idList);
        gid = groupObject.getGroupId();
        return groupObject.getGroupId();
    }


    @Override
    public String toString() {
        return "GroupCommand{" +
                "objectIds=" + ids.toString() +
                '}';
    }

    @Override
    public boolean undo(Context context) {
        context.unGroup(gid);
        return true;
    }
}