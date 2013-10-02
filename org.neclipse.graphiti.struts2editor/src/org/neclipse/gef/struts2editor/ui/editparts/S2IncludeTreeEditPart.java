package org.neclipse.gef.struts2editor.ui.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;
import org.neclipse.gef.struts2editor.model.IContainer;
import org.neclipse.gef.struts2editor.model.IDiagram;
import org.neclipse.gef.struts2editor.model.IEntity;
import org.neclipse.gef.struts2editor.model.IInclude;
import org.neclipse.gef.struts2editor.model.IPropertyChangeSupport;
import org.neclipse.gef.struts2editor.ui.S2EditorUI;

/**
 * 
 * @author nbhusare
 */
public class S2IncludeTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	private String itemText;
	private IProject project;

	public S2IncludeTreeEditPart(IProject project) {
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

	public void setOutlineItemText(String itemText) {
		this.itemText = itemText;
	}
	
	@Override
	protected String getText() {
		return itemText;
	}
	
	@Override
	protected Image getImage() {
		return S2EditorUI.getImageDescriptor("icons/showchild_mode.gif").createImage();
	}
	
	@Override
	protected List getModelChildren() {
		if (getModel() instanceof IContainer) {
			return ((IContainer)getModel()).getChildren();
		} 
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent) {
		String property = changeEvent.getPropertyName();
		/* Refresh the children if the child has been added or removed */
		if (IContainer.ADD_CHILD.equals(property)) {
			addChild(createChild(changeEvent.getNewValue()), -1);
		} else if (IContainer.REMOVE_CHILD.equals(property)) {
			//removeChild(getEditPartForChild(changeEvent.getNewValue()));
		} else if (property.equals(IInclude.PATH_CHANGED)){
			IEntity entity = (IEntity) changeEvent.getSource();
			if (entity instanceof IInclude) {
				IFile iFile = project.getFile((String) changeEvent.getNewValue());
				if (iFile != null && iFile.exists()) {
					ObjectInputStream in;
					try {
						in = new ObjectInputStream(iFile.getContents());
						IDiagram inputFileDiagram = (IDiagram) in.readObject();
						in.close();
						EditPart editPart = (EditPart)getViewer().getEditPartRegistry().get(entity);
						editPart.setModel(inputFileDiagram);
						editPart.refresh();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (CoreException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			refreshVisuals();
		}
	}
}
