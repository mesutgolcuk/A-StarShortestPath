package com.mesutgolcuk.shortestPath;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Test {
	
	public static void main(String[] args){
		// generate Random Cities
		ArrayList<Node>cityCoordinates = 
				RandomCoordinateGenerator.getGeneratedCoordinates(
						Constants.sizeX, Constants.sizeY, Constants.NUMBER_OF_CITIES);
		// Main Screen 
		CitiesComponent cities = new CitiesComponent(cityCoordinates);
		
	    JFrame frame = new JFrame("Main Screen");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(cities);
	    frame.setSize(Constants.sizeX+200, Constants.sizeY+200);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}
