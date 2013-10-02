/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanScopeEditCommand extends S2BeanAttributesEditCommand {

	public S2BeanScopeEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}
	
	@Override
	public void redo() {
		bean.setScope(newValue);
	}
	
	@Override
	public void undo() {
		bean.setScope(oldValue);
	}
}
