/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.action;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.neclipse.graphiti.struts2editor.model.IEntity;

/**
 * @author nbhusare
 *
 */
public class S2PasteAction extends SelectionAction {

	public S2PasteAction(IWorkbenchPart part) {
		super(part);
		setId(ActionFactory.PASTE.getId());
		setText("&Paste");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		Object selection = Clipboard.getDefault().getContents();
		return selection instanceof IEntity;
	}
	
	/**
	 * 
	 * @return
	 */
	private Command getCommand() {
		List selection = getSelectedObjects();
		if (selection != null && selection.size() == 1) {
			Object obj = selection.get(0);
			if (obj instanceof GraphicalEditPart) {
				GraphicalEditPart editPart = (GraphicalEditPart) obj;
				
				/*
				 * Read the contents from the Clipboard and create a command to
				 * create the copied entity
				 */
				Object cEntity = Clipboard.getDefault().getContents();
				if (cEntity != null) {
					CreateRequest request = new CreateRequest();
					request.setFactory(new SimpleFactory(cEntity.getClass()));
					request.setLocation(getPasteLocation(editPart));
					return editPart.getCommand(request);
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param part
	 * @return
	 */
	private Point getPasteLocation(GraphicalEditPart part) {
		org.eclipse.swt.graphics.Point cursorLocation = Display.getDefault()
				.getCursorLocation();
		if (cursorLocation != null) {
			org.eclipse.swt.graphics.Point point = part.getViewer()
					.getControl().toControl(cursorLocation);
			return new Point(point.x, point.y);
		}
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		execute(getCommand());
	}
}
