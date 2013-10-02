/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.neclipse.graphiti.struts2editor.model.INamedEntity;
import org.neclipse.graphiti.struts2editor.model.IPackage;

/**
 * @author nbhusare
 *
 */
public class S2PackageNamespaceChangeCommand extends S2EntityNameChangeCommand {

	public S2PackageNamespaceChangeCommand(INamedEntity entity, String newNamespace) {
		super(entity, newNamespace);
	}
	
	@Override
	public void undo() {
		((IPackage)entity).setNamespace(oldValue);
	}
	
	@Override
	public void redo() {
		((IPackage)entity).setNamespace(newValue);
	}

	@Override
	public void execute() {
		redo();
	}
}
