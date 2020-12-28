package gestionSalaire;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Personnel extends Employe implements MentionaireInterface,ProductionRisqueInterface{
	
	Scanner scan = new Scanner(System.in);
	
	private String date_dentree_service;
	private String type;
	private double nombre_heures_travail;
	private double chifredaffire;
	private double salire_total;

	public Personnel() {};
	public Personnel(int Id, String Nom, String Prenom, int Age ,String Date_dentree_service,String Type,double Chifredaffire ,double Nombre_heures_travail ,double Salire_total) {
		// TODO Auto-generated constructor stub
		super(Id,Nom,Prenom,Age);
		
		this.date_dentree_service = Date_dentree_service;
		this.type = Type;
		this.chifredaffire = Chifredaffire;
		this.nombre_heures_travail = Nombre_heures_travail;
		this.salire_total = Salire_total;
	}
	
	
	static //declaration
	
	Conection con = new Conection();
	
	Date date = new Date();
	String dateNow = null;
	
	
	public String getDate_dentree_service() {
		return date_dentree_service;
	}
	public void setDate_dentree_service(String date_dentree_service) {
		this.date_dentree_service = date_dentree_service;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getNombre_heures_travail() {
		return nombre_heures_travail;
	}
	public void setNombre_heures_travail(double nombre_heures_travail) {
		this.nombre_heures_travail = nombre_heures_travail;
	}
	public double getChifredaffire() {
		return chifredaffire;
	}
	public void setChifredaffire(double chifredaffire) {
		this.chifredaffire = chifredaffire;
	}
	public double getSalire_total() {
		return salire_total;
	}
	public void setSalire_total(double salire_total) {
		this.salire_total = salire_total;
	}
	

	//ADD
	public void ajouterEmploye(String nom,String prenom,int age,String date_dentree_service,int choixtype) {
		// TODO Auto-generated method stub
		con.Connect();
		PreparedStatement ps = null;
		String query = "";
		int primkey = 0 ;
		String typePost = "";
		
		try {
			query="INSERT INTO `employe`(`nom`, `prenom`, `age`) VALUES (?,?,?)";
			ps=con.conn.prepareStatement(query, new String[] { "id" });
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setInt(3, age);
			//ps.executeUpdate();
			if (ps.executeUpdate() > 0) {
				java.sql.ResultSet generatedKeys = ps.getGeneratedKeys(); 
				if (generatedKeys.next())	
					primkey = generatedKeys.getInt(1); 
			}
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
		if (choixtype == 1) {
			//1
			typePost = "la_Vente" ;
			//vondeur.add();
		}else if (choixtype == 2) {
			//2
			typePost = "la_Representation" ;
		}else if (choixtype == 3) {
			//2
			typePost = "la_Production" ;
		}else if (choixtype == 4) {
			//2
			typePost = "la_Manutention" ;
		}else if (choixtype == 5) {
			//2
			typePost = "la_Production_a_risques" ;
		}else if (choixtype == 6) {
			//2
			typePost = "la_Manutention_a_risques" ;
		}
		
		if(choixtype == 1 || choixtype == 2 || choixtype == 3 || choixtype == 4 || choixtype == 5 || choixtype == 6) {
			try {

				query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
				ps=con.conn.prepareStatement(query);
				ps.setString(1, date_dentree_service);
				ps.setString(2, typePost);
				ps.setInt(3, 1);
				ps.setInt(4, primkey);
				ps.executeUpdate();
				System.out.println("\n Ajouter Employer Avec succes");	
			}catch(Exception e) {
				System.out.print(e);
			}
		}
	}
	
	//SELECT
	public void AfficherEmploye(int choixtype) throws SQLException {
		// TODO Auto-generated method stub
		if(choixtype == 1 || choixtype == 2 || choixtype == 3 || choixtype == 4 || choixtype == 5 || choixtype == 6) {
			String query = "";
			int count = 0;
			if(choixtype == 1) {
				System.out.println("\t \n ------------------------------------------- ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Vente ");
				System.out.println("\t \n ------------------------------------------- ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Vente' AND `poste_de_travail`.`status` = 1";
			}else if(choixtype == 2) {
				System.out.println("\t \n --------------------------------------------------- ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Repr�sentation ");
				System.out.println("\t \n --------------------------------------------------- ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Representation' AND `poste_de_travail`.`status` = 1";
			}else if(choixtype == 3) {
				System.out.println("\t \n ------------------------------------------------ ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Production ");
				System.out.println("\t \n ------------------------------------------------ ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Production' AND `poste_de_travail`.`status` = 1";
			}else if(choixtype == 4) {
				System.out.println("\t \n ------------------------------------------------- ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Manutention ");
				System.out.println("\t \n ------------------------------------------------- ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Manutention' AND `poste_de_travail`.`status` = 1";
			}else if(choixtype == 5) {
				System.out.println("\t \n ------------------------------------------------- ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Production a risques ");
				System.out.println("\t \n ------------------------------------------------- ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Production_a_risques' AND `poste_de_travail`.`status` = 1";
			}else if(choixtype == 6) {
				System.out.println("\t \n ------------------------------------------------- ");
				System.out.println("\t \n Liste des Employes Ceux affect�s � la Manutention a risques ");
				System.out.println("\t \n ------------------------------------------------- ");
				query="SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `poste_de_travail`.`type` = 'la_Manutention_a_risques' AND `poste_de_travail`.`status` = 1";
			}
			con.Connect();
			con.stat = con.conn.createStatement();
			con.rs = con.stat.executeQuery(query);
			while(con.rs.next()) {
				System.out.println(" -------> ID:  " + "\t" +con.rs.getInt("id_employer") + "\n \t Name: " + con.rs.getString("nom") + " \n \t Prenom:  " + con.rs.getString("prenom") + " \n \t Age:   " + con.rs.getInt("age") + " \n \t Date D'entree Service" + con.rs.getString("date_dentree_service") );
				count ++;
			}
			if (count == 0) {
				System.out.println("\t \n La Liste est VID !");
			}
		}else {
			System.out.println("\n Verifier le choix");
		}
	}
	
	//UPDATE
	public void modifierEmploye(int id, String nom,String prenom,int age,int choixtype) throws SQLException {
		if(choixtype == 1 || choixtype == 2 || choixtype == 3 || choixtype == 4  || choixtype == 5  || choixtype == 6) {
			String query = "";
			con.Connect();
			PreparedStatement ps = null;
			query="UPDATE `employe` SET `nom`=?,`prenom`=?,`age`=? WHERE `id_employer`=?";
			ps=con.conn.prepareStatement(query);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setInt(3, age);
			ps.setInt(4, id);
			ps.executeUpdate();
			System.out.println("\n Modification Employer Avec succes");
		}else {
			System.out.println("\n Verifier le choix");
		}
	}
	
	//delete
	public void supprimerEmploye(int id) throws SQLException {
		
		//First f = new First();
		con.Connect();
		String query = "";
		
		PreparedStatement ps = null;
		
		try {
			//khasni nkemel suuprision
			//delet 1
			query = "DELETE info_salaire.* FROM `employe`,`poste_de_travail`,`info_salaire` WHERE `employe`.`id_employer`=`poste_de_travail`.`id_employer` AND `poste_de_travail`.`id_post_de_travail` = `info_salaire`.`id_poste_de_travail` AND `employe`.`id_employer` = ?";
			ps=con.conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			//delet 2
			query = "DELETE poste_de_travail.* FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer`=`poste_de_travail`.`id_employer` AND `employe`.`id_employer` = ?";
			ps=con.conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			//delet 3
			query = "DELETE employe.* FROM `employe`WHERE `id_employer` = ?";
			ps=con.conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("\n Employer Supprimer Avec succes");
			
		}catch (Exception e){
			
		}
		
	}
	
	//SERCHE With ID EMPLOYER
	public void sherchEmployeWithId(int id) throws SQLException {
		
		String query = "";
		int count = 0;
		int countpost = 0;
		String TypeDePost = "";
		int IdDePost = 0;
		int IdDeemployer = 0;
		double chiffre_daffaire = 0;
		double nombre_dunites_produites = 0;
		double nombre_dheures_travail = 0;
		int primkey3 = 0;
		//info omployer
		System.out.println("\n ------->Information Employer :");
		
		query = "SELECT * FROM `employe` WHERE `employe`.`id_employer` = "+id ;
		con.Connect();
		con.stat = con.conn.createStatement();
		con.rs = con.stat.executeQuery(query);
		while(con.rs.next()) {
			System.out.println(" -------> ID:  " + "\t" +con.rs.getInt("id_employer") + "\n \t Name: " + con.rs.getString("nom") + " \n \t Prenom:  " + con.rs.getString("prenom") + " \n \t Age:   " + con.rs.getInt("age"));
			count ++;
			IdDeemployer = con.rs.getInt("id_employer");
		}
		if (count == 0) {
			System.out.println("\t \n La Liste est VID !");
		}
		//info post de traveil
		System.out.println("\n ------->Information Poste De Travail :");
		query = "SELECT * FROM `employe`,`poste_de_travail` WHERE `employe`.`id_employer` = `poste_de_travail`.`id_employer` AND `employe`.`id_employer` ="+id + " AND `poste_de_travail`.`status` = 1 ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
		con.Connect();
		con.stat = con.conn.createStatement();
		con.rs = con.stat.executeQuery(query);
		while(con.rs.next()) {
			System.out.println(" -------> ID Poste De Travail:  " + "\t" +con.rs.getInt("id_post_de_travail") + "\n \t Type De Poste: " + con.rs.getString("type") + " \n \t Date D'entree Service:  " + con.rs.getString("date_dentree_service"));
			IdDePost = con.rs.getInt("id_post_de_travail");
			TypeDePost = con.rs.getString("type");
			countpost ++;
		}
		if (countpost == 0) {
			System.out.println("\t \n La Liste est VID !");
		}
		
		//PART 2
		else if (countpost > 0) {
			
			int choixtype = 0;
			System.out.println("\t \n Ajouter le salaire sur le meme poste  :1"
								+ "\n changer le post et ajouter le salaire :2");
			choixtype = scan.nextInt();
			if (choixtype == 1) {
				//Ajouter le salaire sur le meme poste
				if (TypeDePost.equals("la_Vente")) {
					System.out.println("\n Entrer le chiffre d'affaire:");
					chiffre_daffaire = scan.nextDouble();
					//Methode calculerSalaire
					System.out.println("\n hahowa id :"+IdDePost+"\n hahowa chifr dafiir :"+chiffre_daffaire);
					calculerSalaires(IdDePost,chiffre_daffaire,1);
				}else if (TypeDePost.equals("la_Representation")) {
					System.out.println("\n Entrer le chiffre d'affaire:");
					chiffre_daffaire = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(IdDePost,chiffre_daffaire,2);
				}else if (TypeDePost.equals("la_Production")) {
					System.out.println("\n Entrer le nombre d'unit�s produites:");
					nombre_dunites_produites = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(IdDePost,nombre_dunites_produites,3);
				}else if (TypeDePost.equals("la_Manutention")) {
					System.out.println("\n Entrer nombre d'heures de travail:");
					nombre_dheures_travail = scan.nextDouble();
					calculerSalaires(IdDePost,nombre_dheures_travail,4);
					//Methode calculerSalaire
					//calculerSalaires();
				}else if (TypeDePost.equals("la_Production_a_risques")) {
					System.out.println("\n Entrer le nombre d'unit�s produites a risques:");
					nombre_dunites_produites = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(IdDePost,nombre_dunites_produites,5);
				}else if (TypeDePost.equals("la_Manutention_a_risques")) {
					System.out.println("\n Entrer nombre d'heures de travail a risques::");
					nombre_dheures_travail = scan.nextDouble();
					calculerSalaires(IdDePost,nombre_dheures_travail,6);
					//Methode calculerSalaire
					//calculerSalaires();
				}
			}else if(choixtype == 2) {
				//changer le post et ajouter le salaire
				System.out.println("\t \n Choisir le type pour changer:"
									+ "\n -----------------------"
							 		+ "\n Ceux affect�s � la Vente ----------------->:1"
							 		+ "\n Ceux affect�s � la Repr�sentation -------->:2"
							 		+ "\n Ceux affect�s � la Production ------------>:3"
							 		+ "\n Ceux affect�s � la Manutention ----------->:4"
							 		+ "\n Ceux affect�s � la production � risques -->:5"
							 		+ "\n Ceux affect�s � la manutention � risques ->:6");
				choixtype = scan.nextInt();
				PreparedStatement ps = null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
				dateNow = (formatter.format(date)) ;
				int primkey2 = 0;

				if (choixtype == 1) {
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Vente");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();
						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}
						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer le chiffre d'affaire:");
					chiffre_daffaire = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(primkey2,chiffre_daffaire,1);
					
				}else if (choixtype == 2) {
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Representation");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();

						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}
						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer le chiffre d'affaire:");
					chiffre_daffaire = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(primkey2,chiffre_daffaire,2);
				}else if (choixtype == 3) {
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Production");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();

						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}
						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer le nombre d'unit�s produites:");
					nombre_dunites_produites = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(primkey2,nombre_dunites_produites,3);
				}else if (choixtype == 4) {
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Manutention");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();

						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}
						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer nombre d'heures de travail:");
					nombre_dheures_travail = scan.nextDouble();
					calculerSalaires(primkey2,nombre_dheures_travail,4);
					//Methode calculerSalaire
					//calculerSalaires();
				}else if (choixtype == 5) {
					
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Production_a_risques");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();

						//return id
						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}

						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n ID CHLADA :" +primkey2);
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer le nombre d'unit�s produites:");
					nombre_dunites_produites = scan.nextDouble();
					//Methode calculerSalaire
					calculerSalaires(primkey2,nombre_dunites_produites,5);
				}else if (choixtype == 6) {
					try {
						//UPDATE
						try {
							query = "UPDATE `poste_de_travail` SET `status`=? WHERE `id_employer`=?";
							ps=con.conn.prepareStatement(query);
							ps.setInt(1, 0);
							ps.setInt(2, IdDeemployer);
							ps.executeUpdate();
							
						}catch (Exception e){
							
						}
						//ENDTEST
						System.out.println("\n 3AAK3AK HNA HNA");
						query="INSERT INTO `poste_de_travail`(`date_dentree_service`, `type`,`status`, `id_employer`) VALUES (?,?,?,?)";
						ps=con.conn.prepareStatement(query);
						ps.setString(1, dateNow);
						ps.setString(2, "la_Manutention_a_risques");
						ps.setInt(3, 1);
						ps.setInt(4, IdDeemployer);
						ps.executeUpdate();

						query = "SELECT * FROM `poste_de_travail` ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
						con.Connect();
						con.stat = con.conn.createStatement();
						con.rs = con.stat.executeQuery(query);
						while(con.rs.next()) {
							primkey2 = con.rs.getInt("id_post_de_travail");
						}
						System.out.println("\n Ajouter id_post_de_travail Avec succes");					
					}catch(Exception e) {
						System.out.print(e);
					}
					System.out.println("\n Chengement avec succes");
					System.out.println("\n Entrer nombre d'heures de travail:");
					nombre_dheures_travail = scan.nextDouble();
					calculerSalaires(primkey2,nombre_dheures_travail,6);
					//Methode calculerSalaire
					//calculerSalaires();
				}
				else {
					System.out.println("\n Verifier le choix");
				}
				//endtest
			}
		}
	}



	@Override
	public void calculerSalaires(int id,double vleurAjoute,int choixtype) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
		dateNow = (formatter.format(date)) ;
		double valeurtotal = 0;
		if (choixtype == 1) {
			valeurtotal = (20 * vleurAjoute) / 100;
			valeurtotal = valeurtotal + 1500 ;
		}else if (choixtype == 2) {
			valeurtotal = (20 * vleurAjoute) / 100;
			valeurtotal = valeurtotal + 2500 ;
		}else if (choixtype == 3) {
			valeurtotal = vleurAjoute * 5 ;
		}else if (choixtype == 4) {
			valeurtotal = vleurAjoute * 50 ;
		}else if (choixtype == 5) {
			valeurtotal = vleurAjoute * 5 ;
			valeurtotal = valeurtotal + ProductionRisqueInterface.salaireFixeProductionRisque ;
			
		}else if (choixtype == 6) {
			valeurtotal = vleurAjoute * 50 ;
			valeurtotal = valeurtotal + MentionaireInterface.salaireFixeMentionaire ;
		}
		con.Connect();
		PreparedStatement ps = null;
		try {
			String query="INSERT INTO `info_salaire`(`date`, `chifredaffire`, `Salire_total`, `id_poste_de_travail`) VALUES (?,?,?,?)";
			ps=con.conn.prepareStatement(query);
			ps.setString(1, dateNow);
			ps.setDouble(2, vleurAjoute);
			ps.setDouble(3, valeurtotal);
			ps.setInt(4, id);
			ps.executeUpdate();
			System.out.println("\n Salaire Total est :"+ valeurtotal + " DH");
			System.out.println("\n Salaire ajouter avec succes");
		}catch(Exception e) {
			System.out.print(e);
		}
	}


	public void salaireMoyen() throws SQLException {
		int choixtype = 0;
		String choixtype2 = "";
		int id = 0;
		String query = "";
		int id_post = 0;
		con.Connect();
		System.out.println("\n Calcule la Moyene de salaire pour un seul employe  : 1");
		String method = scan.next();
		switch (method) {
			case "1":
				//2
				System.out.println("\n choisi le type de l'employ� pour la Moyene de Salaire :"
				 		 + "\n -----------------------"
				 		 + "\n Ceux affect�s � la Vente ----------------->:1"
				 		 + "\n Ceux affect�s � la Repr�sentation -------->:2"
				 		 + "\n Ceux affect�s � la Production ------------>:3"
				 		 + "\n Ceux affect�s � la Manutention  ---------->:4"
				 		 + "\n Ceux affect�s � la Production a risques -->:5"
				 		 + "\n Ceux affect�s � la Manutention a risques ->:6");
				choixtype = scan.nextInt();
				try {
					AfficherEmploye(choixtype);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("pour Calculer Salaire entrer le Id de Employ�s :");
				id = scan.nextInt();

				query = "SELECT * FROM `poste_de_travail` WHERE `id_employer`="+id+"  ORDER BY `poste_de_travail`.id_post_de_travail DESC LIMIT 1";
				//con.Connect();
				con.stat = con.conn.createStatement();
				con.rs = con.stat.executeQuery(query);
				while(con.rs.next()) {
					id_post = con.rs.getInt("id_post_de_travail");
					System.out.println("\n id_post_de_travail :"+id_post);
				}

				//con.Connect();
				con.stat = con.conn.createStatement();
				con.rs = con.stat.executeQuery("SELECT AVG(`Salire_total`) AS Moyene , id_poste_de_travail FROM info_salaire WHERE `id_poste_de_travail` ="+id_post);
				while(con.rs.next()) {
					System.out.println(" -------> ID:  " + "\t" +con.rs.getInt("id_poste_de_travail") + "\n \t La Moyene: " + con.rs.getString("Moyene") +" DH");
				}
				break;
				default:
		}
		
	}

}


