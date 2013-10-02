/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import org.eclipse.core.runtime.Assert;


/**
 * @author nbhusare
 *
 */
public class S2InterceptorStack extends S2AbstractContainer implements IInterceptorStack {

	/* Interceptor attributes */
	private IEntity parent;
	
	public S2InterceptorStack() {
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
		super.setName(name);
	}
	
	@Override
	public Object cloneEntity(){
		return new S2InterceptorStack();
	}

}
