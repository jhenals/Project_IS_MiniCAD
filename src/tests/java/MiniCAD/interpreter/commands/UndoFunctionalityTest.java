package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.utilExpr.*;
import MiniCAD.util.InterpreterCommandAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UndoFunctionalityTest {

    private Context context;
    private InterpreterCommandAdapter adapter;
    @BeforeEach
    void setUp() {
        context = new Context();
    }

    @DisplayName("Undo functionality test successful")
    @Test
    void testUndo() throws ParseException, IOException {
        TypeConstructorExpr.CircleConstructor circleConstructor = new TypeConstructorExpr.CircleConstructor(5.0f);
        PosizioneExpr posizione = new PosizioneExpr(3.1f, 4.5f);
        UndoableCmdExprIF createCommand = new CreateCommand(circleConstructor, posizione);
        adapter = new InterpreterCommandAdapter(createCommand, context);

        adapter.doIt();
        assertTrue(createCommand.undo(context));
    }


}
