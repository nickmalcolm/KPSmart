package backend;

import priority.Priority;
import routes.Route;

public class PrioritisedRoute {
	private Route route;
	private Priority priority;
	
	public void setRoute(Route route) {
		this.route = route;
	}
	public Route getRoute() {
		return route;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Priority getPriority() {
		return priority;
	}
	
	@Override
	public String toString(){
		return route.getD1() + " - " + route.getD2() + " (" + priority + ")";
	}
}
