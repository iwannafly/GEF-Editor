package org.neclipse.graphiti.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.graphiti.struts2editor.model.IPackage;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2PackageEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2EntityNameChangeCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2PackageAbstractChangeCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2PackageNamespaceChangeCommand;

/**
 * Policy to enable direct editing of the Package Name and Namespace labels.
 * @author nbhusare
 *
 */
public class S2PackageDirectEditPolicy extends DirectEditPolicy{

	private String oldValue;
	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		Command command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		S2PackageEditPart editPart = (S2PackageEditPart) getHost();
		IPackage entity = (IPackage) editPart.getModel();
		if(editPart.isNameEdited(request)) {
			command = new S2EntityNameChangeCommand(entity, newValue);
			((S2EntityNameChangeCommand)command).setOldName(entity.getName());
		} else if (editPart.isNamespaceEdited(request)) {
			command = new S2PackageNamespaceChangeCommand(entity, newValue);
			((S2EntityNameChangeCommand)command).setOldName(entity.getNamespace());
		} else if (editPart.isAbstractEdited(request)) {
			command = new S2PackageAbstractChangeCommand(entity, newValue);
			((S2EntityNameChangeCommand)command).setOldName(entity.getAbstractValue());
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
		((S2PackageEditPart) getHost()).revertLabelEdit(request, oldValue);
	}
}
