/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.io.Serializable;

/**
 * Interface representing the model element that is used for connection two
 * <code>IConnectableModel</code> model elements.
 * 
 * @author nbhusare
 * 
 */
public interface IS2ConnectionModel extends Serializable {
	
//	/**
//	 * Set the Source of the connection.
//	 * 
//	 * @param model the <code>IS2ConnectableModel</code> representing the source of the connection.
//	 */
//	void setSource(IS2ConnectableModel model);
//	
//	/**
//	 * Get the <code>IS2ConnectableModel</code> representing the source of the connection. 
//	 * 
//	 * @return the <code>IS2ConnectableModel</code> representing the source of the connection. 
//	 */
//	IS2ConnectableModel getSource();
//	
//	/**
//	 * Set the Target of the connection.
//	 * 
//	 * @param model the <code>IS2ConnectableModel</code> representing the Target of the connection.
//	 */
//	void setTarget(IS2ConnectableModel model);
//	
//	/**
//	 * Get the <code>IS2ConnectableModel</code> representing the Target of the connection. 
//	 * 
//	 * @return the <code>IS2ConnectableModel</code> representing the Target of the connection. 
//	 */
//	IS2ConnectableModel getTarget();
	
	/**
	 * Creating a connection between the source and the target.
	 * 
	 * @param source the source of the connection.
	 * @param target the target of the connection.
	 */
	void connect(IS2ConnectableModel source, IS2ConnectableModel target);
	
	/**
	 * Disconnecting the connection.
	 * 
	 * @param source the source of the connection.
	 * @param target the target of the connection.
	 */
	void disconnect(IS2ConnectableModel source, IS2ConnectableModel target);
}
