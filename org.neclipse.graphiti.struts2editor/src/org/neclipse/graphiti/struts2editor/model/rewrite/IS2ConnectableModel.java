/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.io.Serializable;
import java.util.List;

/**
 * @author nbhusare
 *
 */
public interface IS2ConnectableModel extends Serializable{
	
	/**
	 * Add a new Connection model element in the source or target model element.
	 * 
	 * @param connection new Connection model element.
	 */
	void addConnection(IS2ConnectionModel connection);
	
	/**
	 * Remove a connection model element.
	 * 
	 * @param connection the connection model element to be removed.
	 */
	void removeConnection(IS2ConnectionModel connection);
	
	/**
	 * Return the connections that are originating from the model element.
	 *  
	 * @return the connections that are originating from the model element. 
	 */
	List<IS2ConnectionModel> getOutgoingConnections();
	
	/**
	 * Return the incoming connections of the model element.
	 *  
	 * @return the incoming connections of the model element.
	 */
	List<IS2ConnectionModel> getIncommingConnections();
}
