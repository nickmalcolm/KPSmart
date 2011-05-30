package routes;

import java.util.ArrayList;
import java.util.List;

import backend.PrioritisedRoute;

import priority.Priority;

public class Route {
	
	private DistributionCentre d1;
	private DistributionCentre d2;
	private ArrayList<Vehicle> vehicles;
	
	public Route(DistributionCentre d1, DistributionCentre d2){
		this.d1 = d1;
		this.d2 = d2;
		vehicles = new ArrayList<Vehicle>();
	}
	
	public Route(DistributionCentre d1, DistributionCentre d2, ArrayList<Vehicle> vehicles) {
		this.d1 = d1;
		this.d2 = d2;
		this.vehicles = vehicles;
	}
	
	public void discontinueTransport(int ID) {
		if (getVehicle(ID) != null){
			vehicles.remove(getVehicle(ID));
		}
	}
	
	public Vehicle getVehicle(int ID) {
		for (Vehicle v : vehicles) {
			if (v.getID() == ID) {
				return v;
			}
		}
		
		return null;
	}
	
	public Vehicle getVehicle(Priority priority, Firm firm){
		for (Vehicle vehicle : vehicles){
			if (vehicle.getPriority() == priority && vehicle.getFirm().equals(firm)){
				return vehicle;
			}
		}
		// no vehicle found for that firm and priority
		return null;
	}
	
	public List<Vehicle> getVehiclesByPriority(Priority priority){
		List<Vehicle> result = new ArrayList<Vehicle>();
		for (Vehicle vehicle : vehicles){
			if (vehicle.getPriority() == priority){
				result.add(vehicle);
			}
		}
		return result;
	}

	public DistributionCentre getD1() {
		return d1;
	}

	public void setD1(DistributionCentre d1) {
		this.d1 = d1;
	}

	public DistributionCentre getD2() {
		return d2;
	}

	public void setD2(DistributionCentre d2) {
		this.d2 = d2;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Route))
			return false;
		Route other = (Route) obj;
		if (this.d1.getName().equals(other.getD1().getName()) && this.d2.getName().equals(other.getD2().getName())){
			return true;
		}
		else if (this.d1.getName().equals(other.getD2().getName()) && this.d2.getName().equals(other.getD1().getName())){
			return true;
		}
		return false;
	}
	
}
