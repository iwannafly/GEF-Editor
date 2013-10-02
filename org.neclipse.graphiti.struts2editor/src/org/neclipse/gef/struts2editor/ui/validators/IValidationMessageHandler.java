/**
 * 
 */
package org.neclipse.gef.struts2editor.ui.validators;

/**
 * @author nbhusare
 *
 */
public interface IValidationMessageHandler {
		
	/**
	 * Sets the message text on the status line.
	 * @param text the text message to output to the status line.
	 */
	void setMessageText(String text);
	
	/**
	 * Reset the status line.
	 */
	void reset();
}
