package MiniCAD.miniinterpreter.specificCmds.commandsExpr;


import MiniCAD.miniinterpreter.specificCmds.Context;

public class Command<T> implements CommandExprIF, UndoableCmdExprIF {
    private CommandExprIF<T> cmd;

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


