package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;

	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "cat_id")
	private Categories categories;
	
	private String grade;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "compo",

			joinColumns = @JoinColumn(name = "id_pro", referencedColumnName = "id"),

			inverseJoinColumns = @JoinColumn(name = "id_ing", referencedColumnName = "id")

	)
	private List<Ingredients> ingredients = new ArrayList<>();

	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "marques_id")
	private Marques marques;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "compo3",

			joinColumns = @JoinColumn(name = "id_pro", referencedColumnName = "id"),

			inverseJoinColumns = @JoinColumn(name = "id_all", referencedColumnName = "id")

	)
	private List<Allergenes> allergenes = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "compo4",

			joinColumns = @JoinColumn(name = "id_pro", referencedColumnName = "id"),

			inverseJoinColumns = @JoinColumn(name = "id_add", referencedColumnName = "id")

	)
	private List<Additifs> additifs = new ArrayList<>();

	private double energy;
	private double graisse;
	private double sucres;
	private double fibres;
	private double proteines;
	private double sel;

	public Products() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

	public Marques getMarques() {
		return marques;
	}

	public void setMarques(Marques marques) {
		this.marques = marques;
	}

	public List<Allergenes> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(List<Allergenes> allergenes) {
		this.allergenes = allergenes;
	}

	public List<Additifs> getAdditifs() {
		return additifs;
	}

	public void setAdditifs(List<Additifs> additifs) {
		this.additifs = additifs;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getGraisse() {
		return graisse;
	}

	public void setGraisse(double graisse) {
		this.graisse = graisse;
	}

	public double getSucres() {
		return sucres;
	}

	public void setSucres(double sucres) {
		this.sucres = sucres;
	}

	public double getFibres() {
		return fibres;
	}

	public void setFibres(double fibres) {
		this.fibres = fibres;
	}

	public double getProteines() {
		return proteines;
	}

	public void setProteines(double proteines) {
		this.proteines = proteines;
	}

	public double getSel() {
		return sel;
	}

	public void setSel(double sel) {
		this.sel = sel;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", nom=" + nom + ", categories=" + categories + ", grade=" + grade
				+ ", ingredients=" + ingredients + ", marques=" + marques + ", allergenes=" + allergenes + ", additifs="
				+ additifs + ", energy=" + energy + ", graisse=" + graisse + ", sucres=" + sucres + ", fibres=" + fibres
				+ ", proteines=" + proteines + ", sel=" + sel + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
