/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanClassEditCommand extends S2BeanAttributesEditCommand {

	
	public S2BeanClassEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}

	@Override
	public void undo() {
		bean.setBeanClass(oldValue);
	}
	
	@Override
	public void redo() {
		bean.setBeanClass(newValue);
	}
}
