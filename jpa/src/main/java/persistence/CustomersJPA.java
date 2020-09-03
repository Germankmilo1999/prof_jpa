package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import model.Customer;

public class CustomersJPA {

	public static List<Customer> listCustomers() {
		final EntityManager eM = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		final Query query = eM.createNamedQuery("Customer.findAll");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public static Customer listCustomer(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Customer customer = em.find(Customer.class, id);
		return customer;
	}

	public static void createCustomers(Customer ct) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ct);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void updateCustomers(int id, String newName, Integer newPhone) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Customer customer = em.find(Customer.class, id);
			customer.setPhoneCustomer(newPhone);
			customer.setNameCustomer(newName);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void deleteCustomers(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Customer customer = em.find(Customer.class, id);
			em.remove(customer);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}