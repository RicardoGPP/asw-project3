package sonc.server;

import java.io.File;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import sonc.client.SoncService;
import sonc.game.Manager;
import sonc.shared.Movie;
import sonc.shared.SoncException;
import sonc.utils.AgentBuilder;

public class SoncServiceImpl extends RemoteServiceServlet implements SoncService {

	private static final long serialVersionUID = 1L;
	
	static {
		AgentBuilder.addToClassPath("src");
		Manager.setPlayersFile(new File("USERS.ser"));
	}
	
	public boolean register(String userId, String password) throws SoncException {
		return Manager.getInstance().register(userId, password);
	}

	public boolean updatePassword(String nick, String oldPassword, String newPassword) throws SoncException {
		return Manager.getInstance().updatePassword(nick, oldPassword, newPassword);
	}

	public boolean updateCode(String nick, String password, String code) throws SoncException {
		return Manager.getInstance().updateCode(nick, password, code);
	}
	
	public boolean testCode(String nick, String password, String code) throws SoncException {
		return Manager.getInstance().testCode(nick, password, code);
	}
	
	public boolean authenticate(String nick, String password) throws SoncException {
		return Manager.getInstance().authenticate(nick, password);
	}

	public String getCurrentCode(String nick, String password) throws SoncException {
		return Manager.getInstance().getCurrentCode(nick, password);
	}
	
	public List<String> getPlayersNamesWithShips() throws SoncException {
		return Manager.getInstance().getPlayersNamesWithShips();
	}

	public Movie battle(List<String> nicks) throws SoncException {
		return Manager.getInstance().battle(nicks);
		
		/*
		Movie movie = new Movie();
		
		for (int i = 0; i < 400; i++) {
			movie.newFrame();
			movie.addOblong(i, i, (float) Math.toRadians(i), 30, "black");
			movie.addOblong(400 - i, 400 - i, (float) Math.toRadians(i), 30, "black");
			movie.addScore("Ricardo", i, 1000);
			movie.addScore("Matheus", 0, i);
		}
		
		return movie;*/
	}
}