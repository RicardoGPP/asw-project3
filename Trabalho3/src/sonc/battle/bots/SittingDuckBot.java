package sonc.battle.bots;

/**
 * Ship that neither moves nor responds to attacks 
 * 
 * @author Jos&eacute; Paulo Leal {@code zp@dcc.fc.up.pt}
 */
public class SittingDuckBot extends BasicShip {

	public SittingDuckBot(String name) {
		super(name);
	}

	@Override
	public String getColor() {
		return "#FFFFFF";
	}
}
