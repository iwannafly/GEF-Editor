/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorSite;
import org.neclipse.gef.struts2editor.model.IConstraintEnity;
import org.neclipse.gef.struts2editor.model.IPropertyChangeSupport;
import org.neclipse.gef.struts2editor.ui.S2DirectEditManager;
import org.neclipse.gef.struts2editor.ui.S2LabelCellEditorLocator;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.gef.struts2editor.ui.validators.S2StatusMessageHandler;

/**
 * @author nbhusare
 *
 */
public abstract class S2ConnectionEntityEditPart extends AbstractConnectionEditPart implements PropertyChangeListener{
	
	protected DirectEditManager directEditManager;
	protected Point hitLocation;

	
	@Override
	public void activate() {
		/* Activate the Edit part if not active and then hook into the model */
		if (!isActive()) {
			super.activate();
			IPropertyChangeSupport entity = ((IPropertyChangeSupport)getModel());
			entity.addPropertyChangeListener(this);
		}
	}
	
	@Override
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			IPropertyChangeSupport entity = ((IPropertyChangeSupport)getModel());
			entity.removePropertyChangeListener(this);
		}
	}
	
	@Override
	protected void createEditPolicies() {
		/* The connection should be selectable and it should display handles at the end points */
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		
		/* Allow removing the connection */
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
			
			@Override
			protected Command getDeleteCommand(GroupRequest request) {
				return null;
			}
		});
	}
	
	@Override
	public void performRequest(Request request) {
		/* If the request is a Direct edit request and the mouse is clicked over the Name/Namespace label area */
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			/* Check if the request is for editing labels */
			if (request instanceof DirectEditRequest
					&& isLabelEditRequest(((DirectEditRequest) request).getLocation().getCopy())) {
				hitLocation = ((DirectEditRequest) request).getLocation().getCopy();
				performDirectEdit(hitLocation);
			}
		}
	}
	
	/**
	 * Performs the edit operation.
	 */
	private void performDirectEdit(Point hitLocation) {
		Label editedLabel = ((S2ConnectionFigure)getFigure()).getLabelAt(hitLocation);
		//if (directEditManager == null) {
			/* Creating a new Validaton message handler */
			IEditorSite editorSite = (IEditorSite) ((DefaultEditDomain) getViewer()
					.getEditDomain()).getEditorPart().getSite();
			IValidationMessageHandler handler = new S2StatusMessageHandler(editorSite);
			
			/* The Cell validator */
			ICellEditorValidator validator = getCellEditValidator(handler);
			
			/* The Cell editor locator */
			CellEditorLocator locator = new S2LabelCellEditorLocator(editedLabel);
			
			/* Initializing the Direct edit manager */
			directEditManager = new S2DirectEditManager(this,
					TextCellEditor.class, locator, editedLabel, validator);
		//}
		directEditManager.show();
	}

	/**
	 * Handles the edit operation performed on the label. 
	 * @param request the edit request 
	 */
	public void handleLabelEdit(DirectEditRequest request) {
		S2ConnectionFigure figure = (S2ConnectionFigure) getFigure();
		Label label = figure.getLabelAt(hitLocation);
		label.setVisible(false);
		refreshVisuals();
	}
	
	/**
	 * Check if the request is for editing the label.
	 * @param hitLocation the location where mouse has hit on the figure.
	 * @return true if the request is for editing the label.
	 */
	protected abstract boolean isLabelEditRequest(Point hitLocation);
	
	/**
	 * Return the validator for cell edit operations.
	 * @param the handler for placing the error messages on the status line. 
	 * @return the validator for cell edit operations.
	 */
	protected abstract ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler);

	@Override
	protected void refreshVisuals() {
		Rectangle dimension = ((IConstraintEnity) getModel()).getDimension();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), dimension);
	}
}
