package termibooking.server.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Bus {
	private String code;
	private int number;
	private int total_seats;
	private int remaining_seats;
	private int price;
	@Persistent(defaultFetchGroup="true")
	private Station departureSta;
	@Persistent(defaultFetchGroup="true")
	private Station arrivalSta;
	@Persistent(defaultFetchGroup="true")
	private Company company;
	public Bus(String code, int number, int total_seats, int remaining_seats,
			int price, Station departureSta, Station arrivalSta, Company company) {
		super();
		this.code = code;
		this.number = number;
		this.total_seats = total_seats;
		this.remaining_seats = remaining_seats;
		this.price = price;
		this.departureSta = departureSta;
		this.arrivalSta = arrivalSta;
		this.company = company;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTotal_seats() {
		return total_seats;
	}
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	public int getRemaining_seats() {
		return remaining_seats;
	}
	public void setRemaining_seats(int remaining_seats) {
		this.remaining_seats = remaining_seats;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Station getDepartureSta() {
		return departureSta;
	}
	public void setDepartureSta(Station departureSta) {
		this.departureSta = departureSta;
	}
	public Station getArrivalSta() {
		return arrivalSta;
	}
	public void setArrivalSta(Station arrivalSta) {
		this.arrivalSta = arrivalSta;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
