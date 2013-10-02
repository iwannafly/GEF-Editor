/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2Include extends S2AbstractEntity implements IInclude {

	/* Include attributes */
	private IEntity parent;
	private String path;
	
	public S2Include() {
		setPath(DEFAULT_PATH_VALUE);
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.IDiagramChild#getParent()
	 */
	@Override
	public IEntity getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.Reparentable#setParent(java.lang.Object)
	 */
	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for a Bean cannot be null");
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.IInclude#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.IInclude#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) {
		Assert.isNotNull(path, "Include Path cannot be null");
		this.path = path.trim();
		firePropertyChange(IInclude.PATH_CHANGED, null, this.path);
	}

	@Override
	public Object cloneEntity(){
		// TODO Auto-generated method stub
		return null;
	}

}
