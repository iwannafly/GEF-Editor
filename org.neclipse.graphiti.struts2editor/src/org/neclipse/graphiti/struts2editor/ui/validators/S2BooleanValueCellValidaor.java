/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.validators;

import org.neclipse.graphiti.struts2editor.ui.S2CellValidator;

/**
 * @author nbhusare
 *
 */
public class S2BooleanValueCellValidaor extends S2CellValidator {

	public S2BooleanValueCellValidaor(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String abstractValue = (String) value;
		abstractValue = abstractValue.trim();
		if (!abstractValue.equals("true") && !abstractValue.equals("false")) {
			String message = "Value can be either be true or false";
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
