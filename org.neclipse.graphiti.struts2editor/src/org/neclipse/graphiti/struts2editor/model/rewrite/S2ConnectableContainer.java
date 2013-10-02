/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.util.ArrayList;
import java.util.List;

import org.neclipse.graphiti.struts2editor.model.IConnection;

/**
 * @author nbhusare
 *
 */
public class S2ConnectableContainer extends S2Container implements
		IS2ConnectableModel {

	private static final long serialVersionUID = 1L;
	
	/* List containing the connection outgoing from this model */
	private List<IS2ConnectionModel> outgoingConn = new ArrayList<IS2ConnectionModel>();
	
	/* List containing the connection comming into this model */
	private List<IS2ConnectionModel> incommingConn = new ArrayList<IS2ConnectionModel>(); 

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectableModel#addConnection(org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectionModel)
	 */
	@Override
	public void addConnection(IS2ConnectionModel connection) {
		
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectableModel#removeConnection(org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectionModel)
	 */
	@Override
	public void removeConnection(IS2ConnectionModel connection) {

	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectableModel#getOutgoingConnections()
	 */
	@Override
	public List<IS2ConnectionModel> getOutgoingConnections() {
		return outgoingConn;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2ConnectableModel#getIncommingConnections()
	 */
	@Override
	public List<IS2ConnectionModel> getIncommingConnections() {
		return incommingConn;
	}

}
