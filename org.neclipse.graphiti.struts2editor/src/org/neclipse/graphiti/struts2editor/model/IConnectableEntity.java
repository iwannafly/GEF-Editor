/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import java.util.List;

/**
 * This class represents the entity that is connectable.
 * 
 * @author nbhusare
 *
 */
public interface IConnectableEntity {
	
	String CONNECTION_ADDED = "Connection Added";
	
	String CONNECTION_REMOVED = "Connection Removed";
	
	/**
	 * Add connection to the entity.
	 * @param connection the connection to be added.
	 */
	void addConnection(IConnection connection);
	
	/**
	 * Remove connection from the entity.
	 * @param connection the connection to be removed
	 */
	void removeConnection(IConnection connection);
	
	/**
	 * 
	 * @return
	 */
	List<IConnection> getSourceConnections();
	
	/**
	 * 
	 * @return
	 */
	List<IConnection> getTargetConnections();
}
