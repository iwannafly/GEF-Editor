package org.neclipse.graphiti.struts2editor.ui.editparts.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Container;
import org.neclipse.graphiti.struts2editor.model.rewrite.IS2Model;

/**
 * 
 * @author nbhusare
 * 
 */
public class S2EntityCreationCommand extends Command {
	
	protected IS2Model child;
	protected IS2Container parent;
	
	protected Rectangle dimension;

	public S2EntityCreationCommand(IS2Model child, IS2Container parent,
			Rectangle dimension) {
		this.child = child;
		this.parent = parent;
		this.dimension = dimension;
		setLabel("Create Model");
	}
	
	@Override
	public boolean canExecute() {
		return child != null && parent != null && dimension != null;
	}
	
	@Override
	public void undo() {
		parent.removeChild(child);
	}
	
	@Override
	public void redo() {
		parent.addChild(child);
	}
	
	@Override
	public void execute() {
		child.setDimension(dimension);
		child.setParent(parent);
		redo();
	}
}
