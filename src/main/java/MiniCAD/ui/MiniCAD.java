package MiniCAD.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

// Main Application Class
public class MiniCAD extends JFrame {
    private DrawCanvas canvas;
    private String currentAction = "Line";

    public MiniCAD() {
        super("Mini CAD Application");
        canvas = new DrawCanvas();
        add(canvas, BorderLayout.CENTER);
        createMenuBar();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
            MiniCAD app = new MiniCAD();
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
