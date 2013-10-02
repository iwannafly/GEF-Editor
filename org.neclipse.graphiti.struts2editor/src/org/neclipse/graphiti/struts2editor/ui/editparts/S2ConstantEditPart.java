/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.neclipse.graphiti.struts2editor.model.IConstant;
import org.neclipse.graphiti.struts2editor.model.IPackage;
import org.neclipse.graphiti.struts2editor.ui.S2EditorUI;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2ConstantDirectEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2ConstantEditPart extends S2EntityEditPart {

	private static class ConstantFigure extends S2Figure {

		private Label nameLabel;
		private Label valueLabel;
		private Label constantLabel;
		
		public static class ChildFigure extends Label {
			
			public ChildFigure() {
				setOpaque(true);
				setBorder(new ChildFigureBorder(new Insets(4, 0, 0, 0)));
			}
			
			class ChildFigureBorder extends MarginBorder {
				
				public ChildFigureBorder(Insets insets) {
					super(insets);
				}
				
				@Override
				public void paint(IFigure figure, Graphics graphics,
						Insets insets) {
					/* Calling getPaintRectangle() initializes the AbstractBorder#tempRect bounds and crops the same */
					Rectangle editedRect = getPaintRectangle(figure, insets);
					
					/* After calling the above method, we can directly make use of the tempRect*/
					graphics.drawLine(editedRect.getTopLeft(), editedRect.getTopRight());
				}
			}
			
			@Override
			public Dimension getPreferredSize(int wHint, int hHint) {
				Dimension prefSize = super.getPreferredSize(wHint, hHint);
				Dimension defaultSize = new Dimension(SWT.DEFAULT, 20);
				prefSize.union(defaultSize);
				return prefSize;
			}
			
		}
		
		public ConstantFigure() {
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			/* Header Label */
			constantLabel = new Label("Constant");
			constantLabel.setLabelAlignment(PositionConstants.LEFT);
			constantLabel.setIcon(S2EditorUI.getImageDescriptor("icons/17405.thread_view.png").createImage());
			constantLabel.setBackgroundColor(new Color(null, 231, 225, 137));
			constantLabel.setOpaque(true);
			constantLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(constantLabel);
			
			/* Label for setting the Name of the Constant */
			nameLabel = new ChildFigure();
			nameLabel.setLabelAlignment(PositionConstants.LEFT);
			nameLabel.setToolTip(new Label("Set the Name of the Constant"));
			nameLabel.setBackgroundColor(new Color(null, 251, 248, 211));
			nameLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(nameLabel);
			
			/* Label for setting class of the Bean */
			valueLabel = new ChildFigure();
			valueLabel.setLabelAlignment(PositionConstants.LEFT);
			valueLabel.setToolTip(new Label("Set the Value of the Constant"));
			valueLabel.setBackgroundColor(new Color(null, 251, 248, 211));
			valueLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(valueLabel);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(140, 60);
		}
		
		/**
		 * Return the Name label.
		 * @return the Name label.
		 */
		public Label getNameLabel() {
			return nameLabel;
		}

		/**
		 * Return the Value label.
		 * @return the Value label.
		 */
		public Label getValueLabel() {
			return valueLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(nameLabel, location)) return nameLabel;
			else if (isLabelEdited(valueLabel, location)) return valueLabel; 
			return null;
		}
		
		/**
		 * Return true if the location lies on the Name Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Name Label.
		 */
		public boolean isNameLabelAt(Point location) {
			return isLabelEdited(nameLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Value Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Value Label.
		 */
		public boolean isValueLabelAt(Point location) {
			return isLabelEdited(valueLabel, location);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart#getCellEditValidator(org.neclipse.graphiti.struts2editor.ui.IValidationMessageHandler)
	 */
	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		return null;
	}
	
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		IConstant constant = (IConstant) getModel();
		if (isNameEdited(request)) {
			handleNameChange(constant.getName());
			label = getConstantFigure().getNameLabel();
			label.setVisible(true);
		} else if (isValueEdited(request)) {
			handleValueChange(constant.getValue());
			label = getConstantFigure().getValueLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		IConstant constant = (IConstant) getModel();
		ConstantFigure figure = new ConstantFigure();
		setName(constant.getName(), figure);
		setValue(constant.getValue(), figure);
		return figure;
	}
	
	/**
	 * Return the casted instance of the Constant figure.
	 * @return the casted instance of the Constant figure.
	 */
	private ConstantFigure getConstantFigure() {
		return (ConstantFigure) getFigure();
	}
	
	/**
	 * 
	 * @param name
	 * @param figure
	 */
	private void setName(String name, ConstantFigure figure) {
		figure.getNameLabel().setText(IConstant.NAME_PREFIX + name);
	}
	
	/**
	 * 
	 * @param value
	 * @param figure
	 */
	private void setValue(String value, ConstantFigure figure) {
		figure.getValueLabel().setText(IConstant.VALUE_PREFIX + value);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		/* Add the role to allow direct editing of the Name and Value attributes */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2ConstantDirectEditPolicy());
		
		/* Enable deleting the Include entity*/
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getConstantFigure().getLabelAt(hitLocation) != null;
	}
	
	/**
	 * Checks if the Constant Name label is edited. 
	 * @param request the edit request
	 * @return true if the Constant Name is edited.
	 */
	public boolean isNameEdited(DirectEditRequest request) {
		return getConstantFigure().isNameLabelAt(hitLocation);
	}
	
	/**
	 * Checks if the Constant Value label is edited. 
	 * @param request the edit request
	 * @return true if the Constant Value is edited.
	 */
	public boolean isValueEdited(DirectEditRequest request) {
		return getConstantFigure().isValueLabelAt(hitLocation);
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IConstant.NAME_CHANGE.equals(property)) {
			handleNameChange(property);
		} else if (IConstant.VALUE_CHANGE.equals(property)) {
			handleValueChange(property);	
		} else if (IPackage.DIMENSION_CHANGE.equals(property)
				|| IPackage.EXTENDS_CHANGED.equals(property)) {
			refreshVisuals();
		} 
	}

	private void handleValueChange(String value) {
		setValue(value, getConstantFigure());
		refreshVisuals();
	}

	private void handleNameChange(String name) {
		setName(name, getConstantFigure());
		refreshVisuals();
	}
}
