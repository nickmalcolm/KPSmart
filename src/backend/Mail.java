package backend;

import java.util.ArrayList;
import java.util.PriorityQueue;
import events.MailEvent;
import priority.*;
import routes.DistributionCentre;

public class Mail {
	
	private int ID;
	
	private double weight;
	private double volume;
	
	private String origin;
	private String destination;
	
	private Priority priority;
	
	public Mail(int ID, double weight, double volume, String origin, String destination, Priority priority) {
		this.ID = ID;
		this.weight = weight;
		this.volume = volume;
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
	}
	
	public int getID() {
		return ID;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public Priority getPriority() {
		return priority;
	}
	
	
	//Finding route
	//Nodes = distribution centers
	//edges = vehciles
	//origin = start node
	//destination goal node
	public ArrayList<MailEvent>  calculateRoute(){
		
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();
		
		//need to find starting dist centre name
		for(Route r : )
		DistributionCentre start ;
		DistributionCentre dest ;
		int pathLength;
		
		
		//for (Node n : nodes.values()) {
		//	n.visit(false); //Reset all nodes to unvisited.
		//}
	 
		SearchNode search = new SearchNode(start, null, pathLength  );
		
		fringe.offer(search); //Queue our starting node.
		
		while (fringe.size() != 0) {
						
			SearchNode tmp_search = fringe.poll(); //Get the most likely 
			
			DistributionCentre tmp_node = tmp_search.getCurrent(); //Get the node for referencing
						
		    double tmp_path = tmp_search.getPathLength(); //Get the length for referencing
			
			//If this is our goal, add it to the path nodes (so we can say we got there)
			//and exit the search.
			 
			//If we haven't been to this node before
			 
				 //Set the visited flag

				  //Make note of how we got here
				  //Make note of how long it took
				
				 //For each edge/segment
		
					  //Get the neighbor node for reference

					  //If we haven't seen this already
						  //Record the path length
												
						//Deals with "better roads".
						//Adds 1 to prevent divide-by-zero errors.
						 

						//Work out the "true" length
						 
						  //Add it to our queue
				 
				
				  //Add this node to our path
			 
		}
		
		
		
		
		
		
		
		return null;
		
	}
	
	
	
	 
	
	
	private class SearchNode{
	

		private DistributionCentre current;
		private DistributionCentre previous ; 
		private double pathLength ; 
		private double estimate;
		 
		private boolean visited;
		
		public SearchNode(DistributionCentre current , DistributionCentre previous , int pathLength  ){
			this.current = current;
			this.previous = previous;
			this.pathLength = pathLength;
			estimate = estimate(current,previous);
	 
		}
		
		public DistributionCentre getCurrent() {
			return current;
		}
		public DistributionCentre getPrevious() {
			return previous;
		}
		public double getPathLength() {
			return pathLength;
		} 
		public double getEstimate() {
			return estimate;
		} 
		public boolean isVisited() {
			return visited;
		}
		public void setIsVisited(boolean b) {
			visited = b;
		}
 
		
	}
 
 	//Pythagoras' Theorum 
	public static double estimate(DistributionCentre start, DistributionCentre goal) {

		double lat_dif = Math.max(start.lat(), goal.lat()) - Math.min(start.lat(), goal.lat());
		double lon_dif = Math.max(start.lon(), goal.lon()) - Math.min(start.lon(), goal.lon());

		double x = 111 * lat_dif;
		double y = 88.649 * lon_dif;
		
		double z = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
		
		if (goal.lon() < start.lon()) z = z * -1;

		return z;

	}
}
