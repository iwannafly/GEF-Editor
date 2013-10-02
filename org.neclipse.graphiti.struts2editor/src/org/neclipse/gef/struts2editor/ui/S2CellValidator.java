/**
 * 
 */
package org.neclipse.gef.struts2editor.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public abstract class S2CellValidator implements ICellEditorValidator {

	protected IValidationMessageHandler messageHandler;
	
	public S2CellValidator(IValidationMessageHandler messageHandler) {
		Assert.isNotNull(messageHandler, "Message handler cannot be null");
		this.messageHandler = messageHandler;
	}

}
