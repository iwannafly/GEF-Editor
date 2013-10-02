/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.neclipse.graphiti.struts2editor.model.IPropertyChangeSupport;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container;
import org.neclipse.graphiti.struts2editor.model.rewrite.S2ModelCngConstants;
import org.neclipse.graphiti.struts2editor.ui.editparts.policies.S2EntityComponentEditPolicy;

/**
 * @author nbhusare
 *
 */
public abstract class S2ModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	@Override
	public void activate() {
		/* Activate the Edit part if not active and then hook into the model */
		if (!isActive()) {
			super.activate();
			IPropertyChangeSupport model = ((IPropertyChangeSupport)getModel());
			model.addPropertyChangeListener(this);
		}
	}
	
	@Override
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			IPropertyChangeSupport model = ((IPropertyChangeSupport)getModel());
			model.removePropertyChangeListener(this);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (S2ModelCngConstants.DIMENSION_CHG.equals(event.getPropertyName())) {
			refreshVisuals();
		}
	}

	@Override
	protected void createEditPolicies() {
		/* Role to enable deletion of Packages from the editor */
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new S2EntityComponentEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		Rectangle dimension = ((IS2Container) getModel()).getDimension();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), dimension);
	}
}
