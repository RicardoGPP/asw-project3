package sonc.shared;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;

public class BattlePanel extends ContentMenuPanel {

	private static final int CANVAS_WIDTH = 672;
	private static final int CANVAS_HEIGHT = 478;
	
	private final Canvas canvasBattle;
	private final Button buttonStart;
	private final Button buttonConfigure;
		
	public Context2d getContextCanvas() {
		return (this.canvasBattle == null) ? null : this.canvasBattle.getContext2d();
	}

	public Button getButtonStart() {
		return buttonStart;
	}

	public Button getButtonConfigure() {
		return buttonConfigure;
	}

	public BattlePanel() {
		this.canvasBattle = Canvas.createIfSupported();
		
		if (this.canvasBattle == null)
			Window.alert("Your browser doesn't support canvas.");
		else {			
			this.canvasBattle.setSize(CANVAS_WIDTH + "px", CANVAS_HEIGHT + "px");
			this.canvasBattle.setCoordinateSpaceWidth(CANVAS_WIDTH);
			this.canvasBattle.setCoordinateSpaceHeight(CANVAS_HEIGHT);
			this.canvasBattle.setStyleName("canvas");
			this.verticalPanelContent.add(this.canvasBattle);
		}
		
		this.buttonStart = new Button();
		this.buttonStart.setText("Start");
		this.buttonStart.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonStart);
		
		this.buttonConfigure = new Button();
		this.buttonConfigure.setText("Configure");
		this.buttonConfigure.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonConfigure);
	}
}