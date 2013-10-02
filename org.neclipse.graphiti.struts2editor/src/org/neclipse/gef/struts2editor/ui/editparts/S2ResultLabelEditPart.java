package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.IResultConnection;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2ResultDirectEditPolicy;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;

public class S2ResultLabelEditPart extends S2EntityEditPart {

private static class ResultLabelFigure extends S2Figure {
		
		private Label resultLabel;
		
		public ResultLabelFigure() {
			/* Setting the layout for the Bean figure */
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			/* Label for setting class of the Bean */
			resultLabel = new Label();
			resultLabel.setLabelAlignment(PositionConstants.RIGHT);
			add(resultLabel);
		}
		
		/**
		 * Return the Path label of the Include.
		 * @return the Path label of the Include.
		 */
		public Label getResultLabel() {
			return resultLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(resultLabel, location)) return resultLabel;
			return null;
		}
		
		/**
		 * Return true if the location lies on the Path Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Path Label.
		 */
		public boolean isResultLabelAt(Point location) {
			return isLabelEdited(resultLabel, location);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		IResultConnection connection = (IResultConnection) getModel();
		ResultLabelFigure figure = new ResultLabelFigure();
		figure.getResultLabel().setText(connection.getName() + "#" + connection.getType());
		return figure;
	}

	private ResultLabelFigure getResultFigure() {
		return (ResultLabelFigure) getFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		/* Install Editing role */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2ResultDirectEditPolicy());
		
		/* Enable deleting the Include entity*/
		//installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IResultConnection.NAME_CHANGED.equals(property)) {
			handleNameChange((String) changeEvent.getNewValue());
		} else if (IResultConnection.TYPE_CHANGED.equals(property)) {
			handleTypeChange((String) changeEvent.getNewValue());
		} else if (IPackage.DIMENSION_CHANGE.equals(property)) {
			refreshVisuals();
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
	

	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getResultFigure().getLabelAt(hitLocation) != null;
	}

	@Override
	protected ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler) {
		return null;
	}
	
	public boolean isResultLabelEdited(DirectEditRequest request) {
		return getResultFigure().isResultLabelAt(hitLocation);
	}
	
	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
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
