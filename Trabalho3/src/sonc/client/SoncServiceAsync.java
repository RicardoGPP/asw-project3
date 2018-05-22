package sonc.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import sonc.shared.Movie;

public interface SoncServiceAsync {
	
	void authenticate(String nick, String password, AsyncCallback<Boolean> callback);

	void battle(List<String> nicks, AsyncCallback<Movie> callback);

	void buildShip(String nick, String password, String code, AsyncCallback<Void> callback);

	void getCurrentCode(String nick, String password, AsyncCallback<String> callback);

	void getPlayersNamesWithShips(AsyncCallback<List<String>> callback);

	void register(String userId, String password, AsyncCallback<Boolean> callback);

	void updatePassword(String nick, String oldPassword, String newPassword, AsyncCallback<Boolean> callback);

}