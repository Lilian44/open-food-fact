package fr.diginamic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FileUtils;
import org.hibernate.query.NativeQuery;

import fr.diginamic.daos.AdditifsDao;
import fr.diginamic.daos.AllergenesDao;
import fr.diginamic.daos.CategoriesDao;
import fr.diginamic.daos.IngredientsDao;
import fr.diginamic.daos.MarquesDao;
import fr.diginamic.daos.ProductsDao;
import fr.diginamic.entites.Additifs;
import fr.diginamic.entites.Allergenes;
import fr.diginamic.entites.Categories;
import fr.diginamic.entites.Ingredients;
import fr.diginamic.entites.Marques;
import fr.diginamic.entites.Products;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("food");
		EntityManager em = entityManagerFactory.createEntityManager();

		File file = new File("C:/workspace/traitement-fichier-dao/resources/open-food-facts.csv");
		EntityTransaction tr = em.getTransaction();

		CategoriesDao catDao = new CategoriesDao(em);
		MarquesDao marqueDao = new MarquesDao(em);
		AdditifsDao additifsDao = new AdditifsDao(em);
		AllergenesDao allergenesDao = new AllergenesDao(em);
		IngredientsDao ingredientsDao = new IngredientsDao(em);
		ProductsDao productsDao = new ProductsDao(em);

		try {
			tr.begin();
			List<String> lignes = FileUtils.readLines(file, "UTF-8");

//			test sur les 50 premiers produits du fichier csv pour le moment
			for (int i = 1; i < 100; i++) {
				String[] decoupage = lignes.get(i).split("\\|", -1);

				Categories categorieCreation = catDao.insertCategorie(decoupage[0]);
				Marques marquesCreation = marqueDao.insertMarque(decoupage[1]);

				Products produit = new Products();

				ingredientsDao.insertIngredients(decoupage[4], produit);
				additifsDao.insertAdditifs(decoupage[29], produit);
				allergenesDao.insertAllergene(decoupage[28], produit);

				produit.setNom(decoupage[2]);
				produit.setMarques(marquesCreation);
				produit.setCategories(categorieCreation);

				produit.setGrade(decoupage[3]);

				if (decoupage[5] != "") {

					produit.setEnergy(Double.parseDouble(decoupage[5]));
				}

				if (decoupage[6] != "") {

					produit.setGraisse(Double.parseDouble(decoupage[6]));
				}

				if (decoupage[7] != "") {

					produit.setSucres(Double.parseDouble(decoupage[7]));
				}
				if (decoupage[8] != "") {

					produit.setFibres(Double.parseDouble(decoupage[8]));
				}

				if (decoupage[9] != "") {

					produit.setProteines(Double.parseDouble(decoupage[9]));
				}

				if (decoupage[10] != "") {

					produit.setSel(Double.parseDouble(decoupage[10]));
				}

				em.persist(produit);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tr.commit();

	}

}
