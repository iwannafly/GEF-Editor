/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * This Class represents the Extends Connection.
 * 
 * @author nbhusare
 */
public class S2ResultConnection extends S2AbstractConnectionEntity implements IResultConnection{

	private String type;
	
	private boolean isConnected = false;
	
	public S2ResultConnection(IConnectableEntity source, IConnectableEntity target) {
		reconnect(source, target);
		setName(DEFAULT_NAME);
		setType(DEFAULT_TYPE);
	}
	
	@Override
	public void reconnect() {
		if (!isConnected) {
			getSource().addConnection(this);
			getTarget().addConnection(this);
			isConnected = true;
		}
	}

	@Override
	public void disconnect() {
		if (isConnected) {
			getSource().removeConnection(this);
			getTarget().removeConnection(this);
			isConnected = false;
		}
	}

	@Override
	public void reconnect(IConnectableEntity source, IConnectableEntity target) {
		disconnect();
		setSource(source);
		setTarget(target);
		reconnect();
	}

	public void setName(String name) {
		Assert.isNotNull(name, "Result Name cannot be null");
		this.name = name;
		firePropertyChange(IResultConnection.NAME_CHANGED, null, this.name);
	}
	
	@Override
	public void setType(String type) {
		Assert.isNotNull(type, "Result Type cannot be null");
		this.type = type;
		firePropertyChange(IResultConnection.TYPE_CHANGED, null, this.type);
	}

	@Override
	public String getType() {
		return type;
	}
}
