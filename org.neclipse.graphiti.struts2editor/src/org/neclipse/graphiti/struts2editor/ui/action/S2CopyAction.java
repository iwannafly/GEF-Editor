/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui.action;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.neclipse.graphiti.struts2editor.model.IEntity;
import org.neclipse.graphiti.struts2editor.ui.editparts.S2DiagramEditPart;

/**
 * @author nbhusare
 *
 */
public class S2CopyAction extends SelectionAction {

	private Object template;

	public S2CopyAction(IEditorPart editor) {
		super(editor);
		setId(ActionFactory.COPY.getId());
		setText("&Copy");
	}
	
	@Override
	protected boolean calculateEnabled() {
		ISelection s = getSelection();
		template = null;
		if (s instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) s;
			if (selection != null && selection.size() == 1) {
				Object obj = selection.getFirstElement();
				if (obj instanceof EditPart
						&& !(obj instanceof S2DiagramEditPart)) {
					Object model = ((EditPart) obj).getModel();
					if (model instanceof IEntity) {
						template = model;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void dispose() {
		template = null;
	}
	
	@Override
	public void run() {
		Clipboard.getDefault().setContents(template);
	}
}
