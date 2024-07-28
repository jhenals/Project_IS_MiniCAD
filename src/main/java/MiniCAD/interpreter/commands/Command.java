package MiniCAD.interpreter.commands;


import MiniCAD.interpreter.Context;

public class Command<T> implements CommandExprIF, UndoableCmdExprIF {
    CommandExprIF cmd;

    public Command( CommandExprIF cmd){
        this.cmd = cmd;
    }

    @Override
    public T interpreta(Context context) {
        return (T) cmd.interpreta(context);
    }

    @Override
    public boolean undo(Context context) {
        if( cmd instanceof UndoableCmdExprIF)
            return ((UndoableCmdExprIF) cmd).undo(context);
        else
            return false;
    }
}


