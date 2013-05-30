import core.Position;
import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.map.FieldType;
import core.map.SNDMap;
import core.player.Player.Action;
import core.player.Player.Item;
import core.player.PlayerController;

public class KI2 extends PlayerController {

	public KI2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "BigRunner";
	}

	@Override
	public String getAuthor() {
		return "Ulrich Rott";
	}

	private boolean iswalkable(FieldType toCheckField) {
		if (toCheckField == FieldType.DROPPED_FIELD || toCheckField == FieldType.RESTRICTED_FIELD)
			return false;
		else
			return true;
	}

	private boolean isImasse(Action direction, AiMapInfo map, AiPlayerInfo ownPlayer) {

		switch (direction) {
		case MOVE_LEFT:
			if ((ownPlayer.getPosition().x > 1) && ((ownPlayer.getPosition().y > 0) || (ownPlayer.getPosition().y < 24))) {
				if (iswalkable(map.getField(ownPlayer.getPosition().left().up())) || iswalkable(map.getField(ownPlayer.getPosition().left().down()))
						|| iswalkable(map.getField(ownPlayer.getPosition().left().left())))
					return false;
				else
					return true;
			} else
				return false;
		case MOVE_RIGHT:
			if ((ownPlayer.getPosition().x < 27) && ((ownPlayer.getPosition().y > 0) || (ownPlayer.getPosition().y < 24))) {
				if (iswalkable(map.getField(ownPlayer.getPosition().right().up())) || iswalkable(map.getField(ownPlayer.getPosition().right().down()))
						|| iswalkable(map.getField(ownPlayer.getPosition().right().right())))
					return false;
				else
					return true;
			} else
				return false;
		case MOVE_UP:
			if ((ownPlayer.getPosition().y > 1) && ((ownPlayer.getPosition().x > 0) || (ownPlayer.getPosition().x < 28))) {
				if (iswalkable(map.getField(ownPlayer.getPosition().up().up())) || iswalkable(map.getField(ownPlayer.getPosition().up().left()))
						|| iswalkable(map.getField(ownPlayer.getPosition().up().right())))
					return false;
				else
					return true;
			} else
				return false;
		case MOVE_DOWN:
			if ((ownPlayer.getPosition().y < 23) && ((ownPlayer.getPosition().x > 0) || (ownPlayer.getPosition().x < 28))) {
				if (iswalkable(map.getField(ownPlayer.getPosition().down().down())) || iswalkable(map.getField(ownPlayer.getPosition().down().left()))
						|| iswalkable(map.getField(ownPlayer.getPosition().down().right())))
					return false;
				else
					return true;
			} else
				return false;

		case DO_NOTHING:
			return false;
		default:
			return true;
		}

	}

	// {right, left, up, down}
	private int[] localize(AiPlayerInfo ownPlayer, int[] direcgo) {
		if (ownPlayer.getPosition().x <= 14)
			direcgo[1]++;
		else
			direcgo[0]++;
		if (ownPlayer.getPosition().y <= 12)
			direcgo[2]++;
		else
			direcgo[3]++;

		return direcgo;
	}

	private Action thinkAgainsCareful(AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {
		FieldType rightField = map.getField(ownPlayer.getPosition().right());
		FieldType leftField = map.getField(ownPlayer.getPosition().left());
		FieldType upField = map.getField(ownPlayer.getPosition().up());
		FieldType downField = map.getField(ownPlayer.getPosition().down());
		FieldType nowField = map.getField(ownPlayer.getPosition());

		int[] calcarray = { 0, 0, 0, 0 };
		calcarray = localize(ownPlayer, calcarray).clone();

		if (ownPlayer.getItem() == Item.CAKE || ownPlayer.getItem() == Item.PITFALL || ownPlayer.getItem() == Item.STABILIZER)
			return Action.USE_ITEM;

		if (nowField == FieldType.UNSTABLE_FIELD)
			return Action.DO_NOTHING;
		else

		if (iswalkable(rightField) && !isImasse(Action.MOVE_RIGHT, map, ownPlayer))
			return Action.MOVE_RIGHT;
		if (iswalkable(downField) && !isImasse(Action.MOVE_DOWN, map, ownPlayer))
			return Action.MOVE_DOWN;

		if (iswalkable(leftField) && !isImasse(Action.MOVE_LEFT, map, ownPlayer))
			return Action.MOVE_LEFT;
		if (iswalkable(upField) && !isImasse(Action.MOVE_UP, map, ownPlayer))
			return Action.MOVE_UP;

		if (ownPlayer.getItem() != Item.BOMB)
			return Action.USE_ITEM;

		return Action.DO_NOTHING;
	}

	@Override
	public Action think(AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {
		if (!map.isParkourMap())
			return thinkAgainsCareful(map, enemies, ownPlayer);
		else{
			PathFinder p = new AStarAlgorithm(map);
			final Iterable<SNDMap> path = p.findShortestPath(ownField));
			if (path == null){
				return null;}else{
					for (final SNDMap f : path){
						//TODO write the Stackabarbeitung
				}
			
			return Action.MOVE_DOWN;
		}
			
	}

	@Override
	public void onGameStarted(java.lang.String gameTypeName, AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {
		System.out.println("Starting the Game. Gametyp: " + map.getGameTyp() + ".");

	}

}