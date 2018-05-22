package sonc.server;

import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import sonc.client.SoncService;
import sonc.game.Manager;
import sonc.shared.Movie;
import sonc.shared.SoncException;

public class SoncServiceImpl extends RemoteServiceServlet implements SoncService {

	private static final long serialVersionUID = 1L;
	
	public boolean register(String userId, String password) throws SoncException {
		return Manager.getInstance().register(userId, password);
	}

	public boolean updatePassword(String nick, String oldPassword, String newPassword) throws SoncException {
		return Manager.getInstance().updatePassword(nick, oldPassword, newPassword);
	}

	public boolean authenticate(String nick, String password) throws SoncException {
		return Manager.getInstance().authenticate(nick, password);
	}

	public String getCurrentCode(String nick, String password) throws SoncException {
		return Manager.getInstance().getCurrentCode(nick, password);
	}

	public void buildShip(String nick, String password, String code) throws SoncException {
		Manager.getInstance().buildShip(nick, password, code);
	}
	
	public List<String> getPlayersNamesWithShips() throws SoncException {
		return Manager.getInstance().getPlayersNamesWithShips();
	}

	public Movie battle(List<String> nicks) throws SoncException {
		return Manager.getInstance().battle(nicks);
	}

}