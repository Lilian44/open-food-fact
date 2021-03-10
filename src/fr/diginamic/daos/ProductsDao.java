package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Products;

public class ProductsDao {

	private EntityManager em;

	public ProductsDao(EntityManager em) {
		this.em = em;
	}
//
//	public Products findById(int id) {
//		return em.find(Products.class, id);
//	}
//
//	public void insert(Products product) {
//		em.persist(product);
//	}
//
//	public void update(Products product) {
//		Products productBdd = findById(product.getId());
//
//	}
//
//	public void delete(Products product) {
//		Products productBdd = findById(product.getId());
//		em.refresh(productBdd);
//
//	}
//
//	public void productByName(String nameProduct) {
//		TypedQuery<Products> query = em.createQuery("SELECT p FROM Products p WHERE p.nom=?1", Products.class);
//		query.setParameter(1, nameProduct);
//
//		List<Products> productsList = query.getResultList();
//
//		for (int i = 0; i < productsList.size(); i++) {
//			System.out.println(productsList.get(i));
//		}
//
//	}

}
