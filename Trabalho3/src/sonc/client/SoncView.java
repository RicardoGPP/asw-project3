package sonc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class SoncView implements EntryPoint {

	private final SoncServiceAsync service = GWT.create(SoncService.class);
	
	public void onModuleLoad() {
	
	}
	
}