package MiniCAD.interpreter.commands;

import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.RemoveCommand;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.Token;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.TokenType;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.view.GraphicObjectPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveUndoTest {
    private Context context;
    private GraphicObjectPanel panel;

    @BeforeEach
    public void setUp() {
        panel = new GraphicObjectPanel();
        context = new Context(panel);

        // Add an object to the context for testing
        GraphicObject go = new CircleObject(new Point2D.Double(0, 0), 10);
        String id = context.generaId();
        context.addObject(id, go);
    }

    @Test
    public void testUndo() {
        // RemoveCommand should remove the object from context
        String id = context.getAllObjects().keySet().iterator().next();
        Token idToken = new Token(TokenType.OBJ_ID, id);
        RemoveCommand removeCmd = new RemoveCommand(idToken);
        removeCmd.interpreta(context);

        // Assert that the object is removed
        assertNull(context.getObjectbyId(id));

        // Perform undo operation
        removeCmd.undo(context);

        // Assert that the object is restored
        GraphicObject restoredObject = context.getObjectbyId(id);
        assertNotNull(restoredObject);
        assertTrue(restoredObject instanceof CircleObject);
    }
}