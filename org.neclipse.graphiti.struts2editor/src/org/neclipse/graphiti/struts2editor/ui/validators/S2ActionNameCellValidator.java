/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.validators;

import java.util.List;

import org.neclipse.graphiti.struts2editor.model.IConstant;
import org.neclipse.graphiti.struts2editor.model.IDiagram;
import org.neclipse.graphiti.struts2editor.model.S2Action;
import org.neclipse.graphiti.struts2editor.model.S2Package;
import org.neclipse.graphiti.struts2editor.ui.S2CellValidator;

/**
 * The Validator for performing the validatons of the text entered in the cell while editing.
 * 
 * @author nbhusare
 *
 */
public class S2ActionNameCellValidator extends S2CellValidator{

	private S2Action child;
	
	public S2ActionNameCellValidator(IValidationMessageHandler messageHandler, S2Action child) {
		super(messageHandler);
		this.child = child;
	}
	
	private boolean isConstantDefined(IDiagram diagram) {
		List<IConstant> constants = diagram.getConstants();
		for (IConstant constant : constants) {
			String name = constant.getName();
			if (name.indexOf(":") != -1) {
				name = name.substring(name.indexOf(":") + 1); //$NON-NLS-1$
				name = name.trim();
				if (name.equals("struts.enable.SlashesInActionNames")){ //$NON-NLS-1$
					return true;
				} 
			} 
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
	 */
	@Override
	public String isValid(Object value) {
		String name = (String) value;
		name = name.trim();
		if (name.equals("")) { //$NON-NLS-1$
			String message = "Action Name should not be empty"; 
			messageHandler.setMessageText(message);
			return message;
		} else if (name.contains("/")) { //$NON-NLS-1$
			IDiagram diagram = (IDiagram) ((S2Package)child.getParent()).getParent();
			if (!isConstantDefined(diagram)) {
				String message = "For Action name to have slashes in them, you need to define the 'struts.enable.SlashesInActionNames' constant"; 
				messageHandler.setMessageText(message);
				return message;
			}
			return null;
		} else {
			messageHandler.reset();
			return null;
		}
	}
}
