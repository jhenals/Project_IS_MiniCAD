package MiniCAD.command;

import MiniCAD.interpreter.commands.UndoableCmdExprIF;


public interface MiniCadCommandHandler {

    void handle(UndoableCmdExprIF cmd);
}
