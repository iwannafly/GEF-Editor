/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.neclipse.gef.struts2editor.model.IResultConnection;

/**
 * @author nbhusare
 *
 */
public class S2ResultChangeCommand extends Command {

	protected String oldValue;
	protected String newValue;
	protected IResultConnection entity;
	
	public S2ResultChangeCommand(IResultConnection entity, String newValue) {
		Assert.isNotNull(entity, "Null entity cannot be edited");
		this.entity = entity;
		this.newValue = newValue;
	}
	
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	
	private String getName(String result) {
		return result.substring(0, result.indexOf("#"));
	}
	
	private String getType(String result) {
		return result.substring(result.indexOf("#") + 1, result.length());
	}
	
	@Override
	public void undo() {
		entity.setName(getName(oldValue));
		entity.setType(getType(oldValue));
	}
	
	@Override
	public void redo() {
		entity.setName(getName(newValue));
		entity.setType(getType(newValue));
	}

	@Override
	public void execute() {
		redo();
	}
}
