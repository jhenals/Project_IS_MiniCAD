package MiniCAD;

import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.CommandExprIF;
import MiniCAD.interpreter.commands.UndoableCmdExprIF;
import MiniCAD.util.InterpreterCommandAdapter;
import ObserverCommandFlyweight.is.command.HistoryCommandHandler;

public class SistemaMiniCAD {
    private final HistoryCommandHandler historyCommandHandler = new HistoryCommandHandler();

    public void esegueComando(CommandExprIF comando, Context context){
        if(comando instanceof  UndoableCmdExprIF){
            InterpreterCommandAdapter adapter = new InterpreterCommandAdapter(comando, context);
            historyCommandHandler.handle(adapter);
        }else{
            comando.interpreta(context);
        }
    }

    public void undo(){
        historyCommandHandler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO);
    }


    public void redo(){
        historyCommandHandler.handle(HistoryCommandHandler.NonExecutableCommands.REDO);
    }

}


/*
Schema del Processo
Esecuzione di un Comando:

Il sistema riceve un comando NonUndoableCommand.
Il comando viene adattato tramite CommandAdapter.
L'adattatore viene gestito da historyHandler che esegue il comando e lo aggiunge alla storia se è undoable.
Annullamento di un Comando:

Quando viene chiamato il metodo undo, il historyHandler annulla l'ultimo comando eseguito.
Rifacimento di un Comando:

Quando viene chiamato il metodo redo, il historyHandler rifà l'ultimo comando annullato.
 */
