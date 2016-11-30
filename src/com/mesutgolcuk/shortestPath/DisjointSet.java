package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;
import java.util.List;
/**
 * Disjoint Dataset
 *
 */
public class DisjointSet {
	private int nodeCount = 0; // node count
	private int setCount = 0; // set count

	ArrayList<NodeHolder> rootNodes; // root nodes
	/**
	 * finds nodeHolder
	 * @param n nodeholder
	 * @return
	 */
	public int find(NodeHolder n) {
		NodeHolder current = n;

		while (current.parent != null)
			current = current.parent;

		NodeHolder root = current;

		current = n;
		while (current != root) {
			NodeHolder temp = current.parent;
			current.parent = root;
			current = temp;
		}

		return root.index;
	}
	/**
	 * Union nodeholders
	 * @param i 
	 * @param j
	 */
	public void union(NodeHolder i, NodeHolder j) {
		int indexI = find(i);
		int indexJ = find(j);

		if (indexI == indexJ)
			return;

		NodeHolder a = this.rootNodes.get(indexI);
		NodeHolder b = this.rootNodes.get(indexJ);

		if (a.rank < b.rank) {
			a.parent = b;
		} else if (a.rank > b.rank) {
			b.parent = a;
		} else {
			b.parent = a;
			a.rank++;
		}

		this.setCount--;
	}
	/**
	 * makeSet from node list
	 * @param vertices
	 */
	public void makeSets(List<Node> vertices) {
		for (Node v : vertices)
			makeSet(v);
	}
	/**
	 * makeSet from node 
	 * @param vertex
	 */
	public void makeSet(Node vertex) {
		NodeHolder n = new NodeHolder(0, rootNodes.size(), null);
		vertex.setN(n);
		this.rootNodes.add(n);
		setCount++;
		nodeCount++;
	}
	/**
	 * disjoint set
	 * @param vertices
	 */
	public DisjointSet(List<Node> vertices) {
		this.rootNodes = new ArrayList<NodeHolder>(vertices.size());
		makeSets(vertices);
	}
}