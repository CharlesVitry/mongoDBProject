package model;

import java.util.ArrayList;

public class Formation {

    private String intitule;
    private List<String> ListeDisciplines;


    public Adresse(int numero, String voie, String ville, int codePostal) {
        super();
        this.numero = numero;
        this.voie = voie;
        this.ville = ville;
        this.codePostal = codePostal;
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

}
