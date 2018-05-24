package sonc.client.panel;

import com.google.gwt.event.dom.client.ClickHandler;
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
	
	public String getOldPassword() {
		return this.passwordTextBoxOldPassword.getText();
	}
	
	public void setOldPassword(String oldPassword) {
		this.passwordTextBoxOldPassword.setText(oldPassword);
	}
	
	public String getNewPassword() {
		return this.passwordTextBoxNewPassword.getText();
	}
	
	public void setNewPassword(String newPassword) {
		this.passwordTextBoxNewPassword.setText(newPassword);
	}
	
	public void setClickHandlerButtonCancel(ClickHandler handler) {
		this.buttonCancel.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonOK(ClickHandler handler) {
		this.buttonOK.addClickHandler(handler);
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
	
	public boolean isAllFilled() {
		return ((!this.getOldPassword().equals("")) && (!this.getNewPassword().equals("")));
	}
	
	public void clearAllFields() {
		this.setOldPassword("");
		this.setNewPassword("");
	}
}