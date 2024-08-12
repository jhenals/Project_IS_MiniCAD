package MiniCAD.interpreter.commands;

import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.controller.undoMngr.InterpreterCommandAdapter;
import MiniCAD.mvc.specificCmds.commandsExpr.CreateCommand;
import MiniCAD.mvc.specificCmds.commandsExpr.UndoableCmdExprIF;
import MiniCAD.mvc.specificCmds.utilExpr.PosizioneExpr;
import MiniCAD.mvc.specificCmds.utilExpr.Token;
import MiniCAD.mvc.specificCmds.utilExpr.TokenType;
import MiniCAD.mvc.specificCmds.utilExpr.TypeConstructorExpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UndoFunctionalityTest {

    private Context context;

    @BeforeEach
    void setUp() {
        context = new Context();
    }

    @DisplayName("Undo functionality test successful")
    @Test
    void testUndo() {
        Token raggio = new Token(TokenType.POS_FLOAT, 5.0f);
        TypeConstructorExpr.CircleConstructor circleConstructor = new TypeConstructorExpr.CircleConstructor(raggio);
        Token p1 = new Token(TokenType.POS_FLOAT, 3.1f);
        Token p2 = new Token(TokenType.POS_FLOAT, 4.5f);
        PosizioneExpr posizione = new PosizioneExpr(p1, p2);
        UndoableCmdExprIF createCommand = new CreateCommand(circleConstructor, posizione);
        InterpreterCommandAdapter adapter = new InterpreterCommandAdapter(createCommand, context);

        adapter.doIt();
        assertTrue(createCommand.undo(context));
    }


}
