/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nbhusare
 *
 */
public class S2Container extends S2Model implements IS2Container {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<IS2Model> children = new ArrayList<IS2Model>();

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container#getChildren()
	 */
	@Override
	public List<IS2Model> getChildren() {
		return children;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container#setChildren(java.util.List)
	 */
	@Override
	public void setChildren(List<IS2Model> children) {
		this.children = children;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container#addChild(org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model)
	 */
	@Override
	public void addChild(IS2Model child) {
		children.add(child);
		firePropertyChange(S2ModelCngConstants.CHILD_ADDED, null, child);
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container#removeChild(org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model)
	 */
	@Override
	public boolean removeChild(IS2Model child) {
		boolean result = children.remove(child);
		firePropertyChange(S2ModelCngConstants.CHILD_REMOVED, null, child);
		return result;
	}

}
