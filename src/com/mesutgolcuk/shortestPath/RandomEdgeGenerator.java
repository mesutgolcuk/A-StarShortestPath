package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;
import java.util.Random;
/**
 * Random Edge Generator
 * @author mesut
 *
 */
public class RandomEdgeGenerator {
	int count; // how many edge 
	ArrayList<Node> nodes;
	public RandomEdgeGenerator(ArrayList<Node> nodes,int count) {
		this.nodes = nodes;
		this.count = count;
	}
	/**
	 * Static method for getting random edges
	 * @param nodes 
	 * @param count
	 * @return random edges
	 */
	public static ArrayList<Edge> getRandomEdges(ArrayList<Node> nodes, int count){
		RandomEdgeGenerator reg = new RandomEdgeGenerator(nodes,count );
		return reg.randomizeEdges(nodes,count);
		
	}
	/**
	 * Randomize edges
	 * @param nodes
	 * @param count
	 * @return random edges
	 */
	public ArrayList<Edge> randomizeEdges(ArrayList<Node> nodes, int count){
		ArrayList<Edge> edges = new ArrayList<>();
		Random rand = new Random();
		Edge edge=null;
		int randomNodeIndex1;
		int randomNodeIndex2;
		for(int i=0;i<count+1;i++){
			do{
				randomNodeIndex1 = rand.nextInt(nodes.size());
				randomNodeIndex2 = rand.nextInt(nodes.size());
				edge = new Edge(nodes.get(randomNodeIndex1),nodes.get(randomNodeIndex2));
			}while(edges.contains(edge));
			edges.add(edge);
			nodes.get(randomNodeIndex1).addNeighbour(nodes.get(randomNodeIndex2));
			nodes.get(randomNodeIndex2).addNeighbour(nodes.get(randomNodeIndex1));
		}
		return edges;
	}
	
}
