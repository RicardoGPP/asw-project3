package sonc.client.panel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class ContentMenuPanel extends HorizontalPanel {
	
	private static final String GENERAL_PANEL_WIDTH = "800px";
	private static final String GENERAL_PANEL_HEIGHT = "500px";
	private static final String CONTENT_PANEL_WIDTH = "672px";
	private static final String CONTENT_PANEL_HEIGHT = "478px";
	private static final String MENU_PANEL_WIDTH = "100px";
	
	protected final VerticalPanel verticalPanelContent;
	protected final VerticalPanel verticalPanelMenu;
	
	public ContentMenuPanel() {		
		this.setSize(GENERAL_PANEL_WIDTH, GENERAL_PANEL_HEIGHT);
		this.setStyleName("panel");
		
		this.verticalPanelContent = new VerticalPanel();
		this.verticalPanelContent.setSize(CONTENT_PANEL_WIDTH, CONTENT_PANEL_HEIGHT);
		this.add(this.verticalPanelContent);
		
		this.verticalPanelMenu = new VerticalPanel();
		this.verticalPanelMenu.setWidth(MENU_PANEL_WIDTH);
		this.add(this.verticalPanelMenu);		
	}
}
