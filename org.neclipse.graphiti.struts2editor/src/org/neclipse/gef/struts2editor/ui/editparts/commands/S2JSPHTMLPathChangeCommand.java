/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.IJSPHTML;
import org.neclipse.gef.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2JSPHTMLPathChangeCommand extends S2EntityNameChangeCommand {

	public S2JSPHTMLPathChangeCommand(INamedEntity entity, String newName) {
		super(entity, newName);
	}

	public void setOldPath(String oldPath) {
		super.setOldName(oldPath);
	}
	
	@Override
	public void undo() {
		((IJSPHTML)entity).setPath(oldValue);
	}
	
	@Override
	public void redo() {
		((IJSPHTML)entity).setPath(newValue);
	}
}
