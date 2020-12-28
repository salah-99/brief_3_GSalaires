package gestionSalaire;


public abstract class Employe {

	private int id_employer;
	private String nom,prenom;
	private int age;
	//private String date_dentree_service;
	
	public Employe () {}
	public Employe (int Id,String Nom,String Prenom,int Age) {
	
		this.id_employer = Id;
		this.nom = Nom;
		this.prenom = Prenom;
		this.age = Age;
	}
	public int getId_employer() {
		return id_employer;
	}
	public void setId_employer(int id_employer) {
		this.id_employer = id_employer;
	}
	public String getNam() {
		return nom;
	}
	public void setNam(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract void calculerSalaires(int id,double vleurAjoute,int choixtype);
	
	
	public String  getNom() {
		return "nom : " + this.nom + "prenom :" + this.prenom + "age :"+this.age;
	}
	
	
	
	
	
	
}

