package sonc.client;

import java.util.List;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
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
import sonc.shared.Movie;
import sonc.shared.Movie.Frame;
import sonc.utils.Data;

public class SoncView implements EntryPoint {

	private final SoncServiceAsync service = GWT.create(SoncService.class);
	 
	private String username = "Ricardo";
	private String password = "123";
	
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
	
		//BattleConfigurationPanel events
		battleConfigurationPanel.setClickHandlerButtonPlus(new ClickHandler() {
			public void onClick(ClickEvent event) {
				battleConfigurationPanel.addNickOnListOfSelectedNicks();
			}
		});
		
		battleConfigurationPanel.setClickHandlerButtonMinus(new ClickHandler() {
			public void onClick(ClickEvent event) {
				battleConfigurationPanel.removeNickOnListOfSelectedNicks();
			}
		});
		
		battleConfigurationPanel.setClickHandlerButtonOK(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deckLayoutPanel.showWidget(battlePanel);
			}
		});
		
		//BattlePanel events
		battlePanel.setClickHandlerButtonStart(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				List<String> nicks = battleConfigurationPanel.getSelectedNicks();				
				if (nicks.size() == 0)
					Window.alert(Data.NO_SHIPS_SELECTED_MESSAGE);
				else {
					service.battle(nicks, new AsyncCallback<Movie>() {						
						public void onSuccess(Movie result) {							
							final List<Frame> frames = result.getFrames();							
							final AnimationScheduler animation = AnimationScheduler.get();														
							animation.requestAnimationFrame(new AnimationCallback() {
								public void execute(double timestamp) {
									if (frames.size() != 0) {
										final Frame frame = frames.remove(0);
										battlePanel.draw(frame);
										animation.requestAnimationFrame(this);
									}
								}
							});	
						}
						
						public void onFailure(Throwable caught) {
							Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
						}
					});
				}				
			}
		});
		
		battlePanel.setClickHandlerButtonConfigure(new ClickHandler() {
			public void onClick(ClickEvent event) {
				service.getPlayersNamesWithShips(new AsyncCallback<List<String>>() {					
					public void onSuccess(List<String> result) {
						battleConfigurationPanel.setNicksToSelect(result);
						deckLayoutPanel.showWidget(battleConfigurationPanel);
					}
					
					public void onFailure(Throwable caught) {
						Window.alert(Data.DEFAULT_ERROR_MESSAGE + " Error: " + caught.getMessage());
					}
				});
			}
		});
	}
}