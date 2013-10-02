/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.neclipse.graphiti.struts2editor.model.rewrite.S2ModelCngConstants;

/**
 * @author nbhusare
 * 
 */
public abstract class S2ConnectableContainerEditPart extends S2ContainerEditPart
		implements NodeEditPart {

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		String property = event.getPropertyName();
		
		if (S2ModelCngConstants.SOURCE_CONN_ADDED.equals(property)
				|| S2ModelCngConstants.SOURCE_CONN_REMOVED.equals(property)) {
			refreshSourceConnections();
		} else if (S2ModelCngConstants.TARGET_CONN_ADDED.equals(property)
				|| S2ModelCngConstants.TARGET_CONN_REMOVED.equals(property)) {
			refreshTargetConnections();
		}
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
	}
}
