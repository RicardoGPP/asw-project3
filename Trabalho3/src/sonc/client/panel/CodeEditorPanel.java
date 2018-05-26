package sonc.client.panel;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;

public class CodeEditorPanel extends ContentMenuPanel {

	private final TextArea textAreaCode;
	private final Button buttonLoad;
	private final Button buttonSave;
	private final Button buttonClean;
	private final Button buttonTest;
<<<<<<< HEAD
	private final Button buttonBack;
		
	public String getCode() {
		return this.textAreaCode.getText();
	}

	public void setCode(String code) {
		this.textAreaCode.setText(code);
	}
	
	public void setClickHandlerButtonLoad(ClickHandler handler) {
		this.buttonLoad.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonSave(ClickHandler handler) {
		this.buttonSave.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonClean(ClickHandler handler) {
		this.buttonClean.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonTest(ClickHandler handler) {
		this.buttonTest.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonBack(ClickHandler handler) {
		this.buttonBack.addClickHandler(handler);
	}
	
	public CodeEditorPanel() {
		this.textAreaCode = new TextArea();
		this.textAreaCode.setSize("100%", "478px");
		this.verticalPanelContent.add(this.textAreaCode);
		
		this.buttonLoad = new Button();
		this.buttonLoad.setText("Load");
		this.buttonLoad.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonLoad);
		
		this.buttonSave = new Button();
		this.buttonSave.setText("Save");
		this.buttonSave.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonSave);
		
		this.buttonClean = new Button();
		this.buttonClean.setText("Clean");
		this.buttonClean.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonClean);
		
		this.buttonTest = new Button();
		this.buttonTest.setText("Test");
		this.buttonTest.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonTest);
		
		this.buttonBack = new Button();
		this.buttonBack.setText("Back");
		this.buttonBack.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonBack);
	}
	
	public void reset() {
		this.textAreaCode.setText("");
	}
=======
		
	public String getCode() {
		return this.textAreaCode.getText();
	}

	public void setCode(String code) {
		this.textAreaCode.setText(code);
	}
	
	public void setClickHandlerButtonLoad(ClickHandler handler) {
		this.buttonLoad.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonSave(ClickHandler handler) {
		this.buttonSave.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonClean(ClickHandler handler) {
		this.buttonClean.addClickHandler(handler);
	}
	
	public void setClickHandlerButtonTest(ClickHandler handler) {
		this.buttonTest.addClickHandler(handler);
	}
	
	public CodeEditorPanel() {
		this.textAreaCode = new TextArea();
		this.textAreaCode.setSize("100%", "100%");
		this.verticalPanelContent.add(this.textAreaCode);
		
		this.buttonLoad = new Button();
		this.buttonLoad.setText("Load");
		this.buttonLoad.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonLoad);
		
		this.buttonSave = new Button();
		this.buttonSave.setText("Save");
		this.buttonSave.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonSave);
		
		this.buttonClean = new Button();
		this.buttonClean.setText("Clean");
		this.buttonClean.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonClean);
		
		this.buttonTest = new Button();
		this.buttonTest.setText("Test");
		this.buttonTest.setWidth("100%");
		this.verticalPanelMenu.add(this.buttonTest);		
	}
	
>>>>>>> branch 'master' of https://github.com/RicardoGPP/asw-project3.git
}