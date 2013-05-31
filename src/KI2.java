import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.player.Player.Action;
import core.player.PlayerController;

public class KI2 extends PlayerController {

	public KI2() {
	}

	@Override
	public String getName() {
		return "BigRunner";
	}

	@Override
	public String getAuthor() {
		return "Ulrich Rott";
	}


	@Override
	public Action think(AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {
		if (!map.isParkourMap()){
			NormalGame now = new NormalGame(map,enemies, ownPlayer);
			return now.thinkAgainsCareful(map, enemies, ownPlayer);
			}
		else {
			/*
			 * PathFinder p = new AStarAlgorithm(map); final Iterable<SNDMap>
			 * path = p.findShortestPath(ownField)); if (path == null){ return
			 * null;}else{ for (final SNDMap f : path){ //TODO write the
			 * Stackabarbeitung }
			 */
			return Action.MOVE_DOWN;
		}

	}

	@Override
	public void onGameStarted(java.lang.String gameTypeName, AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {
		System.out.println("Starting the Game. Gametyp: " + map.getGameTyp() + ".");

	}

}