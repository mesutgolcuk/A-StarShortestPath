package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;
import java.util.Random;
/**
 * Random Coordinaate Generator
 * @author mesut
 *
 */
public class RandomCoordinateGenerator {
	private int sizeX; // max x 
	private int sizeY; // max y
	private int generateCount; // how many coordinate
	
	private RandomCoordinateGenerator(int sizeX, int sizeY,int generateCount) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.generateCount = generateCount;
	}
	/**
	 * generate coordinates 
	 * @return created nodes from generated coordinates
	 */
	public ArrayList<Node> generateCoordinates(){
		ArrayList<Node> coordinates = new ArrayList<Node>();
		Random rand = new Random();
		for(int i=0;i<generateCount;i++){
			Node coordinate=null;
			do{
				int randomX = rand.nextInt(sizeX);
				int randomY = rand.nextInt(sizeY);
				coordinate = new Node(randomX, randomY);
			}while(coordinates.contains(coordinate));
			coordinates.add(coordinate);
		}
		return coordinates;
	}
	/**
	 * Static method for getting created nodes from random coordinates
	 * @param sizeX
	 * @param sizeY
	 * @param generateCount
	 * @return
	 */
	public static ArrayList<Node> getGeneratedCoordinates(int sizeX, int sizeY,int generateCount){
		RandomCoordinateGenerator generator = new RandomCoordinateGenerator(sizeX, sizeY, generateCount);
		return generator.generateCoordinates();
		
	}
}
