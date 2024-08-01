package MiniCAD.command;

import MiniCAD.shapes.interpreter.commands.UndoableCmdExprIF;


public interface MiniCadCommandHandler {

    void handle(UndoableCmdExprIF cmd);
}
