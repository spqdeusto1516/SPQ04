package termibooking.server.data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Company {
	
	private String name;
	private String code;
	private int commission;
	public Company(String name, String code, int commission) {
		super();
		this.name = name;
		this.code = code;
		this.commission = commission;
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
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	
	
}
