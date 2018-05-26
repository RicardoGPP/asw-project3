package sonc.client.panel;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class SignUpPanel extends UserPanel {
	
	private final Button buttonRegister;
	private final Button buttonCancel;
	
	public void setClickHandlerButtonRegister(ClickHandler handler) {
		this.buttonRegister.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonCancel(ClickHandler handler) {
		this.buttonCancel.addClickHandler(handler);
	}
	
	public SignUpPanel() {
		this.buttonRegister = new Button();
		this.buttonRegister.setText("Register");
		this.horizontalPanelButtons.add(this.buttonRegister);
		
		this.buttonCancel = new Button();
		this.buttonCancel.setText("Cancel");
		this.horizontalPanelButtons.add(this.buttonCancel);
	}
}