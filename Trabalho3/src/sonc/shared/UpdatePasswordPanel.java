package sonc.shared;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class UpdatePasswordPanel extends AuthenticationPanel {
	
	private final Label labelOldPassword;
	private final PasswordTextBox passwordTextBoxOldPassword;
	private final Label labelNewPassword;
	private final PasswordTextBox passwordTextBoxNewPassword;
	private final Button buttonCancel;
	private final Button buttonOK;
	
	public PasswordTextBox getPasswordTextBoxOldPassword() {
		return passwordTextBoxOldPassword;
	}

	public PasswordTextBox getPasswordTextBoxNewPassword() {
		return passwordTextBoxNewPassword;
	}

	public Button getButtonCancel() {
		return buttonCancel;
	}

	public Button getButtonOK() {
		return buttonOK;
	}

	public UpdatePasswordPanel() {
		this.labelOldPassword = new Label();
		this.labelOldPassword.setText("Old password:");
		this.labelOldPassword.setWidth("100%");
		this.verticalPanelFields.add(this.labelOldPassword);
		
		this.passwordTextBoxOldPassword = new PasswordTextBox();
		this.passwordTextBoxOldPassword.setWidth("100%");
		this.verticalPanelFields.add(this.passwordTextBoxOldPassword);
		
		this.labelNewPassword = new Label();
		this.labelNewPassword.setText("New password:");
		this.labelNewPassword.setWidth("100%");
		this.verticalPanelFields.add(this.labelNewPassword);
		
		this.passwordTextBoxNewPassword = new PasswordTextBox();
		this.passwordTextBoxNewPassword.setWidth("100%");
		this.verticalPanelFields.add(this.passwordTextBoxNewPassword);
		
		this.buttonOK = new Button();
		this.buttonOK.setText("OK");
		this.horizontalPanelButtons.add(this.buttonOK);
		
		this.buttonCancel = new Button();
		this.buttonCancel.setText("Cancel");
		this.horizontalPanelButtons.add(this.buttonCancel);		
	}
}