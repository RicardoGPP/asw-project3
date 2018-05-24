package sonc.client.panel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public abstract class UserPanel extends AuthenticationPanel {
	
	private final Label labelUsername;
	private final TextBox textBoxUsername;
	private final Label labelPassword;
	private final PasswordTextBox passwordTextBoxPassword;

	public String getUsername() {
		return this.textBoxUsername.getText();
	}
	
	public void setUsername(String username) {
		this.textBoxUsername.setText(username);
	}
	
	public String getPassword() {
		return this.passwordTextBoxPassword.getText();
	}
	
	public void setPassword(String password) {
		this.passwordTextBoxPassword.setText(password);
	}
	
	public UserPanel() {
		this.labelUsername = new Label();
		this.labelUsername.setText("Username: ");
		this.labelUsername.setWidth("100%");
		this.verticalPanelFields.add(this.labelUsername);
				
		this.textBoxUsername = new TextBox();
		this.textBoxUsername.setWidth("100%");
		this.verticalPanelFields.add(this.textBoxUsername);
				
		this.labelPassword = new Label();
		this.labelPassword.setText("Password: ");
		this.labelPassword.setWidth("100%");
		this.verticalPanelFields.add(this.labelPassword);
		
		this.passwordTextBoxPassword = new PasswordTextBox();
		this.passwordTextBoxPassword.setWidth("100%");
		this.verticalPanelFields.add(this.passwordTextBoxPassword);
	}
	
	public boolean isAllFilled() {
		return ((!this.getUsername().trim().equals("")) && (!this.getPassword().equals("")));
	}
	
	public void clearAllFields() {
		this.setUsername("");
		this.setPassword("");
	}
}