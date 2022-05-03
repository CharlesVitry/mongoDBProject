package main;

import GUI.Fenetre_Principal;
import dao.Dao;
import dao.DaoFactory;
import model.*;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;


// TO DO
/*

 */

public class App {
    public static void main( String[] args ) throws JAXBException, IOException, TransformerException, SAXException {

        //DAO
        Dao<Adresse> adresseDao = DaoFactory.getAdresseDAO();
        Dao<Etudiant> etudiantDao = DaoFactory.getEtudiantDAO();
        Dao<Formation> formationDao = DaoFactory.getFormationDAO();
        Dao<Etablissement> etablissementDao = DaoFactory.getEtablissementDAO();

        //De réinitialiser la base (en injectant le fichier json concernant les universités, ainsi
        //que le jeu de données
        adresseDao.DropCollection();
        etablissementDao.DropCollection();
        etudiantDao.DropCollection();
        formationDao.DropCollection();

        //INITIALISATION DES JEUX DE DONNEES

        //etablissements fournis
        Utils.JsonAdd(etablissementDao);

        //Adresses, Etablissements, Etudiants & Formations
        Utils.Jeux_De_Donnees_Ajouts(adresseDao,etudiantDao,formationDao);

        //DEMONSTRATION DES FONCTIONNALITES
        System.out.println("=========================================================================");
        System.out.println("DEMONSTRATION DES FONCTIONNALITES");
        Adresse ad1 = new Adresse(4, "Rue Rabelaie", "Angers", 49000, "49", 49.3, 49.7);


        //De rajouter/modifier une formation
        ArrayList Disciplines_MIASHS = new ArrayList() {{
            add("Classification Automatique");
            add("Prévision sur  Séries Chronollogique");
            add("Technologie XML et Bases de Données");
        }};
        Formation formation = new Formation(49, "Master MASHS", Disciplines_MIASHS);
        formationDao.create(formation);

        // De rajouter/modifier un étudiant
        Etudiant etudiant = new Etudiant(235488, "Charles", "Vitry", ad1, formation, "present");
        etudiantDao.create(etudiant);

        // De rajouter/modifier un établissement
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        ArrayList<String> diplomes = new ArrayList<>();

        diplomes.add("Licences");
        diplomes.add("Master");
        etudiants.add(etudiant);

        ArrayList<Formation> formations = new ArrayList<>();
        formations.add(formation);

        Etablissement eta1 = new Etablissement("hf8ui91az", "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
        etablissementDao.create(eta1);
        Etablissements etablissements = new Etablissements(etablissementDao.findAll());


        //Modifier un étudiant
        etudiant = new Etudiant(235488, "Charles", "Vitry", ad1, formation, "abscent");
        etudiantDao.update(etudiant);


        System.out.println("====================ETUDIANT UPDATE AKA PROBLEMES====================================================");

        //On a modifié l'étudiant sur "abscent", la liste contient un pointeur vers l'objet, mais lorsque l'on questionne l'établissement, l'update n'a pas été faite
        System.out.println(eta1.getListe_Etudiant());

        //Pourtant l'objet a bien été modifié
        System.out.println(etudiant);
        System.out.println("========================================================================================");


        // De rechercher un étudiant par numéro d’étudiant
        System.out.println("◉Rechercher un étudiant par numéro d’étudiant");
        Etudiant recherche_etudiant = new Etudiant(5, "", "", ad1, formation, "");
        System.out.println((etudiantDao.find(recherche_etudiant)).toString());

        // De rechercher un établissement (par numéro de SIRET ou par commune)
        System.out.println("◉Rechercher un établissement par numéro de SIRET");
        Etablissement recherche_Etablissement = new Etablissement("hf8ui91az", "", "", "", "", "", "", ad1, etudiants, diplomes,  formations);
        System.out.println((etablissementDao.find(recherche_Etablissement)).toString());

        // De rechercher une formation (par intitulé)
        System.out.println("◉Rechercher une formation par intitulé");
        Formation recherche_Formation = new Formation(0, "Génie civil", new ArrayList<String>());
        System.out.println((formationDao.find(recherche_Formation)).toString());

        System.out.println("=========================================================================");

        //De lister l’ensemble des formations
        System.out.println("◉De lister l’ensemble des formations");
        formationDao.findAll();
        Utils.Affichage_liste_Formations(formationDao);

        System.out.println("=========================================================================");

        //De lister l’ensemble des établissements
        System.out.println("◉De lister l’ensemble des établissements");
        etablissementDao.findAll();
        //Utils.Affichage_liste_Etablissements(etablissements); Il y en a trop à afficher
        System.out.println(eta1);


        System.out.println("=========================================================================");

        //De lister l’ensemble des étudiants
        System.out.println("◉De lister l’ensemble des étudiants");
        etudiantDao.findAll();
        Utils.Affichage_liste_Etudiants(etudiantDao);

        System.out.println("=========================================================================");

        //De lister les cours d’un étudiant
        System.out.println("◉De lister les cours d’un étudiant");
        System.out.println((Utils.Liste_de_Cours_Etudiant(etudiant)).toString());

        //L’application doit aussi générer une page HTML représentant les différentes
        //informations des établissements (à l’aide d’une transformation XSLT)


        //De faire une extraction des données des établissements sous format XML (en
        //        utilisant DOM, SAX ou JAXB)
        Utils.generateXml(etablissements);

        //Generation HTML
        Utils.generateWithXSLtransformer();

        //Demarrage Interface
        /*
        try {
            Fenetre_Principal window = new Fenetre_Principal();
            window.frEtablissement.setVisible(true);
            Fenetre_Principal.Tableau_Etablissement(etablissements);
            Fenetre_Principal.Tableau_Etudiant(etudiantDao);
            Fenetre_Principal.Tableau_Formation(formationDao);

        } catch (Exception e) {
            e.printStackTrace();
        }
*/



    }
}

