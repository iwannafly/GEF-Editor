/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.validators;

import org.neclipse.gef.struts2editor.ui.S2CellValidator;

/**
 * @author nbhusare
 *
 */
public class S2IncludePathCellValidator extends S2CellValidator {

	public S2IncludePathCellValidator(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String path = (String) value;
		path = path.trim();
		if (path.equals("")) {
			String message = "Include Path should not be empty";
			messageHandler.setMessageText(message);
			return message;
		} else {
			messageHandler.reset();
			return null;
		}
	}

}
