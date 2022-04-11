package main;

import dao.Dao;
import dao.DaoFactory;
import model.*;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;


// TO DO
/*

 */

public class App {
    public static void main( String[] args ) throws JAXBException, FileNotFoundException, TransformerException {

        //DAO
        Dao<Adresse> adresseDao = DaoFactory.getAdresseDAO();
        Dao<Etudiant> etudiantDao = DaoFactory.getEtudiantDAO();
        Dao<Formation> formationDao = DaoFactory.getFormationDAO();
        Dao<Etablissement> etablissementDao = DaoFactory.getEtablissementDAO();

        //INITIALISATION DES JEUX DE DONNEES

        //etablissements fournis
        Utils.JsonAdd(etablissementDao);

        //Adresses, Etablissements, Etudiants & Formations
        Utils.Jeux_De_Donnees_Ajouts(adresseDao,etudiantDao,formationDao);

        //DEMONSTRATION DES FONCTIONNALITES
        System.out.println("DEMONSTRATION DES FONCTIONNALITES");
        Adresse ad1 = new Adresse(4, "Rue Rabelaie", "Angers", 49000, "49", 49.3, 49.7);


        //De rajouter/modifier une formation
        Formation formation = new Formation(49, "Master MASHS", new ArrayList<String>());
        formationDao.create(formation);

        // De rajouter/modifier un étudiant
        Etudiant etudiant = new Etudiant(456789, "Charles", "Vitry", ad1, formation, "present");
        etudiantDao.create(etudiant);

        // De rajouter/modifier un établissement
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        ArrayList<String> diplomes = new ArrayList<>();

        diplomes.add("Licences");
        diplomes.add("Master");

        ArrayList<Formation> formations = new ArrayList<>();
        formations.add(formation);

        Etablissement eta1 = new Etablissement("hf8ui91az", "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
        etablissementDao.create(eta1);

        // De rechercher un étudiant par numéro d’étudiant
        Etudiant recherche_etudiant = new Etudiant(5, "", "", ad1, formation, "");
        etudiantDao.find(recherche_etudiant);

        // De rechercher un établissement (par numéro de SIRET ou par commune)
        Etablissement recherche_Etablissement = new Etablissement("hf8ui91az", "", "", "", "", "", "", ad1, etudiants, diplomes,  formations);
        etablissementDao.find(recherche_Etablissement);

        // De rechercher une formation (par intitulé)
        Formation recherche_Formation = new Formation(0, "Génie civil", new ArrayList<String>());
        formationDao.find(recherche_Formation);

        //De lister l’ensemble des formations
        formationDao.findAll();
        Utils.Affichage_liste_Formations(formationDao);

        //De lister l’ensemble des établissements
        etablissementDao.findAll();
        Utils.Affichage_liste_Etablissements(etablissementDao);

        //De lister l’ensemble des étudiants
        etudiantDao.findAll();
        Utils.Affichage_liste_Etudiants(etudiantDao);

        //De lister les cours d’un étudiant
        Utils.Liste_de_Cours_Etudiant(etudiant);

        //L’application doit aussi générer une page HTML représentant les différentes
        //informations des établissements (à l’aide d’une transformation XSLT)

        //De réinitialiser la base (en injectant le fichier json concernant les universités, ainsi
        //que le jeu de données
        adresseDao.DropCollection();
        etablissementDao.DropCollection();
        etudiantDao.DropCollection();
        formationDao.DropCollection();

        //De faire une extraction des données des établissements sous format XML (en
        //        utilisant DOM, SAX ou JAXB)
        Etablissements etablissements = new Etablissements(etablissementDao.findAll());
        Utils.generateXml(etablissements);

    }
}

