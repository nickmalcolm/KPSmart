package routes;

public class DistributionCentre {

	private String name ;
	private String city ;
	private String country ;
	//Need for A star estimate
	private int lat;
	private int lon;

	public DistributionCentre(String name, String city, String country , int lat, int lon) {
	this.name = name;
	this.city = city;
	this.country = country;
	this.lat = lat;
	this.lon = lon;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getCity() {
	return city;
	}

	public void setCity(String city) {
	this.city = city;
	}

	public String getCountry() {
	return country;
	}
	public int lat() {
	return lat;
	}
	public int lon() {
	return lon;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DistributionCentre))
			return false;
		DistributionCentre other = (DistributionCentre) obj;
		if (this.name.equals(other.getName()) && this.city.equals(other.getCity()) && this.country.equals(other.getCountry())){
			return true;
		}
		return false;
	}

	public String displayString() {
		return "\t"+city+", "+country;
	}

}
