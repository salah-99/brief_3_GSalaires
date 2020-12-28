package gestionSalaire;


public class Production_risque extends Producteur {
	
	private int id_production_risque;
	private double prime_risque;
	
	public Production_risque () {}
	public Production_risque (int Id_production_risque,double Prime_risque) {
		this.id_production_risque = Id_production_risque;
		this.prime_risque = Prime_risque;
	}
	public int getId_production_risque() {
		return id_production_risque;
	}
	public void setId_production_risque(int id_production_risque) {
		this.id_production_risque = id_production_risque;
	}
	public double getPrime_risque() {
		return prime_risque;
	}
	public void setPrime_risque(double prime_risque) {
		this.prime_risque = prime_risque;
	}

	
	
}

