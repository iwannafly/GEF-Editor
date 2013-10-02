/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

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
import org.neclipse.gef.struts2editor.model.IBean;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.S2Bean;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2BeanDirectEditPolicy;
import org.neclipse.gef.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;
import org.neclipse.gef.struts2editor.ui.validators.IValidationMessageHandler;
import org.neclipse.gef.struts2editor.ui.validators.S2BeanScopeCellValidator;
import org.neclipse.gef.struts2editor.ui.validators.S2BooleanValueCellValidaor;
import org.neclipse.gef.struts2editor.ui.validators.S2ClassCellValidator;
import org.neclipse.gef.struts2editor.ui.validators.S2NameCellValidator;

/**
 * @author nbhusare
 *
 */
public class S2BeanEditPart extends S2EntityEditPart {

	private static class BeanFigure extends S2Figure {
		
		private Label classLabel;
		private Label typeLabel;
		private Label optionalLabel;
		private Label staticLabel;
		private Label scopeLabel;
		private Label nameLabel;
		
		
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

		public BeanFigure() {
			/* Setting the layout for the Bean figure */
			ToolbarLayout layout = new ToolbarLayout();
			layout.setVertical(true);
			setLayoutManager(layout);
			setBorder(new LineBorder(ColorConstants.black, 1));
			
			Label headerLabel = new Label("Bean");
			headerLabel.setLabelAlignment(PositionConstants.LEFT);
			headerLabel.setIcon(S2EditorUI.getImageDescriptor("icons/11880.bmp.gif").createImage());
			headerLabel.setBackgroundColor(new Color(null, 219, 187, 190));
			headerLabel.setOpaque(true);
			headerLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
			add(headerLabel);
			
			/* Label for setting class of the Bean */
			classLabel = new ChildFigure();
			classLabel.setLabelAlignment(PositionConstants.LEFT);
			classLabel.setToolTip(new Label("Set the class of the bean"));
			classLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			classLabel.setIcon(S2EditorUI.getImageDescriptor("icons/16991.compare_method.png").createImage());
			add(classLabel);
			
			/* Label for setting class of the Bean */
			typeLabel = new ChildFigure();
			typeLabel.setLabelAlignment(PositionConstants.LEFT);
			typeLabel.setToolTip(new Label("Set the Type of the bean"));
			typeLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			typeLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(typeLabel);
			
			/* Label for setting Name of the Bean */
			nameLabel = new ChildFigure();
			nameLabel.setLabelAlignment(PositionConstants.LEFT);
			nameLabel.setToolTip(new Label("Set the Name of the bean"));
			nameLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			nameLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(nameLabel);
			
			/* Label for setting Scope of the Bean */
			scopeLabel = new ChildFigure();
			scopeLabel.setLabelAlignment(PositionConstants.LEFT);
			scopeLabel.setToolTip(new Label("Set the Scope of the bean"));
			scopeLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			scopeLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(scopeLabel);

			/* Label for setting Scatic value of the Bean */
			staticLabel = new ChildFigure();
			staticLabel.setLabelAlignment(PositionConstants.LEFT);
			staticLabel.setToolTip(new Label("Set the Static valie of the bean"));
			staticLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			staticLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(staticLabel);
			
			/* Label for setting Optional value of the Bean */
			optionalLabel = new ChildFigure();
			optionalLabel.setLabelAlignment(PositionConstants.LEFT);
			optionalLabel.setToolTip(new Label("Set the Static valie of the bean"));
			optionalLabel.setBackgroundColor(new Color(null, 242, 229, 229));
			optionalLabel.setIcon(S2EditorUI.getImageDescriptor("icons/13943.task-inactive-centered.gif").createImage());
			add(optionalLabel);
		}
		
		/**
		 * Return the default dimension of the Bean.
		 * @return the default dimension of the Bean.
		 */
		@Override
		public Dimension getPreferredSize(int wHint, int hHint) {
			return new Dimension(200, 138);
		}
		
		/**
		 * Return the Class label of the Bean.
		 * @return the Class label of the Bean.
		 */
		public Label getClassLabel() {
			return classLabel;
		}

		/**
		 * Return the Type Label of a Bean.
		 * @return the Type Label of a Bean.
		 */
		public Label getTypeLabel() {
			return typeLabel;
		}

		/**
		 * Return the Optional label of the Bean.
		 * @return the Optional label of the Bean.
		 */
		public Label getOptionalLabel() {
			return optionalLabel;
		}

