package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.model.GroupObject;
import MiniCAD.miniinterpreter.specificCmds.commandsExpr.CommandExprIF;
import MiniCAD.miniinterpreter.specificCmds.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerimeterCommandTest {

    private Context context;
    private CommandParser parser;
    private CommandExprIF command;

    @BeforeEach
    void setUp() {
        context = new Context();
        parser = new CommandParser();

        Point2D pos = new Point2D.Double(3.1, 4.5);
        CircleObject circleObject = new CircleObject(pos, 5);
        context.addObject("id0", circleObject);
    }

    @Test
    void perimeterSingleObjectCommandTest() throws ParseException, IOException {
        String input = "perimeter id0";
        command = parser.parseCommand(input);
        String perim = (String) command.interpreta(context);

        assertNotNull("id0");

        GraphicObject go = context.getObjectbyId("id0");

        assertTrue(go instanceof CircleObject);
        CircleObject circle = (CircleObject)go;

        double expectedPerimeter = 2 * Math.PI * circle.getRadius();
        double actualPerimeter = Double.parseDouble(perim);
        double delta = 1e-7;
        assertEquals(expectedPerimeter,actualPerimeter, delta);
    }


    @Test
    void perimeterCommandTypeTest() throws ParseException, IOException {
        String input = "perimeter circle";
        command = parser.parseCommand(input);
        String perim = (String) command.interpreta(context);

        assertNotNull("id0");

        GraphicObject go = context.getObjectbyId("id0");

        assertTrue(go instanceof CircleObject);
        CircleObject circle = (CircleObject)go;

        double expectedPerimeter = 2 * Math.PI * circle.getRadius();
        double actualPerimeter = Double.parseDouble(perim);
        double delta = 1e-7;
        assertEquals(expectedPerimeter,actualPerimeter, delta);
    }

    @Test
    void perimeterCommandGroupTest() throws ParseException, IOException {
        Point2D pos2 = new Point2D.Double(6.1, 4.6);
        CircleObject circleObject1 = new CircleObject(pos2, 2);
        context.addObject("id1", circleObject1);

        Point2D pos3 = new Point2D.Double(3.0, 4.0);
        CircleObject circleObject2 = new CircleObject(pos3, 3);
        context.addObject("id2", circleObject2);

        GroupObject groupObject = context.createGroup(List.of("id1", "id2"));
        String gid = groupObject.getGroupId();

        String input = "perimeter "+ gid;
        command = parser.parseCommand(input);
        String perim = (String) command.interpreta(context);

        assertNotNull(gid);

        GraphicObject go = context.getObjectbyId(gid);

        assertTrue(go instanceof GroupObject);
        GroupObject group = (GroupObject)go;

        CircleObject circ1 = (CircleObject) context.getObjectbyId("id1");
        double perim1 = 2 * Math.PI * circ1.getRadius();

        CircleObject circ2 = (CircleObject) context.getObjectbyId("id2");
        double perim2 = 2 * Math.PI * circ2.getRadius();

        double expectedPerimeter= perim1 + perim2;
        double actualPerimeter = Double.parseDouble(perim);
        double delta = 1e-7;
        assertEquals(expectedPerimeter,actualPerimeter, delta);
    }

}