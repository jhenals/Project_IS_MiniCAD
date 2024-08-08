package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.mvc.specificCmds.Context;
import MiniCAD.mvc.model.GroupObject;
import MiniCAD.mvc.specificCmds.commandsExpr.CommandExprIF;
import MiniCAD.mvc.specificCmds.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupCommandTest {

    private Context context;
    private CommandParser parser;

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
    }

    @DisplayName("Group command test successful")
    @Test
    void interpreta() throws ParseException, IOException {
        String input = "grp id0, id1, id2";
        CommandExprIF command = parser.parseCommand(input);
        String res = (String) command.interpreta(context);

        GraphicObject go = context.getObjectbyId(res);

        assertNotNull(res);

        assertTrue(go instanceof GroupObject);
        List<String> ids = ((GroupObject) go).getObjectIds();
        assertEquals( ids,List.of("id0", "id2", "id1"));
    }
}