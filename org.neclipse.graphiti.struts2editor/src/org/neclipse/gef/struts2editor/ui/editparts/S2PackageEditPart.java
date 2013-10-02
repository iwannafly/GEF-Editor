/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayout;
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
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.neclipse.gef.struts2editor.model.S2Action;
import org.neclipse.gef.struts2editor.model.rewrite.IS2Container;
import org.neclipse.gef.struts2editor.model.rewrite.IS2Model;
import org.neclipse.gef.struts2editor.model.rewrite.IS2Package;
import org.neclipse.gef.struts2editor.model.rewrite.S2ModelCngConstants;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;
import org.neclipse.gef.struts2editor.ui.editparts.commands.S2EntityCreationCommand;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2ContainerXYLayoutEditPolicy;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2PackageDirectEditPolicy;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.gef.struts2editor.ui.validators.S2BooleanValueCellValidaor;
import org.neclipse.gef.struts2editor.ui.validators.S2NameCellValidator;
import org.neclipse.gef.struts2editor.ui.validators.S2PackageNamespaceCellValidator;
import org.neclipse.gef.struts2editor.ui.views.S2Browser;

/**
 * The controller for the Struts 2 Package entity.
 * 
 * @author nbhusare
 */
public class S2PackageEditPart extends S2ConnectableContainerEditPart {
	
	/**
	 * Figure for the Struts 2 Package entity.
	 * 
	 * @author nbhusare
	 */
	private static class PackageFigure extends S2Figure {

		private Label nameLabel;
		private Label namespaceLabel;
		private Label abstractLabel;
		private BodyFigure bodyFigure;
		
		public static class ChildLabelFigure extends Label {
			
			public ChildLabelFigure() {
				setOpaque(true);
				setLabelAlignment(PositionConstants.LEFT);
				setBackgroundColor(new Color(null, 242, 229, 229));
				setBorder(new ChildFigureBorder(new Insets(4, 0, 0, 0)));
				setCursor(Display.getDefault().getSystemCursor(SWT.CURSOR_HAND));
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
				Dimension defaultSize = new Dimension(SWT.DEFAULT, 15);
				prefSize.union(defaultSize);
				return prefSize;
			}
			
		}
		
		public static class BodyFigure extends Figure {
			
			public BodyFigure() {
				/* The Child figure will contain actions that can moved in any direction */
				setLayoutManager(new FreeformLayout());
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
				int bodyHeight = getParent().getBounds().height - 30;
				Dimension defaultSize = new Dimension(SWT.DEFAULT, bodyHeight);
				prefSize.union(defaultSize);
				return prefSize;
			}
			
			@Override
			protected boolean useLocalCoordinates() { return true; }
		}
		
		public PackageFigure() {
			/* Setting the layout for the package figure */
			ToolbarLayout layout = new ToolbarLayout();
			//layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));

			final Label packageHdr = new Label("Package", S2EditorUI.getImageDescriptor("icons/10464.package_obj.gif").createImage());
			packageHdr.setLabelAlignment(PositionConstants.LEFT);
			packageHdr.setBackgroundColor(new Color(null, 219, 187, 190));
			packageHdr.setOpaque(true);
			packageHdr.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(packageHdr);
			
			/* Label for setting the Package Name and the icon */
			nameLabel = new ChildLabelFigure();
			nameLabel.setToolTip(new Label("Set the Name of the Package"));
			nameLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(nameLabel);
			
			/* Label for setting the Package Namespace and icon*/
			namespaceLabel = new ChildLabelFigure();
			namespaceLabel.setToolTip(new Label("Set the Namespace of the Package"));
			namespaceLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(namespaceLabel);
			
			/* Label for setting the Package Abstract variable and icon*/
			abstractLabel = new ChildLabelFigure();
			abstractLabel.setToolTip(new Label("Set the Abstaract attribute of the Package"));
			abstractLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(abstractLabel);
			
			/* Figure for the Package Body */
			bodyFigure = new BodyFigure(); 
			add(bodyFigure);
		}
		
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			Dimension defaultSize = new Dimension(300, 200);
			return defaultSize;
		}
		
		public BodyFigure getBodyFigure() {
			return bodyFigure;
		}

		public Label getNameLabel() {
			return nameLabel;
		}
		
		public Label getNamespaceLabel() {
			return namespaceLabel;
		}
		
