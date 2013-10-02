/**
 * 
 */
package org.neclipse.graphiti.struts2editor.model;

/**
 * Interface to represent a comment.
 *  
 * @author nbhusare
 */
public interface IComment extends IDiagramChild {
	
	String DEFAULT_COMMENT = "This is a comment";
	
	String COMMENT_CHANGED = "Comment Changed";
	
	String getComment();
	
	void setComment(String comment);
}
