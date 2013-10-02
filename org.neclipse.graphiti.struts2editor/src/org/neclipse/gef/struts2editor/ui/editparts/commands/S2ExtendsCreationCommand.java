/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.neclipse.gef.struts2editor.model.IConnection;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.S2ExtendsConnection;

/**
 * @author nbhusare
 *
 */
public class S2ExtendsCreationCommand extends Command {
	
	private IPackage source;
	
	private IPackage target; 
	
	private IConnection connection;
	
	public S2ExtendsCreationCommand(IPackage source) {
		Assert.isNotNull(source, "Source of the connection cannot be null");
		this.source = source;
	}
	
	public void setTarget(IPackage target) {
		Assert.isNotNull(target, "Target of the connection cannot be null");
		this.target = target;
	}
	
	@Override
	public boolean canExecute() {
		if (source.equals(target)) return false;
		return true;
	}
	
	@Override
	public void redo() {
		connection.reconnect();
		
		/* Add the target package in the source package extends list */
		source.addExtend(target);
	}
	
	@Override
	public void undo() {
		connection.disconnect();
		
		/* Remove the package from the source package extends list */
		source.removeExtend(target);
	}
	
	@Override
	public void execute() {
		connection = new S2ExtendsConnection(source, target);
	}
}
