package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private Integer phone;

	public Customer() {
	}

	public Customer(String nameCustomer, Integer phoneCustomer) {
		super();
		this.name = nameCustomer;
		this.phone = phoneCustomer;
	}
	
	public int getIdCustomer() {
		return this.id;
	}
	public void setIdCustomer(int idCustomer) {
		this.id = idCustomer;
	}

	public String getNameCustomer() {
		return this.name;
	}

	public void setNameCustomer(String nameCustomer) {
		this.name = nameCustomer;
	}

	public Integer getPhoneCustomer() {
		return this.phone;
	}

	public void setPhoneCustomer(Integer phoneCustomer) {
		this.phone = phoneCustomer;
	}

	@Override
	public String toString() {
		return "Cliente [idCustomer=" + id + ", nameCustomer=" + name + ", phoneCustomer=" + phone
				+ "]";
	}
}