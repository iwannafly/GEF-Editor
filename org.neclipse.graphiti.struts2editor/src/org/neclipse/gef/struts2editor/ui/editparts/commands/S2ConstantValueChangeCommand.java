/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IConstant;
import org.neclipse.gef.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2ConstantValueChangeCommand extends S2EntityNameChangeCommand {

	public S2ConstantValueChangeCommand(INamedEntity entity, String newNamespace) {
		super(entity, newNamespace);
	}
	
	@Override
	public void undo() {
		((IConstant)entity).setValue(oldValue);
	}
	
	@Override
	public void redo() {
		((IConstant)entity).setValue(newValue);
	}

	@Override
	public void execute() {
		redo();
	}
}
