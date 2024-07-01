package MiniCAD.ui;

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

        //add(canvas, BorderLayout.CENTER);
        setLayout(new BorderLayout());

        createToolBar();
        //createMenuBar();
        createCanvaspanel();
        createSideBar();
        setSize(1200, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createSideBar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(400, getHeight()));
        sidebar.setBorder(BorderFactory.createTitledBorder("Sidebar"));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Options");
        JButton button1 = new JButton("Option 1");
        JButton button2 = new JButton("Option 2");

        sidebar.add(label);
        sidebar.add(button1);
        sidebar.add(button2);

        add(sidebar, BorderLayout.EAST);

    }

    private void createCanvaspanel() {
        canvas = new MiniCadApp.DrawCanvas();
        JPanel canvasPanel = new JPanel((new BorderLayout()));
        canvasPanel.add(canvas, BorderLayout.CENTER);
        add(canvasPanel, BorderLayout.CENTER);
    }

    private void createToolBar(){
        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");
        JButton rectangle = new JButton("Rectangle");
        JButton circle = new JButton("Circle");
        JButton image = new JButton("Image");


        toolbar.add(undoButt);
        toolbar.add(redoButt);
        toolbar.add(rectangle);
        toolbar.add(circle);
        toolbar.add(image);

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
