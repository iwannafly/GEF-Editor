/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 *
 */
public class S2Interceptors extends S2AbstractContainer implements IInterceptors {

	private IEntity parent;
	
	@Override
	public IEntity getParent() {
		return parent;
	}

	@Override
	public void setParent(IEntity newParent) {
		Assert.isNotNull(newParent, "Parent cannot be null");
		this.parent = newParent;
		//TODO: Fire the property here.
	}
	
	@Override
	public Object cloneEntity() {
		return new S2Interceptors();
	}
}
