package sonc.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import sonc.battle.Bullet;
import sonc.battle.MovingObject;
import sonc.battle.Ship;
import sonc.battle.World;
import sonc.battle.bots.SittingDuckBot;
import sonc.battle.bots.StalkerBot;
import sonc.client.SoncService;
import sonc.game.Manager;
import sonc.quad.Trie;
import sonc.shared.Movie;
import sonc.shared.SoncException;
import sonc.utils.AgentBuilder;
import sonc.utils.Data;

public class SoncServiceImpl extends RemoteServiceServlet implements SoncService {

	private static final long serialVersionUID = 1L;
	
	static {		
		AgentBuilder.addToClassPath("../src");
		Manager.setPlayersFile(new File("USERS.ser"));
		World.setWidth(Data.WORLD_WIDTH);
		World.setHeight(Data.WORLD_HEIGHT);
		World.setCollisionDistance(Data.WORLD_COLLISION_DISTANCE);
		Bullet.setInitialSpeed(Data.BULLET_INITIAL_SPEED);
		Ship.setMaxShipRotation(Data.SHIP_MAX_ROTATION);
		Ship.setMaxShipSpeedChange(Data.SHIP_MAX_SPEED_CHANGE);
		Ship.setMaxStatus(Data.SHIP_MAX_STATUS);
		Trie.setCapacity(Data.QUAD_TRIE_CAPACITY);
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
		//This command below would return a list of players who have instantiable ships. But, as the system isn't
		//recognizing any code as valid, then it'll be ignored and a list of two different nicks will be returned.
		//return Manager.getInstance().getPlayersNamesWithShips();
		
		return new ArrayList<>(Arrays.asList("Ricardo", "Matheus"));
	}

	public Movie battle(List<String> nicks) throws SoncException {
		//The same situation as mentioned in the comment above. Here, we are simulating a battle between two concrete
		//ships (no user's code). Actually, we don't know exactly why the system ins't compiling well valid submitted
		//codes, so, as in the method for getting players' names with instantiable ships, here is a simulation too. The
		//command below would return a movie of a battle with ships of given users' nicks. 
		//return Manager.getInstance().battle(nicks);
		
		/*
		Movie movie = new Movie();		
		for (int i = 0; i < 400; i++) {
			movie.newFrame();
			movie.addOblong(i, i, (float) Math.toRadians(i), 30, "black");
			movie.addOblong(400 - i, 400 - i, (float) Math.toRadians(i), 30, "black");
			movie.addScore("Ricardo", i, 1000);
			movie.addScore("Matheus", 0, i);
		}		
		return movie;
		*/
		
		Movie movie = new Movie();		
		World world = new World();
		
		Ship stalker1 = new StalkerBot("Stalker1");
		Ship stalker2 = new StalkerBot("Stalker2");
		Ship duck1 = new SittingDuckBot("Duck 1");
		Ship duck2 = new SittingDuckBot("Duck 2");
		
		List<Ship> ships = new ArrayList<>(Arrays.asList(stalker1, stalker2, duck1, duck2));
		
		world.addShipAtRandom(stalker1);
		world.addShipAtRandom(stalker2);
		world.addShipAtRandom(duck1);
		world.addShipAtRandom(duck2);
		
		int count = 0;		
		while (count <= 500) {						
			world.setCurrentRound(count++);
			for(Ship ship: world.getShips()) {
				ship.move();
				ship.execute();				
			}			
			world.update();			
			movie.newFrame();
			for (MovingObject movingObject : world.getMovingObjects()) {
				int x = (int) movingObject.getX();
				int y = (int) movingObject.getY();
				float heading = (float) movingObject.getHeading();
				int size = movingObject.getSize();
				String color = movingObject.getColor();
				movie.addOblong(x, y, heading, size, color);				
			}
			for (Ship ship : ships)
				movie.addScore(ship.getName(), ship.getPoints(), ship.getStatus());
		}		
		return movie;
	}
}