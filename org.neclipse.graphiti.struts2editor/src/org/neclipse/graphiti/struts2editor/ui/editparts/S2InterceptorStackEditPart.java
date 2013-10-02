/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
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
import org.neclipse.graphiti.struts2editor.model.IContainer;
import org.neclipse.graphiti.struts2editor.model.IEntity;
import org.neclipse.graphiti.struts2editor.model.IInterceptorStack;
import org.neclipse.graphiti.struts2editor.model.IPackage;
import org.neclipse.graphiti.struts2editor.model.S2InterceptorStack;
import org.neclipse.graphiti.struts2editor.ui.S2EditorUI;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2InterceptorsChildAddCommand;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;

/**
 * @author nbhusare
 *
 */
public class S2InterceptorStackEditPart extends S2EntityEditPart {

	private static class InterceptorStackFigure extends S2Figure {

		private BodyFigure bodyFigure;
		
		private Label nameLabel;
		
		public static class BodyFigure extends Figure {
			
			public BodyFigure() {
				/* The Child figure will contain actions that can moved in any direction */
				FlowLayout flowLayout = new FlowLayout(false);
				setLayoutManager(flowLayout);
				setOpaque(true);
				setBorder(new BodyFigureBorder(new Insets(4, 0, 0, 0)));
			}
			
			class BodyFigureBorder extends MarginBorder {

				public BodyFigureBorder(Insets insets) {
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
				int bodyHeight = getParent().getBounds().height - 17;
				Dimension defaultSize = new Dimension(SWT.DEFAULT, bodyHeight);
				prefSize.union(defaultSize);
				return prefSize;
			}
			
			@Override
			protected boolean useLocalCoordinates() { return true; }
		}

		public static class ChildLabelFigure extends Label {
			
			public ChildLabelFigure() {
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
				setBackgroundColor(new Color(null, 0xe7, 0xfd, 0xd8));
			}
			
			@Override
			public void paint(IFigure figure, Graphics graphics,
					Insets insets) {
				/* Calling getPaintRectangle() initializes the AbstractBorder#tempRect bounds and crops the same */
				Rectangle editedRect = getPaintRectangle(figure, insets);
				
				/* After calling the above method, we can directly make use of the tempRect*/
				graphics.drawLine(editedRect.getTopLeft(), editedRect.getTopRight());
				
				/* Reduce the height by one pixel*/
				editedRect.resize(0, -1);
				
				/* Draw the line at the bottom of the body figure.*/
				graphics.drawLine(editedRect.getBottomLeft(), editedRect.getBottomRight());
			}
		}

		public InterceptorStackFigure() {
			ToolbarLayout layout = new ToolbarLayout();
			setLayoutManager(layout);
			setBorder(new ParentFigureBorder(new Insets(1, 0, 0, 0)));
			
			/* Header Label */
			Label iStackLabel = new Label("Interceptor-stack");
			iStackLabel.setLabelAlignment(PositionConstants.LEFT);
			iStackLabel.setIcon(S2EditorUI.getImageDescriptor("icons/thin_max_view.gif").createImage());
			iStackLabel.setBackgroundColor(new Color(null, 0xb7, 0xe4, 0xa8));
			iStackLabel.setOpaque(true);
			iStackLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(iStackLabel);
			
			/* Label for setting the Package Name and the icon */
			nameLabel = new ChildLabelFigure();
			nameLabel.setLabelAlignment(PositionConstants.LEFT);
			nameLabel.setToolTip(new Label("Set the Name of the Interceptor stack"));
			nameLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(nameLabel);
			
			bodyFigure = new BodyFigure();
			add(bodyFigure);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			Dimension prefSize = super.getPreferredSize(wHint, hHint);
			Dimension defaultSize = new Dimension(getParent().getSize().width, 60);
			prefSize.union(defaultSize);
			return prefSize;
		}

		@Override
		public Label getLabelAt(Point location) {
			return null;
		}
		
		public BodyFigure getBodyFigure() {
			return bodyFigure;
		}
		
		public Label getNameLabel() {
			return nameLabel;
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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		S2InterceptorStack iStack = (S2InterceptorStack) getModel();
		InterceptorStackFigure figure = new InterceptorStackFigure();
		figure.getNameLabel().setText(IInterceptorStack.NAME_PREFIX + iStack.getName());
		return figure;
	}
	
	/**
	 * Return the casted instance of the Constant figure.
	 * @return the casted instance of the Constant figure.
	 */
	private InterceptorStackFigure getInterceptorsFigure() {
		return (InterceptorStackFigure) getFigure();
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new S2InterceptorStackLayoutEditPolicy());
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
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
	private static class S2InterceptorStackLayoutEditPolicy extends FlowLayoutEditPolicy {

		@Override
		protected EditPolicy createChildEditPolicy(EditPart child) {
			// TODO Auto-generated method stub
			return super.createChildEditPolicy(child);
		}
		
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
