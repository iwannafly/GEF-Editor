package org.neclipse.graphiti.struts2editor.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

/**
 * View for exploring the Struts 2 projects 
 *
 */
public class S2Browser extends ViewPart {
	
	/* Browser variables */
	private Browser browser;
	public static final String VIEW_ID = "org.neclipse.graphiti.struts2editor.browserview"; //$NON-NLS-1$
	private static final String DEFAULT_URL = "http://struts.apache.org/2.0.14/docs/home.html";
	
	public S2Browser() {}

	@Override
	public void createPartControl(Composite parent) {
		/* Set the parent layout */
		GridLayout gridLayout = new GridLayout(1, false);
		parent.setLayout(gridLayout);
		
		/* Create a new Browser witget */
		browser = new Browser(parent, SWT.None);
		org.eclipse.swt.layout.GridData data = new org.eclipse.swt.layout.GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);
		browser.setUrl(DEFAULT_URL);
	}

	public void openUrl(String url) {
		browser.setUrl(url);
	}
	
	@Override
	public void setFocus() {
		if (browser != null && !browser.isDisposed()) {
			browser.setFocus();
		}
	}
}
