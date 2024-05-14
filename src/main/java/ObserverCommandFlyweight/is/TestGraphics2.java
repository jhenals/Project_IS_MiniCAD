package ObserverCommandFlyweight.is;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import ObserverCommandFlyweight.is.command.HistoryCommandHandler;
import ObserverCommandFlyweight.is.shapes.view.*;
import ObserverCommandFlyweight.is.shapes.controller.GraphicObjectController;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.CircleObject;
import ObserverCommandFlyweight.is.shapes.model.ImageObject;
import ObserverCommandFlyweight.is.shapes.model.RectangleObject;
import ObserverCommandFlyweight.is.shapes.view.*;

public class TestGraphics2 {

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

		gpanel.setPreferredSize(new Dimension(400, 400));

		gpanel.installView(RectangleObject.class, new RectangleObjectView());
		gpanel.installView(CircleObject.class, new CircleObjectView());
		gpanel.installView(ImageObject.class, new ImageObjectView());

		AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);

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

		go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
				new Point(240, 187));

		JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
		imgButton.setText(go.getType());
		toolbar.add(imgButton);

		final GraphicObjectController goc = new GraphicObjectController(handler);

		gpanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
			}
		});

		f.add(toolbar, BorderLayout.NORTH);
		f.add(new JScrollPane(gpanel), BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout());

		controlPanel.add(goc);
		f.setTitle("Shapes");
		f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);

	}
}