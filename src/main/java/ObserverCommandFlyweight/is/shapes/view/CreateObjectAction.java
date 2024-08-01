package ObserverCommandFlyweight.is.shapes.view;

import ObserverCommandFlyweight.is.command.CommandHandler;
import ObserverCommandFlyweight.is.shapes.model.AbstractGraphicObject;
import ObserverCommandFlyweight.is.shapes.model.GraphicObject;
import ObserverCommandFlyweight.is.shapes.specificcommand.NewObjectCmd;

import java.awt.event.ActionEvent;
import java.io.Serial;

import javax.swing.AbstractAction;

public class CreateObjectAction extends AbstractAction {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 5399493440620423134L;
	AbstractGraphicObject prototype;
	GraphicObjectPanel panel;
	CommandHandler ch;

	public CreateObjectAction(AbstractGraphicObject prototype,
			GraphicObjectPanel panel, CommandHandler ch) {
		super();
		this.prototype = prototype;
		this.panel = panel;
		this.ch = ch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GraphicObject go = prototype.clone();
		ch.handle(new NewObjectCmd(panel, go));
	}

}
