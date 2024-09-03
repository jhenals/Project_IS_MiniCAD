package MiniCAD.miniinterpreter.specificCmds.commandsExpr;

import MiniCAD.miniinterpreter.specificCmds.Context;

public interface UndoableCmdExprIF extends CommandExprIF{

    boolean undo(Context context);
}
