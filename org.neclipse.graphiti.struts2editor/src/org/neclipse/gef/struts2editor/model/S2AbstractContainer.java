/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.ArrayList;
import java.util.List;


/**
 * @author nbhusare
 *
 */
public abstract class S2AbstractContainer extends S2AbstractEntity implements IContainer {

	protected List<IEntity> children = new ArrayList<IEntity>();

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IContainer#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return children.isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IContainer#containsChild(org.rssclient.core.model.IEntity)
	 */
	@Override
	public boolean containsChild(IEntity child) {
		return children.contains(child);
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IContainer#getChildren()
	 */
	@Override
	public List<IEntity> getChildren() {
		return children;
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IContainer#removeChild(org.rssclient.core.model.IEntity)
	 */
	@Override
	public boolean removeChild(IEntity child) {
		boolean result = children.remove(child);
		firePropertyChange(IContainer.REMOVE_CHILD, null, child);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.rssclient.core.model.IContainer#addChild(org.rssclient.core.model.IEntity)
	 */
	@Override
	public boolean addChild(IEntity child) {
		boolean result = children.add(child);
		firePropertyChange(IContainer.ADD_CHILD, null, child);
		return result;
	}
	
	@Override
	public boolean addChild(IEntity child, int index) {
		children.add(index, child);
		firePropertyChange(IContainer.ADD_CHILD, null, child);
		return true;
	}
}
