/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.IAction;
import org.neclipse.graphiti.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2ActionClassChangeCommand extends S2EntityNameChangeCommand {

	public S2ActionClassChangeCommand(INamedEntity entity, String newNamespace) {
		super(entity, newNamespace);
	}
	
	@Override
	public void undo() {
		((IAction)entity).setActionClass(oldValue);
	}
	
	@Override
	public void redo() {
		((IAction)entity).setActionClass(newValue);
	}

	@Override
	public void execute() {
		redo();
	}
}
