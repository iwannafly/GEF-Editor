/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.IInclude;
import org.neclipse.graphiti.struts2editor.model.INamedEntity;

/**
 * @author nbhusare
 *
 */
public class S2IncludePathChangeCommand extends S2EntityNameChangeCommand {

	public S2IncludePathChangeCommand(INamedEntity entity, String newName) {
		super(entity, newName);
	}

	public void setOldPath(String oldPath) {
		super.setOldName(oldPath);
	}
	
	@Override
	public void undo() {
		((IInclude)entity).setPath(oldValue);
	}
	
	@Override
	public void redo() {
		((IInclude)entity).setPath(newValue);
	}
}
