package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.GroupObject;
import MiniCAD.interpreter.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AreaCommandTest {

    private Context context;
    private CommandParser parser;
    private CommandExprIF command;
    private String gid;


    @BeforeEach
    void setUp() {
        context = new Context();
        parser = new CommandParser();

        Point2D pos = new Point2D.Double(3.1, 4.5);
        CircleObject circleObject = new CircleObject(pos, 5);
        context.addObject("id0", circleObject);
    }

    @Test
    void areaSingleObjectCommandTest() throws ParseException, IOException {
        String input = "area id0";
        command = parser.parseCommand(input);
        String area = (String) command.interpreta(context);

        assertNotNull("id0");

        GraphicObject go = context.getObjectbyId("id0");

        assertTrue(go instanceof CircleObject);
        CircleObject circle = (CircleObject)go;

        double expectedArea = Math.PI * circle.getRadius()* circle.getRadius();
        double actualArea = Double.parseDouble(area);
        double delta = 1e-7;
        assertEquals(expectedArea,actualArea, delta);
    }


    @Test
    void areaCommandTypeTest() throws ParseException, IOException {
        String input = "area circle";
        command = parser.parseCommand(input);
        String area = (String) command.interpreta(context);

        assertNotNull("id0");

        GraphicObject go = context.getObjectbyId("id0");

        assertTrue(go instanceof CircleObject);
        CircleObject circle = (CircleObject)go;

        double expectedArea = Math.PI * circle.getRadius() * circle.getRadius() ;
        double actualArea = Double.parseDouble(area);
        double delta = 1e-7;
        assertEquals(expectedArea,actualArea, delta);
    }

    @Test
    void areaCommandGroupTest() throws ParseException, IOException {
        Point2D pos2 = new Point2D.Double(6.1, 4.6);
        CircleObject circleObject1 = new CircleObject(pos2, 2);
        context.addObject("id1", circleObject1);

        Point2D pos3 = new Point2D.Double(3.0, 4.0);
        CircleObject circleObject2 = new CircleObject(pos3, 3);
        context.addObject("id2", circleObject2);

        GroupObject groupObject = context.createGroup(List.of("id1", "id2"));
        gid = groupObject.getGroupId();

        String input = "area "+gid;
        command = parser.parseCommand(input);
        String area = (String) command.interpreta(context);

        assertNotNull(gid);

        GraphicObject go = context.getObjectbyId(gid);

        assertTrue(go instanceof GroupObject);
        GroupObject group = (GroupObject)go;

        CircleObject circ1 = (CircleObject) context.getObjectbyId("id1");
        double perim1 = Math.PI * circ1.getRadius() * circ1.getRadius();

        CircleObject circ2 = (CircleObject) context.getObjectbyId("id2");
        double perim2 = Math.PI * circ2.getRadius() * circ2.getRadius();

        double expectedArea= perim1 + perim2;
        double actualArea = Double.parseDouble(area);
        double delta = 1e-7;
        assertEquals(expectedArea,actualArea, delta);
    }
}