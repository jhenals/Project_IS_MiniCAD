package MiniCAD.util;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandIF;
import ObserverCommandFlyweight.is.command.Command;

public class InterpreterCommandAdapter implements Command {

    private final CommandIF miniCadCommand;
    private Context context;
    private Object res;

    public InterpreterCommandAdapter(CommandIF miniCadCommand, Context context) {
        this.context = context;
        this.miniCadCommand = miniCadCommand;

    }

    @Override
    public boolean doIt() {
        res= miniCadCommand.interpreta(context);
        return true;
    }

    @Override
    public boolean undoIt() {
        return miniCadCommand.undo(context);
    }

    public Object getRes() { return  res; }
}
