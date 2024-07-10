package MiniCAD.controllers;

import MiniCAD.util.NumericDocumentFilter;
import ObserverCommandFlyweight.is.command.CommandHandler;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.specificcommand.MoveCommand;
import ObserverCommandFlyweight.is.shapes.specificcommand.ZoomCommand;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serial;

public class MiniCADController extends JPanel {
    @Serial
    private static final long serialVersionUID = -1200564294398210114L;
    private final CommandHandler cmdHandler;
    private GraphicObject subject;
    static final int offset = 10;
    static final double zoom_factor= 0.1;


    public void setControlledObject(GraphicObject go) { subject = go; }
    public MiniCADController(CommandHandler cmdH) {
        this(null, cmdH);
    }

    public MiniCADController(GraphicObject go, CommandHandler cmdH){
        cmdHandler = cmdH;
        subject = go;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel commandPanel = new JPanel();
        commandPanel.setPreferredSize(new Dimension(300, 400));
        commandPanel.setBorder(BorderFactory.createTitledBorder("Commands"));

        commandPanel.add(zoomMovePanel());
        commandPanel.add(moveOffPanel());
        commandPanel.add(commandButtonsPanel());
        commandPanel.add(geomOperationsPanel());

        JPanel propViewerPanel = new JPanel();
        propViewerPanel.setBorder(BorderFactory.createTitledBorder("Properties"));
        propViewerPanel.add(new JScrollPane(propertiesViewer()));

        add(commandPanel);
        add(propViewerPanel);
    }


