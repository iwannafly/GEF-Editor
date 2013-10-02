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
public class S2ClassCellValidator extends S2CellValidator{

	private static final char DOT = '.';
	
	public S2ClassCellValidator(IValidationMessageHandler messageHandler) {
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
			String message = "Class name should not be empty"; 
			messageHandler.setMessageText(message);
			return message;
		} else if (name.charAt(0) == DOT || name.charAt(name.length() - 1) == DOT) {  
			String message = "Class name should not start or end with a dot"; 
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
