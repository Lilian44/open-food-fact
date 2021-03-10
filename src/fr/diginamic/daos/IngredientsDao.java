package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Ingredients;
import fr.diginamic.entites.Marques;
import fr.diginamic.entites.Products;

public class IngredientsDao {
	
	private EntityManager em;

	public IngredientsDao(EntityManager em) {
		this.em = em;
	}
	
	public	void insertIngredients(String arrIngred,Products produit) {
		Ingredients ingred = new Ingredients();
		String[] arrIngredient = arrIngred.split(",");
		for (int j = 0; j < arrIngredient.length; j++) {
			Ingredients newIngr = new Ingredients();
			if (arrIngredient[j].length() < 255) {

				newIngr.setNom(arrIngredient[j]);
				produit.getIngredients().add(newIngr);
			}
			TypedQuery<Ingredients> queryIngred = em.createQuery("SELECT f FROM Ingredients f WHERE f.nom = ?1",
					Ingredients.class);
			queryIngred.setParameter(1, arrIngredient[j]);

			List<Ingredients> ingr = queryIngred.getResultList();
			if (ingr.size() == 0 && arrIngredient[j].length() < 254) {
				ingred.setNom(arrIngredient[j]);
				em.persist(ingred);
			}
		}
	}

}
