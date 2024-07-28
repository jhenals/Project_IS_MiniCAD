package MiniCAD.util;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;
import MiniCAD.interpreter.commands.UndoableCmdExprIF;
import ObserverCommandFlyweight.is.command.Command;
public class InterpreterCommandAdapter<T> implements Command {

    private MiniCAD.interpreter.commands.Command miniCadCommand;
    private Context context;
    private T res ;

    public InterpreterCommandAdapter(MiniCAD.interpreter.commands.Command miniCadCommand, Context context) {
        if(miniCadCommand instanceof UndoableCmdExprIF){
            this.miniCadCommand = (MiniCAD.interpreter.commands.Command) miniCadCommand;
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
        if (miniCadCommand instanceof UndoableCmdExprIF) {
            return ((UndoableCmdExprIF) miniCadCommand).undo(context);
        }
        return false;
    }

    public T getRes(){
        return res;
    }
}
