/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model.rewrite;

import java.util.List;

/**
 * @author nbhusare
 *
 */
public class S2Package extends S2ConnectableContainer implements IS2Package {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The Type of the Model element.
	 * 
	 * @see S2Model#getType()
	 */
	private static final Object MODEL_TYPE_PACKAGE = "Package Model";
	
	/* Struts 2 Package attributes */
	private String ns;
	private boolean abs;
	private List<IS2Model> exts;
	
	public S2Package() {
		super();
		setType(MODEL_TYPE_PACKAGE);
	}
	
	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#getNamespace()
	 */
	@Override
	public String getNamespace() {
		return ns;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#setNamespace(java.lang.String)
	 */
	@Override
	public void setNamespace(String ns) {
		this.ns = ns;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#isAbstract()
	 */
	@Override
	public boolean isAbstract() {
		return abs;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#setAbstract(boolean)
	 */
	@Override
	public void setAbstract(boolean abs) {
		this.abs = abs;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#getExtends()
	 */
	@Override
	public List<IS2Model> getExtends() {
		return exts;
	}

	/* (non-Javadoc)
	 * @see org.neclipse.graphiti.struts2editor.model.rewrite.IS2Package#setExtends(org.neclipse.graphiti.struts2editor.model.rewrite.IS2Extends)
	 */
	@Override
	public void setExtends(IS2Model ext) {
		if (!exts.contains(ext)) {
			exts.add(ext);
			//firePropertyChange(property, oldValue, newValue);
		}
	}

	@Override
	public void removeExtends(IS2Model ext) {
		if (exts.contains(ext)) {
			exts.remove(ext);
			//firePropertyChange(property, oldValue, newValue);
		}
	}
}
