package MiniCAD.ui;
import MiniCAD.controllers.MiniCADController;
import ObserverCommandFlyweight.is.command.HistoryCommandHandler;
import ObserverCommandFlyweight.is.shapes.model.*;
import ObserverCommandFlyweight.is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MiniCAD {

    public static void main(String[] args) {
        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");
        final HistoryCommandHandler handler = new HistoryCommandHandler();

        undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));
        redoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.REDO));

        toolbar.add(undoButt);
        toolbar.add(redoButt);

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();

        gpanel.setPreferredSize(new Dimension(500, 500));

        gpanel.installView(RectangleObject.class, new RectangleObjectView());
        gpanel.installView(CircleObject.class, new CircleObjectView());
        gpanel.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(0, 0), 20, 50);

        JButton rectButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        go = new CircleObject(new Point(200, 100), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        go = new CircleObject(new Point(200, 100), 100);
        JButton circButton2 = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton2.setText("big " + go.getType());
        toolbar.add(circButton2);

        go = new ImageObject(new ImageIcon(MiniCAD.class.getResource("NyaNya.gif")),
                new Point(240, 187));
        JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

        final MiniCADController goc = new MiniCADController(handler);

        gpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });

        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        //JPanel commandPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        //commandPanel.add(buttons);
        f.setTitle("MiniCAD App");
        f.getContentPane().add(controlPanel, BorderLayout.EAST);
        //f.getContentPane().add(commandPanel, BorderLayout.EAST)
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
