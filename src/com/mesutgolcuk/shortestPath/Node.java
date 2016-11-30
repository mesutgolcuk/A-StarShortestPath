package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;

public class Node {
	private int x; // x Axis coordinate
	private int y; // y Axis coordinate
	private NodeHolder n; // Nodehholder for node
	private ArrayList<Node> neighbors; // connected cities
	private ArrayList<Double> neighborDistances; // distances with connected cities
	private Node parent; // parent of node in the graph traverse
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		neighbors = new ArrayList<>();
		neighborDistances = new ArrayList<>();
	}
	public Node(Node node){
		this.x = node.x;
		this.y = node.y;
		this.n = node.n;
		this.neighbors = node.neighbors;
		this.neighborDistances = node.neighborDistances;
		this.parent = node.parent;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public NodeHolder getN() {
		return n;
	}

	public void setN(NodeHolder n) {
		this.n = n;
	}

	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}
	public void addNeighbour(Node node){
		neighbors.add(node);
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public boolean equals(Object obj) {
		Node c = (Node) obj;
		if(c.getX() == x && c.getY() == y)
			return true;
		else
			return false;
	}

	public ArrayList<Double> getNeighborDistances() {
		return neighborDistances;
	}

	public void setNeighborDistances(ArrayList<Double> neighborDistances) {
		this.neighborDistances = neighborDistances;
	}
	
}
