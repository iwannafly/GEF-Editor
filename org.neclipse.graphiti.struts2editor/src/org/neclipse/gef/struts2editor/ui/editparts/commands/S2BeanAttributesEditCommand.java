/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.neclipse.gef.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanAttributesEditCommand extends Command {
	
	protected String oldValue;
	protected String newValue;
	protected IBean bean;
	
	public S2BeanAttributesEditCommand(IBean bean, String newValue) {
		Assert.isNotNull(bean, "Null Bean cannot be edited");
		this.bean = bean;
		this.newValue = newValue;
	}
	
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
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
	public void execute() {
		redo();
	}
}
