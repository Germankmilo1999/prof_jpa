package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;


/**
 * The persistent class for the bills database table.
 * 
 */
@Entity
@Table(name="bills")
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int number;
	
	private int id_customer;

	private String products;
	
	private int totalPrice;
	
	private LocalDateTime date;
	

	public Bill() {
	}


	public Bill(int idCustomerBill, String productsBill, int totalPriceBill, LocalDateTime dateBill) {
		super();
		this.id_customer = idCustomerBill;
		this.products = productsBill;
		this.totalPrice = totalPriceBill;
		this.date = dateBill;
	}

	public int getNumBill() {
		return this.number;
	}

	public void setNumBill(int numBill) {
		this.number = numBill;
	}
	
	public String getProducts() {
		return this.products;
	}

	public void setProducts(String productsBill) {
		this.products = productsBill;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime dateBill) {
		this.date = dateBill;
	}

	public int getIdCustomer() {
		return this.id_customer;
	}

	public void setIdCustomer(int idCustomerBill) {
		this.id_customer = idCustomerBill;
	}
	
	public int getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(int totalPriceBill) {
		this.totalPrice = totalPriceBill;
	}

}