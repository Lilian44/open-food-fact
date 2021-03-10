package fr.diginamic.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Additifs;
import fr.diginamic.entites.Products;

public class AdditifsDao {

	private EntityManager em;

	public AdditifsDao(EntityManager em) {
		this.em = em;
	}

	public void insertAdditifs(String arrAdditifs, Products produit) {
		Additifs additif = new Additifs();
		String[] arrAddtifs = arrAdditifs.split(",");
		for (int k = 0; k < arrAddtifs.length; k++) {

			if (arrAddtifs[k].length() < 255 && arrAddtifs[k] != "") {

				additif.setNom(arrAddtifs[k]);
				produit.getAdditifs().add(additif);
			}
			TypedQuery<Additifs> queryAdditif = em.createQuery("SELECT f FROM Additifs f WHERE f.nom = ?1",
					Additifs.class);
			queryAdditif.setParameter(1, arrAddtifs[k]);

			List<Additifs> addtf = queryAdditif.getResultList();
			if (addtf.size() == 0 && arrAddtifs[k].length() < 254) {
				additif.setNom(arrAddtifs[k]);
				em.persist(additif);

			}
		}
		
	}

}
