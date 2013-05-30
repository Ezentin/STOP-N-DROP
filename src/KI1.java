import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.map.FieldType;
import core.player.Player.Action;
import core.player.PlayerController;

public class KI1 extends PlayerController {
	@Override
	public String getName() {
		return "Kieh";
	}

	@Override
	public String getAuthor() {
		return "Ulrich Rott";
	}

	// -------------------------------------------------------------------

	private boolean iswalkable(FieldType toCheckField) {
		if (toCheckField == FieldType.DROPPED_FIELD || toCheckField == FieldType.RESTRICTED_FIELD)
			return false;
		else
			return true;
	}

	private boolean[] getPossibleDirection(AiMapInfo map, AiPlayerInfo ownPlayer) {
		// Values; goRight, goLeft, goUp goDown
		boolean[] directionarray = { true, true, true, true };

		if (iswalkable(map.getField(ownPlayer.getPosition().right())) == false)
			directionarray[0] = false; // not allow to go Right
		if (iswalkable(map.getField(ownPlayer.getPosition().left())) == false)
			directionarray[1] = false; // not allow to go Left
		if (iswalkable(map.getField(ownPlayer.getPosition().down())) == false)
			directionarray[3] = false; // not allow to go Down
		if (iswalkable(map.getField(ownPlayer.getPosition().up())) == false)
			directionarray[2] = false; // not allow to go Up

		if (ownPlayer.getPosition().x == 26)
			directionarray[0] = false; // not allow to go Right
		if (ownPlayer.getPosition().x == 1)
			directionarray[1] = false; // not allow to go Left
		if (ownPlayer.getPosition().y == 1)
			directionarray[2] = false; // not allow to go Up
		if (ownPlayer.getPosition().y == 22)
			directionarray[3] = false; // not allow to go Down

		return directionarray;
	}

	private int getPoint(FieldType toRateField) {
		int TestFieldPoint = 0;

		switch (toRateField) {
		case STABLE_FIELD:
			TestFieldPoint = 2;
		case UNSTABLE_FIELD:
			TestFieldPoint = 1;
		case ITEM_FIELD_BOMB:
			TestFieldPoint = 1;
		case ITEM_FIELD_BRIDGE:
			TestFieldPoint = 2;
		case ITEM_FIELD_CAKE:
			TestFieldPoint = 2;
		case ITEM_FIELD_COIN:
			TestFieldPoint = 3;
		case ITEM_FIELD_FREEZE:
			TestFieldPoint = 2;
		case ITEM_FIELD_LIFT:
			TestFieldPoint = 2;
		case ITEM_FIELD_MINUS:
			TestFieldPoint = 1;
		case ITEM_FIELD_PITFALL:
			TestFieldPoint = 2;
		case ITEM_FIELD_STABILIZER:
			TestFieldPoint = 2;
		case DROPPED_FIELD:
			TestFieldPoint = 0;
		case RESTRICTED_FIELD:
			TestFieldPoint = 0;
		default:
			break;
		}
		return TestFieldPoint;
	}

	/* Main think method */
	@Override
	public Action think(AiMapInfo map, AiPlayerInfo[] enemies, AiPlayerInfo ownPlayer) {

		// Variables
		FieldType rightField = map.getField(ownPlayer.getPosition().right());
		FieldType leftField = map.getField(ownPlayer.getPosition().left());
		FieldType upField = map.getField(ownPlayer.getPosition().up());
		FieldType downField = map.getField(ownPlayer.getPosition().down());

		boolean[] possibleDirectionArray = getPossibleDirection(map, ownPlayer);
		int[] ratingArray = { 0, 0, 0, 0 };

		if (possibleDirectionArray[0] == true)// permit go Right
			ratingArray[0] = getPoint(rightField);
		if (possibleDirectionArray[1] == true)// permit go Left
			ratingArray[1] = getPoint(leftField);
		if (possibleDirectionArray[2] == true)// permit go Up
			ratingArray[2] = getPoint(upField);
		if (possibleDirectionArray[3] == true)// permit go Down
			ratingArray[3] = getPoint(downField);

		int[] ratingArray2 = ratingArray.clone();
		
		// find biggest value in rating
		java.util.Arrays.sort(ratingArray2);
		if ((ratingArray[0] == ratingArray2[3]) /*&& (ratingArray[0] > 0)*/)
			return Action.MOVE_RIGHT;
		if ((ratingArray[1] == ratingArray2[3]) /*&& (ratingArray[1] > 0)*/)
			return Action.MOVE_LEFT;
		if ((ratingArray[2] == ratingArray2[3]) /*&& (ratingArray[2] > 0)*/)
			return Action.MOVE_UP;
		if ((ratingArray[3] == ratingArray2[3]) /*&& (ratingArray[3] > 0)*/)
			return Action.MOVE_DOWN;
		else 
			return Action.DO_NOTHING;


	}

}
