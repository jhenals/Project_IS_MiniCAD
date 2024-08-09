package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.utilExpr.Token;

public class RemoveCommand implements UndoableCmdExprIF {
    private CommandExprIF<?> id;
    public RemoveCommand(Token id) {
        this.id = id;
    }

    @Override
    public String interpreta(Context context) {
        String res ;
        String idStr = (String) id.interpreta(context);
        if (context.getObjectTypeById(idStr).equals( "Group")){
            for( String objId : context.getObjectIDsOfGroup(idStr)){
                context.removeObjectById(objId);
            }
            context.unGroup(idStr);
            context.removeObjectById(idStr);

            res ="Removed: "+ idStr;
        }else{
            context.removeObjectById(idStr);
            res = "Removed: "+ idStr;
        }
        return res;
    }


    @Override
    public String toString() {
        return "RemoveCommand{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean undo(Context context) {
        return false; //TODO
    }
}
