package MiniCAD.interpreter.commands;

import MiniCAD.interpreter.Context;

public interface UndoableCmdExprIF extends CommandExprIF{

    boolean undo(Context context);
}