		/**
		 * Return the Static label of the Bean.
		 * @return the Static label of the Bean.
		 */
		public Label getStaticLabel() {
			return staticLabel;
		}

		/**
		 * Return the Scope label of the Bean.
		 * @return the Scope label of the Bean.
		 */
		public Label getScopeLabel() {
			return scopeLabel;
		}

		/**
		 * Return the Name label of the Bean.
		 * @return the Name label of the Bean.
		 */
		public Label getNameLabel() {
			return nameLabel;
		}

		@Override
		public Label getLabelAt(Point location) {
			if (isLabelEdited(classLabel, location)) return classLabel;
			else if (isLabelEdited(typeLabel, location)) return typeLabel;
			else if (isLabelEdited(nameLabel, location)) return nameLabel;
			else if (isLabelEdited(scopeLabel, location)) return scopeLabel;
			else if (isLabelEdited(staticLabel, location)) return staticLabel;
			else if (isLabelEdited(optionalLabel, location)) return optionalLabel;
			return null;
		}
		
		/**
		 * Return true if the location lies on the Class Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Class Label.
		 */
		public boolean isClassLabelAt(Point location) {
			return isLabelEdited(classLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Type Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Type Label.
		 */
		public boolean isTypeLabelAt(Point location) {
			return isLabelEdited(typeLabel, location);
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
		 * Return true if the location lies on the Scope Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Scope Label.
		 */
		public boolean isScopeLabelAt(Point location) {
			return isLabelEdited(scopeLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Static Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Static Label.
		 */
		public boolean isStaticLabelAt(Point location) {
			return isLabelEdited(staticLabel, location);
		}
		
		/**
		 * Return true if the location lies on the Optional Label.
		 * @param location the location to be tested.
		 * @return true if the location lies on the Static Label.
		 */
		public boolean isOptionalLabelAt(Point location) {
			return isLabelEdited(optionalLabel, location);
		}
		
	}
	
	@Override
	protected IFigure createFigure() {
		IBean bean = (IBean) getModel();
		BeanFigure figure = new BeanFigure();
		setClass(bean.getBeanClass(), figure);
		setType(bean.getType(), figure);
		setName(bean.getName(), figure);
		setScopeValue(bean.getScope(), figure);
		setStaticValue(bean.getStatic(), figure);
		setOptionalValue(bean.getOptional(), figure);
		return figure;
	}
	
	private BeanFigure getBeanFigure() {
		return (BeanFigure)getFigure();
	}
	
	/**
	 * 
	 * @param bClass
	 * @param figure
	 */
	private void setClass(String bClass, BeanFigure figure) {
		figure.getClassLabel().setText(IBean.CLASS_PREFIX + bClass);
	}
		
	/**
	 * 
	 * @param bType
	 * @param figure
	 */
	private void setType(String bType, BeanFigure figure) {
		figure.getTypeLabel().setText(IBean.TYPE_PREFIX + bType);
	}
	
	/**
	 * 
	 * @param bName
	 * @param figure
	 */
	private void setName(String bName, BeanFigure figure) {
		figure.getNameLabel().setText(IBean.NAME_PREFIX + bName);
	}
	
	/**
	 * 
	 * @param bScopeValue
	 * @param figure
	 */
	private void setScopeValue(String bScopeValue, BeanFigure figure) {
		figure.getScopeLabel().setText(IBean.SCOPE_PREFIX + bScopeValue);
	}
	
	/**
	 * 
	 * @param bStaticValue
	 * @param figure
	 */
	private void setStaticValue(String bStaticValue, BeanFigure figure) {
		figure.getStaticLabel().setText(IBean.STATIC_PREFIX + bStaticValue);
	}
	
	/**
	 * 
	 * @param bOptionalValue
	 * @param figure
	 */
	private void setOptionalValue(String bOptionalValue, BeanFigure figure) {
		figure.getOptionalLabel().setText(IBean.OPTIONAL_PREFIX + bOptionalValue);
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new S2BeanDirectEditPolicy());
		
		/* Enable deleting the Include entity*/
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		if (IBean.CLASS_CHANGED.equals(property)) {
			handleClassChange((String) changeEvent.getNewValue());
		} else if (IBean.TYPE_CHANGED.equals(property)) {
			handleTypeChange((String) changeEvent.getNewValue());
		} else if (IBean.NAME_CHANGED.equals(property)) {
			handleNameChange((String) changeEvent.getNewValue());
		} else if (IBean.SCOPE_CHANGED.equals(property)) {
			handleScopeChange((String) changeEvent.getNewValue());
		} else if (IBean.STATIC_CHANGED.equals(property)) {
			handleStaticChange((String) changeEvent.getNewValue());
		} else if (IBean.OPTIONAL_CHANGED.equals(property)) {
			handleOptionalChange((String) changeEvent.getNewValue());
		} else if (IPackage.DIMENSION_CHANGE.equals(property)) {
			refreshVisuals();
		}
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleClassChange(String className) {
		setClass(className, getBeanFigure());
		refreshVisuals();
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleTypeChange(String typeName) {
		setType(typeName, getBeanFigure());
		refreshVisuals();
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleNameChange(String name) {
		setName(name, getBeanFigure());
		refreshVisuals();
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleScopeChange(String scope) {
		setScopeValue(scope, getBeanFigure());
		refreshVisuals();
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleStaticChange(String staticValue) {
		setStaticValue(staticValue, getBeanFigure());
		refreshVisuals();
	}
	
	/**
	 * Handles the Class change operation. Updates the Class label with the new updated value.
	 * @param className	the new Class value.
	 */ 
	private void handleOptionalChange(String optionalValue) {
		setOptionalValue(optionalValue, getBeanFigure());
		refreshVisuals();
	}

	/**
	 * Check if the hit location lies on the labels.
	 * @param hitLocation the location where the mouse is pressed for editing operation.
	 * @return true if the location lies on any of the two labels.
	 */
	protected boolean isLabelEditRequest(Point hitLocation) {
		return ((S2Figure)getFigure()).getLabelAt(hitLocation) != null; 
	}

	/**
	 * Reverts the label to its initial value.
	 * @param request the edit request 
	 */
	public void revertLabelEdit(DirectEditRequest request, String oldName) {
		Label label = null;
		S2Bean sBean = (S2Bean) getModel();
		if (isBeanClassEdited(request)) {
			handleClassChange(sBean.getBeanClass());
			label = getBeanFigure().getClassLabel();
			label.setVisible(true);
		} else if (isBeanTypeEdited(request)) {
			handleTypeChange(sBean.getType());
			label = getBeanFigure().getTypeLabel();
			label.setVisible(true);
		} else if (isBeanNameEdited(request)) {
			handleNameChange(sBean.getName());
			label = getBeanFigure().getNameLabel();
			label.setVisible(true);
		} else if (isBeanScopeEdited(request)) {
			label = getBeanFigure().getScopeLabel();
			handleScopeChange(sBean.getScope());
			label.setVisible(true);
		} else if (isBeanStaticEdited(request)) {
			handleStaticChange(sBean.getStatic());
			label = getBeanFigure().getStaticLabel();
			label.setVisible(true);
		} else if (isBeanOptionalEdited(request)) {
			handleOptionalChange(sBean.getOptional());
			label = getBeanFigure().getOptionalLabel();
			label.setVisible(true);
		}
		refreshVisuals();
	}
	
	public boolean isBeanClassEdited(DirectEditRequest request) {
		return getBeanFigure().isClassLabelAt(hitLocation);
	}
	
	public boolean isBeanTypeEdited(DirectEditRequest request) {
		return getBeanFigure().isTypeLabelAt(hitLocation);
	}
	
	public boolean isBeanNameEdited(DirectEditRequest request) {
		return getBeanFigure().isNameLabelAt(hitLocation);
	}
	
	public boolean isBeanScopeEdited(DirectEditRequest request) {
		return getBeanFigure().isScopeLabelAt(hitLocation);
	}
	
	public boolean isBeanStaticEdited(DirectEditRequest request) {
		return getBeanFigure().isStaticLabelAt(hitLocation);
	}
	
	public boolean isBeanOptionalEdited(DirectEditRequest request) {
		return getBeanFigure().isOptionalLabelAt(hitLocation);
	}
	
	@Override
	protected ICellEditorValidator getCellEditValidator(
			IValidationMessageHandler handler) {
		if (isBeanClassEdited(null)) {
			return new S2ClassCellValidator(handler);
		} else if (isBeanTypeEdited(null)) {
			return new S2ClassCellValidator(handler);
		} else if (isBeanNameEdited(null)) {
			return new S2NameCellValidator(handler);
		} else if (isBeanScopeEdited(null)) {
			return new S2BeanScopeCellValidator(handler);
		} else if (isBeanStaticEdited(null)) {
			return new S2BooleanValueCellValidaor(handler);
		} else if (isBeanOptionalEdited(null)) {
			return new S2BooleanValueCellValidaor(handler);
		}
		return null;
	}

}
