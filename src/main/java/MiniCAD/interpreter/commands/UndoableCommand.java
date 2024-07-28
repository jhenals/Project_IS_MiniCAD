package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;

public interface UndoableCommand extends CommandIF {
    boolean undo(Context context);
}
