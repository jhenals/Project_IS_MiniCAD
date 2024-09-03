package MiniCAD.miniinterpreter.controller.undoMngr;

import MiniCAD.miniinterpreter.specificCmds.commandsExpr.UndoableCmdExprIF;


public interface MiniCadCommandHandler {
    void handle(UndoableCmdExprIF cmd);
}
