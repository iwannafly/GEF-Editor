package org.neclipse.graphiti.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.neclipse.graphiti.struts2editor.model.IContainer;
import org.neclipse.graphiti.struts2editor.model.IPropertyChangeSupport;

/**
 * 
 * @author nbhusare
 */
public class S2DiagramTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	@Override
	public void activate() {
		/* Activate the Edit part if not active and then hook into the model */
		if (!isActive()) {
			super.activate();
			IPropertyChangeSupport entity = ((IPropertyChangeSupport)getModel());
			entity.addPropertyChangeListener(this);
		}
	}
	
	@Override
	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			IPropertyChangeSupport entity = ((IPropertyChangeSupport)getModel());
			entity.removePropertyChangeListener(this);
		}
	}

	@Override
	protected String getText() {
		return "S2 Diagram Root";
	}
	
	@Override
	protected List getModelChildren() {
		return ((IContainer)getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		/* Refresh the children if the child has been added or removed */
		if (IContainer.ADD_CHILD.equals(property)) {
			addChild(createChild(changeEvent.getNewValue()), -1);
		} else if (IContainer.REMOVE_CHILD.equals(property)) {
			//removeChild(getEditPartForChild(changeEvent.getNewValue()));
		} else {
			refreshVisuals();
		}
	}
}
