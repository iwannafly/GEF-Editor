/**
 * 
 */
package org.neclipse.graphiti.struts2editor.ui;

import org.eclipse.ui.IPageLayout;

/**
 * @author nbhusare
 *
 */
public class S2TutorPerspective extends S2ModelerPerspective {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		super.createInitialLayout(layout);
		bottom.addView("org.neclipse.graphiti.struts2editor.browserview"); //$NON-NLS-1$
	}

}
