/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

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
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
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
import org.eclipse.swt.graphics.Color;
import org.neclipse.graphiti.struts2editor.model.IAction;
import org.neclipse.graphiti.struts2editor.model.IConnectableEntity;
import org.neclipse.graphiti.struts2editor.model.IConnection;
import org.neclipse.graphiti.struts2editor.model.IConstraintEnity;
import org.neclipse.graphiti.struts2editor.model.S2Action;
import org.neclipse.graphiti.struts2editor.ui.S2EditorUI;
import org.neclipse.graphiti.struts2editor.ui.editparts.commands.S2ResultCreationCommand;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2ActionDirectEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;
import org.neclipse.graphiti.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.graphiti.struts2editor.ui.validators.S2ActionMethodCellValidator;
import org.neclipse.graphiti.struts2editor.ui.validators.S2ActionNameCellValidator;
import org.neclipse.graphiti.struts2editor.ui.validators.S2ClassCellValidator;

/**
 * @author nbhusare
 *
 */
public class S2ActionEditPart extends S2EntityEditPart {

	private static class ActionFigure extends S2Figure {

		private Label nameLabel;
		private Label ClassLabel;
		private Label methodLabel;
		
		public static class ChildFigure extends Label {
			
			public ChildFigure() {
				setOpaque(true);
				setBackgroundColor(new Color(null, 0xe7, 0xfd, 0xd8));
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
		
		public ActionFigure() {
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			/* Header Label */
			Label actionHeader = new Label("Action");
			actionHeader.setLabelAlignment(PositionConstants.LEFT);
			actionHeader.setIcon(S2EditorUI.getImageDescriptor("icons/innerclass_public_obj.gif").createImage());
			//actionHeader.setBackgroundColor(new Color(null, 158, 214, 211));
			actionHeader.setBackgroundColor(new Color(null, 0xb7, 0xe4, 0xa8));
			actionHeader.setForegroundColor(ColorConstants.titleForeground);
			actionHeader.setOpaque(true);
			actionHeader.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(actionHeader);
			
			/* Label for setting the Name of the Action */
			nameLabel = new ChildFigure();
			nameLabel.setLabelAlignment(PositionConstants.LEFT);
			nameLabel.setToolTip(new Label("Set the Name of the Action"));
			nameLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(nameLabel);
			
			/* Label for setting class of the Action */
			ClassLabel = new ChildFigure();
			ClassLabel.setLabelAlignment(PositionConstants.LEFT);
			ClassLabel.setToolTip(new Label("Set the Value of the Action Handler"));
			ClassLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(ClassLabel);
			
			/* Label for setting the Entry Method of the Action */
			methodLabel = new ChildFigure();
			methodLabel.setLabelAlignment(PositionConstants.LEFT);
			methodLabel.setToolTip(new Label("Set the Entry Method of the Action Handler"));
			methodLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(methodLabel);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(140, 78);
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
		public Label getClassLabel() {
			return ClassLabel;
		}

		/**
		 * Return the Method label.
		 * @return the Method label.
		 */
		public Label getMethodLabel() {
			return methodLabel;
		}
		
		@Override
		protected boolean isLabelEdited(Label label, Point hitLocation) {
			/*
			 * We need to make a copy here for child Action since it lies in the
			 * local co-ordinates of the Package body
			 */
			Point hitLocationCopy = hitLocation.getCopy();
			
			/* Check if the hit location lies on the passed label */
			label.translateToRelative(hitLocationCopy);
			if (label.containsPoint(hitLocationCopy)) return true;
			return false;
		}
		
		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(nameLabel, location)) return nameLabel;
			else if (isLabelEdited(ClassLabel, location)) return ClassLabel;
			else if (isLabelEdited(methodLabel, location)) return methodLabel;
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
		 * Return true if the location lies on the Class Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Class Label.
		 */
		public boolean isClassLabelAt(Point location) {
			return isLabelEdited(ClassLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Method Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Method Label.
		 */
		public boolean isMethodLabelAt(Point location) {
			return isLabelEdited(methodLabel, location);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart#getCellEditValidator(org.neclipse.graphiti.struts2editor.ui.IValidationMessageHandler)
	 */
	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		if (isNameEdited(null)) {
			return new S2ActionNameCellValidator(handler, (S2Action) getModel());
		} else if (isClassEdited(null)) {
			return new S2ClassCellValidator(handler);
		} else if (isMethodEdited(null)) {
			return new S2ActionMethodCellValidator(handler);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		IAction action = (IAction) getModel();
		ActionFigure figure = new ActionFigure();
		setNameValue(action.getName(), figure);
		setClassValue(action.getActionClass(), figure);
		setMethodValue(action.getMethod(), figure);
		return figure;
	}
	
	/**
	 * Return the casted instance of the Constant figure.
	 * @return the casted instance of the Constant figure.
	 */
	private ActionFigure getActionFigure() {
		return (ActionFigure) getFigure();
	}
	
	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		/* On selection of the editpart, highlight all the connections that are going out from the object */
		if (EditPart.SELECTED_PRIMARY == value) {
			List<ConnectionEditPart> sourceConnections = this.getSourceConnections();
			if (sourceConnections != null) {
				for (ConnectionEditPart connection : sourceConnections) {
					((S2ResultEditPart)connection).selected();
				}
			}
		} else {
			List<ConnectionEditPart> sourceConnections = this.getSourceConnections();
			if (sourceConnections != null) {
				for (ConnectionEditPart connection : sourceConnections) {
					((S2ResultEditPart)connection).resetSelection();
				}
			}
		}
	}
	
	/**
	 * Set the value of the Name attribute on the action name label.
	 * @param name	the value of the Name attribute.	
	 * @param actionFigure the actionFigure figure.
	 */
	private void setNameValue(String name, ActionFigure actionFigure) {
		actionFigure.getNameLabel().setText(IAction.NAME_PREFIX + name);
	}
	
	/**
	 * Set the value of the Class attribute on the package name label.
	 * @param name	the value of the Class attribute.	
	 * @param actionFigure the actionFigure figure.
	 */
	private void setClassValue(String aClass, ActionFigure actionFigure) {
		actionFigure.getClassLabel().setText(IAction.CLASS_PREFIX + aClass);
	}
	
	/**
	 * Set the value of the Method attribute on the package name label.
	 * @param name	the value of the Method attribute.	
	 * @param actionFigure the actionFigure figure.
	 */
	private void setMethodValue(String method, ActionFigure actionFigure) {
		actionFigure.getMethodLabel().setText(IAction.METHOD_PREFIX + method);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		/* Role to enable deletion of Actions from the editor */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
		
		/* The Package should allow creating Extends connection */
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new S2ActionConnectionEditPolicy());
		
		/* Add the role to allow direct editing of the Name and Namespace values */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2ActionDirectEditPolicy());
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.ui.editparts.S2EntityEditPart#isLabelEditRequest(org.eclipse.draw2d.geometry.Point)
	 */
	@Override
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getActionFigure().getLabelAt(hitLocation) != null;
	}
	
	/**
	 * Checks if the Package Name label is edited. 
	 * @param request the edit request
	 * @return true if the Package Name is edited.
	 */
	public boolean isNameEdited(DirectEditRequest request) {
		return getActionFigure().isNameLabelAt(hitLocation);
	}
	
	/**
	 * Checks if the Package Name label is edited. 
	 * @param request the edit request
	 * @return true if the Package Name is edited.
	 */
	public boolean isClassEdited(DirectEditRequest request) {
		return getActionFigure().isClassLabelAt(hitLocation);
	}
	
	/**
	 * Checks if the Package Name label is edited. 
	 * @param request the edit request
	 * @return true if the Package Name is edited.
	 */
	public boolean isMethodEdited(DirectEditRequest request) {
		return getActionFigure().isMethodLabelAt(hitLocation);
	}
	
	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		IAction action = (IAction) getModel();
		if (isNameEdited(request)) {
			setNameValue(action.getName(), getActionFigure());
			label = getActionFigure().getNameLabel();
			label.setVisible(true);
		} else if (isClassEdited(request)) {
			setClassValue(action.getActionClass(), getActionFigure());
			label = getActionFigure().getClassLabel();
			label.setVisible(true);
		} else if (isMethodEdited(request)) {
			setMethodValue(action.getMethod(), getActionFigure());
			label = getActionFigure().getMethodLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IAction.NAME_CHANGE.equals(property)) {
			handleNameChange((String) changeEvent.getNewValue());
		} else if (IAction.CLASS_CHANGED.equals(property)) {
			handleClassChange((String) changeEvent.getNewValue());	
		} else if (IAction.METHOD_CHANGED.equals(property)) {
			handleMethodChange((String) changeEvent.getNewValue());
		} else if (IConstraintEnity.DIMENSION_CHANGE.equals(property)) {
			refreshVisuals();
		} else if (IConnection.SOURCE_CHANGED.equals(property)) {
			refreshSourceConnections();
		} else if (IConnection.TARGET_CHANGED.equals(property)) {
			refreshTargetConnections();
		}
	}

	@Override
	protected List getModelSourceConnections() {
		return ((S2Action)getModel()).getSourceConnections();
	}

	@Override
	protected List getModelTargetConnections() {
		return ((S2Action)getModel()).getTargetConnections();
	}
	
	private void handleMethodChange(String method) {
		setMethodValue(method, getActionFigure());
		refreshVisuals();
	}

	private void handleClassChange(String value) {
		setClassValue(value, getActionFigure());
		refreshVisuals();
	}

	private void handleNameChange(String name) {
		setNameValue(name, getActionFigure());
		refreshVisuals();
	}
	
	/**
	 * Policy to enable creation of Result connection.
	 * 
	 * @author nbhusare
	 */
	private static class S2ActionConnectionEditPolicy extends GraphicalNodeEditPolicy {

		@Override
		protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
			IConnectableEntity target = (IConnectableEntity) getHost().getModel();
			S2ResultCreationCommand command = (S2ResultCreationCommand) request.getStartCommand();
			command.setTarget(target);
			return command;
		}

		@Override
		protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
			IConnectableEntity source = (IConnectableEntity) getHost().getModel();
			S2ResultCreationCommand command = new S2ResultCreationCommand(source);
			request.setStartCommand(command);
			return command;
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
