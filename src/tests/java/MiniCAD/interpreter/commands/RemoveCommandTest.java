package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.GroupObject;
import MiniCAD.interpreter.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCommandTest {

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

        Point2D pos2 = new Point2D.Double(6.1, 4.6);
        ImageObject imageObject = new ImageObject(new ImageIcon("./pippo.png"), pos2);
        context.addObject("id1", imageObject);

        Point2D pos3 = new Point2D.Double(3.0, 4.0);
        CircleObject circleObject2 = new CircleObject(pos3, 3);
        context.addObject("id2", circleObject2);

        GroupObject groupObject = context.createGroup(List.of("id1", "id2"));
        gid = groupObject.getGroupId();
    }

    @DisplayName("Remove single object test successful")
    @Test
    void testRemoveSingleObject() throws ParseException, IOException {
        String input = "del id0";
        command = parser.parseCommand(input);
        String res = (String) command.interpreta(context);

        assertEquals("Rimosso: id0", res);
    }

    @DisplayName("Remove group test successful")
    @Test
    void testRemoveGroup() throws ParseException, IOException {
        String input = "del "+ gid;
        command = parser.parseCommand(input);
        String res = (String) command.interpreta(context);

        assertEquals("Rimosso: "+gid, res);
    }
}