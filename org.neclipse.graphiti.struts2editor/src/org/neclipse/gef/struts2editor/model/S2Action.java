/**
 * 
 */
package org.neclipse.gef.struts2editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 * 
 */
public class S2Action extends S2AbstractConnectableEntity implements IAction {

	private IEntity parent;
	
	/* Action attributes */
	private String aClass;
	private String method;
	
	//TODO: Change to JSP nodes - IResult
	private List<IConstant> results = new ArrayList<IConstant>(); 
	
	/**
	 * Initializes the default values for the Name, Class and Method attributes
	 * of an Action.
	 */
	public S2Action() {
		setName(DEFAULT_NAME);
		setActionClass(DEFAULT_CLASS);
		setMethod(DEFAULT_METHOD);
	}

	public IEntity getParent() {
		return parent;
	}
	
	@Override
	public void setParent(IEntity newParent) {
		Assert.isNotNull(newParent, "Parent cannot be null");
		this.parent = newParent;
	}

	@Override
	public String getActionClass() {
		return aClass;
	}

	@Override
	public void setActionClass(String aClass) {
		Assert.isNotNull(aClass, "Action Class cannot be null");
		this.aClass = aClass;
		firePropertyChange(IAction.CLASS_CHANGED, null, this.aClass);
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public void setMethod(String method) {
		Assert.isNotNull(method, "Action Method cannot be null");
		this.method = method;
		firePropertyChange(IAction.METHOD_CHANGED, null, this.method);
	}

	@Override
	public void setName(String name) {
		Assert.isNotNull(name, "Action Name cannot be null");
		this.name = name;
		firePropertyChange(IAction.NAME_CHANGE, null, this.name);
	}
	
	@Override
	public Object cloneEntity() {
		return new S2Action();
	}

	public List<IConstant> getResults() {
		return results;
	}
	
}
