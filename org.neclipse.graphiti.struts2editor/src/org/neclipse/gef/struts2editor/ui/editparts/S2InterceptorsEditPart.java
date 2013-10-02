/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.neclipse.gef.struts2editor.model.IContainer;
import org.neclipse.gef.struts2editor.model.IEntity;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2InterceptorsChildAddCommand;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2InterceptorsEditPart extends S2EntityEditPart {

	private static class InterceptorsFigure extends S2Figure {

		private BodyFigure bodyFigure;
		
		public static class BodyFigure extends Figure {
			
			public BodyFigure() {
				/* The Child figure will contain actions that can moved in any direction */
				setLayoutManager(new FlowLayout(false));
				setOpaque(true);
				setBorder(new ChildFigureBorder(new Insets(4, 0, 0, 0)));
				setForegroundColor(ColorConstants.black);
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
				int bodyHeight = getParent().getBounds().height - 10;
				Dimension defaultSize = new Dimension(SWT.DEFAULT, bodyHeight);
				prefSize.union(defaultSize);
				return prefSize;
			}
			
			@Override
			protected boolean useLocalCoordinates() { return true; }
		}

		public InterceptorsFigure() {
			ToolbarLayout layout = new ToolbarLayout();
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			/* Header Label */
			Label constantLabel = new Label("Interceptors");
			constantLabel.setLabelAlignment(PositionConstants.LEFT);
			constantLabel.setIcon(S2EditorUI.getImageDescriptor("icons/arraypartition_obj.gif").createImage());
			constantLabel.setBackgroundColor(new Color(null, 0xb7, 0xe4, 0xa8));
			constantLabel.setOpaque(true);
			constantLabel.setForegroundColor(ColorConstants.titleForeground);
			constantLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(constantLabel);
			
			bodyFigure = new BodyFigure();
			add(bodyFigure);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(200, 200);
		}

		@Override
		public Label getLabelAt(Point location) {
			return null;
		}
		
		public BodyFigure getBodyFigure() {
			return bodyFigure;
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
		InterceptorsFigure figure = new InterceptorsFigure();
		return figure;
	}
	
	/**
	 * Return the casted instance of the Constant figure.
	 * @return the casted instance of the Constant figure.
	 */
	private InterceptorsFigure getInterceptorsFigure() {
		return (InterceptorsFigure) getFigure();
	}
	
	@Override
	public IFigure getContentPane() {
		return getInterceptorsFigure().getBodyFigure();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new S2InterceptorsLayoutEditPolicy());
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.ui.editparts.S2EntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return false;
	}
	
	
	@Override
	protected List getModelChildren() {
		/* Return the children contained in the Interceptors model */
		IContainer container = (IContainer) getModel();
		return container.getChildren();
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IPackage.DIMENSION_CHANGE.equals(property)
				|| IPackage.EXTENDS_CHANGED.equals(property)) {
			refreshVisuals();
		} else if (IContainer.ADD_CHILD.equals(property)
				|| IContainer.REMOVE_CHILD.equals(property)) {
			refreshChildren();
		} 
	}
	
	/**
	 * Installing the Layout policy to allow creation of child Interceptors,
	 * 
	 * @author nbhusare
	 */
	private static class S2InterceptorsLayoutEditPolicy extends FlowLayoutEditPolicy {

		@Override
		protected Command createAddCommand(EditPart childPart, EditPart after) {
			IEntity child = (IEntity) childPart.getModel();
			IContainer container = (IContainer) getHost().getModel(); 
			return new S2InterceptorsChildAddCommand(child, container);
		}

		@Override
		protected Command getCreateCommand(CreateRequest request) {
			IEntity child = (IEntity) request.getNewObject();
			IContainer container = (IContainer) getHost().getModel();
			S2InterceptorsChildAddCommand command = new S2InterceptorsChildAddCommand(
					child, container);
			EditPart after = getInsertionReference(request);
			int index = getHost().getChildren().indexOf(after);
			command.setIndex(index);
			return command;
		}
		
		@Override
		protected Command createMoveChildCommand(EditPart child, EditPart after) {
			return UnexecutableCommand.INSTANCE;
		}
	}
}
