package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.GroupObject;
import MiniCAD.shapes.interpreter.commands.CommandExprIF;
import MiniCAD.shapes.interpreter.lexerparser.CommandParser;
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

class MoveCommandTest {

    private Context context;
    private CommandParser parser;
    CommandExprIF command;
    String gid;

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

    @DisplayName("Move single object test successful")
    @Test
    void testInterpretaMoveSingleObject() throws ParseException, IOException {
        String inputMove = "mv id0 (5.9, 8.2)";
        command = parser.parseCommand(inputMove);
        String res = (String) command.interpreta(context);

        assertEquals("Oggetto con id= id0 viene spostato alla posizione (5.9, 8.2)", res);
    }

    @DisplayName("MoveOff single object test successful")
    @Test
    void testInterpretaMoveOffSingleObject() throws ParseException, IOException {
        String inputMoveOff = "mvoff "+gid+" (2, 2)";
        command = parser.parseCommand(inputMoveOff);
        String res = (String) command.interpreta(context);

        GraphicObject go = context.getObjectbyId("id0");
        double nuovoX = go.getPosition().getX();
        double nuovoY = go.getPosition().getY() ;
        assertEquals("Nuova posizione: ("+nuovoX+","+nuovoY+")", res);
    }

    @DisplayName("Move group object test successful")
    @Test
    void testInterpretaMoveGroup() throws ParseException, IOException {
        String inputMoveGrp = "mv id0 (5.9, 8.2)";
        command = parser.parseCommand(inputMoveGrp);
        String res = (String) command.interpreta(context);

        assertEquals("Oggetto con id= "+gid+" viene spostato alla posizione (5.9, 8.2)", res);
    }
}