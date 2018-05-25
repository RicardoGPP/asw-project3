package sonc.client.panel;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import sonc.shared.Movie.Frame;
import sonc.shared.Movie.Oblong;
import sonc.shared.Movie.Score;

public class BattlePanel extends ContentMenuPanel {

	private static final int CANVAS_WIDTH = 672;
	private static final int CANVAS_HEIGHT = 478;
	private static final int OBLONG_HEIGHT = 10;
	
	private final Canvas canvasBattle;
	private final ListBox listBoxScore;
	private final Button buttonStart;
	private final Button buttonConfigure;

	public void setClickHandlerButtonStart(ClickHandler handler) {
		this.buttonStart.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonConfigure(ClickHandler handler) {
		this.buttonConfigure.addClickHandler(handler);
	}

	public BattlePanel() {
		this.canvasBattle = Canvas.createIfSupported();
					
		this.canvasBattle.setSize(CANVAS_WIDTH + "px", CANVAS_HEIGHT + "px");
		this.canvasBattle.setCoordinateSpaceWidth(CANVAS_WIDTH);
		this.canvasBattle.setCoordinateSpaceHeight(CANVAS_HEIGHT);
		this.canvasBattle.setStyleName("canvas");
		this.verticalPanelContent.add(this.canvasBattle);
		
		this.listBoxScore = new ListBox();
		this.listBoxScore.setWidth(CANVAS_WIDTH + "px");
		this.listBoxScore.setMultipleSelect(false);
		this.listBoxScore.setVisibleItemCount(7);
		this.listBoxScore.setEnabled(false);
		this.verticalPanelContent.add(this.listBoxScore);
		
		this.buttonStart = new Button();
		this.buttonStart.setText("Start");
		this.buttonStart.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonStart);
		
		this.buttonConfigure = new Button();
		this.buttonConfigure.setText("Configure");
		this.buttonConfigure.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonConfigure);
	}
	
	private void reset(Context2d context) {
		this.listBoxScore.clear();
		context.setFillStyle("#1E90FF");
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
		this.listBoxScore.addItem("(" + score.getStatus() + ", " + score.getPoints() + ") - " + score.getName());
	}
	
	public void draw(Frame frame) {		
		Context2d context = this.canvasBattle.getContext2d();		
		this.reset(context);		
		for (Oblong oblong : frame.getOblongs())
			this.drawOblong(context, oblong);		
		for (Score score : frame.getScores())
			this.drawScore(score);		
	}
}