package sonc.shared;

import com.google.gwt.user.client.ui.Button;

public class SignUpPanel extends AuthenticationPanel {
	
	private final Button buttonRegister;
	private final Button buttonCancel;
	
	public SignUpPanel() {
		this.buttonRegister = new Button();
		this.buttonRegister.setText("Register");
		this.horizontalPanelButtons.add(this.buttonRegister);
		
		this.buttonCancel = new Button();
		this.buttonCancel.setText("Cancel");
		this.horizontalPanelButtons.add(this.buttonCancel);
	}
}