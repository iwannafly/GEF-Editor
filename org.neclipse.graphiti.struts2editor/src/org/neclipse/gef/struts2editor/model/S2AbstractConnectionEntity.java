/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.Bendpoint;

/**
 * @author nbhusare
 *
 */
public abstract class S2AbstractConnectionEntity extends S2AbstractEntity implements IConnection {

	/* The Label on the connection */
	protected String label;
	
	/* The Source of the connection */
	private IConnectableEntity source;
	
	/* The Target of the connection */
	private IConnectableEntity target;

	private List<Bendpoint> bendpoints = new ArrayList<Bendpoint>();
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.IClonable#cloneEntity()
	 */
	@Override
	public Object cloneEntity() {
		return null;
	}

	@Override
	public void setSource(IConnectableEntity entity) {
		Assert.isNotNull(entity, "Connection Source cannot be null");
		this.source = entity;
		firePropertyChange(IConnection.SOURCE_CHANGED, null, this.source);
	}

	@Override
	public IConnectableEntity getSource() {
		return source;
	}

	@Override
	public void setTarget(IConnectableEntity entity) {
		Assert.isNotNull(entity, "Connection Target cannot be null");
		this.target = entity;
		firePropertyChange(IConnection.TARGET_CHANGED, null, this.target);
	}

	@Override
	public IConnectableEntity getTarget() {
		return target;
	}
	
	@Override
	public void insertBendpoint(int index, Bendpoint point) {
		getBendpoints().add(index, point);
		firePropertyChange(IConnection.BENDPOINT_ADDED, null, null);
	}
	
	@Override
	public void removeBendpoint(int index) {
		getBendpoints().remove(index);
		firePropertyChange(IConnection.BENDPOINT_REMOVED, null, null);
	}
	
	@Override
	public void setBendpoint(int index, Bendpoint point) {
		getBendpoints().set(index, point);
		firePropertyChange(IConnection.BENDPOINT_ADDED, null, null);
	}
	
	@Override
	public void setBendpoints(List<Bendpoint> points) {
		this.bendpoints = points;
		firePropertyChange(IConnection.BENDPOINTS_ADDED, null, null);
	}
	
	@Override
	public List<Bendpoint> getBendpoints() {
		return bendpoints;
	}
}
