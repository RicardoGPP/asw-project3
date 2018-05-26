package sonc.client.panel;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class SignInPanel extends UserPanel {

	private final Button buttonSignIn;
	private final Button buttonSignUp;
		
	public void setClickHandlerButtonSignIn(ClickHandler handler) {
		this.buttonSignIn.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonSignUp(ClickHandler handler) {
		this.buttonSignUp.addClickHandler(handler);
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