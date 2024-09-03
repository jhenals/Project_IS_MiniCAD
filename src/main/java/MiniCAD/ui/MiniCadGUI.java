package MiniCAD.ui;

import MiniCAD.miniinterpreter.controller.undoMngr.MiniCadCommandHandler;
import MiniCAD.miniinterpreter.controller.undoMngr.MiniCadHistoryCmdHandler;
import MiniCAD.miniinterpreter.controller.MiniCADController;
import MiniCAD.miniinterpreter.specificCmds.Context;
import MiniCAD.miniinterpreter.specificCmds.lexerparser.CommandParser;
import MiniCAD.miniinterpreter.view.CreateObjectActionMiniCad;
import ObserverCommandFlyweight.is.shapes.model.*;
import ObserverCommandFlyweight.is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MiniCadGUI {

    public static void main(String[] args)  {
        CommandParser parser = new CommandParser();

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        Context context = new Context(gpanel);
        final MiniCadHistoryCmdHandler handler = new MiniCadHistoryCmdHandler(context);

        JFrame f = new JFrame();
        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        undoButt.addActionListener(evt -> handler.handle(MiniCadHistoryCmdHandler.NonExecutableCommands.UNDO));
        toolbar.add(undoButt);

        gpanel.setPreferredSize(new Dimension(500, 500));
        gpanel.installView(RectangleObject.class, new RectangleObjectView());
        gpanel.installView(CircleObject.class, new CircleObjectView());
        gpanel.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(0, 0), 20, 50);


        toolbar.add(new JLabel("SHAPES"));
        toolbar.add(createObjectButton("Rectangle", go, gpanel, handler, context, parser));
        toolbar.add(createObjectButton("Circle", new CircleObject(new Point(0, 0), 10), gpanel, handler, context, parser));
        toolbar.add(createObjectButton("Image",new ImageObject(new ImageIcon(Objects.requireNonNull(MiniCadGUI.class.getResource("NyaNya.gif"))),
                (new Point(0, 0))), gpanel, handler, context, parser));


        final MiniCADController goc = new MiniCADController(handler,context);

        gpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GraphicObject go = gpanel.getGraphicObjectAt(e.getPoint());
                if( go != null ){
                    String id = context.getIdOfClickedObject(go);
                    System.out.println(id);
                    goc.setControlledObject(go, id);
                }
            }
        });

        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        f.setTitle("MiniCAD App");
        f.getContentPane().add(controlPanel, BorderLayout.EAST);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    } //main

    private static JButton createObjectButton(String type, AbstractGraphicObject go, GraphicObjectPanel gpanel, MiniCadCommandHandler handler, Context context, CommandParser parser) {
        String cmdInput = "";
        switch (go.getType()){
            case "Rectangle" -> cmdInput = "create rectangle ("+ go.getDimension().getHeight()+","+go.getDimension().getWidth()+") ("+ go.getPosition().getX()+","+go.getPosition().getY()+")";
            case "Circle" -> {
                CircleObject circ = (CircleObject) go;
                cmdInput = "create circle (" + circ.getRadius() + ") (" + circ.getPosition().getX() + "," + circ.getPosition().getY() + ")";
            }
            case "Image" -> {
                ImageObject img = (ImageObject) go;
                cmdInput = "create img (\"NyaNya.gif\") (" +img.getPosition().getX() + "," + img.getPosition().getY()+")";
            }
        }
        JButton button = new JButton(new CreateObjectActionMiniCad(go, gpanel, handler, context, parser, cmdInput));
        button.setText(type);
        return button;
    }
} //MiniCadGUI
