/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import java.util.List;

import org.eclipse.draw2d.Bendpoint;

/**
 * @author nbhusare
 *
 */
public interface IConnection extends IEntity {

	String SOURCE_CHANGED = "Source Changed";
	
	String TARGET_CHANGED = "Target Changed";
	
	String BENDPOINT_ADDED = "Bendpoint Added";
	
	String BENDPOINT_REMOVED = "Bendpoint Removed";
	
	String BENDPOINTS_ADDED = "Bendpoints Added";
	
	void setSource(IConnectableEntity entity);
	
	IConnectableEntity getSource();
	
	void setTarget(IConnectableEntity entity);
	
	IConnectableEntity getTarget();
	
	void reconnect();
	
	void disconnect();
	
	void reconnect(IConnectableEntity source, IConnectableEntity target);
	
	void insertBendpoint(int index, Bendpoint point);
	
	void removeBendpoint(int index);
	
	void setBendpoint(int index, Bendpoint point);
	
	void setBendpoints(List<Bendpoint> points);
	
	List<Bendpoint> getBendpoints();
	
}
