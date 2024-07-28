package MiniCAD.interpreter.commands;


import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.Posizione;
import MiniCAD.interpreter.utilExpr.TypeConstructor;
import MiniCAD.util.InterpreterCommandAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UndoableCommandTest {

    private Context context;
    private CreateCommand cc;
    private CommandIF createCommand;
    private InterpreterCommandAdapter adapter;
    private String objectId;

    @BeforeEach
    public void setup() {
        context = new Context();
        TypeConstructor tc = new TypeConstructor.CircleConstructor(5.0F);
        Posizione pos = new Posizione(3.1F, 4.5F);
        createCommand =  new CreateCommand(tc, pos);
        adapter = new InterpreterCommandAdapter(createCommand, context);

    }

    @Test
    public void testDoAndUndo() {
        Boolean eseguito = adapter.doIt();
        objectId = (String) adapter.getRes();
        assertNotNull(objectId, "ID dell'oggetto creato non dovrebbe essere nullo");

        // Verifica che l'oggetto sia stato aggiunto al contesto
        assertTrue(context.getAllObjects().containsKey(objectId), "L'oggetto creato non dovrebbe essere presente nel contesto");

        // Annulla il comando
        boolean undoResult = adapter.undoIt();
        assertTrue(undoResult, "L'operazione di undo dovrebbe restituire true");

        // Verifica che l'oggetto sia stato rimosso dal contesto
        assertFalse(context.getAllObjects().containsKey(objectId), "L'oggetto dovrebbe essere stato rimosso dal contesto");
    }
}
