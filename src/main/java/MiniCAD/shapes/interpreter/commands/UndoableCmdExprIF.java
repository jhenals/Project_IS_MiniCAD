package MiniCAD.shapes.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;

public interface UndoableCmdExprIF extends CommandExprIF{

    boolean undo(Context context);
}
