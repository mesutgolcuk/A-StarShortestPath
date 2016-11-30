package com.mesutgolcuk.shortestPath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * Main screen 2D graphics component
 * @author mesut
 *
 */
public class CitiesComponent extends JComponent {

	private ArrayList<Node> cities; // cities in graph
	private ArrayList<Ellipse2D> ellipseCities; // 2D Cities

	private int startCityIndex=-1; // which city selected as start
	private int endCityIndex=-1; // which city selected as end
	
	private boolean clicked = false; // is clicked to a city
	ArrayList<Line2D.Double> lines; // 2D lines between cities
	ArrayList<Line2D.Double> path; // 2D lines between src to target
	ArrayList<Edge> edges; // all edges
	// calculate shortest path button
	private Rectangle2D.Double button = new Rectangle2D.Double(Constants.sizeX/2-50, Constants.sizeY+50, 200, 50);
	
	public CitiesComponent(ArrayList<Node> cities) {
		this.cities = cities;
		
		ellipseCities = new ArrayList<>();
		for (int i = 0; i < cities.size(); i++) {
			Ellipse2D city = new Ellipse2D.Double(
					cities.get(i).getX(), cities.get(i).getY(), Constants.CITY_X_R, Constants.CITY_Y_R);
			ellipseCities.add(city);
		}
		
		path = new ArrayList<>();
		lines = new ArrayList<>();
		
		ArrayList<Edge> randomEdges = Utils.getRandomEdges(cities);
		ArrayList<Edge> mstEdges = Utils.getMSTEdges(cities);
		
		edges = new ArrayList<>();
		edges.addAll(mstEdges);
		edges.addAll(randomEdges);
		lines.addAll(Utils.createLine2DArrayFromNodes(randomEdges));
		lines.addAll(Utils.createLine2DArrayFromMST(mstEdges));
		
		ArrayList<Double> distances = new ArrayList<>();
		for(Node each : cities ){
			for(Node neighbor : each.getNeighbors()){
				distances.add(Utils.calculateRealDistance(neighbor, each));
			}
			each.setNeighborDistances(distances);
			distances = new ArrayList<>();
		}
		// click Listener
		addMouseListener(new MouseAdapter() {
	          @Override
	          public void mouseClicked(MouseEvent me) {
	              super.mouseClicked(me);
	              // if clicked one of the cities
	              for(int i=0;i<ellipseCities.size();i++){
	            	  if ( ellipseCities.get(i).contains(me.getX(),me.getY()) ) {
	 	     			 if( !clicked )
	 	     				 startCityIndex = i;
	 	     			 else
	 	     				 endCityIndex = i;
	 	     			clicked = !clicked;
	 	     			repaint();
	 	     		 }
	              }
	              // if clicked calculate button
	              if(button.contains(me.getX(),me.getY())){
	            	  System.out.println("Button clicked");
	            	  if(startCityIndex!=-1 && endCityIndex !=-1){
		            	  ArrayList<Edge> al = new AStarAlgorithm(cities).traverse(cities.get(startCityIndex), cities.get(endCityIndex));
		            	  for(Edge each : al){
		            		  path.add(new Line2D.Double(each.getNode1().getX(), each.getNode1().getY(),
		            				  each.getNode2().getX(), each.getNode2().getY()));
		            	  }
		            	  repaint();
	            	  }
	            	  else{
	            		  javax.swing.JOptionPane.showMessageDialog(null, "Please select start and stop city", "Error", JOptionPane.ERROR_MESSAGE);
	            	  }
	              }
	          }
	      });
		ConsoleFrame consoleFrame = new ConsoleFrame();
	    consoleFrame.setVisible(true);

	    String a = null;
	    for(int i=0;i<cities.size();i++){
	    	a = "\nDistances from a"+i+"\n";
	    	a += "--------------------------------------\n";
	    	for(int j=0;j<cities.get(i).getNeighbors().size();j++){
	    		a += "a"+cities.indexOf(cities.get(i).getNeighbors().get(j));
	    		a += " ---> ";
	    		a += String.format("%.2f", (double)cities.get(i).getNeighborDistances().get(j)); 
	    		a += "\n";
	    	}
	    	System.out.println(a);
	    	consoleFrame.addTextToJPane(a);
	    }
	    
	}
	/**
	 * paints Components
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// draw connections between cities
		for(Line2D.Double each : lines){
			g2d.draw(each);
		}
		// draw path to target
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(3));
		for(Line2D.Double each : path){
			g2d.draw(each);
		}
		// draw cities
		for (int i = 0; i < cities.size(); i++) {
			if( i == startCityIndex ){
				g2d.setColor(Color.GREEN);
			}
			else if( i == endCityIndex ){
				g2d.setColor(Color.RED);
			}
			else{
				g2d.setColor(Color.DARK_GRAY);
			}

		    g2d.fill(ellipseCities.get(i));
		    g2d.drawString("a"+i, (float)ellipseCities.get(i).getX(),(float)ellipseCities.get(i).getY());		 
		}
		// draw seperator line
		g2d.setColor(Color.DARK_GRAY);
		g2d.drawLine(0, Constants.sizeY+45, Constants.sizeX, Constants.sizeY+45);
		g2d.setFont(new Font("default", Font.BOLD, 16));
		// draw slected color cities
		if(startCityIndex != -1 && endCityIndex != -1){
			g2d.drawString("Selected Start City : a"+startCityIndex , 0, Constants.sizeY+70);
			g2d.drawString("Selected Stop City : a"+endCityIndex , 0, Constants.sizeY+90);
		}
		else if( startCityIndex != -1 ){
			g2d.drawString("Selected Start City : a"+startCityIndex , 0, Constants.sizeY+70);
			g2d.drawString("Selected Stop City : " , 0, Constants.sizeY+90);
		}
		else{
			g2d.drawString("Selected Start City : " , 0, Constants.sizeY+70);
			g2d.drawString("Selected Stop City : " , 0, Constants.sizeY+90);
		}
		// draw calculate button
		g2d.fill(button);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Calculate Shortest Path", Constants.sizeX/2-40, Constants.sizeY+80);

		g2d.dispose();
		
	}


	
}
