/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.neclipse.gef.struts2editor.model.IInterceptor;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2InterceptorEditPart extends S2EntityEditPart {

	private static class InterceptorFigure extends S2Figure {

		private Label nameLabel;
		private Label classLabel;
		
		public static class ChildFigure extends Label {
			
			public ChildFigure() {
				setOpaque(true);
				setBorder(new ChildFigureBorder(new Insets(4, 0, 0, 0)));
				setBackgroundColor(new Color(null, 0xe7, 0xfd, 0xd8));
				setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
				setLabelAlignment(PositionConstants.LEFT);
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
					
					editedRect.resize(0, -1);	
					
					/* After calling the above method, we can directly make use of the tempRect*/
					graphics.drawLine(editedRect.getBottomLeft(), editedRect.getBottomRight());
					
					editedRect.resize(0, 1);
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
		
		public static class ChildFigure2 extends Label {
			
			public ChildFigure2() {
				setOpaque(true);
				setBorder(new ChildFigureBorder(new Insets(4, 0, 0, 0)));
				setBackgroundColor(new Color(null, 0xe7, 0xfd, 0xd8));
				setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
				setLabelAlignment(PositionConstants.LEFT);
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
					
					editedRect.resize(0, -1);	
					
					/* After calling the above method, we can directly make use of the tempRect*/
					graphics.drawLine(editedRect.getBottomLeft(), editedRect.getBottomRight());
				}
			}
			
			@Override
			public Dimension getPreferredSize(int wHint, int hHint) {
				Dimension defaultSize = new Dimension(SWT.DEFAULT, 20);
				return defaultSize;
			}
			
		}
		
		
		class ParentFigureBorder extends MarginBorder {
			
			public ParentFigureBorder(Insets insets) {
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
		
		public InterceptorFigure() {
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new ParentFigureBorder(new Insets(1, 0, 0, 0)));
			
			/* Header Label */
			Label header = new Label("Interceptor");
			header.setLabelAlignment(PositionConstants.LEFT);
			header.setIcon(S2EditorUI.getImageDescriptor("icons/remove_correction.gif").createImage());
			header.setBackgroundColor(new Color(null, 0xb7, 0xe4, 0xa8));
			header.setOpaque(true);
			header.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(header);
			
			/* Label for setting the Name of the Constant */
			nameLabel = new ChildFigure();
			nameLabel.setToolTip(new Label("Set the Name of the Interceptor"));
			add(nameLabel);
			
			/* Label for setting class of the Bean */
			classLabel = new ChildFigure2();
			classLabel.setToolTip(new Label("Set the Class of the Interceptor"));
			add(classLabel);
		}
		
		/**
		 * Return the Name label.
		 * @return the Name label.
		 */
		public Label getNameLabel() {
			return nameLabel;
		}

		/**
		 * Return the Class label.
		 * @return the Class label.
		 */
		public Label getClassLabel() {
			return classLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(nameLabel, location)) return nameLabel;
			else if (isLabelEdited(classLabel, location)) return classLabel; 
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
		public boolean isClassLabelAt(Point location) {
			return isLabelEdited(classLabel, location);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			IFigure parentFigure = getParent();
			Dimension defaultSize = new Dimension(parentFigure.getSize().width, 60);
			return defaultSize;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.ui.editparts.S2EntityEditPart#getCellEditValidator(org.neclipse.gef.struts2editor.ui.IValidationMessageHandler)
	 */
	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		IInterceptor interceptor = (IInterceptor) getModel();
		InterceptorFigure figure = new InterceptorFigure();
		figure.getNameLabel().setText(interceptor.getName());
		figure.getClassLabel().setText(interceptor.getIntClass());
		return figure;
	}
	
	/**
	 * Return the casted instance of the Constant figure.
	 * @return the casted instance of the Constant figure.
	 */
	private InterceptorFigure getConstantFigure() {
		return (InterceptorFigure) getFigure();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.ui.editparts.S2EntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IInterceptor.NAME_CHANGE.equals(property)) {
			handleNameChange(property);
		} else if (IInterceptor.CLASS_CHANGED.equals(property)) {
			handleClassChange(property);	
		} else if (IPackage.DIMENSION_CHANGE.equals(property)
				|| IPackage.EXTENDS_CHANGED.equals(property)) {
			refreshVisuals();
		} 
	}

	private void handleClassChange(String value) {
		getConstantFigure().getClassLabel().setText(value);
		refreshVisuals();
	}

	private void handleNameChange(String name) {
		getConstantFigure().getNameLabel().setText(name);
		refreshVisuals();
	}
}
