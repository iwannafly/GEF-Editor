/**
 * 
 */
package org.simplestruts2.core.model.old;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * @author nbhusare
 *
 */
public class Struts2Package extends AbstractModelEntity implements IPackage{
	
	
	private IDiagram parent;
	private String namespace;
	private List<IPackage> pExtends = new ArrayList<IPackage>();
	private List<IModelEntity> children = new ArrayList<IModelEntity>();
	
	
	@Override
	public boolean isEmpty() {
		return children.isEmpty();
	}

	@Override
	public boolean containsChild(IModelEntity child) {
		return children.contains(child);
	}

	@Override
	public List<IModelEntity> getChildren() {
		return children;
	}

	@Override
	public boolean removeChild(IModelEntity child) {
		return children.remove(child);
	}

	@Override
	public void addChild(IModelEntity child) {
		children.add(child);
	}

	@Override
	public void setParent(IDiagram parent) {
		this.parent = parent;
	}

	@Override
	public IModelEntity getParent() {
		return parent;
	}
	
	@Override
	public List<IAction> getActions() {
		List<IAction> actions = new ArrayList<IAction>();
		for (IModelEntity entity : children) {
			if (entity instanceof IPackage) {
				actions.add((IAction) entity);
			}
		}
		return actions;
	}

	@Override
	public List<IPackage> getExtends() {
		return pExtends;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}

	@Override
	public void setNamespace(String namespace) {
		Assert.isNotNull(namespace, "The Diagram requires a Name that is not NULL"); //$NON-NLS-1$
		this.namespace = namespace;
	}

}
