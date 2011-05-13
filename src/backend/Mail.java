package backend;

import java.util.ArrayList;
import java.util.PriorityQueue;




import events.MailEvent;
import priority.*;
import routes.DistributionCentre;
import routes.Route;

public class Mail {
	
	private int ID;
	
	private double weight;
	private double volume;
	
	private DistributionCentre origin;
	private DistributionCentre destination;
	
	private Priority priority;
	
	public Mail(int ID, double weight, double volume, DistributionCentre origin, DistributionCentre destination, Priority priority) {
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

	public DistributionCentre getOrigin() {
		return origin;
	}

	public DistributionCentre getDestination() {
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
		
		DistributionCentre dest = destination;	//Need to actually find this probably going to need a list of 
		DistributionCentre org = origin;   // routes in kpsBackend
		
		ArrayList<SearchNode> searched = new ArrayList<SearchNode>(); // The set of nodes already evaluated. 
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();// The set of tentative nodes to be evaluated.
									//	     came_from := the empty map    // The map of navigated nodes.
		SearchNode search = new SearchNode(org, null, 0 );
		fringe.offer(search); //Queue our starting node.
		
 
		while(fringe.size() != 0){
			//Remove node closest
			SearchNode tempNode = fringe.poll(); 
				
			//Goal node has been reached
			if(tempNode.getCurrent().equals(dest)){
				//*******************************FINISHED DO STUFF NOW************************************
			}
	        //Temp node is now in visited set
			searched.add(tempNode);
//	         foreach y in neighbor_nodes(x)
			//want to be able to go tempNode.getRoutes
			for(DistributionCentre r : tempNode.getConnectingNodes()){
				
				
//	             if y in closedset
					if(!searched.contains(r)){
					}
						
//	                 continue
//	             tentative_g_score := g_score[x] + dist_between(x,y)
//	 
//	             if y not in openset
//	                 add y to openset
//	                 tentative_is_better := true
//	             else if tentative_g_score < g_score[y]
//	                 tentative_is_better := true
//	             else
//	                 tentative_is_better := false
//	 
//	             if tentative_is_better = true
//	                 came_from[y] := x
//	                 g_score[y] := tentative_g_score
//	                 h_score[y] := heuristic_cost_estimate(y, goal)
//	                 f_score[y] := g_score[y] + h_score[y]
//	 
					
					
			}
		}
		
		return null;
		
		
	}
	
	
	
	 
	
	
	private class SearchNode implements Comparable<SearchNode>{
	

		private DistributionCentre current;
		private DistributionCentre previous ; 
		private double pathLength ; 
		private double estimate;
		private double total;
		
		public SearchNode(DistributionCentre current , DistributionCentre previous , int pathLength  ){
			this.current = current;
			this.previous = previous;
			this.pathLength = pathLength;
			estimate = estimate(current,previous);
			total = estimate + pathLength;
	 
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
		public double getTotal() {
			return total;
		}
		public ArrayList<DistributionCentre> getConnectingNodes(){
			ArrayList<DistributionCentre> connected = new ArrayList<DistributionCentre>();
			//go through all routes that come off this node
			//make sure they arnt of greater priority than the mail was sent with
			//Find other distruibution center they connect to
			//add to an arraylist
			//return arraylist
			return connected;
		}

		//Find the shortest route based on the estimated distance to go added
		//to the current distance to get to this node.
		public int compareTo(SearchNode s) {
				if (this.total < s.getTotal()) return -1;
				if (this.total > s.getTotal()) return 1;
				return 0;
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
