package MiniCAD.interpreter.commands;

import MiniCAD.exceptions.ParseException;
import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.model.GroupObject;
import MiniCAD.shapes.interpreter.commandsExpr.CommandExprIF;
import MiniCAD.shapes.interpreter.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScaleCommandTest {
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
        context.addObject("id1", circleObject);

        Point2D pos2 = new Point2D.Double(6.1, 4.6);
        ImageObject imageObject = new ImageObject(new ImageIcon("./pippo.png"), pos2);
        context.addObject("id2", imageObject);

        GroupObject groupObject = context.createGroup(List.of("id1", "id2"));
        gid = groupObject.getGroupId();
    }

    @DisplayName("Scale test of single object successful")
    @Test
    void testGroupCommandSingleObject() throws ParseException, IOException {
        String input = "scale id1 2.0";
        command = parser.parseCommand(input);
        String res = (String) command.interpreta(context);

        assertEquals(res, "Nuova dimensione= "+5*2D);
    }

    @DisplayName("Scale test of group object successful")
    @Test
    void testScaleCommandSingleObject() throws ParseException, IOException {
        String input = "scale "+gid+" 2.0";
        command = parser.parseCommand(input);
        String res = (String) command.interpreta(context);

        GroupObject groupObject = (GroupObject) context.getObjectbyId(gid);
        Dimension2D dim = groupObject.getDimension();
        double dimX = dim.getWidth();
        double dimY = dim.getHeight();
        assertEquals(res, "Nuova dimensione= ("+ dimX+ ","+ dimY+ ")" );
    }


}