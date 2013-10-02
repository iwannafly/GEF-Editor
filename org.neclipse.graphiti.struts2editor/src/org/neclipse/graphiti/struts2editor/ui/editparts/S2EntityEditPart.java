/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorSite;
import org.neclipse.graphiti.struts2editor.model.IConstraintEnity;
import org.neclipse.graphiti.struts2editor.model.IPropertyChangeSupport;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model;
import org.neclipse.graphiti.struts2editor.model.rewrite.S2ModelCngConstants;
import org.neclipse.graphiti.struts2editor.ui.S2DirectEditManager;
import org.neclipse.graphiti.struts2editor.ui.S2LabelCellEditorLocator;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.graphiti.struts2editor.ui.validators.S2StatusMessageHandler;

/**
 * @author nbhusare
 *
 */
public abstract class S2EntityEditPart extends AbstractGraphicalEditPart implements NodeEditPart, PropertyChangeListener {
	
	protected DirectEditManager directEditManager;
	protected Point hitLocation;

	@Override
	public void activate() {
		/* Activate the Edit part if not active and then hook into the model */
		if (!isActive()) {
			super.activate();
			IPropertyChangeSupport model = ((IPropertyChangeSupport)getModel());
			model.addPropertyChangeListener(this);
		}
	}
	
	@Override
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			IPropertyChangeSupport model = ((IPropertyChangeSupport)getModel());
			model.removePropertyChangeListener(this);
		}
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
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
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
	protected void performDirectEdit(Point hitLocation) {
		Label editedLabel = ((S2Figure)getFigure()).getLabelAt(hitLocation);
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

//	/**
//	 * Checks if the edit operation if performed on the label.
//	 * @param label	the label that has to be checked.
//	 * @param hitLocation the location to be checked. 
//	 * @return	true if the location lies on the passed label.
//	 */
//	protected boolean isLabelEdited(Label label, Point hitLocation) {
//		/* Check if the hit location lies on the passed label */
//		label.translateToRelative(hitLocation);
//		if (label.containsPoint(hitLocation)) return true;
//		return false;
//	}
//	
	/**
	 * Handles the edit operation performed on the label. 
	 * @param request the edit request 
	 */
	public void handleLabelEdit(DirectEditRequest request) {
		S2Figure figure = (S2Figure) getFigure();
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
	 * Return the instance of the label that has been edited.
	 * @param hitLocationthe location where mouse has hit on the figure.
	 * @return the instance of the label that has been edited.
	 */
	//protected abstract Label getEditedLabel(Point hitLocation);
	
	/**
	 * Return the validator for cell edit operations.
	 * @param the handler for placing the error messages on the status line. 
	 * @return the validator for cell edit operations.
	 */
	protected abstract ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler);

	@Override
	protected void refreshVisuals() {
		Rectangle dimension = ((IS2Model) getModel()).getDimension();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), dimension);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String property = event.getPropertyName();
		
		if (S2ModelCngConstants.CHILD_ADDED.equals(property)
				|| S2ModelCngConstants.CHILD_REMOVED.equals(property)) {
			refreshChildren();
		} else if (S2ModelCngConstants.SOURCE_CONN_ADDED.equals(property)
				|| S2ModelCngConstants.SOURCE_CONN_REMOVED.equals(property)) {
			refreshSourceConnections();
		} else if (S2ModelCngConstants.TARGET_CONN_ADDED.equals(property)
				|| S2ModelCngConstants.TARGET_CONN_REMOVED.equals(property)) {
			refreshTargetConnections();
		} else if (S2ModelCngConstants.DIMENSION_CHG.equals(event.getPropertyName())) {
			refreshVisuals();
		}
	}
	
}
