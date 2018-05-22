package sonc.shared;

import com.google.gwt.user.client.ui.Button;

public class SignInPanel extends UserPanel {

	private final Button buttonSignIn;
	private final Button buttonSignUp;
		
	public Button getButtonLogin() {
		return buttonSignIn;
	}

	public Button getButtonRegister() {
		return buttonSignUp;
	}

	public SignInPanel() {		
		this.buttonSignIn = new Button();
		this.buttonSignIn.setText("Sign in");
		this.horizontalPanelButtons.add(this.buttonSignIn);
		
		this.buttonSignUp = new Button();
		this.buttonSignUp.setText("Sign up");
		this.horizontalPanelButtons.add(this.buttonSignUp);
	}
	
}