package org.neclipse.graphiti.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.graphiti.struts2editor.model.IAction;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2ActionEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2ActionClassChangeCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2ActionMethodChangeCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2EntityNameChangeCommand;

/**
 * Policy to enable direct editing of the Package Name and Namespace labels.
 * @author nbhusare
 *
 */
public class S2ActionDirectEditPolicy extends DirectEditPolicy{

	private String oldValue;
	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		S2EntityNameChangeCommand command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		S2ActionEditPart editPart = (S2ActionEditPart) getHost();
		IAction entity = (IAction) editPart.getModel();
		if(editPart.isNameEdited(request)) {
			command = new S2EntityNameChangeCommand(entity, newValue);
			command.setOldName(entity.getName());
		} else if (editPart.isClassEdited(request)) {
			command = new S2ActionClassChangeCommand(entity, newValue);
			command.setOldName(entity.getActionClass());
		} else if (editPart.isMethodEdited(request)) {
			command = new S2ActionMethodChangeCommand(entity, newValue);
			command.setOldName(entity.getMethod());
		}
		return command;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		((S2EntityEditPart) getHost()).handleLabelEdit(request);
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
		((S2ActionEditPart) getHost()).revertLabelEdit(request, oldValue);
	}
}
