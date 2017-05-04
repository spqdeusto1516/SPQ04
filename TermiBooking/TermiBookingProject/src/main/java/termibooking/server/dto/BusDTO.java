package termibooking.server.dto;

import java.io.Serializable;

import javax.jdo.annotations.Persistent;

import termibooking.server.data.Company;
import termibooking.server.data.Station;

public class BusDTO implements Serializable{
	
	private String code;
	private int number;
	private int total_seats;
	private int remaining_seats;
	private int price;
	private String departureSta;
	private String arrivalSta;
	private String company;
	
	public BusDTO(String code, int number, int total_seats,
			int remaining_seats, int price, String departureSta,
			String arrivalSta, String company) {
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

	public String getDepartureSta() {
		return departureSta;
	}

	public void setDepartureSta(String departureSta) {
		this.departureSta = departureSta;
	}

	public String getArrivalSta() {
		return arrivalSta;
	}

	public void setArrivalSta(String arrivalSta) {
		this.arrivalSta = arrivalSta;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	
	
	
}
