package MiniCAD.mvc.controller.undoMngr;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.specificCmds.commandsExpr.UndoableCmdExprIF;

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

    private LinkedList<UndoableCmdExprIF> redoList = new LinkedList<>();

    public MiniCadHistoryCmdHandler(Context c){
        super();
        this.context = c;
    }

    public void handle(UndoableCmdExprIF cmd) {
        if (cmd == NonExecutableCommands.UNDO) {
            undo();
            return;
        }
        if (cmd == NonExecutableCommands.REDO) {
            redo();
            return;
        }
        if (cmd.interpreta(context) != null ) {
            // restituisce true: può essere annullato
            addToHistory(cmd);
        } else {
            // restituisce false: non può essere annullato
            history.clear();
        }
        if (redoList.size() > 0)
            redoList.clear();
    }

    private void redo() {
        if (redoList.size() > 0) {
            UndoableCmdExprIF redoCmd = redoList.removeFirst();
            redoCmd.interpreta(context);
            history.addFirst(redoCmd);

        }
    }

    private void undo() {
        if (history.size() > 0) {
            UndoableCmdExprIF undoCmd = history.removeFirst();
            undoCmd.undo(context);
            redoList.addFirst(undoCmd);
        }
    }

    private void addToHistory(UndoableCmdExprIF cmd) {
        history.addFirst(cmd);
        if (history.size() > maxHistoryLength) {
            history.removeLast();
        }

    }


}
