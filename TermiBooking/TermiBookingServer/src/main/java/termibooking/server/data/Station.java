package termibooking.server.data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Station {
	
	private String name;
	private String code;
	private String location;
	public Station(String name, String code, String location) {
		super();
		this.name = name;
		this.code = code;
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
