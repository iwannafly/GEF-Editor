/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @author nbhusare
 * 
 */
public class S2EditorContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry registry;
	
	public S2EditorContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		this.registry = registry;
	}

	@Override
	public void buildContextMenu(IMenuManager manager) {
		/* Add standalone group separators */
		GEFActionConstants.addStandardActionGroups(manager);
		
		/* Adding Copy, Paste actions to the Context menu */
		IAction action = registry.getAction(ActionFactory.COPY.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		action = registry.getAction(ActionFactory.PASTE.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		/* Adding Delete Action to the context menu */
		action = registry.getAction(ActionFactory.DELETE.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		/* Adding Undo-Redo actions to the context menu */
		action = registry.getAction(ActionFactory.UNDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		action = registry.getAction(ActionFactory.REDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
	}
}