		public Label getAbstractLabel(){
			return abstractLabel;
		}
		
		/**
		 * Returns the Label at a particular location or null if no label found.
		 * @return the Label at a particular location or null if no label found.
		 */
		public Label getLabelAt(Point location){
			if (isLabelEdited(nameLabel, location)) return nameLabel;
			else if (isLabelEdited(namespaceLabel, location))return namespaceLabel;
			else if (isLabelEdited(abstractLabel, location)) return abstractLabel;
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
		 * Return true if the location lies on the Namespace Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Namespace Label.
		 */
		public boolean isNamespaceLabelAt(Point location) {
			return isLabelEdited(namespaceLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Abstract Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Abstract Label.
		 */
		public boolean isAbstractLabelAt(Point location) {
			return isLabelEdited(abstractLabel, location);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		IS2Package model = (org.neclipse.gef.struts2editor.model.rewrite.S2Package) getModel();
		PackageFigure figure = new PackageFigure();
		setNameValue(model.getName(), figure);
		setNamespaceValue(model.getNamespace(), figure);
		setAbstractValue(Boolean.toString(model.isAbstract()), figure);
		return figure;
	}
	
	@Override
	public IFigure getContentPane() {
		return getPackageFigure().getBodyFigure();
	}
	
	/**
	 * Return the castes Package figure instance.
	 * @return the castes Package figure instance.
	 */
	private PackageFigure getPackageFigure(){
		return (PackageFigure) getFigure();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		
		/* Add the role to allow direct editing of the Name and Namespace values */
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2PackageDirectEditPolicy());
		
		/* The Package should allow creation of child elements */
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new S2ContainerXYLayoutEditPolicy(){

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				Object child = request.getNewObjectType();
				if (child == S2Action.class) {
					IS2Container parent = (IS2Container) getHost().getModel();
					Rectangle dimension = (Rectangle) getConstraintFor(request);
					return new S2EntityCreationCommand((IS2Model) request.getNewObject(), parent, dimension);
				} 
				return null;
			}
		});
		
		/* The Package should allow creating Extends connection */
		//installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new S2PackageConnectionEditPolicy());
	}

	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		S2Browser browser = S2EditorUI.findBrowser(S2Browser.VIEW_ID);
		if (browser == null) {
			return;
		}
		if (value != EditPart.SELECTED_NONE) {
			browser.openUrl("http://struts.apache.org/2.0.14/docs/package-configuration.html");
		} else {
			browser.openUrl("http://struts.apache.org/2.0.14/docs/configuration-elements.html");
		}
	}

	@Override
	protected ICellEditorValidator getCellEditValidator(IValidationMessageHandler handler) {
		if (isNameEdited(null)) {
			return new S2NameCellValidator(handler);
		} else if(isNamespaceEdited(null)) {
			return new S2PackageNamespaceCellValidator(handler);
		} else if (isAbstractEdited(null)) {
			return new S2BooleanValueCellValidaor(handler);
		}
		return null;
	}
	
	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		IS2Package sPackage = (IS2Package) getModel();
		if (isNameEdited(request)) {
			handleNameChange(sPackage.getName());
			label = getPackageFigure().getNameLabel();
			label.setVisible(true);
		} else if (isNamespaceEdited(request)) {
			handleNamespaceChange(sPackage.getNamespace());
			label = getPackageFigure().getNamespaceLabel();
			label.setVisible(true);
		} else if (isAbstractEdited(request)) {
			handleAbstractAttribChange(Boolean.toString(sPackage.isAbstract()));
			label = getPackageFigure().getAbstractLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}
	
	/**
	 * Set the value of the Name attribute on the package name label.
	 * @param name	the value of the Name attribute.	
	 * @param packageFigure the package figure.
	 */
	private void setNameValue(String name, PackageFigure packageFigure) {
		packageFigure.getNameLabel().setText("name:" + name);
	}
	
	/**
	 * Set the value of the Namespace attribute on the package name label.
	 * @param name	the value of the Namespace attribute.	
	 * @param packageFigure the package figure.
	 */
	private void setNamespaceValue(String namespace, PackageFigure packageFigure) {
		packageFigure.getNamespaceLabel().setText("namespace:" + namespace);
	}
	
	/**
	 * Set the value of the Abstract attribute on the package name label.
	 * @param name	the value of the Abstract attribute.	
	 * @param packageFigure the package figure.
	 */
	private void setAbstractValue(String abstractValue, PackageFigure packageFigure) {
		packageFigure.getAbstractLabel().setText("abstract:" + abstractValue);
	}
	
	/**
	 * Check if the hit location lies on the labels.
	 * @param hitLocation the location where the mouse is pressed for editing operation.
	 * @return true if the location lies on any of the two labels.
	 */
	protected boolean isLabelEditRequest(Point hitLocation) {
		return getPackageFigure().getLabelAt(hitLocation) != null; 
	}
	
	/**
	 * Checks if the Package Name label is edited. 
	 * @param request the edit request
	 * @return true if the Package Name is edited.
	 */
	public boolean isNameEdited(DirectEditRequest request) {
		return getPackageFigure().isNameLabelAt(hitLocation);
	}
	
	/**
	 * Checks if the Package Namespace label is edited. 
	 * @param request the edit request
	 * @return	true if the Package Namespace is edited.
	 */
	public boolean isNamespaceEdited(DirectEditRequest request) {
		return getPackageFigure().isNamespaceLabelAt(hitLocation);
	}
	
	/**
	 * Checks if the Package Abstract label is edited. 
	 * @param request the edit request
	 * @return	true if the Package Abstract is edited.
	 */
	public boolean isAbstractEdited(DirectEditRequest request) {
		return getPackageFigure().isAbstractLabelAt(hitLocation);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		
		String property = event.getPropertyName();
		if (S2ModelCngConstants.NAME_CNG.equals(property)) {
			handleNameChange((String) event.getNewValue());
		} else if (S2ModelCngConstants.PKG_NAMESPACE_CNG.equals(property)) {
			handleNamespaceChange((String) event.getNewValue());
		} else if (S2ModelCngConstants.PKG_ABSTRACT_CNG.equals(property)) {
			handleAbstractAttribChange((String) event.getNewValue());
		} else if (S2ModelCngConstants.PKG_EXTENDS_CNG.equals(property)) {
			handleExtendsChange((String) event.getNewValue());
		} 
	}

	@Override
	protected List getModelSourceConnections() {
		//return ((S2Package)getModel()).getSourceConnections();
		return Collections.EMPTY_LIST;
	}

	@Override
	protected List getModelTargetConnections() {
		//return ((S2Package)getModel()).getTargetConnections();
		return Collections.EMPTY_LIST;
	}
	
	private void handleExtendsChange(String newValue) {
		//Code to Handle change in the Extends attribute.
		refreshVisuals();
	}

	private void handleAbstractAttribChange(String abstractValue) {
		setAbstractValue(abstractValue, getPackageFigure());
		refreshVisuals();
	}
	
	private void handleNamespaceChange(String namespace) {
		setNamespaceValue(namespace, getPackageFigure());
		refreshVisuals();
	}

	private void handleNameChange(String name) {
		setNameValue(name, getPackageFigure());
		refreshVisuals();
	}
	
