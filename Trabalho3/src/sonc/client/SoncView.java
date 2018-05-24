package sonc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import sonc.client.panel.BattleConfigurationPanel;
import sonc.client.panel.BattlePanel;
import sonc.client.panel.CodeEditorPanel;
import sonc.client.panel.SignInPanel;
import sonc.client.panel.SignUpPanel;
import sonc.client.panel.UpdatePasswordPanel;
import sonc.utils.Data;

public class SoncView implements EntryPoint {

	private final SoncServiceAsync service = GWT.create(SoncService.class);
	
	//Set your username and password here to use panels that asks for authentication 
	private String username = "";
	private String password = "";
	
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		final SignInPanel signInPanel = new SignInPanel();
		final SignUpPanel signUpPanel = new SignUpPanel();
		final UpdatePasswordPanel updatePasswordPanel = new UpdatePasswordPanel();
		final CodeEditorPanel codeEditorPanel = new CodeEditorPanel();
		final BattlePanel battlePanel = new BattlePanel();
		final BattleConfigurationPanel battleConfigurationPanel = new BattleConfigurationPanel();
				
		final DeckLayoutPanel deckLayoutPanel = new DeckLayoutPanel();
		deckLayoutPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
		deckLayoutPanel.getElement().getStyle().setLeft(0, Unit.PX);
		deckLayoutPanel.getElement().getStyle().setTop(0, Unit.PX);
		deckLayoutPanel.setSize("100%", "100%");
		rootPanel.add(deckLayoutPanel);
		
		deckLayoutPanel.add(signInPanel);
		deckLayoutPanel.add(signUpPanel);
		deckLayoutPanel.add(updatePasswordPanel);
		deckLayoutPanel.add(codeEditorPanel);
		deckLayoutPanel.add(battlePanel);
		deckLayoutPanel.add(battleConfigurationPanel);
		
		//Change this to set which panel should be visible
		deckLayoutPanel.showWidget(codeEditorPanel);
		
		//SignInPanel events
		signInPanel.setClickHandlerButtonSignIn(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!signInPanel.isAllFilled())
					Window.alert(Data.NOT_ALL_FIELDS_FILLED_MESSAGE);
				else
					service.authenticate(signInPanel.getUsername().trim(), signInPanel.getPassword(), new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (!result) {
								Window.alert(Data.FAILURE_LOGIN_MESSAGE);
								signInPanel.setPassword("");
							} else {
								username = signInPanel.getUsername().trim();
								password = signInPanel.getPassword();
								signInPanel.clearAllFields();
								deckLayoutPanel.showWidget(battlePanel);
							}
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
						}						
					});
			}
		});
		
		signInPanel.setClickHandlerButtonSignUp(new ClickHandler() {
			public void onClick(ClickEvent event) {
				signUpPanel.clearAllFields();
				deckLayoutPanel.showWidget(signUpPanel);
			}
		});
		
		//SignUpPanel events
		signUpPanel.setClickHandlerButtonRegister(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!signUpPanel.isAllFilled())
					Window.alert(Data.NOT_ALL_FIELDS_FILLED_MESSAGE);
				else
					service.register(signUpPanel.getUsername().trim(), signUpPanel.getPassword(), new AsyncCallback<Boolean>() {						
						public void onSuccess(Boolean result) {
							if (!result) {
								Window.alert(Data.FAILURE_REGISTRATION_MESSAGE);
								signUpPanel.setPassword("");
							} else {
								Window.alert(Data.SUCCESS_REGISTRATION_MESSAGE);
								deckLayoutPanel.showWidget(signInPanel);
							}
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
							signUpPanel.setPassword("");
						}						
					});				
			}
		});
	
		signUpPanel.setClickHandlerButtonCancel(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckLayoutPanel.showWidget(signInPanel);
			}
		});
	
		//UpdatePasswordPanel events
		updatePasswordPanel.setClickHandlerButtonOK(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (!updatePasswordPanel.isAllFilled())
					Window.alert(Data.NOT_ALL_FIELDS_FILLED_MESSAGE);
				else
					service.updatePassword(username, updatePasswordPanel.getOldPassword(), updatePasswordPanel.getNewPassword(), new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (!result) {
								Window.alert(Data.FAILURE_UPDATE_PASSWORD_MESSAGE);
								updatePasswordPanel.clearAllFields();
							} else {
								Window.alert(Data.SUCCESS_UPDATE_PASSWORD_MESSAGE);
								deckLayoutPanel.showWidget(battlePanel);
							}
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
							updatePasswordPanel.clearAllFields();
						}
					});
			}
		});
		
		updatePasswordPanel.setClickHandlerButtonCancel(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckLayoutPanel.showWidget(battlePanel);
			}
		});
		
		//CodeEditorPanel events
		codeEditorPanel.setClickHandlerButtonLoad(new ClickHandler() {
			public void onClick(ClickEvent event) {
				service.getCurrentCode(username, password, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						if ((result == null) || (result.trim().equals("")))
							Window.alert(Data.NO_CODE_ASSOCIATED_MESSAGE);
						else if ((codeEditorPanel.getCode().trim().equals("")) ||
						        (Window.confirm(Data.CONFIRM_REPLACE_CODE_MESSAGE)))
							codeEditorPanel.setCode(result);
					}
					
					public void onFailure(Throwable caught) {
						Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
					}
				});
			}
		});
		
		codeEditorPanel.setClickHandlerButtonSave(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (Window.confirm(Data.CONFIRM_SAVE_CODE_MESSAGE))
				{
					service.updateCode(username, password, codeEditorPanel.getCode().trim(), new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (!result)
								Window.alert(Data.FAILURE_SAVE_CODE_MESSAGE);
							else
								Window.alert(Data.SUCCESS_SAVE_CODE_MESSAGE);
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
						}
					});
				}
			}
		});
		
		codeEditorPanel.setClickHandlerButtonClean(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (Window.confirm(Data.CONFIRM_CLEAN_CODE_MESSAGE)) {
					service.updateCode(username, password, "", new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (!result)
								Window.alert(Data.FAILURE_CLEAN_CODE_MESSAGE);
							else
								codeEditorPanel.setCode("");
						}
						
						public void onFailure(Throwable caught) {	
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
						}
					});
				}
			}
		});
		
		codeEditorPanel.setClickHandlerButtonTest(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (codeEditorPanel.getCode().trim().equals(""))
					Window.alert(Data.NO_CODE_WRITTEN_MESSAGE);
				else {
					service.testCode(username, password, codeEditorPanel.getCode().trim(), new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (!result)
								Window.alert(Data.FAILURE_TEST_CODE_MESSAGE);
							else
								Window.alert(Data.SUCCESS_TEST_CODE_MESSAGE);
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
						}
					});
				}
			}
		});
	}
}