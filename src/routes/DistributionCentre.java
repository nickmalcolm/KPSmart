package routes;

public class DistributionCentre {
	
	private String name;
	private String city;
	private String country;
	
	public DistributionCentre(String name, String city, String country) {
		this.name = name;
		this.city = city;
		this.country = country;
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

}
