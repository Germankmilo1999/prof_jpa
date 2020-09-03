package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;
	
	private int price;

	public Product()
	{
		
	}
	
	public Product(String nameProduct, int priceProduct)
	{
		super();
		this.name = nameProduct;
		this.price = priceProduct;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int idProdcuto)
	{
		this.id = idProdcuto;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String nameProduct)
	{
		this.name = nameProduct;
	}
	
	public int getPrice()
	{
		return this.price;
	}

	public void setPrice(int priceProduct)
	{
		this.price = priceProduct;
	}

}