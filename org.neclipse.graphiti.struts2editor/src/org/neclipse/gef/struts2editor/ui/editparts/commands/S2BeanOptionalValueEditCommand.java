/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanOptionalValueEditCommand extends S2BeanAttributesEditCommand {

	public S2BeanOptionalValueEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}
	
	@Override
	public void redo() {
		bean.setoptional(newValue);
	}
	
	@Override
	public void undo() {
		bean.setoptional(oldValue);
	}
}
