/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.neclipse.gef.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2EntityNameChangeCommand extends Command {
	
	protected String oldValue;
	protected String newValue;
	protected INamedEntity entity;
	
	public S2EntityNameChangeCommand(INamedEntity entity, String newName) {
		Assert.isNotNull(entity, "Null entity cannot be edited");
		this.entity = entity;
		this.newValue = newName;
	}
	
	public void setOldName(String oldName) {
		this.oldValue = oldName;
	}

	@Override
	public boolean canExecute() {
		/* Allow the operation if the name is not null*/
		if (newValue != null) return true;
		
		/* Don't allow if the name is null and also set the name to old value */
		newValue = oldValue;
		return false;
	}
	
	@Override
	public void undo() {
		entity.setName(oldValue);
	}
	
	@Override
	public void redo() {
		entity.setName(newValue);
	}

	@Override
	public void execute() {
		redo();
	}
}
