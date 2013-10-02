/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.neclipse.gef.struts2editor.model.INamedEntity;
import org.neclipse.gef.struts2editor.model.IPackage;

/**
 * @author nbhusare
 *
 */
public class S2PackageAbstractChangeCommand extends S2EntityNameChangeCommand  {

	public S2PackageAbstractChangeCommand(INamedEntity entity, String newName) {
		super(entity, newName);
	}

	@Override
	public void undo() {
		((IPackage)entity).setAbstractValue(oldValue);
	}
	
	@Override
	public void redo() {
		((IPackage)entity).setAbstractValue(newValue);
	}
	
}
