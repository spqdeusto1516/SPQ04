package termibooking.server.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Pay {
	private int amount;
	private String CardCode;
	private String PayPal;
	@Persistent(defaultFetchGroup="true")
	private User user;
	
	public void PayCreditCard(int amount, String cardCode, User user) {
		this.amount = amount;
		CardCode = cardCode;
		PayPal = null;
		this.user = user;
	}
	
	public void PayPayPal(int amount, String payPal, User user) {
		this.amount = amount;
		CardCode = null;
		PayPal = payPal;
		this.user = user;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCardCode() {
		return CardCode;
	}

	public void setCardCode(String cardCode) {
		CardCode = cardCode;
	}

	public String getPayPal() {
		return PayPal;
	}

	public void setPayPal(String payPal) {
		PayPal = payPal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
