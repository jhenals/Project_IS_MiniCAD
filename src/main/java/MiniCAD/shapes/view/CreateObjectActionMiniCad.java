package MiniCAD.shapes.view;


import MiniCAD.command.MiniCadCommandHandler;
import MiniCAD.exceptions.ParseException;
import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.commands.UndoableCmdExprIF;
import MiniCAD.shapes.interpreter.lexerparser.CommandParser;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.view.GraphicObjectPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CreateObjectActionMiniCad extends AbstractAction {
    AbstractGraphicObject prototype;
    GraphicObjectPanel panel;
    MiniCadCommandHandler ch;
    Context context;
    CommandParser commandParser;
    String input;

    public CreateObjectActionMiniCad(AbstractGraphicObject prototype,
                                     GraphicObjectPanel panel, MiniCadCommandHandler cmdH, Context context,
                                     CommandParser cParser, String input) {
        super();
        this.prototype = prototype;
        this.panel = panel;
        this.input = input;
        this.context = context;
        ch = cmdH;
        commandParser = cParser;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ch.handle((UndoableCmdExprIF) commandParser.parseCommand(input));
        } catch (ParseException | IOException ex) {
            throw new RuntimeException(ex);
        }

        //GraphicObject go = prototype.clone();
       //ch.handle(new NewObjectCmd(panel, go));
    }
}
