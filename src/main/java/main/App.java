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






        //System.out.println(adresseDao.find(ad1));
        //System.out.println(formationDao.find(form1));

        //etudiantDao.delete(et3);




        ArrayList<Etudiant> etudiants = new ArrayList<>();

        ArrayList<String> diplomes = new ArrayList<>();
        diplomes.add("Licences");
        diplomes.add("Master");

        ArrayList<Formation> formations = new ArrayList<>();
       // formations.add(form1);

   //    Etablissement eta1 = new Etablissement("hf8ui91az", "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
     //   etablissementDao.create(eta1);




        // Le programme devra initialiser la base avec un jeu de donnée



        //    Etablissements etablissements = new Etablissements();
      //  etablissements.setEtablissements(etablissementDao.findAll());

        Etablissements etablissements = new Etablissements(etablissementDao.findAll());

        Utils.generateXml(etablissements);








    }




}
