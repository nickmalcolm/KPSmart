package routes;

import java.util.ArrayList;

public class Route {
	
	private DistributionCentre d1;
	private DistributionCentre d2;
	private ArrayList<Vehicle> vehicles;
	
	public Route(DistributionCentre d1, DistributionCentre d2, ArrayList<Vehicle> vehicles) {
		this.d1 = d1;
		this.d2 = d2;
		this.vehicles = vehicles;
	}
	
	public void discontinueTransport(int ID) {
		
	}
	
	public Vehicle getVehicle(int ID) {
		for (Vehicle v : vehicles) {
			if (v.getID() == ID) {
				return v;
			}
		}
		
		return null;
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
	
}
