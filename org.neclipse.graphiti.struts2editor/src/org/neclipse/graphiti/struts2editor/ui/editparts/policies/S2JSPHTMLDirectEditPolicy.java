/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.graphiti.struts2editor.model.IJSPHTML;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2JSPHTMLEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2JSPHTMLPathChangeCommand;

/**
 * @author nbhusare
 *
 */
public class S2JSPHTMLDirectEditPolicy extends DirectEditPolicy {

	private String oldValue;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		S2JSPHTMLPathChangeCommand command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		S2JSPHTMLEditPart editPart = (S2JSPHTMLEditPart) getHost();
		IJSPHTML include = (IJSPHTML) getHost().getModel();
		if (editPart.isPathEdited(request)) {
			command = new S2JSPHTMLPathChangeCommand(include, newValue);
			command.setOldPath(include.getPath());
		}
		return command;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		((S2JSPHTMLEditPart)getHost()).handleLabelEdit(request);
	}
	
	@Override
	protected void storeOldEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		oldValue = (String) request.getCellEditor().getValue();
	}

	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		CellEditor cellEditor = request.getCellEditor();
		cellEditor.setValue(oldValue);
		((S2JSPHTMLEditPart)getHost()).revertLabelEdit(request, oldValue);
	}
}
