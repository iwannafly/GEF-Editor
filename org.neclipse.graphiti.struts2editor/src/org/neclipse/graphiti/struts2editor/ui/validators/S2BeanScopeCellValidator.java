/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.validators;

import org.neclipse.graphiti.struts2editor.ui.S2CellValidator;

/**
 * The Validator for performing the validatons of the text entered in the cell while editing.
 * 
 * @author nbhusare
 *
 */
public class S2BeanScopeCellValidator extends S2CellValidator{

	public S2BeanScopeCellValidator(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String name = (String) value;
		name = name.trim();
		if (name.equals("")) {
			String message = "Bean Scope should not be empty";
			messageHandler.setMessageText(message);
			return message;
		} else if (!(name.equals("default") || name.equals("singleton")
				|| name.equals("request") || name.equals("session") || name.equals("thread"))) {
			String message = "Invalid Bean Scope. Valid Scope values are - default, singleton, request, session, thread";
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
