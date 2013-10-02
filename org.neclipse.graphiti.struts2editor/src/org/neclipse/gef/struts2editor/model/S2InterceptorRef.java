/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2InterceptorRef extends S2AbstractEntity implements IInterceptorRef {

	/* Include attributes */
	private IEntity parent;
	
	public S2InterceptorRef() {
		setName(DEFAULT_NAME);
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.IDiagramChild#getParent()
	 */
	@Override
	public IEntity getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.gef.struts2editor.model.Reparentable#setParent(java.lang.Object)
	 */
	@Override
	public void setParent(IEntity parent) {
		Assert.isNotNull(parent, "Parent for an Interceptor cannot be null");
		this.parent = parent;
	}

	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Interceptor Name Cannot be null");
		super.setName(NAME_PREFIX + name);
	}
	
	@Override
	public Object cloneEntity(){
		return new S2InterceptorRef();
	}

}
