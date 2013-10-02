/**
 * 
 */
package org.neclipse.gef.struts2editor.model;


/**
 * This Class represents the Extends Connection.
 * 
 * @author nbhusare
 */
public class S2ExtendsConnection extends S2AbstractConnectionEntity implements IExtendsConnection{

	private boolean isConnected = false;
	
	public S2ExtendsConnection(IPackage source, IPackage target) {
		reconnect((IConnectableEntity)source, (IConnectableEntity)target);
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

	@Override
	public String getExtendsLabel() {
		return IExtendsConnection.EXTENDS_LABEL;
	}
}
