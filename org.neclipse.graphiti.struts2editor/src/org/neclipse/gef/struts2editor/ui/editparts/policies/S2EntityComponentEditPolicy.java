/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.neclipse.gef.struts2editor.model.IContainer;
import org.neclipse.gef.struts2editor.model.IEntity;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2EntityDeleteCommand;

/**
 * Policy to support deletion of Entities.
 * 
 * @author nbhusare
 */
public class S2EntityComponentEditPolicy extends ComponentEditPolicy {
	
	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		IContainer parent = (IContainer) getHost().getParent().getModel();
		IEntity child = (IEntity) getHost().getModel();
		return new S2EntityDeleteCommand(parent, child);
	}
}
