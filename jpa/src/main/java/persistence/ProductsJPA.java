package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Product;

public class ProductsJPA {

	public static List<Product> listProducts() {
		final EntityManager eM = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		final Query query = eM.createNamedQuery("Product.findAll");
		List resultList = query.getResultList();
		return resultList;
	}
	
	public static Product listProduct(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Product product = em.find(Product.class, id);
		return product;
		
	}

	public static void createProduct(Product pd) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pd);
			em.getTransaction().commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			em.close();

		}

	}
	
	public static void updateProduct(Integer id, String newName, Integer newPrice) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Product product = em.find(Product.class, id);
			product.setPrice(newPrice);
			product.setName(newName);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void deleteProduct(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Product product = em.find(Product.class, id);
			em.remove(product);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}