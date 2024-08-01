package MiniCAD.controllers;

import MiniCAD.command.MiniCadCommandHandler;
import MiniCAD.exceptions.ParseException;
import MiniCAD.interpreter.Context;
import MiniCAD.interpreter.commands.UndoableCmdExprIF;
import MiniCAD.interpreter.lexerparser.CommandParser;
import MiniCAD.util.NumericDocumentFilter;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.Serial;


public class MiniCADController extends JPanel {

    @Serial
    private static final long serialVersionUID = -1200564294398210114L;
    private final MiniCadCommandHandler cmdHandler;
    static final int offset = 10;
    static final double zoom_factor= 0.1;
    private String objId= null;
    private GraphicObject subject;
    private CommandParser commandParser;
    private Context context;


    public void setControlledObject(GraphicObject go, String id) {
        subject = go;
        objId = id;
    }

    public MiniCADController(MiniCadCommandHandler cmdH, Context context) {
        this(null,cmdH,context);
    }

    public MiniCADController(GraphicObject go, MiniCadCommandHandler cmdH, Context c){
        cmdHandler = cmdH;
        subject = go;
        context = c;
        commandParser = new CommandParser();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel commandPanel = new JPanel();
        commandPanel.setPreferredSize(new Dimension(300, 400));
        commandPanel.setBorder(BorderFactory.createTitledBorder("Commands"));

        commandPanel.add(zoomMovePanel());
        commandPanel.add(moveOffPanel());
        commandPanel.add(commandButtonsPanel());
        commandPanel.add(operazioniAvanzatePanel());

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
                String scaleMinusInput = String.format("scale %s %s", objId, 1.0 - zoom_factor);
                try {
                    UndoableCmdExprIF scaleMinus = (UndoableCmdExprIF) commandParser.parseCommand(scaleMinusInput);
                    cmdHandler.handle(scaleMinus);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        zoom.add(minus);

        JButton plus = new JButton("+");
        plus.addActionListener(e -> {
            if (subject != null) {
                String scalePlusInput = String.format("scale %s %s", objId, 1.0 + zoom_factor);
                try {
                    UndoableCmdExprIF scalePlus = (UndoableCmdExprIF) commandParser.parseCommand(scalePlusInput);
                    cmdHandler.handle(scalePlus);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        zoom.add(plus);
        zoom.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton nw = new JButton("\\");

        nw.addActionListener(e -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveNWInput = "mv "+ objId + "("+ (p.getX()  - offset) +","+(p.getY() - offset)+")";
                try {
                    UndoableCmdExprIF mvNW = (UndoableCmdExprIF) commandParser.parseCommand(moveNWInput);
                    cmdHandler.handle(mvNW);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        grid.add(nw);

        JButton n = new JButton("|");
        n.addActionListener(e -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveNInput = "mv "+ objId + "("+ p.getX() +","+(p.getY() - offset)+")";
                try {
                    UndoableCmdExprIF mvN = (UndoableCmdExprIF) commandParser.parseCommand(moveNInput);
                    cmdHandler.handle(mvN);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        grid.add(n);

        JButton ne = new JButton("/");
        ne.addActionListener(e -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveNEInput = "mv "+ objId + "("+ (p.getX()  + offset) +","+(p.getY() - offset)+")";
                try {
                    UndoableCmdExprIF mvNE = (UndoableCmdExprIF) commandParser.parseCommand(moveNEInput);
                    cmdHandler.handle(mvNE);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY() - offset)));

        });

        grid.add(ne);

        JButton w = new JButton("<-");
        w.addActionListener(e -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveWInput = "mv "+ objId + "("+ (p.getX()  - offset) +","+p.getY()+")";
                try {
                    UndoableCmdExprIF mvW = (UndoableCmdExprIF) commandParser.parseCommand(moveWInput);
                    cmdHandler.handle(mvW);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() - offset, p.getY())));
        });

        grid.add(w);

        grid.add(zoom);

        JButton e = new JButton("->");
        e.addActionListener(e1 -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveEInput = "mv "+ objId + "("+ (p.getX()  + offset) +","+p.getY()+")";
                try {
                    UndoableCmdExprIF mvE = (UndoableCmdExprIF) commandParser.parseCommand(moveEInput);
                    cmdHandler.handle(mvE);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY())));
        });

        grid.add(e);

        JButton sw = new JButton("/");

        sw.addActionListener(e12 -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveSWInput = "mv "+ objId + "("+ (p.getX()  - offset) +","+(p.getY() + offset)+")";
                try {
                    UndoableCmdExprIF mvSW = (UndoableCmdExprIF) commandParser.parseCommand(moveSWInput);
                    cmdHandler.handle(mvSW);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() - offset, p.getY() + offset)));
        });
        grid.add(sw);

        JButton s = new JButton("|");
        s.addActionListener(e13 -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveSInput = "mv "+ objId + "("+ p.getX() +","+(p.getY() + offset)+")";
                try {
                    UndoableCmdExprIF mvS = (UndoableCmdExprIF) commandParser.parseCommand(moveSInput);
                    cmdHandler.handle(mvS);
                }catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX(), p.getY() + offset)));
        });

        grid.add(s);

        JButton se = new JButton("\\");
        se.addActionListener(e14 -> {
            if (subject == null) {
                return;
            }
            else if (subject != null) {
                Point2D p = subject.getPosition();
                String moveSEInput = "mv "+ objId + "("+ (p.getX()  + offset) +","+(p.getY() + offset)+")";
                try {
                    UndoableCmdExprIF mvSE = (UndoableCmdExprIF) commandParser.parseCommand(moveSEInput);
                    cmdHandler.handle(mvSE);
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //cmdHandler.handle(new MoveCommand(subject, new Point2D.Double(p.getX() + offset, p.getY() + offset)));
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
        JButton areaButton = new JButton("Area");
        JButton perimButton = new JButton("Perimeter");
        JButton viewProperty = new JButton("View Property");
        JButton groupButton = new JButton("Group");
        JButton ungroupButton = new JButton("Ungroup");


        viewProperty.addActionListener(e -> {
            try {
                updatePropertiesViewer();
            } catch (ParseException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        //constraints
        c.gridx=0;
        c.gridy=0;
        c.gridwidth= 2;
        c.fill= GridBagConstraints.HORIZONTAL;
        cmdButtonsPanel.add(deleteButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        cmdButtonsPanel.add(areaButton, c);

        c.gridx = 1;
        c.gridy = 1;
        cmdButtonsPanel.add(perimButton, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth= 2;
        c.fill= GridBagConstraints.HORIZONTAL;
        cmdButtonsPanel.add(viewProperty, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        cmdButtonsPanel.add(groupButton, c);

        c.gridx = 1;
        c.gridy = 3;
        cmdButtonsPanel.add(ungroupButton, c);

        return cmdButtonsPanel;
    }

    private void updatePropertiesViewer() throws ParseException, IOException {
        if(subject != null ){
            JTextArea propertiesArea = propertiesViewer();
            propertiesArea.setText(getPropertiesAsString(subject));
        }
    }

    private String getPropertiesAsString(GraphicObject subject) throws ParseException, IOException {
        /*CommandParser parser = new CommandParser();
        String objId = objectManager.getIdByObject(subject);
        CommandExprIF listPropCommand = parser.parseCommand("ls "+ objId);

         */
        return null;
    }


    private JPanel operazioniAvanzatePanel() {
        JPanel advOpsPanel = new JPanel();
        advOpsPanel.setPreferredSize(new Dimension(280,115));
        advOpsPanel.setLayout(new BoxLayout(advOpsPanel, BoxLayout.PAGE_AXIS));

        advOpsPanel.setBorder(BorderFactory.createTitledBorder("Advance Operations"));

        //Visualizza Proprietà
        JMenuBar advViewPropMenuBar = new JMenuBar();
        JMenu advancePropertyViewMenu = new JMenu("Advance View");
        JMenuItem imgItem = new JMenuItem("View all images");
        JMenuItem rectItem = new JMenuItem("View all rectangles");
        JMenuItem circleItem = new JMenuItem("View all circles");
        JMenuItem groupItem = new JMenuItem("View all groups");
        JMenuItem allItem = new JMenuItem("View all");

        advancePropertyViewMenu.add(imgItem);
        advancePropertyViewMenu.add(rectItem);
        advancePropertyViewMenu.add(circleItem);
        advancePropertyViewMenu.add(groupItem);
        advancePropertyViewMenu.add(allItem);

        //Area
        JMenuBar areaMenuBar = new JMenuBar();
        JMenu calculateAreasMenu = new JMenu("Calculate Areas");
        JMenuItem areaRectItem = new JMenuItem("Areas of all rectangles");
        JMenuItem areaCircleItem = new JMenuItem("Areas of all circles");
        JMenuItem areaAllItem = new JMenuItem("Total Areas");

        calculateAreasMenu.add(areaRectItem);
        calculateAreasMenu.add(areaCircleItem);
        calculateAreasMenu.add(areaAllItem);

        //Perimetro
        JMenuBar perimMenuBar = new JMenuBar();
        JMenu calculatePerimsMenu = new JMenu("Calculate Perimeters");
        JMenuItem perimRectItem = new JMenuItem("Perimeters of all rectangles");
        JMenuItem perimCircleItem = new JMenuItem("Perimeters of all circles");
        JMenuItem perimAllItem = new JMenuItem("Total Perimeters");

        calculatePerimsMenu.add(perimRectItem);
        calculatePerimsMenu.add(perimCircleItem);
        calculatePerimsMenu.add(perimAllItem);

        advViewPropMenuBar.add(advancePropertyViewMenu);
        areaMenuBar.add(calculateAreasMenu);
        perimMenuBar.add(calculatePerimsMenu);

        advOpsPanel.add(areaMenuBar);
        advOpsPanel.add(perimMenuBar);
        advOpsPanel.add(advViewPropMenuBar);

        return advOpsPanel;
    }

    private JTextArea propertiesViewer() {
        JTextArea propertiesArea = new JTextArea();
        propertiesArea.setBackground(Color.WHITE);
        propertiesArea.setPreferredSize(new Dimension(300,200));
        propertiesArea.setEditable(false);
        return propertiesArea;
    }


}
