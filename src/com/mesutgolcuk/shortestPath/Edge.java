package com.mesutgolcuk.shortestPath;
/**
 * Edge between two node
 * @author mesut
 *
 */
public class Edge implements Comparable{
	private Node node1; // node 1
	private Node node2; // node 2
	double distance; // distance between nodes
	private double weight;
	public Edge(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
		weight = 0;
	}
	public Edge(Node node1, Node node2,double weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}
	public Node getNode1() {
		return node1;
	}
	public void setNode1(Node node1) {
		this.node1 = node1;
	}
	public Node getNode2() {
		return node2;
	}
	public void setNode2(Node node2) {
		this.node2 = node2;
	}
	 public double getWeight() { return this.weight; }
	 
	 public void setWeight(double w) { this.weight = w; }
	 public int compareTo(Object o) {
		    Edge other = (Edge) o;

		    if (this.getWeight() < other.getWeight())
		      return -1;
		    else if (this.getWeight() > other.getWeight())
		      return 1;
		    else
		      return 0;
		  }
	public boolean equals(Object obj) {
		Edge e = (Edge) obj;
		if( (	e.getNode1().getX() == node1.getX() && 
				e.getNode1().getY() == node1.getY() && 
				e.getNode2().getX() == node2.getX() &&
				e.getNode2().getY() == node2.getY())||
				e.getNode1().getX() == node2.getX() &&
				e.getNode1().getY() == node2.getY() &&
				e.getNode2().getX() == node1.getX() &&
				e.getNode2().getY() == node1.getY() ){
			return true;
		}else{
			return false;
		}
	}
	
	
}
