/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.validators;

import org.neclipse.gef.struts2editor.ui.S2CellValidator;


/**
 * @author nbhusare
 *
 */
public class S2PackageNamespaceCellValidator extends S2CellValidator {

	public S2PackageNamespaceCellValidator(IValidationMessageHandler messageHandler) {
		super(messageHandler);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		return null;
	}

}
