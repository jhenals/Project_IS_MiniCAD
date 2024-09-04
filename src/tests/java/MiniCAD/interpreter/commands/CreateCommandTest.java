package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CommandExprIF;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CreateCommand;
import MiniCAD.miniinterpreter.specificCmds.lexerparser.CommandParser;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.PosizioneExpr;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.Token;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.TokenType;
import MiniCAD.miniinterpreter.specificCmds.utilExpr.TypeConstructorExpr;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import ObserverCommandFlyweight.is.shapes.model.RectangleObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateCommandTest {
    private Context context;
    private CommandParser parser;
    private CommandExprIF command;

    @BeforeEach
    void setUp() {
        context = new Context();
        parser = new CommandParser();
    }

    @DisplayName("Create circle test successful")
    @Test
    void testInterpretaCircleConstructor() throws ParseException, IOException {
        String input = "create circle (5.0) (10.0,10.0)";
        command = parser.parseCommand(input);
        String objId = (String) command.interpreta(context);

        GraphicObject go = context.getObjectbyId(objId);

        assertNotNull(objId);

        assertTrue(go instanceof CircleObject);
        CircleObject circle = (CircleObject) go;
        assertEquals(5.0F, circle.getRadius());

        Point2D.Double expected = new Point2D.Double(10, 10);
        Point2D.Double actual = (Point2D.Double) circle.getPosition();
        double delta = 1e-7;
        assertEquals(expected.getX(), actual.getX(), delta);
        assertEquals(expected.getY(), actual.getY(), delta);
    }

    @DisplayName("Create rectangle test successful")
    @Test
    void testInterpretaRectangleConstructor() throws ParseException, IOException {
        String input = "create rectangle (2.0, 3.0) (10.0,10.0)";
        command = parser.parseCommand(input);
        String objId = (String) command.interpreta(context);

        GraphicObject go = context.getObjectbyId(objId);

        assertNotNull(objId);

        assertTrue(go instanceof RectangleObject);
        RectangleObject rect = (RectangleObject) go;
        assertEquals(2.0F, rect.getDimension().getWidth());
        assertEquals(3.0F, rect.getDimension().getHeight());

        Point2D.Double expected = new Point2D.Double(10.0, 10.0);
        Point2D.Double actual = (Point2D.Double) rect.getPosition();
        double delta = 1e-7;  // Tolerance level for floating-point comparison
        assertEquals(expected.getX(), actual.getX(), delta);
        assertEquals(expected.getY(), actual.getY(), delta);
    }

    @DisplayName("Create image test successful")
    @Test
    void testInterpretaImageConstructor() throws ParseException, IOException {
        String input = "create img (\"./pippo.png\") (10.0,10.0)";
        command = parser.parseCommand(input);
        String objId = (String) command.interpreta(context);

        GraphicObject go = context.getObjectbyId(objId);
        assertNotNull(objId);

        assertTrue(go instanceof ImageObject);
        ImageObject img = (ImageObject) go;

        Point2D.Double expected = new Point2D.Double(10.0, 10.0);
        Point2D.Double actual = (Point2D.Double) img.getPosition();
        double delta = 1e-7;  // Tolerance level for floating-point comparison
        assertEquals(expected.getX(), actual.getX(), delta);
        assertEquals(expected.getY(), actual.getY(), delta);
    }


    @DisplayName("Undo create circle command")
    @Test
    void testUndoCreateCircleCommand()  {
        TypeConstructorExpr.CircleConstructor circleConstructor = new TypeConstructorExpr.CircleConstructor(new Token(TokenType.POS_FLOAT, 5f));
        PosizioneExpr pos = new PosizioneExpr(new Token(TokenType.POS_FLOAT, 10f), new Token(TokenType.POS_FLOAT, 10f));
        CreateCommand createCommand = new CreateCommand(circleConstructor, pos);
        String objId = createCommand.interpreta(context);

        GraphicObject go = context.getObjectbyId(objId);
        assertNotNull(go);
        assertTrue(go instanceof CircleObject);

        createCommand.undo(context);

        GraphicObject removedGo = context.getObjectbyId(objId);
        assertNull(removedGo);
    }


}