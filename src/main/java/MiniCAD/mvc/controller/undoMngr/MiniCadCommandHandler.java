package MiniCAD.mvc.controller.undoMngr;

import MiniCAD.mvc.specificCmds.commandsExpr.UndoableCmdExprIF;


public interface MiniCadCommandHandler {
    void handle(UndoableCmdExprIF cmd);
}
