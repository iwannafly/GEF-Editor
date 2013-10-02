/**
 * 
 */
package org.neclipse.gef.struts2editor.model;


/**
 * @author nbhusare
 *
 */
public class S2Comment extends S2AbstractEntity implements IComment {

	private String comment;
	private IEntity parent;
	
	public S2Comment() {
		setComment(DEFAULT_COMMENT);
	}
	
	@Override
	public IEntity getParent() {
		return parent;
	}

	@Override
	public Object cloneEntity() {
		return new S2Comment();
	}

	@Override
	public void setParent(IEntity parent) {
		this.parent = parent;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
