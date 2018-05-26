package sonc.client.panel;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BattleConfigurationPanel extends VerticalPanel implements Panel {

	private static final String GENERAL_PANEL_WIDTH = "250px";
	private static final String NICKS_TO_SELECTED_LIST_WIDTH = "180px";
	private static final String SELECTED_NICKS_LIST_WIDTH = "240px";
	private static final String BUTTONS_WIDTH = "30px";
	
	private final HorizontalPanel horizontalPanelSelection;
	private final ListBox listBoxNicksToSelect;
	private final Button buttonPlus;
	private final Button buttonMinus;
	private final ListBox listBoxSelectedNicks;
	private final HorizontalPanel horizontalPanelButtons;
	private final Button buttonOK;
	
	public void setNicksToSelect(List<String> nicks) {
		this.listBoxNicksToSelect.clear();
		for (String nick : nicks)
			this.listBoxNicksToSelect.addItem(nick);
	}
	
	public List<String> getSelectedNicks() {
		List<String> nicks = new ArrayList<>();
		for (int i = 0; i < this.listBoxSelectedNicks.getItemCount(); i++)
			nicks.add(this.listBoxSelectedNicks.getItemText(i));
		return nicks;
	}
	
	public void addNickOnListOfSelectedNicks() {
		String nick = this.listBoxNicksToSelect.getSelectedItemText();
		if ((nick != null) && (!nick.equals("")))
			this.listBoxSelectedNicks.addItem(nick);
		this.listBoxNicksToSelect.setSelectedIndex(-1);
	}
	
	public void removeNickOnListOfSelectedNicks() {
		int selected = this.listBoxSelectedNicks.getSelectedIndex();
		if (selected > -1)
			this.listBoxSelectedNicks.removeItem(selected);
	}
	
	public void setClickHandlerButtonPlus(ClickHandler handler) {
		this.buttonPlus.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonMinus(ClickHandler handler) {
		this.buttonMinus.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonOK(ClickHandler handler) {
		this.buttonOK.addClickHandler(handler);
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
		
		this.horizontalPanelButtons = new HorizontalPanel();
		this.add(this.horizontalPanelButtons);
		
		this.buttonOK = new Button();
		this.buttonOK.setText("OK");
		this.horizontalPanelButtons.add(this.buttonOK);
	}
	
	public void reset() {
		this.listBoxNicksToSelect.clear();
		this.listBoxSelectedNicks.clear();
	}
}