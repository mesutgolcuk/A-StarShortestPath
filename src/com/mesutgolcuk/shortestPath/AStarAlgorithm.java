package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
/**
 * AStar Algorithm
 * @author mesut
 *
 */
public class AStarAlgorithm {
	private PriorityQueue<Node> openList; // The set of currently discovered nodes still to be evaluated.
	private ArrayList<Node> closedList; // The set of nodes already evaluated.
	private HashMap<Node, Double> gVals; // g(x)
	private HashMap<Node, Double> fVals; // f(x)
	private ArrayList<Node> nodes; // cities
	int pollCount; // poll item count
	int queueMax; // max size of queue
	QueueFrame frame; 
	
	public AStarAlgorithm(ArrayList<Node> nodes) {
		this.nodes = nodes;
		gVals = new HashMap<Node, Double>();
		fVals = new HashMap<Node, Double>();
		openList = new PriorityQueue<Node>(new fCompare());
		closedList = new ArrayList<Node>();
		pollCount = 0;
		queueMax = 1;
		frame = new QueueFrame();
	}
	/**
	 * Traverse and find path
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Edge> traverse(Node start, Node end) {
		long startTime = System.currentTimeMillis();
		openList.clear(); 
		closedList.clear();

		gVals.put(start, 0.0); 
		openList.add(start);
		// while queue isn't empty
		while (!openList.isEmpty()) {
			Node current = openList.element();
			// if goal reached
			if (current.equals(end)) {
				long endTime = System.currentTimeMillis();
				frame.addTextToJPane("A* worked in "+(endTime-startTime)+" milisec\n");
				frame.addTextToJPane(pollCount+" City polled from queue\n" );
				frame.addTextToJPane("Max size of queue : "+queueMax+"\n" );
				frame.addTextToJPane("\n------------------------------\n");
				frame.addTextToJPane("Remaining cities in queue:\n");
				while(!openList.isEmpty()){
					frame.addTextToJPane("a"+nodes.indexOf(openList.poll())+" - ");
				}
				frame.setVisible(true);
				System.out.println("Poll count: "+pollCount);
				// construct path and return it
				return constructPath(current);
			}
			pollCount++;
			closedList.add(openList.poll());
			// get neighbor of current node
			ArrayList<Node> neighbors = current.getNeighbors();
			
			for (int i =0 ;i<neighbors.size();i++) {

				if( closedList.contains(neighbors.get(i)))
					continue;// Ignore the neighbor which is already evaluated.
				double gScore = gVals.get(current) + current.getNeighborDistances().get(i);
				double fScore = gScore + h(neighbors.get(i), current);

				if (closedList.contains(neighbors.get(i))) {

					if (gVals.get(neighbors.get(i)) == null) {
						gVals.put(neighbors.get(i), gScore);
					}
					if (fVals.get(neighbors.get(i)) == null) {
						fVals.put(neighbors.get(i), fScore);
					}
					// This is not a better path.
					if (fScore >= fVals.get(neighbors.get(i))) {
						continue;
					}
				}
				if (!openList.contains(neighbors.get(i)) || fScore < fVals.get(neighbors.get(i))) {
					neighbors.get(i).setParent(current);// This path is the best until now. Record it!
					gVals.put(neighbors.get(i), gScore);
					fVals.put(neighbors.get(i), fScore);
					// Discover a new node
					if (!openList.contains(neighbors.get(i))) {
						openList.add(neighbors.get(i));

					}
				}
			}
			if( queueMax < openList.size())
				queueMax = openList.size();
		}
		return null;
	}
	/**
	 * Heuristic function
	 * @param node 
	 * @param goal
	 * @return 
	 */
	private double h(Node node, Node goal) {
		return Utils.distance(node, goal);
	}
	/**
	 * construct path with backtracking
	 * @param current
	 * @return
	 */
	private ArrayList<Edge> constructPath(Node current) {
		ArrayList<Edge>path = new ArrayList<>();
		while (current.getParent() != null ) {
			path.add(new Edge(current,current.getParent()));
			current = current.getParent();
		}
		return path;
	}
	/**
	 * Priority Queue comparator
	 * @author mesut
	 *
	 */
	class fCompare implements Comparator<Node> {

		public int compare(Node o1, Node o2) {
			if (fVals.get(o1) < fVals.get(o2)) {
				return -1;
			} else if (fVals.get(o1) > fVals.get(o2)) {
				return 1;
			} else {
				return 1;
			}
		}
	}
}