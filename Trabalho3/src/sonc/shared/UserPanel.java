package sonc.shared;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public abstract class UserPanel extends AuthenticationPanel {
	
	private final Label labelUsername;
	private final TextBox textBoxUsername;
	private final Label labelPassword;
	private final PasswordTextBox passwordTextBoxPassword;

	public TextBox getTextBoxUsuario() {
		return textBoxUsername;
	}
	
	public PasswordTextBox getPasswordTextBoxSenha() {
		return passwordTextBoxPassword;
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
	
}