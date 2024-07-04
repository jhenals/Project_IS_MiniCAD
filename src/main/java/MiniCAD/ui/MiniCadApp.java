package MiniCAD.ui;

import ObserverCommandFlyweight.is.command.HistoryCommandHandler;
import ObserverCommandFlyweight.is.shapes.controller.GraphicObjectController;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import ObserverCommandFlyweight.is.shapes.model.RectangleObject;
import ObserverCommandFlyweight.is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MiniCadApp extends JFrame{

    private MiniCadApp.DrawCanvas canvas;
    private String currentAction = "Line";

    public MiniCadApp() {
        super("Mini-CAD Application");

        setLayout(new BorderLayout());

        createToolBar();
        //createCanvaspanel();
        createSideBar();
        setSize(1200, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createSideBar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(400, getHeight()));
        sidebar.setBorder(BorderFactory.createTitledBorder("Commands"));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        //JLabel label = new JLabel("Options");
        JButton removeButton = new JButton("Remove");

        //move offset
        JTextField newXAxis = new JTextField();
        newXAxis.setPreferredSize(new Dimension(50, 20));
        JTextField newYAxis = new JTextField();
        newYAxis.setPreferredSize(new Dimension(50, 20));
        JButton sposta = new JButton("Move");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("X:"));
        inputPanel.add(newXAxis);
        inputPanel.add(new JLabel("Y:"));
        inputPanel.add(newYAxis);
        inputPanel.add(sposta);


        //move and zoom : using graphic object controller

        /*
        final GraphicObjectController goc = new GraphicObjectController(handler);


        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });

         */

        //JLabel labelProperties = new JLabel("Properties");
        JButton propertyButt = new JButton("Visualizza Proprietà");
        JButton propertyCircleButt = new JButton("Visualizza i cerchi");
        JButton propertyRectButt = new JButton("Visualizza i rettangoli");
        JButton propertyImgButt = new JButton("Visualizza gli immagini");

        //group and ungroup
        JButton createGrp = new JButton("Group");
        JButton UnGrp = new JButton("Ungroup");

        //calcola area
        JButton area = new JButton("Area");
        JButton perimeter = new JButton("Perimeter");

        JButton areaCircle = new JButton("Area of all circles");
        JButton areaRect = new JButton("Area of all rectangles");

        JButton areaAll = new JButton("Total area");
        JLabel propertyPanelLabel = new JLabel("Properties:");
        JTextArea propertiesArea = createPropertiesPanel();

        //sidebar.add(label);
        sidebar.add(removeButton);
        sidebar.add(inputPanel, BorderLayout.SOUTH);
        //sidebar.add(labelProperties);
        sidebar.add(propertyButt);
        sidebar.add(propertyCircleButt);
        sidebar.add(propertyRectButt);
        sidebar.add(propertyImgButt);
        sidebar.add(createGrp);
        sidebar.add(UnGrp);
        sidebar.add(area);
        sidebar.add(perimeter);
        sidebar.add(areaCircle);
        sidebar.add(areaRect);
        sidebar.add(areaAll);
        sidebar.add(propertyPanelLabel);
        sidebar.add(new JScrollPane(propertiesArea), BorderLayout.CENTER);

        add(sidebar, BorderLayout.EAST);

    }

    private JTextArea createPropertiesPanel() {
        JTextArea propertiesArea = new JTextArea();
        propertiesArea.setBackground(Color.WHITE);
        propertiesArea.setPreferredSize(new Dimension(600,600));
        propertiesArea.setEditable(false);
        return propertiesArea;
    }

    private void createCanvaspanel() {
        canvas = new MiniCadApp.DrawCanvas();
        JPanel canvasPanel = new JPanel((new BorderLayout()));
        canvasPanel.add(canvas, BorderLayout.CENTER);
        add(canvasPanel, BorderLayout.CENTER);
    }

    private void createToolBar(){
        JToolBar toolbar = new JToolBar();
        toolbar.setLayout(new BorderLayout());

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        final HistoryCommandHandler handler = new HistoryCommandHandler();

        undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));
        redoButt.addActionListener( evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.REDO));

        JPanel undoRedoPanel = new JPanel();
        undoRedoPanel.add(undoButt);
        undoRedoPanel.add(redoButt);


        JLabel createLabel = new JLabel("DRAW");

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();

        gpanel.setPreferredSize(new Dimension(400, 400));

        gpanel.installView(RectangleObject.class, new RectangleObjectView());
        gpanel.installView(CircleObject.class, new CircleObjectView());
        gpanel.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);
        JButton rectangle = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectangle.setText(go.getType());

        go= new CircleObject(new Point(200, 100), 10);
        JButton circle = new JButton(new CreateObjectAction(go, gpanel, handler));
        circle.setText(go.getType());

        go = new ImageObject(new ImageIcon(MiniCadApp.class.getResource("NyaNya.gif")),
                new Point(240, 187));
        JButton image = new JButton(new CreateObjectAction(go, gpanel, handler));
        image.setText(go.getType());


        JPanel createPanel = new JPanel();
        createPanel.add(createLabel);
        createPanel.add(rectangle);
        createPanel.add(circle);
        createPanel.add(image);

        toolbar.add(createPanel, BorderLayout.WEST);
        toolbar.add(undoRedoPanel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu drawMenu = new JMenu("Draw");
        JMenuItem lineItem = new JMenuItem("Line");
        JMenuItem rectItem = new JMenuItem("Rectangle");
        JMenuItem circleItem = new JMenuItem("Circle");

        lineItem.addActionListener(e -> currentAction = "Line");
        rectItem.addActionListener(e -> currentAction = "Rectangle");
        circleItem.addActionListener(e -> currentAction = "Circle");

        drawMenu.add(lineItem);
        drawMenu.add(rectItem);
        drawMenu.add(circleItem);
        menuBar.add(drawMenu);

        setJMenuBar(menuBar);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MiniCadApp app = new MiniCadApp();
            app.setVisible(true);
        });
    }

    // Inner class for the drawing canvas
    class DrawCanvas extends JPanel {
        private ArrayList<Shape> shapes = new ArrayList<>();
        private Point startPoint, endPoint;

        public DrawCanvas() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                }

                public void mouseReleased(MouseEvent e) {
                    endPoint = e.getPoint();
                    addShape(startPoint, endPoint);
                    repaint();
                }
            });
        }

        private void addShape(Point start, Point end) {
            switch (currentAction) {
                case "Line":
                    shapes.add(new Line2D.Double(start, end));
                    break;
                case "Rectangle":
                    shapes.add(new Rectangle(start.x, start.y, Math.abs(end.x - start.x), Math.abs(end.y - start.y)));
                    break;
                case "Circle":
                    int radius = (int) start.distance(end);
                    shapes.add(new Ellipse2D.Double(start.x - radius, start.y - radius, 2 * radius, 2 * radius));
                    break;
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            for (Shape shape : shapes) {
                g2d.draw(shape);
            }
        }
    }
}
