import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Comparator;

import java.util.Stack;

import javax.swing.tree.ExpandVetoException;

import core.Position;
import core.ai.AiMapInfo;
import core.map.SNDMap;
import core.map.FieldType;
import core.map.SNDMapGenerator;

public class AStarAlgorithm implements PathFinder {
	// variables
	private final AiMapInfo map;
	private Map<SNDMapExpand, Integer> costs;
	private final Map<SNDMapExpand, Integer> fValues;

	// constructor
	public AStarAlgorithm(final AiMapInfo map) {
		this.map = map;
		this.fValues = new HashMap<SNDMapExpand, Integer>();
	}

	// comparator
	private final Comparator<SNDMap> FIELD_COMPARATOR = new Comparator<SNDMap>() {
		@Override
		public int compare(SNDMap arg0, SNDMap arg1) {
			// TODO Auto-generated method stub
			return fValues.get(arg0) - fValues.get(arg1);
		}
	};

	private void initCosts(final SNDMap from) {
		this.fValues.clear();
		costs = new HashMap<SNDMapExpand, Integer>();

		for (int i = 0; i < map.getSizeX(); i++) {
			for (int j = 0; i < map.getSizeY(); j++) {
				// costs.put(map.get, Integer.MAX_VALUE);
				// fValues.put(map.getField(i, i), Integer.MAX_VALUE);
			}
		}
		costs.put(from, 0);
		fValues.put(from, 0);
	}

	private int h(final SNDMapExpand to) {
		final SNDMap goal = to;
		final int diff = (int) Math.round(Math.sqrt(Math.pow(goal.getSizeX() - to.getSizeX(), 2) + Math.pow(goal.getSizeY() - to.getSizeY(), 2)));
		return diff;
	}

	/*
	 * private void expandNode(final SNDMap curr, final PriorityQueue<SNDMap>
	 * openList, final Set<SNDMap> closedList, final Map<SNDMap, SNDMap> preds)
	 * { for (final SNDMap succ : map.getNeighbours(curr)) { if
	 * (!closedList.contains(succ)) { int newCosts = costs.get(curr) +
	 * map.getCosts(succ);
	 * 
	 * if (!openList.contains(succ) || newCosts < costs.get(succ)) {
	 * preds.put(succ, curr); costs.put(succ, newCosts); fValues.put(succ,
	 * newCosts + h(succ));
	 * 
	 * if (openList.contains(succ)) { openList.remove(succ); }
	 * openList.add(succ); } } } }
	 */

	public Iterable<SNDMap> backtrack(final Map<SNDMap, SNDMap> preds) {
		final Stack<SNDMap> stacky = new Stack<SNDMap>();
		SNDMap curr = 
		// TODO change to getGoal stacky.add(cur);
		if (preds.get(curr) != null) {
			curr = preds.get(curr);
			stacky.add(curr);
		}
		return stacky;
	}

	@Override
	public Iterable<SNDMapExpand> findShortestPath(SNDMapExpand from) {
		final PriorityQueue<SNDMapExpand> openList = new PriorityQueue<SNDMapExpand>();
		final Set<SNDMapExpand> closedList = new HashSet<SNDMapExpand>();
		final Map<SNDMapExpand, SNDMapExpand> preds = new HashMap<SNDMapExpand, SNDMapExpand>();

		preds.put(from, null);
		initCosts(from);
		openList.add(from);

		do {
			final SNDMapExpand curr = openList.poll();
			if (curr == map.get) {
				return backtrack(preds);
			}
			closedList.add(curr);
			expandNode(curr, openList, closedList, preds);
		} while (!openList.isEmpty());
		return null;
	}

}
