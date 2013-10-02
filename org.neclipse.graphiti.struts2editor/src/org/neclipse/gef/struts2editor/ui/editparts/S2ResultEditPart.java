/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.neclipse.gef.struts2editor.model.IResultConnection;
import org.neclipse.gef.struts2editor.model.S2JSPHTML;
import org.neclipse.gef.struts2editor.model.S2ResultConnection;
import org.neclipse.gef.struts2editor.ui.S2ConnectionEndpointLocator;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2ResultDirectEditPolicy;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2ResultEditPart extends S2ConnectionEntityEditPart {

	private class ResultFigure extends S2ConnectionFigure {
		
		private Label resultLabel;
		
		public ResultFigure() {
			/* Label to set the name of the result*/
			resultLabel = new Label();
			resultLabel.setOpaque(true);
			resultLabel.setBorder(new LineBorder());
			
			/* Set the properties of the connection */
			setLineStyle(Graphics.LINE_DASH);
			setLineWidth(1);
			setAntialias(SWT.ON);
			setTargetDecoration(new PolygonDecoration());
			setForegroundColor(ColorConstants.lightGray);
			
			/* For JSP/HTML nodes using the default End point locator */
			S2ResultConnection connection = (S2ResultConnection) getModel();
			if (connection.getTarget() instanceof S2JSPHTML) {
				ConnectionEndpointLocator locator = new ConnectionEndpointLocator(this, true);
				locator.setVDistance(-5);
				locator.setUDistance(5);
				add(resultLabel, locator);
			} else {
				S2ConnectionEndpointLocator locator = new S2ConnectionEndpointLocator(this, true);
				locator.setVDistance(-5);
				locator.setUDistance(5);
				add(resultLabel, locator);
			}
		}

		public Label getResultLabel() {
			return resultLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(resultLabel, location)) return resultLabel;
			return null;
		}
		
		public boolean isResultLabelAt(Point location) {
			return isLabelEdited(resultLabel, location);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		IResultConnection connection = (IResultConnection) getModel();
		ResultFigure figure = new ResultFigure();
		figure.getResultLabel().setText(connection.getName() + "#" + connection.getType());
		return figure;
	}
	
	private ResultFigure getResultFigure() {
		return (ResultFigure) getFigure();
	}
	
	public void selected() { 
		getResultFigure().setForegroundColor(ColorConstants.darkGray);
	}
	
	public void resetSelection() {
		getResultFigure().setForegroundColor(ColorConstants.lightGray);
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		
		if (IResultConnection.NAME_CHANGED.equals(property)) {
			handleNameChange((String) changeEvent.getNewValue());
		} else if (IResultConnection.TYPE_CHANGED.equals(property)) {
			handleTypeChange((String) changeEvent.getNewValue());
		} 
	}

	/**
	 * 
	 * @param newValue
	 */
	private void handleNameChange(String newValue) {
		getResultFigure().getResultLabel().setText(newValue);
		refreshVisuals();
	}

	/**
	 * 
	 * @param newValue
	 */
	private void handleTypeChange(String newValue) {
		String resutlLabel = getResultFigure().getResultLabel().getText();
		resutlLabel = resutlLabel + "#" + newValue;
		getResultFigure().getResultLabel().setText(resutlLabel);
		refreshVisuals();
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.ui.editparts.S2ConnectionEntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getResultFigure().getLabelAt(hitLocation) != null;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.ui.editparts.S2ConnectionEntityEditPart#getCellEditValidator(org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler)
	 */
	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		
		/* Add the role to allow direct editing of the Name and Namespace values */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2ResultDirectEditPolicy());
		
		/* Install the role to enable adding bend-points to the connection */
		//installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new ConnectionBendpointEditPolicy());
	}

	public boolean isResultLabelEdited(DirectEditRequest request) {
		return getResultFigure().isResultLabelAt(hitLocation);
	}
	
	public void revertLabelEdit(DirectEditRequest request, String oldValue) {
		Label label = null;
		IResultConnection connection = (IResultConnection) getModel();
		if (isResultLabelEdited(request)) {
			label = getResultFigure().getResultLabel();
			label.setText(connection.getName() + "#" + connection.getType());
		}
		label.setVisible(true);
		refreshVisuals();
	}
}
