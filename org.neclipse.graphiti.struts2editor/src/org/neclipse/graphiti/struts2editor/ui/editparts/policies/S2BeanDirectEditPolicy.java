/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.policies;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;
import org.neclipse.graphiti.struts2editor.model.IBean;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2BeanEditPart;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanAttributesEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanClassEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanNameEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanOptionalValueEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanScopeEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanStaticValueEditCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2BeanTypeEditCommand;

/**
 * @author nbhusare
 *
 */
public class S2BeanDirectEditPolicy extends DirectEditPolicy {

	private String oldValue;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		S2BeanAttributesEditCommand command = null;
		
		String newValue = (String) request.getCellEditor().getValue();
		
		S2BeanEditPart editPart = (S2BeanEditPart) getHost();
		IBean bean = (IBean) getHost().getModel();
		if (editPart.isBeanClassEdited(request)) {
			command = new S2BeanClassEditCommand(bean, newValue);
			command.setOldValue(bean.getBeanClass());
		} else if (editPart.isBeanTypeEdited(request)) {
			command  = new S2BeanTypeEditCommand(bean, newValue);
			command.setOldValue(bean.getType());
		} else if (editPart.isBeanNameEdited(request)) {
			command  = new S2BeanNameEditCommand(bean, newValue);
			command.setOldValue(bean.getType());
		} else if (editPart.isBeanScopeEdited(request)) {
			command  = new S2BeanScopeEditCommand(bean, newValue);
			command.setOldValue(bean.getType());
		} else if (editPart.isBeanStaticEdited(request)) {
			command  = new S2BeanStaticValueEditCommand(bean, newValue); 
			command.setOldValue(bean.getType());
		} else if (editPart.isBeanOptionalEdited(request)) {
			command  = new S2BeanOptionalValueEditCommand(bean, newValue); 
			command.setOldValue(bean.getType());
		}
		return command;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		Assert.isNotNull(request, "Direct Edit Request cannot be null");
		((S2BeanEditPart)getHost()).handleLabelEdit(request);
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
		((S2BeanEditPart)getHost()).revertLabelEdit(request, oldValue);
	}
}
