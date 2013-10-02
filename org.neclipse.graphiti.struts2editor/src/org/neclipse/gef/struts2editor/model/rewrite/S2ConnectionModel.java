/**
 * 
 */
package org.neclipse.gef.struts2editor.model.rewrite;

/**
 * @author nbhusare
 *
 */
public class S2ConnectionModel implements IS2ConnectionModel {

	private static final long serialVersionUID = 1L;
	
	private boolean connected = false;
	
	public S2ConnectionModel(IS2ConnectableModel src, IS2ConnectableModel tgt) {
		connect(src, tgt);
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectionModel#reconnect(org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectableModel, org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectableModel)
	 */
	@Override
	public void connect(IS2ConnectableModel src, IS2ConnectableModel tgt) {
		if (!connected) {
			src.addConnection(this);
			tgt.addConnection(this);
			connected = true;
		}
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectionModel#disconnect(org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectableModel, org.neclipse.gef.struts2editor.model.rewrite.IS2ConnectableModel)
	 */
	@Override
	public void disconnect(IS2ConnectableModel src, IS2ConnectableModel tgt) {
		if (connected) {
			src.removeConnection(this);
			tgt.removeConnection(this);
			connected = false;
		}
	}
}
