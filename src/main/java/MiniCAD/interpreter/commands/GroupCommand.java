package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.GroupObject;
import MiniCAD.interpreter.utilExpr.ListId;

import java.util.List;

public class GroupCommand implements UndoableCmdExprIF {
    private ListId ids ;

    public GroupCommand(ListId listId) {
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
