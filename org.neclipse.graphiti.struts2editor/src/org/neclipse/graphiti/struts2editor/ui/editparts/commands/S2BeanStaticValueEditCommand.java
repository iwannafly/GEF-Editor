/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.IBean;

/**
 * @author nbhusare
 *
 */
public class S2BeanStaticValueEditCommand extends S2BeanAttributesEditCommand {

	public S2BeanStaticValueEditCommand(IBean bean, String newValue) {
		super(bean, newValue);
	}
	
	@Override
	public void redo() {
		bean.setStatic(newValue);
	}
	
	@Override
	public void undo() {
		bean.setStatic(oldValue);
	}
}
