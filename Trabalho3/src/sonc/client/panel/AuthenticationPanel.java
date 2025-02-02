package sonc.client.panel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

<<<<<<< HEAD
public abstract class AuthenticationPanel extends VerticalPanel implements Panel {	
	
	private static final String GENERAL_PANEL_WIDTH = "250px";
	private static final String PANEL_FIELDS_WIDTH = "208px";
=======
public abstract class AuthenticationPanel extends VerticalPanel {	
	
	private static final String GENERAL_PANEL_WIDTH = "250px";
	private static final String PANEL_FIELDS_WIDTH = "228px";
>>>>>>> branch 'master' of https://github.com/RicardoGPP/asw-project3.git
	private static final String PANEL_FIELDS_HEIGHT = "110px";
	
	protected final VerticalPanel verticalPanelFields;
	protected final HorizontalPanel horizontalPanelButtons;	
	
	public AuthenticationPanel() {				
		this.setWidth(GENERAL_PANEL_WIDTH);
		this.setStyleName("panel");
		
		this.verticalPanelFields = new VerticalPanel();
		this.verticalPanelFields.setSize(PANEL_FIELDS_WIDTH, PANEL_FIELDS_HEIGHT);
		this.add(this.verticalPanelFields);
		
		this.horizontalPanelButtons = new HorizontalPanel();
		this.add(this.horizontalPanelButtons);
	}
}