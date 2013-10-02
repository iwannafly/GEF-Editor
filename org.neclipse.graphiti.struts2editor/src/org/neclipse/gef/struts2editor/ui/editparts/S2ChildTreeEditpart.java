/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.neclipse.gef.struts2editor.model.IAction;
import org.neclipse.gef.struts2editor.model.IBean;
import org.neclipse.gef.struts2editor.model.IConstant;
import org.neclipse.gef.struts2editor.model.IContainer;
import org.neclipse.gef.struts2editor.model.IEntity;
import org.neclipse.gef.struts2editor.model.IInclude;
import org.neclipse.gef.struts2editor.model.IInterceptor;
import org.neclipse.gef.struts2editor.model.IInterceptorRef;
import org.neclipse.gef.struts2editor.model.IInterceptorStack;
import org.neclipse.gef.struts2editor.model.IInterceptors;
import org.neclipse.gef.struts2editor.model.IJSPHTML;
import org.neclipse.gef.struts2editor.model.INamedEntity;
import org.neclipse.gef.struts2editor.model.IPackage;
import org.neclipse.gef.struts2editor.model.IPropertyChangeSupport;
import org.neclipse.gef.struts2editor.model.S2Comment;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;

/**
 * @author nbhusare
 */
public class S2ChildTreeEditpart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	private IProject project;
	
	public S2ChildTreeEditpart(IProject project) {
		this.project = project;
	}

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
		String name = null;
		IEntity entity = (IEntity) getModel();
		if (entity instanceof IInterceptors) {
			name = "Interceptors";
		} else if (entity instanceof  IInclude) {
			name = ((IInclude)entity).getPath();
		} else if (entity instanceof IJSPHTML) { 
			name = ((IJSPHTML)entity).getPath();
		} else  {
			name = ((INamedEntity)getModel()).getName(); 
		} 
		return name;
	}
	
	@Override
	protected List getModelChildren() {
		IEntity entity = (IEntity) getModel();
		if (entity instanceof IContainer) {
			IContainer container = (IContainer) getModel();
			return container.getChildren();
		}
		return Collections.EMPTY_LIST;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String property = evt.getPropertyName();
		if (property.equals(IContainer.ADD_CHILD)) {
			if (evt.getNewValue() instanceof S2Comment) {
				refreshVisuals();
			} else {
				addChild(createChild(evt.getNewValue()), -1);
			}
		} else if (property.equals(IContainer.REMOVE_CHILD)) {
			removeChild((EditPart)getViewer().getEditPartRegistry().get(evt.getNewValue()));
		} else {
			refreshVisuals();
		}
	}
	
	@Override
	protected Image getImage() {
		IEntity model = (IEntity) getModel();
		if (model instanceof IBean) {
			return S2EditorUI.getImageDescriptor("icons/11880.bmp.gif").createImage();
		} else if (model instanceof IConstant) {
			return S2EditorUI.getImageDescriptor("icons/17405.thread_view.png").createImage();
		} else if (model instanceof IPackage) {
			return S2EditorUI.getImageDescriptor("icons/10464.package_obj.gif").createImage();
		} else if (model instanceof IInclude) { 
			return S2EditorUI.getImageDescriptor("icons/showchild_mode.gif").createImage();
		} else if (model instanceof IAction) {
			return S2EditorUI.getImageDescriptor("icons/att_class_obj.gif").createImage();
		} else if (model instanceof IInterceptors) {
			return S2EditorUI.getImageDescriptor("icons/arraypartition_obj.gif").createImage();
		} else if (model instanceof IInterceptor) {
			return S2EditorUI.getImageDescriptor("icons/remove_correction.gif").createImage();
		} else if (model instanceof IInterceptorStack) {
			return S2EditorUI.getImageDescriptor("icons/thin_max_view.gif").createImage();
		} else if (model instanceof IInterceptorRef) {
			return S2EditorUI.getImageDescriptor("icons/change.gif").createImage();
		} else if (model instanceof IJSPHTML) {
			return S2EditorUI.getImageDescriptor("icons/14474.PD_Plugin.gif").createImage();
		}
		return null;
	}

}
