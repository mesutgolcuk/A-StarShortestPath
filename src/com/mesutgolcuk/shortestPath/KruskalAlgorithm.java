package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Krusal algorithm to make graph connected 
 * @author mesut
 *
 */
public class KruskalAlgorithm {
	private ArrayList<Node> nodes; // nodes of graph 
	
	public KruskalAlgorithm(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	/**
	 * calculate mst
	 * @return mst edges
	 */
	public ArrayList<Edge> getKruskal(){
		ArrayList<Edge> edges = getFullyConnectedGraph();
		ArrayList<Node> vertices = new ArrayList<>();
		for(Edge each: edges){
			vertices.add(each.getNode1());
			vertices.add(each.getNode2());
		}
	    DisjointSet d = new DisjointSet(vertices);

	    ArrayList<Edge> tree = new ArrayList<Edge>();

	    Collections.sort(edges);
	    
	    for (Edge e : edges) {
	      Node u = e.getNode1();
	      Node v = e.getNode2();
	      if (d.find(u.getN()) != d.find(v.getN())) {
	        tree.add(e);
	        nodes.get(nodes.indexOf(u)).addNeighbour(v);
	        nodes.get(nodes.indexOf(v)).addNeighbour(u);
	        d.union(u.getN(), v.getN());
	      }
	    }		
		
		return tree;
		
	}
	/**
	 * connect nodes to all other nodes
	 * @return
	 */
	private ArrayList<Edge>getFullyConnectedGraph(){
		ArrayList<Edge> edges = new ArrayList<>();
		for(int i=0;i<nodes.size();i++){
			for(int j=i+1;j<nodes.size();j++){
				Edge edge = new Edge(nodes.get(i), nodes.get(j),Utils.distance(nodes.get(i), nodes.get(j)));
				edges.add(edge);
			}
		}
		return edges;
	}
}
