package MiniCAD.interpreter.commands;

public interface UndoableCommand extends Command{
    boolean undo();
}
