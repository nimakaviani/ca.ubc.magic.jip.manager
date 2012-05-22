package de.spiritlink.mvc;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.spiritlink.mvc.view.FormView;
import de.spiritlink.mvc.view.LastCreateView;
import de.spiritlink.mvc.view.View;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addStandaloneView(View.ID,  true, IPageLayout.BOTTOM, 0.35f, editorArea);
		layout.addStandaloneView(LastCreateView.ID,  true, IPageLayout.LEFT, 0.5f, editorArea);
		layout.addStandaloneView(FormView.ID,  true, IPageLayout.LEFT, 0.5f, editorArea);
	}

}
