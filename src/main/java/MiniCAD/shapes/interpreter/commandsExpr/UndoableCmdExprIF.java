package MiniCAD.shapes.interpreter.commandsExpr;

import MiniCAD.shapes.interpreter.Context;

public interface UndoableCmdExprIF extends CommandExprIF{

    boolean undo(Context context);
}
