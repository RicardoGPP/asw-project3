package sonc.client;

import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import sonc.shared.UpdatePasswordPanel;

public class SoncView implements EntryPoint {

	//private final SoncServiceAsync service = GWT.create(SoncService.class);
	
	public void onModuleLoad() {
		RootPanel root = RootPanel.get();
		
		UpdatePasswordPanel signInPanel = new UpdatePasswordPanel();
		
		root.add(signInPanel);
	}
}