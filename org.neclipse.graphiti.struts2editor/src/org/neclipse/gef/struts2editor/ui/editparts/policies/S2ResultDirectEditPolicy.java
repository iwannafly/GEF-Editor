package org.neclipse.gef.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.IResultConnection;
import org.neclipse.gef.struts2editor.ui.editparts.S2EntityEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.S2ExtendsEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.S2PackageEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.S2ResultEditPart;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2EntityNameChangeCommand;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2PackageAbstractChangeCommand;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2PackageNamespaceChangeCommand;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2ResultChangeCommand;

/**
 * Policy to enable direct editing of the Package Name and Namespace labels.
 * @author nbhusare
 *
 */
public class S2ResultDirectEditPolicy extends DirectEditPolicy{

	private String oldValue;
	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		S2ResultChangeCommand command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		S2ResultEditPart editPart = (S2ResultEditPart) getHost();
		IResultConnection entity = (IResultConnection) editPart.getModel();
		if(editPart.isResultLabelEdited(request)) {
			command  = new S2ResultChangeCommand(entity, newValue);
			command.setOldValue(oldValue);
		} 
		return command;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		((S2ResultEditPart) getHost()).handleLabelEdit(request);
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
		((S2ResultEditPart) getHost()).revertLabelEdit(request, oldValue);
	}
}
