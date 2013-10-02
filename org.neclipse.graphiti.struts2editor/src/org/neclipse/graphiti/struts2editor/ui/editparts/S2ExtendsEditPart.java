/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.neclipse.graphiti.struts2editor.model.S2ExtendsConnection;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2ExtendsEditPart extends S2ConnectionEntityEditPart {

	private class ExtendsFigure extends S2ConnectionFigure {
		
		Label extendsLabel;
		
		public ExtendsFigure() {
			extendsLabel = new Label();
			extendsLabel.setOpaque(true);
			extendsLabel.setBorder(new LineBorder());
			ConnectionEndpointLocator locator = new ConnectionEndpointLocator(this, true);
			locator.setUDistance(10);
			locator.setVDistance(-5);
			add(extendsLabel, locator);
			setTargetDecoration(new PolygonDecoration());
			setAntialias(SWT.ON);
			setLineWidth(1);
			setForegroundColor(ColorConstants.darkGray);
		}

		public Label getExtendsLabel() {
			return extendsLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(extendsLabel, location)) return extendsLabel;
			return null;
		}
	}
	
	@Override
	protected IFigure createFigure() {
		S2ExtendsConnection extendsConnection = (S2ExtendsConnection) getModel();
		ExtendsFigure figure = new ExtendsFigure();
		figure.getExtendsLabel().setText(extendsConnection.getExtendsLabel());
		return figure;
	}
	
	private ExtendsFigure getExtendsFigure() {
		return (ExtendsFigure) getFigure();
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2ConnectionEntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2ConnectionEntityEditPart#getCellEditValidator(org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler)
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
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {}
}
