package org.neclipse.graphiti.struts2editor.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.neclipse.graphiti.struts2editor.S2Plugin;
import org.neclipse.graphiti.struts2editor.ui.views.S2Browser;

/**
 * Provides utility methods for UI
 * 
 */
public class S2EditorUI {
	
	/* Used to cache ImageDescriptor obtained from a file-path */
	private static final Map<String, ImageDescriptor> DESCRIPTOR_CACHE = new HashMap<String, ImageDescriptor>();
	
	/**
	 * Utility method for creating the {@code ImageDescriptor} object. 
	 * @param tool tool for which {@code ImageDescriptor} is to be created.
	 * @return a new {@code ImageDescriptor} instance or the cached copy.
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return getImageDescriptor(S2Plugin.PLUGIN_ID, path);
	}
	
	/**
	 * Utility method for creating the {@code ImageDescriptor} object. 
	 * @param tool tool for which {@code ImageDescriptor} is to be created.
	 * 
	 * @param pluginId the ID of the plug-in containing the image.
	 * @param path the path of the Image in the plug-in.
	 * @return the {@code ImageDescriptor} for the image.
	 */
	private static ImageDescriptor getImageDescriptor(String pluginId, String path) {
		ImageDescriptor descriptor = DESCRIPTOR_CACHE.get(pluginId + path);
		if (descriptor == null) {
			descriptor = S2Plugin.getImageDescriptor(pluginId, path);
			DESCRIPTOR_CACHE.put(pluginId + path, descriptor);
		}
		return descriptor;
	}
	
	/**
	 * Finds the Struts 2 Browser view.
	 * @param browserID	the browser ID
	 * @return the Struts 2 Browser.
	 */
	public static S2Browser findBrowser(String browserID) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IViewPart view = page.findView(browserID);
		if (view != null) {
			return (S2Browser) view;
		}
		IViewReference[] refs = page.getViewReferences();
		for (int i = 0; i < refs.length; i++) {
			if (browserID.equals(refs[i].getId())) {
				return (S2Browser) refs[i].getPart(true);
          }
		}
		return null;
	}
	
}
