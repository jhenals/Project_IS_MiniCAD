package MiniCAD.shapes.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.GroupObject;
import MiniCAD.shapes.interpreter.utilExpr.ListIdExpr;

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
        System.out.println(gid);
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
