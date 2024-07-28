package MiniCAD.interpreter.commands;


import MiniCAD.interpreter.Context;

public class Command<T> implements  CommandIF{
    CommandIF cmd;

    public Command (CommandIF cmd){
        this.cmd = cmd;
    }
    @Override
    public T interpreta(Context context) {
        return (T) cmd.interpreta(context);
    }

    @Override
    public boolean undo(Context context) {
        return cmd.undo(context);
    }
}


