package sonc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class SoncView implements EntryPoint {

	private final SoncServiceAsync service = GWT.create(SoncService.class);
	
	public void onModuleLoad() {
		
	}
}