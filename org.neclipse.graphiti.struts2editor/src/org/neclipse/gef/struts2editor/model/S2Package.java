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
public class S2Package extends S2AbstractConnectableEntity implements IPackage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IEntity parent;
	private String namespace;
	private String abstractValue;
	
	private List<IPackage> pExtends = new ArrayList<IPackage>();
	
	public S2Package() {
		/* Set the default values for the Package attributes */
		setName(DEFAULT_NAME);
		setNamespace(DEFAULT_NAMESPACE);
		setAbstractValue(DEFAULT_ABSTRACT);
	}
	
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
	public void setName(String name) {
		Assert.isNotNull(name, "Name cannot be null");
		this.name = name.trim();
		super.setName(name);
	}
	
	@Override
	public String getNamespace() {
		return namespace;
	}

	@Override
	public void setNamespace(String namespace) {
		Assert.isNotNull(namespace, "Namespace cannot be null");
		this.namespace = namespace.trim();
		firePropertyChange(IPackage.NAMESPACE_CHANGED, null, this.namespace);
	}

	@Override
	public List<IPackage> getExtends() {
		return pExtends;
	}

	@Override
	public void addExtend(IPackage pExtend) {
		Assert.isNotNull(pExtends, "The package to be extended cannot be null");
		if (!pExtends.contains(pExtend)) { // Do not assert this. We need to keep moving.
			pExtends.add(pExtend);
			firePropertyChange(IPackage.EXTENDS_CHANGED, null, pExtend);
		}
	}

	@Override
	public void removeExtend(IPackage pExtend) {
		Assert.isNotNull(pExtends, "The package to be removed from the Extends list cannot be null");
		if (pExtends.contains(pExtend)) { // Do not assert this. We need to keep moving.
			pExtends.remove(pExtend);
			firePropertyChange(IPackage.EXTENDS_CHANGED, null, pExtend);
		}
	}
	
	@Override
	public boolean isExtended(IPackage ePackage) {
		return pExtends.contains(ePackage);
	}

	@Override
	public void addAction(IAction action) {
		Assert.isNotNull(action, "The child action cannot be null");
		if (!children.contains(action)) {
			children.add(action);
			//TODO: Fire notification here
		}
	}

	@Override
	public List<IAction> getActions() {
		List<IAction> actions = new ArrayList<IAction>();
		for (IEntity child : children) {
			if (child instanceof IAction) {
				actions.add((IAction) child);
			}
		}
		return actions;
	}

	@Override
	public String getAbstractValue() {
		return abstractValue;
	}

	@Override
	public void setAbstractValue(String abstractValue) {
		Assert.isNotNull(abstractValue, "Abstract value cannot be null");
		this.abstractValue = abstractValue.trim();
		firePropertyChange(IPackage.ABSTRACT_CHANGE, null, this.abstractValue);
	}
	
	@Override
	public Object cloneEntity()  {
		return new S2Package();
	}

	@Override
	public void setExtends(List<IPackage> pExtends) {
		this.pExtends = pExtends;
	}
}
