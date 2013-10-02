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
import org.neclipse.graphiti.struts2editor.model.IInclude;
import org.neclipse.graphiti.struts2editor.model.IPackage;
import org.neclipse.graphiti.struts2editor.model.S2Include;
import org.neclipse.graphiti.struts2editor.ui.S2EditorUI;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2IncludeDirectEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.graphiti.struts2editor.ui.validators.S2IncludePathCellValidator;

public class S2IncludeEditPart extends S2EntityEditPart {

private static class IncludeFigure extends S2Figure {
		
		private Label pathLabel;
		
		
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

		public IncludeFigure() {
			/* Setting the layout for the Bean figure */
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			Label IncludeHeaderLabel = new Label("Include");
			IncludeHeaderLabel.setLabelAlignment(PositionConstants.LEFT);
			IncludeHeaderLabel.setIcon(S2EditorUI.getImageDescriptor("icons/showchild_mode.gif").createImage());
			IncludeHeaderLabel.setBackgroundColor(new Color(null, 219, 187, 190));
			IncludeHeaderLabel.setOpaque(true);
			IncludeHeaderLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(IncludeHeaderLabel);
			
			/* Label for setting class of the Bean */
			pathLabel = new ChildFigure();
			pathLabel.setLabelAlignment(PositionConstants.LEFT);
			pathLabel.setToolTip(new Label("Set the Path of the included configuration file"));
			pathLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			pathLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(pathLabel);
		}
		
		/**
		 * Return the default dimension of the Bean.
		 * @return the default dimension of the Bean.
		 */
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(160, 38);
		}
		
		/**
		 * Return the Path label of the Include.
		 * @return the Path label of the Include.
		 */
		public Label getPathLabel() {
			return pathLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(pathLabel, location)) return pathLabel;
			return null;
		}
		
		/**
		 * Return true if the location lies on the Path Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Path Label.
		 */
		public boolean isPathLabelAt(Point location) {
			return isLabelEdited(pathLabel, location);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		IInclude include = (IInclude) getModel();
		IncludeFigure figure = new IncludeFigure();
		setPath(include.getPath(), figure);
		return figure;
	}

	private IncludeFigure getIncludeFigure() {
		return (IncludeFigure) getFigure();
	}
	
	private void setPath(String path, IncludeFigure figure) {
		figure.getPathLabel().setText(IInclude.PATH_PREFIX + path);
	}
	
	@Override
	protected void createEditPolicies() {
		/* Install Editing role */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2IncludeDirectEditPolicy());
		
		/* Enable deleting the Include entity*/
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IInclude.PATH_CHANGED.equals(property)) {
			handlePathChange((String)changeEvent.getNewValue());
		} else if (IPackage.DIMENSION_CHANGE.equals(property)
				|| IPackage.EXTENDS_CHANGED.equals(property)) {
			refreshVisuals();
		}
	}

	private void handlePathChange(String newValue) {
		setPath(newValue, getIncludeFigure());
		refreshVisuals();
	}

	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getIncludeFigure().getLabelAt(hitLocation) != null;
	}

	@Override
	protected ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler) {
		return new S2IncludePathCellValidator(handler);
	}
	
	public boolean isPathEdited(DirectEditRequest request) {
		return getIncludeFigure().isPathLabelAt(hitLocation);
	}
	
	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		S2Include include = (S2Include) getModel();
		if (isPathEdited(request)) {
			handlePathChange(include.getPath());
			label = getIncludeFigure().getPathLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}
}
