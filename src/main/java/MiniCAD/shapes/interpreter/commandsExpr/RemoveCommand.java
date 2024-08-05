package MiniCAD.shapes.interpreter.commandsExpr;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.utilExpr.Token;

public class RemoveCommand implements UndoableCmdExprIF {
    private Token id;
    public RemoveCommand(Token id) {
        this.id = id;
    }

    @Override
    public String interpreta(Context context) {
        String res ;
        String idStr = id.interpreta(context);
        if (context.getObjectTypeById(idStr).equals( "Group")){
            for( String objId : context.getObjectIDsOfGroup(idStr)){
                context.removeObjectById(objId);
            }
            context.unGroup(idStr);
            context.removeObjectById(idStr);

            res ="Rimosso: "+ idStr;
        }else{
            context.removeObjectById(idStr);
            res = "Rimosso: "+ idStr;
        }
        System.out.println(res);
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
