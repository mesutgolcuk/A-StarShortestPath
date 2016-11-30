package com.mesutgolcuk.shortestPath;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
/**
 * Generic static methods
 * @author mesut
 *
 */
public class Utils {
	/**
	 * Calculates distance between 2 node
	 * @param coord1 x,y
	 * @param coord2 x,y
	 * @return distance
	 */
	public static double distance(Node coord1, Node coord2){
		int sum = square(coord1.getX()-coord2.getX())+
				square(coord1.getY()-coord2.getY());
		return Math.sqrt(sum);
	}
	/**
	 * Calculate square of a number
	 * @param x number
	 * @return square of number
	 */
	public static int square(int x){
		return x*x;
	}
	/**
	 * Calculates distance all nodes to target node
	 * @param cities all nodes
	 * @param target target
	 * @return arrayList of ditances
	 */
	public static ArrayList<Double> calculateAllDistanceToTarget(ArrayList<Node> cities,Node target){
		ArrayList<Double> distances = new ArrayList<>();
		for(Node each : cities){
			distances.add(distance(each,target));
		}
		return distances;
	}
	/**
	 * Calculates distances to cities's neighbors
	 * @param cities 
	 * @return neighbor distances 
	 */
	public static ArrayList<Node> calculateAllNeighborDistances(ArrayList<Node> cities){
		ArrayList<Double> distances = new ArrayList<>();
		for(Node each : cities ){
			for(Node neighbor : each.getNeighbors()){
				distances.add(calculateRealDistance(neighbor, each));
			}
			each.setNeighborDistances(distances);
			distances.clear();
		}
		return cities;
	}
	/**
	 * Calculate real distances adds %10-%50 of it's crow distance 
	 * @param coord1
	 * @param coord2
	 * @return distance
	 */
	public static double calculateRealDistance(Node coord1, Node coord2){
		Random rand = new Random();
		double percentage = (rand.nextInt(5) + 1.0)/10.0;
		double dist = distance(coord1,coord2);
		return dist+dist*percentage;
		
	}
	/**
	 * Get randomized edges
	 * @param nodes
	 * @return random edges
	 */
	public static ArrayList<Edge> getRandomEdges(ArrayList<Node> nodes){
		return RandomEdgeGenerator.getRandomEdges(nodes, Constants.NUMBER_OF_CITIES);
	}
	/**
	 * Create Line2D Graphic object from edges
	 * @param edges
	 * @return lines
	 */
	public static ArrayList<Line2D.Double>createLine2DArrayFromNodes(ArrayList<Edge> edges){
		ArrayList<Line2D.Double> lines = new ArrayList<>();
		for(Edge each : edges){
			lines.add(new Line2D.Double(
					each.getNode1().getX(), 
					each.getNode1().getY(), 
					each.getNode2().getX(), 
					each.getNode2().getY())
					);
			
		}
		System.out.println("Random : "+lines.size());
		return lines;
	}
	/**
	 * get MST Edges from Kruskal Algorithm 
	 * @param nodes 
	 * @return mst edges 
	 */ 
	public static ArrayList<Edge> getMSTEdges(ArrayList<Node> nodes){
		KruskalAlgorithm ka = new KruskalAlgorithm(nodes);
		return ka.getKruskal();
	}
	/**
	 * create Line2D object from MST Edges
	 * @param mstEdges
	 * @return lines
	 */
	public static ArrayList<Line2D.Double>createLine2DArrayFromMST(ArrayList<Edge> mstEdges){
		
		ArrayList<Line2D.Double> lines = new ArrayList<>();
		for(Edge each : mstEdges){
			lines.add(new Line2D.Double(
					each.getNode1().getX(), 
					each.getNode1().getY(), 
					each.getNode2().getX(), 
					each.getNode2().getY())
					);
		}
		System.out.println("MST : "+lines.size());
		return lines;
		
	}
}

