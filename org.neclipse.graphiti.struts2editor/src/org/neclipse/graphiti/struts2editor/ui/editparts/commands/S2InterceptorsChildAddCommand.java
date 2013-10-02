/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.eclipse.gef.commands.Command;
import org.neclipse.graphiti.struts2editor.model.IContainer;
import org.neclipse.graphiti.struts2editor.model.IEntity;

/**
 * @author nbhusare
 *
 */
public class S2InterceptorsChildAddCommand extends Command {
	
	private IEntity child;
	private IContainer parent;
	private int index;
	
	public S2InterceptorsChildAddCommand(IEntity child, IContainer parent) {
		this.child = child;
		this.parent = parent;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public boolean canExecute() {
		return child != null && parent != null;
	}
	
	@Override
	public void undo() {
		parent.removeChild(child);
	}
	
	@Override
	public void redo() {
		if (index < 0) {
			parent.addChild(child);
		} else {
			parent.addChild(child, index);
		}
	}
	
	@Override
	public void execute() {
		redo();
	}
}