    private JPanel zoomMovePanel(){
        JPanel zoomMovePanel = new JPanel();
        JPanel grid = new JPanel(new GridLayout(3, 3));
        JPanel zoom = new JPanel(new GridLayout(1, 2));

        JButton minus = new JButton("-");

        minus.addActionListener(e -> {
            if (subject != null) {
                cmdHandler.handle(new ZoomCommand(subject, 1.0 - zoom_factor));
            }
        });

        zoom.add(minus);

        JButton plus = new JButton("+");
        plus.addActionListener(e -> {
            if (subject != null) {
                cmdHandler.handle(new ZoomCommand(subject, 1.0 + zoom_factor));
            }
        });

        zoom.add(plus);
        zoom.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton nw = new JButton("\\");

        nw.addActionListener(e -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() - offset, p.getY() - offset)));
        });
        grid.add(nw);

        JButton n = new JButton("|");
        n.addActionListener(e -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX(), p.getY() - offset)));
        });

        grid.add(n);

        JButton ne = new JButton("/");
        ne.addActionListener(e -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY() - offset)));

        });

        grid.add(ne);

        JButton w = new JButton("<-");
        w.addActionListener(e -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() - offset, p.getY())));
        });

        grid.add(w);

        grid.add(zoom);

        JButton e = new JButton("->");
        e.addActionListener(e1 -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY())));
        });

        grid.add(e);

        JButton sw = new JButton("/");

        sw.addActionListener(e12 -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() - offset, p.getY() + offset)));
        });
        grid.add(sw);

        JButton s = new JButton("|");
        s.addActionListener(e13 -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX(), p.getY() + offset)));
        });

        grid.add(s);

        JButton se = new JButton("\\");
        se.addActionListener(e14 -> {
            if (subject == null)
                return;
            Point2D p = subject.getPosition();
            cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY() + offset)));
        });
        grid.add(se);
        zoomMovePanel.add(grid);
        return zoomMovePanel;
    }


    private JPanel moveOffPanel() {

        JTextField  newXAxisField = new JTextField();
        newXAxisField.setPreferredSize(new Dimension(50, 20));
        setNumericFilter(newXAxisField);


        JTextField  newYAxisField = new JTextField();
        newYAxisField.setPreferredSize(new Dimension(50, 20));
        setNumericFilter(newYAxisField);

        JButton sposta = new JButton("Move");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("X-Axis:"));
        inputPanel.add(newXAxisField);
        inputPanel.add(new JLabel("Y-Axis:"));
        inputPanel.add(newYAxisField);
        inputPanel.add(sposta);

        return inputPanel;
    }

    private static void setNumericFilter(JTextField textField){
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
    }

    private JPanel commandButtonsPanel() {
        JPanel cmdButtonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();

        //Buttons
        JButton deleteButton = new JButton("Delete");
        JButton groupButton = new JButton("Group");
        JButton ungroupButton = new JButton("Ungroup");
        JButton viewProperty = new JButton("View Property");

        JMenuBar menuBar = new JMenuBar();
        JMenu advancePropertyMenu = new JMenu("Advance View");
        JMenuItem imgItem = new JMenuItem("View all images");
        JMenuItem rectItem = new JMenuItem("View all rectangles");
        JMenuItem circleItem = new JMenuItem("View all circles");
        JMenuItem groupItem = new JMenuItem("View all groups");
        JMenuItem allItem = new JMenuItem("View all");

        /*
        lineItem.addActionListener(e -> currentAction = "Line");
        rectItem.addActionListener(e -> currentAction = "Rectangle");
        circleItem.addActionListener(e -> currentAction = "Circle");

         */

        advancePropertyMenu.add(imgItem);
        advancePropertyMenu.add(rectItem);
        advancePropertyMenu.add(circleItem);
        advancePropertyMenu.add(groupItem);
        advancePropertyMenu.add(allItem);
        menuBar.add(advancePropertyMenu);

        //constraints
        c.gridx=0;
        c.gridy=0;
        c.gridwidth= 2;
        c.fill= GridBagConstraints.HORIZONTAL;
        cmdButtonsPanel.add(deleteButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        cmdButtonsPanel.add(groupButton, c);

        c.gridx = 1;
        c.gridy = 1;
        cmdButtonsPanel.add(ungroupButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth= 2;
        c.fill= GridBagConstraints.HORIZONTAL;
        cmdButtonsPanel.add(viewProperty, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth= 2;
        c.fill= GridBagConstraints.HORIZONTAL;
        cmdButtonsPanel.add(menuBar, c);

        return cmdButtonsPanel;
    }


    private JPanel geomOperationsPanel() {
        JPanel areaPerim = new JPanel(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();

        areaPerim.setBorder(BorderFactory.createTitledBorder("Operations"));
        JButton area = new JButton("Area");
        JButton perimeter = new JButton("Perimeter");

        JMenuBar areaMenuBar = new JMenuBar();
        JMenu calculateAreasMenu = new JMenu("Calculate Areas");
        JMenuItem areaRectItem = new JMenuItem("Areas of all rectangles");
        JMenuItem areaCircleItem = new JMenuItem("Areas of all circles");
        JMenuItem areaAllItem = new JMenuItem("Total Areas");

        JMenuBar perimMenuBar = new JMenuBar();
        JMenu calculatePerimsMenu = new JMenu("Calculate Perimeters");
        JMenuItem perimRectItem = new JMenuItem("Perimeters of all rectangles");
        JMenuItem perimCircleItem = new JMenuItem("Perimeters of all circles");
        JMenuItem perimAllItem = new JMenuItem("Total Perimeters");

        calculateAreasMenu.add(areaRectItem);
        calculateAreasMenu.add(areaCircleItem);
        calculateAreasMenu.add(areaAllItem);

        calculatePerimsMenu.add(perimRectItem);
        calculatePerimsMenu.add(perimCircleItem);
        calculatePerimsMenu.add(perimAllItem);

        areaMenuBar.add(calculateAreasMenu);
        perimMenuBar.add(calculatePerimsMenu);

         /*
        lineItem.addActionListener(e -> currentAction = "Line");
        rectItem.addActionListener(e -> currentAction = "Rectangle");
        circleItem.addActionListener(e -> currentAction = "Circle");

         */

        c.gridx=0;
        c.gridy=0;
        areaPerim.add(area,c);

        c.gridx=1;
        c.gridy=0;
        areaPerim.add(perimeter,c);

        c.gridx=0;
        c.gridy=1;
        c.fill= GridBagConstraints.HORIZONTAL;
        areaPerim.add(new JLabel("Extended Operations"),c );

        areaPerim.add(areaMenuBar);
        areaPerim.add(perimMenuBar);

        return areaPerim;
    }

    private JTextArea propertiesViewer() {
        JTextArea propertiesArea = new JTextArea();
        propertiesArea.setBackground(Color.WHITE);
        propertiesArea.setPreferredSize(new Dimension(300,200));
        propertiesArea.setEditable(false);
        return propertiesArea;
    }

}
