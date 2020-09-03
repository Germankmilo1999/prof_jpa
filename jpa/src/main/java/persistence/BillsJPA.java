package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Bill;

public class BillsJPA {

	public static List<Bill> listBills() {
		final EntityManager eM = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		final Query query = eM.createNamedQuery("Bill.findAll");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public static Bill listBill(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Bill bill = em.find(Bill.class, id);
		return bill;
		
	}

	public static void createBill(Bill bl) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(bl);
			em.getTransaction().commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();

		}

	}
	
	/*public static void updateCustomers(int id,String newName, Integer newPhone) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Bill customer = em.find(Customer.class, id);
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
	}*/

}