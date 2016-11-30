package com.mesutgolcuk.shortestPath;
/**
 * NodeHolder for Minimum Spanning Tree Algorithm 
 * @author mesut
 *
 */
public class NodeHolder {
	int rank; // rank
	int index;   // index
	NodeHolder parent; // parent

	public NodeHolder(int r, int i, NodeHolder p) {
		this.rank = r;
		this.index = i;
		this.parent = p;
	}
	
}
