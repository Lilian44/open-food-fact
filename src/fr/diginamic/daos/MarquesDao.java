package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Categories;
import fr.diginamic.entites.Marques;

public class MarquesDao {

	private EntityManager em;

	public MarquesDao(EntityManager em) {
		this.em = em;
	}
	
	public Marques insertMarque(String arrLigne) {
		Marques marque = new Marques();
		TypedQuery<Marques> queryMarques = em.createQuery("SELECT f FROM Marques f WHERE f.nom = ?1",
				Marques.class);
		queryMarques.setParameter(1, arrLigne);

		List<Marques> mar = queryMarques.getResultList();

		if (mar.size() == 0) {

			marque.setNom(arrLigne);
			em.persist(marque);
		} else {
			marque = mar.get(0);

		}
		
		return marque;
		
	}

}
