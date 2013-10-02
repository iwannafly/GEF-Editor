/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.editor;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.AbstractTransferDragSourceListener;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.neclipse.gef.struts2editor.model.S2Package;

/**
 * Listener for the drag events made in the outline view. 
 * 
 * @author nbhusare
 */
public class S2EntityTransferDragSrcListener extends AbstractTransferDragSourceListener {
	
	/**
	 * 
	 * @param viewer
	 */
	public S2EntityTransferDragSrcListener(EditPartViewer viewer) {
		super(viewer);
	}

	
	/**
	 * This method checks whether the selected object is allowed to be dragged
	 * or not.
	 */
	@Override
	public void dragStart(DragSourceEvent event) {
		super.dragStart(event);
		Object selectedItem = getSelectedItem();
		if (selectedItem == null) event.doit = false;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.DragSourceListener#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
	 */
	@Override
	public void dragSetData(DragSourceEvent event) {
		/* Initialize the data in the event after the mouse is released on the target */
		event.data = getSelectedItem();
	}

	
	/**
	 * Return the object selected from dragging. 
	 * 
	 * @return the object selected from dragging.
	 */
	private Object getSelectedItem() {
		List selectedParts = getViewer().getSelectedEditParts();
		
		if (selectedParts.size() == 1) {
			EditPart selPart = (EditPart) selectedParts.get(0);
			Object selModel = selPart.getModel();
			if (selModel instanceof S2Package) {
				return selModel;
			} 
		}
		
		return null;
	}

}
