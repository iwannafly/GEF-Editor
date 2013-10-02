/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.validators;

import org.neclipse.gef.struts2editor.ui.S2CellValidator;

/**
 * The Validator for performing the validatons of the text entered in the cell while editing.
 * 
 * @author nbhusare
 *
 */
public class S2ActionMethodCellValidator extends S2CellValidator{

	public S2ActionMethodCellValidator(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}
	
	/*
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String name = (String) value;
		name = name.trim();
		if (name.equals("")) { //$NON-NLS-1$
			String message = "Action method should not be empty. Atlest one entry method should be defined."; 
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
