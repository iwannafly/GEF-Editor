/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editparts.commands;

import org.eclipse.gef.commands.Command;
import org.neclipse.gef.struts2editor.model.IComment;

/**
 * @author nbhusare
 *
 */
public class S2CommentChangeCommand extends Command {
	
	private String oldComment;
	private String newComment;
	private	IComment entity; 
	
	/**
	 * 
	 * @param entity
	 * @param comment
	 */
	public S2CommentChangeCommand(IComment entity, String comment) {
		this.entity = entity;
		this.newComment = comment;
	}

	/**
	 * 
	 * @param oldComment
	 */
	public void setOldComment(String oldComment) {
		this.oldComment = oldComment;
	}
	
	@Override
	public boolean canExecute() {
		return newComment != null;
	}
	
	@Override
	public void undo() {
		entity.setComment(oldComment);
	}
	
	@Override
	public void redo() {
		entity.setComment(newComment);
	}
	
	@Override
	public void execute() {
		redo();
	}
	
}
