/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanNameEditCommand extends S2BeanAttributesEditCommand {

	public S2BeanNameEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}
	
	@Override
	public void redo() {
		bean.setName(newValue);
	}
	
	@Override
	public void undo() {
		bean.setName(oldValue);
	}
}
