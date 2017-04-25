package termibooking.server.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class User {
	
	private String email;
	private String password;
	@Persistent(defaultFetchGroup="true")
	private Station defaultStation;
	
	public User(String email, String password, Station defaultStation) {
		super();
		this.email = email;
		this.password = password;
		this.defaultStation = defaultStation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Station getDefaultStation() {
		return defaultStation;
	}

	public void setDefaultStation(Station defaultStation) {
		this.defaultStation = defaultStation;
	}
	
	

}
