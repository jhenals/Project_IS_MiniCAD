package MiniCAD.util;

import MiniCAD.interpreter.commands.UndoableCommand;
import ObserverCommandFlyweight.is.command.Command;
public class InterpreterCommandAdapter implements Command {

    private final UndoableCommand miniCadCommand;

    public InterpreterCommandAdapter(UndoableCommand miniCadCommand) {
        this.miniCadCommand = miniCadCommand;
    }

    @Override
    public boolean doIt() {
        miniCadCommand.interpreta();
        return true;
    }

    @Override
    public boolean undoIt() {
        return miniCadCommand.undo();
    }
}
