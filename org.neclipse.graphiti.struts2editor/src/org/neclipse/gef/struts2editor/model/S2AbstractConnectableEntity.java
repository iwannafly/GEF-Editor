/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 *
 */
public abstract class S2AbstractConnectableEntity extends S2AbstractContainer
		implements IConnectableEntity {

	/* List containing the connection outgoing from this entity */
	private List<IConnection> sourceConnections = new ArrayList<IConnection>();
	
	/* List containing the connection comming into this entity */
	private List<IConnection> targetConnections = new ArrayList<IConnection>(); 
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.IConnectableEntity#addConnection(org.neclipse.gef.struts2editor.model.IConnection)
	 */
	@Override
	public void addConnection(IConnection connection) {
		Assert.isNotNull(connection, "Connection to be added cannot be null");
		if (connection.getSource() == this) {
			sourceConnections.add(connection);
			firePropertyChange(IConnection.SOURCE_CHANGED, null, connection);
		} else if (connection.getTarget() == this) {
			targetConnections.add(connection);
			firePropertyChange(IConnection.TARGET_CHANGED, null, connection);
		}
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.IConnectableEntity#removeConnection(org.neclipse.gef.struts2editor.model.IConnection)
	 */
	@Override
	public void removeConnection(IConnection connection) {
		Assert.isNotNull(connection, "Connection to be removed cannot be null");
		if (connection.getSource() == this) {
			sourceConnections.remove(connection);
			firePropertyChange(IConnection.SOURCE_CHANGED, null, connection);
		} else if (connection.getTarget() == this) {
			targetConnections.remove(connection);
			firePropertyChange(IConnection.TARGET_CHANGED, null, connection);
		}
	}

	@Override
	public List<IConnection> getSourceConnections() {
		return sourceConnections;
	}

	@Override
	public List<IConnection> getTargetConnections() {
		return targetConnections;
	}
}
