/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model;

/**
 * @author nbhusare
 * 
 */
public class S2EntitySetConstCommand extends Command {

	private ChangeBoundsRequest request;
	private IS2Model model;
	private Rectangle dimension;
	private Rectangle oldDimention;

	public S2EntitySetConstCommand(ChangeBoundsRequest request,
			IS2Model model, Rectangle dimension) {
		if (request == null || model == null || dimension == null) {
			throw new IllegalArgumentException();
		}
		this.request = request;
		this.model = model;
		this.dimension = dimension;
	}

	@Override
	public boolean canExecute() {
		Object type = request.getType();
		return (RequestConstants.REQ_MOVE.equals(type)
				|| RequestConstants.REQ_MOVE_CHILDREN.equals(type)
				|| RequestConstants.REQ_RESIZE.equals(type) || RequestConstants.REQ_RESIZE_CHILDREN
				.equals(type) || RequestConstants.REQ_ALIGN_CHILDREN.equals(type));
	}

	@Override
	public void undo() {
		model.setDimension(oldDimention);
	}

	@Override
	public void redo() {
		model.setDimension(dimension);
	}

	@Override
	public void execute() {
		oldDimention = model.getDimension();
		redo();
	}
}
