/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.IEditorSite;
import org.neclipse.graphiti.struts2editor.ui.S2DirectEditManager;
import org.neclipse.graphiti.struts2editor.ui.S2LabelCellEditorLocator;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.graphiti.struts2editor.ui.validators.S2StatusMessageHandler;

/**
 * @author nbhusare
 *
 */
public abstract class S2EditableEditPart extends S2ModelEditPart {
	
	protected DirectEditManager directEditManager;
	protected Point hitLocation;
	
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
	 * Return the validator for cell edit operations.
	 * @param the handler for placing the error messages on the status line. 
	 * @return the validator for cell edit operations.
	 */
	protected abstract ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler);
}
