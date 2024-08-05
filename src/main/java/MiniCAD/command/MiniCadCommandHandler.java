package MiniCAD.command;

import MiniCAD.shapes.interpreter.commandsExpr.UndoableCmdExprIF;


public interface MiniCadCommandHandler {


    void handle(UndoableCmdExprIF cmd);
}
