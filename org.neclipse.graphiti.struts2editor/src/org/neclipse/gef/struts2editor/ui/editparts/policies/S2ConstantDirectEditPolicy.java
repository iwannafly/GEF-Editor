package org.neclipse.gef.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.gef.struts2editor.model.IConstant;
import org.neclipse.gef.struts2editor.ui.editparts.S2ConstantEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.S2EntityEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2ConstantValueChangeCommand;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2EntityNameChangeCommand;

/**
 * Policy to enable direct editing of the Package Name and Namespace labels.
 * @author nbhusare
 *
 */
public class S2ConstantDirectEditPolicy extends DirectEditPolicy{

	private String oldValue;
	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		S2EntityNameChangeCommand command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		S2ConstantEditPart editPart = (S2ConstantEditPart) getHost();
		IConstant entity = (IConstant) editPart.getModel();
		if(editPart.isNameEdited(request)) {
			command = new S2EntityNameChangeCommand(entity, newValue);
			command.setOldName(entity.getName());
		} else if (editPart.isValueEdited(request)) {
			command = new S2ConstantValueChangeCommand(entity, newValue);
			command.setOldName(entity.getValue());
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
		((S2ConstantEditPart) getHost()).revertLabelEdit(request, oldValue);
	}
}
