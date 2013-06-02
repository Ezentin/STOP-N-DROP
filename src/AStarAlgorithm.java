import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Comparator;

import java.util.Stack;

import core.Position;
import core.ai.AiMapInfo;
import core.ai.AiPlayerInfo;
import core.map.SNDMap;
import core.map.FieldType;

public class AStarAlgorithm implements PathFinder {
	// variables
	private final AirMapInfoExpand map;
	private final AiPlayerInfo ownPlayer;

	private Map<FieldType, Integer> costs;
	private Map<FieldType, Integer> fValues;

	//constructor
	public AStarAlgorithm(final AirMapInfoExpand map, final AiPlayerInfo ownPlayer) {
		this.map = map;
		this.ownPlayer = ownPlayer;
		this.fValues = new HashMap<FieldType, Integer>();
	}
	
	
	private void initCosts(final AirMapInfoExpand map){
		this.fValues.clear();
		costs = new HashMap<FieldType, Integer>();
		
		for(int x = 0; x < map.getWidth(); ++x){
			for(int y = 0; y < map.getHeight(); ++y){
				costs.put(map.getField(new Position(x, y)), Integer.MAX_VALUE);
				fValues.put(map.getField(new Position(x,y)), Integer.MAX_VALUE);
			}
		}
		FieldType from = (FieldType)map.getFlagPosition();
		costs.put(from.get, 0);
		fValues.put(from, 0);
	}
	//function 2: heuristic
	private int h(final Position to, final AirMapInfoExpand map){
		return (int)to.euclideanDistance(map.getFlagPosition());
	}

	
	//unlooking
	@Override
	public Iterable<SNDMap> findShortestPath(SNDMap from) {
		// TODO Auto-generated method stub
		return null;
	}

}
