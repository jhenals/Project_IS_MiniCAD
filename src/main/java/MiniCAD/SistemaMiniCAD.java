package MiniCAD;

import MiniCAD.interpreter.commands.UndoableCommand;
import MiniCAD.util.InterpreterCommandAdapter;
import ObserverCommandFlyweight.is.command.HistoryCommandHandler;

public class SistemaMiniCAD {
    private final HistoryCommandHandler historyCommandHandler = new HistoryCommandHandler();
    /*
    Questo attributo rappresenta un'istanza del gestore di comandi storici (HistoryCommandHandler), responsabile di mantenere la storia dei comandi eseguiti e gestire le operazioni di undo e redo.
     */

    public void esegueComando(UndoableCommand comando){
        InterpreterCommandAdapter adapter = new InterpreterCommandAdapter(comando);
        historyCommandHandler.handle(adapter);
    }

    /*
    Questo metodo accetta un comando che implementa l'interfaccia UndoableCommand e lo esegue attraverso l'handler dei comandi storici. Ecco come funziona:

Viene creato un adattatore (CommandAdapter) per il comando passato come argomento.
L'adattatore viene passato al historyHandler per essere gestito. Questo gestore esegue il comando e lo aggiunge alla storia se è undoable.
     */

    public void undo(){
        historyCommandHandler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO);
    }

    /*
    Questo metodo avvia un'operazione di undo utilizzando il historyHandler.

Il comando UNDO viene passato al gestore, che annulla l'ultimo comando eseguito se esiste.
     */

    public void redo(){
        historyCommandHandler.handle(HistoryCommandHandler.NonExecutableCommands.REDO);
    }

    /*
    Il comando REDO viene passato al gestore, che rifà l'ultimo comando annullato se esiste.
     */
}


/*
Schema del Processo
Esecuzione di un Comando:

Il sistema riceve un comando UndoableCommand.
Il comando viene adattato tramite CommandAdapter.
L'adattatore viene gestito da historyHandler che esegue il comando e lo aggiunge alla storia se è undoable.
Annullamento di un Comando:

Quando viene chiamato il metodo undo, il historyHandler annulla l'ultimo comando eseguito.
Rifacimento di un Comando:

Quando viene chiamato il metodo redo, il historyHandler rifà l'ultimo comando annullato.
 */
