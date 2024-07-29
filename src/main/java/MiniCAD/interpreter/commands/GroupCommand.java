package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.GroupObject;
import MiniCAD.interpreter.utilExpr.ListIdExpr;

import java.util.List;

public class GroupCommand implements UndoableCmdExprIF {
    private ListIdExpr ids ;

    public GroupCommand(ListIdExpr listId) {
        ids=listId;
    }

    @Override
    public String interpreta(Context context) {
        List<String> idList = ids.interpreta(context);
        GroupObject groupObject = context.createGroup(idList);

        System.out.println(groupObject.getGroupId());
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
        return false; //TODO
    }
}
