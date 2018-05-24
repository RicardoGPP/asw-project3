package sonc.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import sonc.shared.Movie;
import sonc.shared.SoncException;

@RemoteServiceRelativePath("manager")
public interface SoncService extends RemoteService {
	
	public boolean register(String userId, String password) throws SoncException;
	
	public boolean updatePassword(String nick, String oldPassword, String newPassword) throws SoncException;
	
	public boolean updateCode(String nick, String password, String code) throws SoncException;
	
	public boolean testCode(String nick, String password, String code) throws SoncException;
	
	public boolean authenticate(String nick, String password) throws SoncException;
	
	public String getCurrentCode(String nick, String password) throws SoncException;
	
	public List<String> getPlayersNamesWithShips() throws SoncException;
	
	public Movie battle(List<String> nicks) throws SoncException;
	
}