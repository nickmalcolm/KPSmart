package routes;

public class Firm {
	
	private String name;
	
	public Firm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Firm))
			return false;
		Firm other = (Firm) obj;
		if (this.name.equals(other.getName())){
			return true;
		}
		return false;
	}

}
