/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2JSPHTML extends S2AbstractConnectableEntity implements IJSPHTML {

	private IEntity parent;
	private String path;  
	
	public S2JSPHTML() {
		setPath(DEFAULT_PATH);
	}
	
	@Override
	public IEntity getParent() {
		return parent;
	}

	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for a Constant cannot be null");
		this.parent = parent;
	}

	@Override
	public void setPath(String path) {
		Assert.isNotNull(path, "Path of the JSP/HTML Entity cannot be null");
		this.path = path;
		firePropertyChange(IJSPHTML.PATH_CHANGE, null, path);
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Object cloneEntity() {
		return new S2JSPHTML();
	}
}
