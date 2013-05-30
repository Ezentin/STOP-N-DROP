import core.ai.AiMapInfo;
import core.map.SNDMap;
import core.map.SNDMapDebugDrawInfo;
import core.map.FieldType;
import core.Position;

public class AirMapInfoExpand extends AiMapInfo {

	public AirMapInfoExpand(SNDMap map, SNDMapDebugDrawInfo debugDraw, String gameType) {
		super(map, debugDraw, gameType);
	}

	public Position getFlagPosition() {
		for (int x = 0; x < this.getWidth(); x++)
			for (int y = 0; y < this.getHeight(); y++)
				if (this.getField(new Position(x, y)) == FieldType.ACTION_FIELD_FLAG)
					return new Position(x, y);

		return null;
	}
}
