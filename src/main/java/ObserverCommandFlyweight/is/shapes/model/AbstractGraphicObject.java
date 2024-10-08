package ObserverCommandFlyweight.is.shapes.model;


import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {

	private List<GraphicObjectListener> listeners = new LinkedList<>();

	@Override
	public void addGraphicObjectListener(GraphicObjectListener l) {
		if (listeners.contains(l))
			return;
		listeners.add(l);

	}

	@Override
	public void removeGraphicObjectListener(GraphicObjectListener l) {
		listeners.remove(l);

	}

	protected void notifyListeners(GraphicEvent e) {

		for (GraphicObjectListener gol : listeners)

			gol.graphicChanged(e);

	}

	@Override
	public void moveTo(double x, double y) {
		moveTo(new Point2D.Double(x, y));

	}

	@Override
	public GraphicObject clone() {
		try {
			AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
			go.listeners = new LinkedList<>();
			return go;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}

}
