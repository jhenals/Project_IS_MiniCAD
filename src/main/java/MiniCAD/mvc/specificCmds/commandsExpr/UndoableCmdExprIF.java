package MiniCAD.mvc.specificCmds.commandsExpr;

import MiniCAD.mvc.specificCmds.Context;

public interface UndoableCmdExprIF extends CommandExprIF{

    boolean undo(Context context);
}
