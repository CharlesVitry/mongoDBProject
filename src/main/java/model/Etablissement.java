package model;
import java.util.ArrayList;

public class Etablissement {

    private int id_Eta;
    private String sigle;
    private String nom;
    private String telephone;
    private String TypeEtablissement;
    private String statut; //public privé privé sous contrat
    private String Universite_de_Rattachement;
    private Adresse adresse;
    private ArrayList<String> Liste_Etudiant;
    private ArrayList<String> Liste_De_Diplome;
    private ArrayList<String> Liste_De_Formations;



    public Etablissement(Integer id_eta, String sigle, String nom, String telephone, String typeEtablissement, String statut, String universite_de_rattachement, Object adresse, ArrayList<String> liste_etudiant, ArrayList<String> liste_de_diplome, ArrayList<String> liste_de_formations) {
        this.id_Eta = id_eta;
        this.sigle = sigle;
        this.nom = nom;
        this.telephone = telephone;
        this.TypeEtablissement = typeEtablissement;
        this.statut = statut;
        this.Universite_de_Rattachement = universite_de_rattachement;
        this.adresse = (Adresse) this.adresse;
        this.Liste_Etudiant = liste_etudiant;
        this.Liste_De_Diplome = liste_de_diplome;
        this.Liste_De_Formations = liste_de_formations;

    }

    public void setId_Eta(int id_Eta) {
        this.id_Eta = id_Eta;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        TypeEtablissement = typeEtablissement;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setUniversite_de_Rattachement(String universite_de_Rattachement) {
        Universite_de_Rattachement = universite_de_Rattachement;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setListe_Etudiant(ArrayList<String> liste_Etudiant) {
        Liste_Etudiant = liste_Etudiant;
    }

    public void setListe_De_Diplome(ArrayList<String> liste_De_Diplome) {
        Liste_De_Diplome = liste_De_Diplome;
    }

    public void setListe_De_Formations(ArrayList<String> liste_De_Formations) {
        Liste_De_Formations = liste_De_Formations;
    }

    public int getId_Eta() {
        return id_Eta;
    }

    public String getSigle() {
        return sigle;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getTypeEtablissement() {
        return TypeEtablissement;
    }

    public String getStatut() {
        return statut;
    }

    public String getUniversite_de_Rattachement() {
        return Universite_de_Rattachement;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public ArrayList<String> getListe_Etudiant() {
        return Liste_Etudiant;
    }

    public ArrayList<String> getListe_De_Diplome() {
        return Liste_De_Diplome;
    }

    public ArrayList<String> getListe_De_Formations() {
        return Liste_De_Formations;
    }
}

