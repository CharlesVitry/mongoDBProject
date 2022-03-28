package model;

public class Adresse {

    private int numero;
    private String voie;
    private String ville;
    private int codePostal;
	private String departement;
	private float longitude;
	private float latitude;
    
    
	public Adresse(int numero, String voie, String ville, int codePostal, float longitude, float latitude) {
		super();
		this.numero = numero;
		this.voie = voie;
		this.ville = ville;
		this.codePostal = codePostal;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	

	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public float getLongitude() {return longitude;}
	public float setLongitude(float longitude) {return longitude;}
	public float getLatitude() {return latitude;}
	public float setLatitude(float latitude) {return latitude;}
}
