/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IAction;
import org.neclipse.gef.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2ActionMethodChangeCommand extends S2EntityNameChangeCommand {

	public S2ActionMethodChangeCommand(INamedEntity entity, String newNamespace) {
		super(entity, newNamespace);
	}
	
	@Override
	public void undo() {
		((IAction)entity).setMethod(oldValue);
	}
	
	@Override
	public void redo() {
		((IAction)entity).setMethod(newValue);
	}

	@Override
	public void execute() {
		redo();
	}
}
