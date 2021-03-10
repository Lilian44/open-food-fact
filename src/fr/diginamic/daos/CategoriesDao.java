package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Categories;

public class CategoriesDao {
	

	private EntityManager em;

	public CategoriesDao(EntityManager em) {
		this.em = em;
	}
	
	public Categories insertCategorie(String arrLigne) {
		Categories categ = new Categories();
		
		TypedQuery<Categories> query = em.createQuery("SELECT f FROM Categories f WHERE f.nom = ?1",
				Categories.class);
		query.setParameter(1, arrLigne);

		List<Categories> cat = query.getResultList();

		if (cat.size() == 0) {
			categ.setNom(arrLigne);
			em.persist(categ);
		} else {
			categ = cat.get(0);

		}
		
		return categ;
	}
	
	
	
	

}
