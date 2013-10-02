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
public class S2NameCellValidator extends S2CellValidator{

	public S2NameCellValidator(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String name = (String) value;
		String trimmed = name.trim();
		if (trimmed.equals("")) {
			String message = "Name should not be empty";
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
