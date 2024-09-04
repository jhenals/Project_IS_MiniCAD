package MiniCAD.miniinterpreter.controller.undoMngr;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.UndoableCmdExprIF;

import java.util.LinkedList;

public class MiniCadHistoryCmdHandler implements MiniCadCommandHandler {

    private final Context context;
    public enum NonExecutableCommands implements UndoableCmdExprIF {
        UNDO, REDO;

        @Override
        public Object interpreta(Context context) {
            throw new NoSuchMethodError();
        }

        @Override
        public boolean undo(Context context) {
            throw new NoSuchMethodError();
        }
    }

    private final int maxHistoryLength = 100;

    private LinkedList<UndoableCmdExprIF> history = new LinkedList<>();

    public MiniCadHistoryCmdHandler(Context c){
        super();
        this.context = c;
    }

    public void handle(UndoableCmdExprIF cmd) {
        if (cmd == NonExecutableCommands.UNDO) {
            undo();
            return;
        }
        if (cmd.interpreta(context) != null ) {
            addToHistory(cmd);
        } else {
            history.clear();
        }
    }

    private void undo() {
        if (history.size() > 0) {
            UndoableCmdExprIF undoCmd = history.removeFirst();
            undoCmd.undo(context);
        }
    }

    private void addToHistory(UndoableCmdExprIF cmd) {
        history.addFirst(cmd);
        if (history.size() > maxHistoryLength) {
            history.removeLast();
        }

    }
}
