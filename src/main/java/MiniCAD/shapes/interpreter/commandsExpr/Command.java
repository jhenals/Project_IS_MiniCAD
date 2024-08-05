package MiniCAD.shapes.interpreter.commandsExpr;


import MiniCAD.shapes.interpreter.Context;

public class Command<T> implements CommandExprIF, UndoableCmdExprIF {
    CommandExprIF<T> cmd;

    public Command( CommandExprIF<T> cmd){
        this.cmd = cmd;
    }

    @Override
    public T interpreta(Context context) {
        return  cmd.interpreta(context);
    }

    @Override
    public boolean undo(Context context) {
        if( cmd instanceof UndoableCmdExprIF)
            return ((UndoableCmdExprIF) cmd).undo(context);
        else
            return false;
    }
}


