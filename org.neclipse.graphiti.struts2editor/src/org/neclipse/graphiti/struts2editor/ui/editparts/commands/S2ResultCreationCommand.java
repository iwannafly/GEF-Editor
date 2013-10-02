/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.commands.Command;
import org.neclipse.graphiti.struts2editor.model.IConnectableEntity;
import org.neclipse.graphiti.struts2editor.model.IConnection;
import org.neclipse.graphiti.struts2editor.model.S2ResultConnection;

/**
 * @author nbhusare
 *
 */
public class S2ResultCreationCommand extends Command {
	
	private IConnectableEntity source;
	
	private IConnectableEntity target; 
	
	private IConnection connection;
	
	public S2ResultCreationCommand(IConnectableEntity source) {
		Assert.isNotNull(source, "Source of the connection cannot be null");
		this.source = source;
	}
	
	public void setTarget(IConnectableEntity target) {
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
	}
	
	@Override
	public void undo() {
		connection.disconnect();
	}
	
	@Override
	public void execute() {
		connection = new S2ResultConnection(source, target);
	}
}
