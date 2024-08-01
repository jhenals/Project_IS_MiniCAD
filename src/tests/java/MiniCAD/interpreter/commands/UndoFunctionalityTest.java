package MiniCAD.interpreter.commands;

import MiniCAD.shapes.interpreter.Context;
import MiniCAD.command.InterpreterCommandAdapter;
import MiniCAD.shapes.interpreter.commands.CreateCommand;
import MiniCAD.shapes.interpreter.commands.UndoableCmdExprIF;
import MiniCAD.shapes.interpreter.utilExpr.PosizioneExpr;
import MiniCAD.shapes.interpreter.utilExpr.TypeConstructorExpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void testUndo() {
        TypeConstructorExpr.CircleConstructor circleConstructor = new TypeConstructorExpr.CircleConstructor(5.0f);
        PosizioneExpr posizione = new PosizioneExpr(3.1f, 4.5f);
        UndoableCmdExprIF createCommand = new CreateCommand(circleConstructor, posizione);
        adapter = new InterpreterCommandAdapter(createCommand, context);

        adapter.doIt();
        assertTrue(createCommand.undo(context));
    }


}
