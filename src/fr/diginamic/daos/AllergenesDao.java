package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Allergenes;
import fr.diginamic.entites.Products;

public class AllergenesDao {
	
	private EntityManager em;

	public AllergenesDao(EntityManager em) {
		this.em = em;
	}
	
	public void insertAllergene(String arrAdditifs, Products produit) {
		Allergenes allergenes = new Allergenes();
		String[] arrAllergenes = arrAdditifs.split(",");
		for (int a = 0; a < arrAllergenes.length; a++) {

			if (arrAllergenes[a].length() < 255 && arrAllergenes[a] != "") {

				allergenes.setNom(arrAllergenes[a]);
				produit.getAllergenes().add(allergenes);
			}
			TypedQuery<Allergenes> queryAllergenes = em
					.createQuery("SELECT f FROM Allergenes f WHERE f.nom = ?1", Allergenes.class);
			queryAllergenes.setParameter(1, arrAllergenes[a]);

			List<Allergenes> allgne = queryAllergenes.getResultList();
			if (allgne.size() == 0 && arrAllergenes[a].length() < 254) {
				allergenes.setNom(arrAllergenes[a]);
				em.persist(allergenes);

			}
		}
		
		
	}

}
