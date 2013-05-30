import java.util.ArrayList;

import core.Position;
import core.ai.AiMapInfo;
import core.map.FieldType;
import core.map.SNDMap;


public class SNDMap extends SNDMap{

	protected SNDMap(int sizeX, int sizeY, int standardFieldLifeTime, boolean useItems, int standardItemTime, int minTimeBetweenItemPlacement,
			int maxTimeBetweenItemPlacement, ArrayList<FieldType> placeableItems, ArrayList<Integer> itemPossibilities, Position[] startPositions,
			FieldType[][] fields, int[][] fieldRemainingLifetime, long rndSeed) {
		super(sizeX, sizeY, standardFieldLifeTime, useItems, standardItemTime, minTimeBetweenItemPlacement, maxTimeBetweenItemPlacement, placeableItems,
				itemPossibilities, startPositions, fields, fieldRemainingLifetime, rndSeed);
	}
	
	public FieldType getFlag(AiMapInfo map){
		for (int x = 0; x < map.getWidth(); x++)
			for (int y = 0; y < map.getHeight(); y++)
				if (map.getField(new Position(x, y)) == FieldType.ACTION_FIELD_FLAG)
					return map.getField(new Position(x, y));
		return null;
	}

}
