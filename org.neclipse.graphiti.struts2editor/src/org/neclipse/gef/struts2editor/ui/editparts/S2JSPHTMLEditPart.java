package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

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
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.neclipse.gef.struts2editor.model.IConnectableEntity;
import org.neclipse.gef.struts2editor.model.IConnection;
import org.neclipse.gef.struts2editor.model.IInclude;
import org.neclipse.gef.struts2editor.model.IJSPHTML;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.S2JSPHTML;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2ResultCreationCommand;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2JSPHTMLDirectEditPolicy;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.gef.struts2editor.ui.validators.S2IncludePathCellValidator;

public class S2JSPHTMLEditPart extends S2EntityEditPart {

private static class JSPHTMLFigure extends S2Figure {
		
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

		public JSPHTMLFigure() {
			/* Setting the layout for the Bean figure */
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			Label IncludeHeaderLabel = new Label("HTML/JSP");
			IncludeHeaderLabel.setLabelAlignment(PositionConstants.LEFT);
			IncludeHeaderLabel.setIcon(S2EditorUI.getImageDescriptor("icons/14474.PD_Plugin.gif").createImage());
			IncludeHeaderLabel.setBackgroundColor(ColorConstants.titleGradient);
			IncludeHeaderLabel.setForegroundColor(ColorConstants.titleForeground);
			IncludeHeaderLabel.setOpaque(true);
			IncludeHeaderLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(IncludeHeaderLabel);
			
			/* Label for setting class of the Bean */
			pathLabel = new ChildFigure();
			pathLabel.setLabelAlignment(PositionConstants.LEFT);
			pathLabel.setToolTip(new Label("Set the Path of the JSP/HTML resource"));
			pathLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(pathLabel);
		}
		
		/**
		 * Return the default dimension of the Bean.
		 * @return the default dimension of the Bean.
		 */
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(160, 80);
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
		IJSPHTML include = (IJSPHTML) getModel();
		JSPHTMLFigure figure = new JSPHTMLFigure();
		setPath(include.getPath(), figure);
		return figure;
	}

	private JSPHTMLFigure getJSPHTMLFigure() {
		return (JSPHTMLFigure) getFigure();
	}
	
	private void setPath(String path, JSPHTMLFigure figure) {
		figure.getPathLabel().setText(path);
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2JSPHTMLDirectEditPolicy());
		
		/* The Package should allow creating Extends connection */
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new S2JSPHTMLConnectionEditPolicy());
		
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
		} else if (IConnection.SOURCE_CHANGED.equals(property)) {
			refreshSourceConnections();
		} else if (IConnection.TARGET_CHANGED.equals(property)) {
			refreshTargetConnections();
		}
	}

	@Override
	protected List getModelSourceConnections() {
		return ((S2JSPHTML)getModel()).getSourceConnections();
	}

	@Override
	protected List getModelTargetConnections() {
		return ((S2JSPHTML)getModel()).getTargetConnections();
	}
	
	private void handlePathChange(String newValue) {
		setPath(newValue, getJSPHTMLFigure());
		refreshVisuals();
	}

	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getJSPHTMLFigure().getLabelAt(hitLocation) != null;
	}

	@Override
	protected ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler) {
		return new S2IncludePathCellValidator(handler);
	}
	
	public boolean isPathEdited(DirectEditRequest request) {
		return getJSPHTMLFigure().isPathLabelAt(hitLocation);
	}
	
	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		IJSPHTML jspHtml = (IJSPHTML) getModel();
		if (isPathEdited(request)) {
			setPath(jspHtml.getPath(), getJSPHTMLFigure());
			label = getJSPHTMLFigure().getPathLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}
	
	/**
	 * Policy to enable creation of Result connection.
	 * 
	 * @author nbhusare
	 */
	private static class S2JSPHTMLConnectionEditPolicy extends GraphicalNodeEditPolicy {

		@Override
		protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
			IConnectableEntity target = (IConnectableEntity) getHost().getModel();
			S2ResultCreationCommand command = (S2ResultCreationCommand) request.getStartCommand();
			command.setTarget(target);
			return command;
		}

		@Override
		protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
			return UnexecutableCommand.INSTANCE;
		}

		@Override
		protected Command getReconnectTargetCommand(ReconnectRequest request) {
			return UnexecutableCommand.INSTANCE;
		}

		@Override
		protected Command getReconnectSourceCommand(ReconnectRequest request) {
			return UnexecutableCommand.INSTANCE;
		}
	}
}
