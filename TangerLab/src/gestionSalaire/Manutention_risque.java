package gestionSalaire;

public class Manutention_risque extends Manutenteur {

	private int id_manutenteur_risque;
	private double prime_risque;
	
	public Manutention_risque () {}
	public Manutention_risque (int Id_manutenteur_risque,double Prime_risque) {
		this.id_manutenteur_risque = Id_manutenteur_risque;
		this.prime_risque = Prime_risque;
	}
	public int getId_manutenteur_risque() {
		return id_manutenteur_risque;
	}
	public void setId_manutenteur_risque(int id_manutenteur_risque) {
		this.id_manutenteur_risque = id_manutenteur_risque;
	}
	public double getPrime_risque() {
		return prime_risque;
	}
	public void setPrime_risque(double prime_risque) {
		this.prime_risque = prime_risque;
	}
	
	
}

