package sonc.client.panel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BattleConfigurationPanel extends VerticalPanel {

	private static final String GENERAL_PANEL_WIDTH = "250px";
	private static final String NICKS_TO_SELECTED_LIST_WIDTH = "180px";
	private static final String SELECTED_NICKS_LIST_WIDTH = "240px";
	private static final String BUTTONS_WIDTH = "30px";
	
	private final HorizontalPanel horizontalPanelSelection;
	private final ListBox listBoxNicksToSelect;
	private final Button buttonPlus;
	private final Button buttonMinus;
	private final ListBox listBoxSelectedNicks;
	
	public ListBox getListBoxNicksToSelect() {
		return listBoxNicksToSelect;
	}

	public Button getButtonPlus() {
		return buttonPlus;
	}

	public Button getButtonMinus() {
		return buttonMinus;
	}

	public ListBox getListBoxSelectedNicks() {
		return listBoxSelectedNicks;
	}

	public BattleConfigurationPanel() {
		this.setWidth(GENERAL_PANEL_WIDTH);
		this.setStyleName("panel");
		
		this.horizontalPanelSelection = new HorizontalPanel();
		this.add(this.horizontalPanelSelection);		
		
		this.listBoxNicksToSelect = new ListBox();
		this.listBoxNicksToSelect.setWidth(NICKS_TO_SELECTED_LIST_WIDTH);
		this.listBoxNicksToSelect.setMultipleSelect(false);
		this.horizontalPanelSelection.add(this.listBoxNicksToSelect);
		
		this.buttonPlus = new Button();
		this.buttonPlus.setWidth(BUTTONS_WIDTH);
		this.buttonPlus.setText("+");
		this.horizontalPanelSelection.add(this.buttonPlus);
		
		this.buttonMinus = new Button();
		this.buttonMinus.setWidth(BUTTONS_WIDTH);
		this.buttonMinus.setText("-");
		this.horizontalPanelSelection.add(this.buttonMinus);		
		
		this.listBoxSelectedNicks = new ListBox();
		this.listBoxSelectedNicks.setWidth(SELECTED_NICKS_LIST_WIDTH);
		this.listBoxSelectedNicks.setMultipleSelect(false);
		this.listBoxSelectedNicks.setVisibleItemCount(15);
		this.add(this.listBoxSelectedNicks);		
	}
}