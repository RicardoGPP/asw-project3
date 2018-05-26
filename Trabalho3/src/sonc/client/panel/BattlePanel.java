package sonc.client.panel;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import sonc.shared.Movie.Frame;
import sonc.shared.Movie.Oblong;
import sonc.shared.Movie.Score;
import sonc.utils.Data;

public class BattlePanel extends ContentMenuPanel {

	private static final int CANVAS_WIDTH = Data.WORLD_WIDTH;
	private static final int CANVAS_HEIGHT = Data.WORLD_HEIGHT;
	private static final String SCORE_WIDTH = (CANVAS_WIDTH - 8) + "px";
	private static final String SCORE_HEIGHT = "80px";
	private static final int OBLONG_HEIGHT = 10;
	private static final String DEFAULT_CANVAS_COLOR = "#1E90FF";
	
	private final Canvas canvasBattle;
	private final TextArea textAreaScore;
	private final Button buttonStart;
	private final Button buttonConfigure;
	private final Button buttonCodeEditor;
	private final Button buttonChangePassword;
	private final Button buttonLogout;

	public void setClickHandlerButtonStart(ClickHandler handler) {
		this.buttonStart.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonConfigure(ClickHandler handler) {
		this.buttonConfigure.addClickHandler(handler);
	}

	public void setClickHandlerButtonCodeEditor(ClickHandler handler) {
		this.buttonCodeEditor.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonUpdatePassword(ClickHandler handler) {
		this.buttonChangePassword.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonLogout(ClickHandler handler) {
		this.buttonLogout.addClickHandler(handler);
	}
	
	public BattlePanel() {
		this.canvasBattle = Canvas.createIfSupported();
					
		this.canvasBattle.setSize(CANVAS_WIDTH + "px", CANVAS_HEIGHT + "px");
		this.canvasBattle.setCoordinateSpaceWidth(CANVAS_WIDTH);
		this.canvasBattle.setCoordinateSpaceHeight(CANVAS_HEIGHT);
		this.canvasBattle.setStyleName("canvas");
		this.verticalPanelContent.add(this.canvasBattle);
		
		this.textAreaScore = new TextArea();
		this.textAreaScore.setSize(SCORE_WIDTH, SCORE_HEIGHT);
		this.textAreaScore.setReadOnly(true);
		this.verticalPanelContent.add(this.textAreaScore);
		
		this.buttonStart = new Button();
		this.buttonStart.setText("Start");
		this.buttonStart.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonStart);
		
		this.buttonConfigure = new Button();
		this.buttonConfigure.setText("Configure");
		this.buttonConfigure.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonConfigure);
		
		this.buttonCodeEditor = new Button();
		this.buttonCodeEditor.setText("Code editor");
		this.buttonCodeEditor.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonCodeEditor);
		
		this.buttonChangePassword = new Button();
		this.buttonChangePassword.setText("Update password");
		this.buttonChangePassword.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonChangePassword);
		
		this.buttonLogout = new Button();
		this.buttonLogout.setText("Logout");
		this.buttonLogout.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonLogout);
	}
	
	private void refresh(Context2d context) {
		this.textAreaScore.setText("");
		context.setFillStyle(DEFAULT_CANVAS_COLOR);
		context.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
	}
	
	private void drawOblong(Context2d context, Oblong oblong) {
		context.save();
		context.translate(oblong.getX() + (oblong.getSize() / 2), oblong.getY() + (OBLONG_HEIGHT / 2));
		context.rotate(oblong.getHeading());
		context.setFillStyle(oblong.getColor());
		context.fillRect(-(oblong.getSize() / 2), -(OBLONG_HEIGHT / 2), oblong.getSize(), OBLONG_HEIGHT);		
		context.restore();
	}
	
	private void drawScore(Score score) {
		String entry = this.textAreaScore.getText();
		entry += ((entry.equals("")) ? "" : "\n") + score.getName() +  " - (" + score.getStatus() + ", " + score.getPoints() + ")"; 
		this.textAreaScore.setText(entry);
	}
	
	public void draw(Frame frame) {		
		Context2d context = this.canvasBattle.getContext2d();		
		this.refresh(context);		
		for (Oblong oblong : frame.getOblongs())
			this.drawOblong(context, oblong);		
		for (Score score : frame.getScores())
			this.drawScore(score);		
	}
	
	public void reset() {
		Context2d context = this.canvasBattle.getContext2d();
		context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		this.textAreaScore.setText("");
	}
}