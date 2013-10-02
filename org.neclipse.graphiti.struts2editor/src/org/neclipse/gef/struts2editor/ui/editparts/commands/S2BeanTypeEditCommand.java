/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanTypeEditCommand extends S2BeanAttributesEditCommand {

	public S2BeanTypeEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}
	
	@Override
	public void redo() {
		bean.setType(newValue);
	}
	
	@Override
	public void undo() {
		bean.setType(oldValue);
	}
}
