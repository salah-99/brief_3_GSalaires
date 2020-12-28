package gestionSalaire;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Salaires {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		Conection con = new Conection();
		
		con.Connect();
		
		Personnel personnel = new Personnel();
		
		//Vondeur vondeur = new Vondeur();		
		
		Date date = new Date();
		String dateNow = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		dateNow = (formatter.format(date)) ;		
		System.out.println("dateNow :"+dateNow);
		
		while (true) {
			int id = 0;
			String nom = "";
			String prenom = "";
			int age = 0;
			int choixtype = 0;
			
			
			
			System.out.println("\n Ajouter employer  --------------------->:1"
							 + "\n Afficher employer --------------------->:2"
							 + "\n Modifier employer --------------------->:3"
							 + "\n Supprimer employer -------------------->:4"
							 + "\n Ajouter Salaire employer -------------->:5"
							 + "\n calcule la moyene de Salaire employer ->:6");
			String method = scan.next();
			switch (method) {
				case "1":
					//Ajouter
					System.out.println("entrer le Nom de Employ�s");
					nom = scan.next();
					System.out.println("entrer le Prenom de Employ�s");
					prenom = scan.next();
					System.out.println("entrer le Age de Employ�s");
					age = scan.nextInt();
					System.out.println(" Type : \n Vondeur ----------------->: 1 "
											 + "\n Presontateur------------->: 2"
											 + "\n producteur -------------->: 3"
											 + "\n Manutentateur ----------->: 4"
											 + "\n producteur � risques ---->: 5"
											 + "\n Manutentateur � risques ->: 6");
					choixtype = scan.nextInt();
					personnel.ajouterEmploye(nom, prenom, age,dateNow, choixtype);
					break;
				case "2":
					//2
					System.out.println("\n choisi le type de l'employ� pour Afficher :"
							 		 + "\n -----------------------"
							 		 + "\n Ceux affect�s � la Vente ----------------->:1"
							 		 + "\n Ceux affect�s � la Repr�sentation -------->:2"
							 		 + "\n Ceux affect�s � la Production ------------>:3"
							 		 + "\n Ceux affect�s � la Manutention ----------->:4"
							 		 + "\n Ceux affect�s � la Production a risques -->:5"
							 		 + "\n Ceux affect�s � la Manutention a risques ->:6");
					choixtype = scan.nextInt();
					try {
						personnel.AfficherEmploye(choixtype);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "3":
					//3
					//read
					System.out.println("\n choisi le type de l'employ� pour Modifier :"
					 		 + "\n -----------------------"
					 		 + "\n Ceux affect�s � la Vente ----------------->:1"
					 		 + "\n Ceux affect�s � la Repr�sentation -------->:2"
					 		 + "\n Ceux affect�s � la Production ------------>:3"
					 		 + "\n Ceux affect�s � la Manutention ----------->:4"
					 		 + "\n Ceux affect�s � la production � risques -->:5"
					 		 + "\n Ceux affect�s � la Manutention � risques ->:6");
					choixtype = scan.nextInt();
					try {
						personnel.AfficherEmploye(choixtype);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//update
					System.out.println("entrer le Id de Employ�s");
					id = scan.nextInt();
					System.out.println("entrer le Nom de Employ�s");
					nom = scan.next();
					System.out.println("entrer le Prenom de Employ�s");
					prenom = scan.next();
					System.out.println("entrer le Age de Employ�s");
					age = scan.nextInt();
					try {
						personnel.modifierEmploye(id, nom, prenom, age,choixtype);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "4":
					//3
					//read
					System.out.println("\n choisi le type de l'employ� pour Supprimer :"
					 		 + "\n -----------------------"
					 		 + "\n Ceux affect�s � la Vente ----------------->:1"
					 		 + "\n Ceux affect�s � la Repr�sentation -------->:2"
					 		 + "\n Ceux affect�s � la Production ------------>:3"
					 		 + "\n Ceux affect�s � la Manutention ----------->:4"
					 		 + "\n Ceux affect�s � la production � risques -->:5"
					 		 + "\n Ceux affect�s � la Manutention � risques ->:6");
					choixtype = scan.nextInt();
					try {
						personnel.AfficherEmploye(choixtype);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//update
					System.out.println("entrer le Id de Employ�s Pour supprimer");
					id = scan.nextInt();


					try {
						personnel.supprimerEmploye(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "5":
					//4
					System.out.println("pour ajouter Salaire entrer le Id de Employ�s :");
					id = scan.nextInt();
					try {
						personnel.sherchEmployeWithId(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					
					break;
				case "6":
					//5
					//calcule la moyene de Salaire employer
					try {
						personnel.salaireMoyen();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				default:
						
			}
		}

	}

}

