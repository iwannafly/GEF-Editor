package org.neclipse.graphiti.struts2editor.ui.validators;

import org.eclipse.ui.IEditorSite;
import org.neclipse.graphiti.struts2editor.ui.S2EditorUI;

/**
 * The handler for setting and resetting the messages on the status line.
 * 
 * @author nbhusare
 */
public class S2StatusMessageHandler implements IValidationMessageHandler{

	private IEditorSite editorSite;

	public S2StatusMessageHandler(IEditorSite editorSite) {
		this.editorSite = editorSite;
	}
	
	/**
	 * Sets the message text on the status line.
	 * @param text the text message to output to the status line.
	 */
	@Override
	public void setMessageText(String text) {
		editorSite.getActionBars().getStatusLineManager().setErrorMessage(S2EditorUI.getImageDescriptor("icons/errorwarning_tab.gif").createImage(),text);
	}

	/**
	 * Reset the status line.
	 */
	@Override
	public void reset() {
		editorSite.getActionBars().getStatusLineManager().setErrorMessage(null);
	}

}
