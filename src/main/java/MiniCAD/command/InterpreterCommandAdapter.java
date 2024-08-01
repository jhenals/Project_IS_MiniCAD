package MiniCAD.command;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commands.CommandExprIF;
import MiniCAD.shapes.interpreter.commands.UndoableCmdExprIF;
import ObserverCommandFlyweight.is.command.Command;
public class InterpreterCommandAdapter<T> implements Command {

    private UndoableCmdExprIF miniCadCommand;
    private Context context;
    private T res ;

    public InterpreterCommandAdapter(CommandExprIF miniCadCommand, Context context) {
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
