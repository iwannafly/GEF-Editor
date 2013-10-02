/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.neclipse.gef.struts2editor.model.rewrite.IS2Container;
import org.neclipse.gef.struts2editor.model.rewrite.S2ModelCngConstants;

/**
 * @author nbhusare
 *
 */
public abstract class S2ContainerEditPart extends S2EditableEditPart {
	
	/**
	 * 
	 */
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		String property = event.getPropertyName();
		
		if (S2ModelCngConstants.CHILD_ADDED.equals(property)
				|| S2ModelCngConstants.CHILD_REMOVED.equals(property)) {
			refreshChildren();
		} 
	}
	
	@Override
	protected List getModelChildren() {
		/* Return the children contained in the Diagram model */
		IS2Container container = (IS2Container) getModel();
		return container.getChildren();
	}
}
