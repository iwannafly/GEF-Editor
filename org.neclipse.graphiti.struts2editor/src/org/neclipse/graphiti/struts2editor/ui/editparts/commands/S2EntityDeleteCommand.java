/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.neclipse.graphiti.struts2editor.model.IConnectableEntity;
import org.neclipse.graphiti.struts2editor.model.IConnection;
import org.neclipse.graphiti.struts2editor.model.IContainer;
import org.neclipse.graphiti.struts2editor.model.IEntity;

/**
 * @author nbhusare
 * 
 */
public class S2EntityDeleteCommand extends Command {
	
	private IContainer parent;
	private IEntity child;
	private List<IConnection> sourceConnections, targetConnections;
	
	public S2EntityDeleteCommand(IContainer parent, IEntity child) {
		this.parent = parent;
		this.child = child;
	}
	
	private void addConnections(List<IConnection> connections) {
		if (connections == null) return;
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			IConnection connection = (IConnection) iter.next();
			connection.reconnect();
		}
	}

	private void removeConnections(List<IConnection> connections) {
		if (connections == null) return;
		for (Iterator iter = connections.iterator(); iter.hasNext();) {
			IConnection connection = (IConnection) iter.next();
			connection.disconnect();
		}
	}
	
	public void redo() {
		removeConnections(sourceConnections);
		removeConnections(targetConnections);
		parent.removeChild(child);
	}
	
	@Override
	public void undo() {
		if (parent.addChild(child)) {
			addConnections(sourceConnections);
			addConnections(targetConnections);
		}
	}
	
	@Override
	public void execute() {
		if (child instanceof IConnectableEntity) {
			IConnectableEntity cEntity = (IConnectableEntity) child;
			sourceConnections = new ArrayList<IConnection>(cEntity.getSourceConnections());
			targetConnections = new ArrayList<IConnection>(cEntity.getTargetConnections());
		}
		redo();
	}
}
