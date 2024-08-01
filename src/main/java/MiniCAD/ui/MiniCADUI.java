package MiniCAD.ui;

import MiniCAD.command.MiniCadCommandHandler;
import MiniCAD.command.MiniCadHistoryCmdHandler;
import MiniCAD.shapes.controllers.MiniCADController;
import MiniCAD.shapes.interpreter.Context;
import MiniCAD.shapes.interpreter.lexerparser.CommandParser;
import MiniCAD.shapes.view.CreateObjectActionMiniCad;
import ObserverCommandFlyweight.is.shapes.model.*;
import ObserverCommandFlyweight.is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MiniCADUI {

    public static void main(String[] args)  {
        CommandParser parser = new CommandParser();

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        Context context = new Context(gpanel);
        final MiniCadHistoryCmdHandler handler = new MiniCadHistoryCmdHandler(context);

        JFrame f = new JFrame();
        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");
        undoButt.addActionListener(evt -> handler.handle(MiniCadHistoryCmdHandler.NonExecutableCommands.UNDO));
        redoButt.addActionListener(evt -> handler.handle(MiniCadHistoryCmdHandler.NonExecutableCommands.REDO));
        toolbar.add(undoButt);
        toolbar.add(redoButt);

        gpanel.setPreferredSize(new Dimension(500, 500));
        gpanel.installView(RectangleObject.class, new RectangleObjectView());
        gpanel.installView(CircleObject.class, new CircleObjectView());
        gpanel.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(0, 0), 20, 50);

        toolbar.add(createObjectButton("Rectangle", go, gpanel, handler, context, parser));
        toolbar.add(createObjectButton("Circle", new CircleObject(new Point(200, 100), 10), gpanel, handler, context, parser));
        toolbar.add(createObjectButton("Image",new ImageObject(new ImageIcon(Objects.requireNonNull(MiniCADUI.class.getResource("NyaNya.gif"))),
                (new Point(200, 100))), gpanel, handler, context, parser));


        final MiniCADController goc = new MiniCADController(handler,context);

        gpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GraphicObject go = gpanel.getGraphicObjectAt(e.getPoint());
                if( go != null ){
                    String id = context.getIdByClickedObject(go);
                    System.out.println(id);
                    goc.setControlledObject(go, id);
                }
            }
        });

        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        f.setTitle("MiniCADUI App");
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
} //MiniCADUI


  /*
        String cmdInput = "create rectangle ("+go.getDimension().getHeight()+","+go.getDimension().getWidth()+") ("+go.getPosition().getX()+","+go.getPosition().getY()+")";

        switch (go.getType()){
            case "Rectangle" ->{
                cmdInput = "create rectangle ("+go.getDimension().getHeight()+","+go.getDimension().getWidth()+") ("+go.getPosition().getX()+","+go.getPosition().getY()+")";
            }
            case  "Circle" ->{
                CircleObject circ = (CircleObject) go;
                cmdInput = "create circle ("+circ.getRadius()+") ("+ circ.getPosition()+")";
            }
            case "Image" ->{
                ImageObject img = (ImageObject) go;
                cmdInput = "create img (\"NyaNya.gif\") "+ img.getPosition();
            }
        }

        JButton rectButton = new JButton(new CreateObjectActionMiniCad(go, gpanel, handler, context, parser, cmdInput));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        go = new CircleObject(new Point(200, 100), 10);
        JButton circButton = new JButton(new CreateObjectActionMiniCad(go, gpanel, handler, context, parser, cmdInput));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        go = new CircleObject(new Point(200, 100), 100);
        JButton circButton2 = new JButton(new CreateObjectActionMiniCad(go, gpanel, handler, context, parser, cmdInput));
        circButton2.setText("big " + go.getType());
        toolbar.add(circButton2);

        toolbar.add(createObjectButton("Rectangle", new RectangleObject(new
                Point(0,0), 20, 50)));

        go = new ImageObject(new ImageIcon(MiniCADUI.class.getResource("NyaNya.gif")),
                new Point(240, 187));
        JButton imgButton = new JButton(new CreateObjectActionMiniCad(go, gpanel, handler, context, parser, cmdInput));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

         */