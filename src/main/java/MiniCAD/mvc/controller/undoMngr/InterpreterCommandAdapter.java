package MiniCAD.mvc.controller.undoMngr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.commandsExpr.CommandExprIF;
import MiniCAD.mvc.specificCmds.commandsExpr.UndoableCmdExprIF;
import ObserverCommandFlyweight.is.command.Command;
public class InterpreterCommandAdapter<T> implements Command {

    private UndoableCmdExprIF miniCadCommand;
    private final Context context;
    private T res ;

    public InterpreterCommandAdapter(CommandExprIF<T> miniCadCommand, Context context) {
        if(miniCadCommand instanceof UndoableCmdExprIF){
            this.miniCadCommand =(UndoableCmdExprIF) miniCadCommand;
        }
        this.context = context;
    }

    @Override
    public boolean doIt() {
        res = (T) miniCadCommand.interpreta(context);
        return true;
    }

    @Override
    public boolean undoIt() {
        return (miniCadCommand.undo(context));
    }

    public T getRes(){
        return res;
    }
}
