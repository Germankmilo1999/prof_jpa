package test;

import model.Customer;
import persistence.CustomersJPA;

public class Test
{
	public static void main(String[] args)
	{
			
		System.out.println("Contenido de la tabla clientes \n =============");
		System.out.println(CustomersJPA.listCustomer(1));
		System.out.println("Pasado");
		//Customer ct = new Customer(4502,"Fernando", 32613152);
		//CustomersJPA.createCustomers(ct);
		System.out.println("Contenido de la tabla despues de crear un cliente \n =============");
		System.out.println(CustomersJPA.listCustomers());
		CustomersJPA.updateCustomers(4502, "Juan", 320463519);
		System.out.println("Contenido de la tabla despues de actualizar un cliente \n =============");
		CustomersJPA.listCustomers();
		CustomersJPA.deleteCustomers(4502);
		System.out.println("Contenido de la tabla despues de eliminar un cliente \n =============");
		CustomersJPA.listCustomers();
	}
}