//	/**
//	 * Policy to enable creation of connections between packages.
//	 * 
//	 * @author nbhusare
//	 */
//	private static class S2PackageConnectionEditPolicy extends GraphicalNodeEditPolicy {
//
//		@Override
//		protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
//			IEntity target = (IEntity) getHost().getModel();
//			if (!(target instanceof IPackage)) return UnexecutableCommand.INSTANCE;
//
//			Command startCommand = request.getStartCommand();
//			if (! (startCommand instanceof S2ExtendsCreationCommand)) return UnexecutableCommand.INSTANCE;
//			
//			S2ExtendsCreationCommand command = (S2ExtendsCreationCommand) startCommand;
//			command.setTarget((IPackage) target);
//			return command;
//		}
//
//		@Override
//		protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
//			IEntity source = (IEntity) getHost().getModel();
//			if (!(source instanceof IPackage)) return UnexecutableCommand.INSTANCE;
//			
//			S2ExtendsCreationCommand command = new S2ExtendsCreationCommand((IPackage) source);
//			request.setStartCommand(command);
//			return command;
//		}
//
//		@Override
//		protected Command getReconnectTargetCommand(ReconnectRequest request) {
//			return null;
//		}
//
//		@Override
//		protected Command getReconnectSourceCommand(ReconnectRequest request) {
//			return null;
//		}
//	}
}
