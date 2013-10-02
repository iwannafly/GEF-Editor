/**
 * 
 */
package org.neclipse.gef.struts2editor.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author nbhusare
 *
 */
public class S2ModelerPerspective implements IPerspectiveFactory {

	private final String leftFolderID = "left"; //$NON-NLS-1$
	
	private final String bottomFolderID = "leftbottom"; //$NON-NLS-1$
	
	protected IFolderLayout bottom;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		/* Adding the layout actions */
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file"); //$NON-NLS-1$
		
		/* Adding the Project explorer to the left of the editor */
		IFolderLayout folder = layout.createFolder(leftFolderID, IPageLayout.LEFT, (float)0.26, editorArea);
		folder.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		/* Adding the outline view to the bottom of the Project explorer view */
		IFolderLayout outlineFolder = layout.createFolder(bottomFolderID, IPageLayout.BOTTOM, (float)0.60, leftFolderID); //$NON-NLS-1$
		outlineFolder.addView(IPageLayout.ID_OUTLINE);
		
		bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, (float)0.60, editorArea); //$NON-NLS-1$
		bottom.addView("org.eclipse.mylyn.tasks.ui.views.tasks"); //$NON-NLS-1$
	}

}
