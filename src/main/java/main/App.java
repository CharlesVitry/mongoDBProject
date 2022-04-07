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


        Formation form1 = new Formation(145, "MathInfo", new ArrayList<String>());
        formationDao.create(form1);
        System.out.println(formationDao.find(form1));

        Adresse ad1 = new Adresse(45, "Rue rabelaie", "Angers", 49000, "49", 49.3, 49.7);
        adresseDao.create(ad1);
        System.out.println(adresseDao.find(ad1));


        Etudiant et1 = new Etudiant(235472, "Paul", "Paul", ad1, form1, "present");
        etudiantDao.create(et1);
        System.out.println(etudiantDao.find(et1));

        Etudiant et2 = new Etudiant(23325472, "Mathieu", "zf", ad1, form1, "present");
        etudiantDao.create(et2);

        Etudiant et3 = new Etudiant(23574472, "Rael", "jh", ad1, form1, "present");
        etudiantDao.create(et3);




        ArrayList<Etudiant> etudiants = new ArrayList<>();


        etudiantDao.delete(et3);

        for (Etudiant e:etudiantDao.findAll()){
            System.out.println(e);

        }
        etudiantDao.delete(et3);

        System.out.println("============");
        for (Etudiant e:etudiantDao.findAll()){
            System.out.println(e);

        }

        ArrayList<String> diplomes = new ArrayList<>();
        diplomes.add("Licences");
        diplomes.add("Master");

        ArrayList<Formation> formations = new ArrayList<>();
        formations.add(form1);

       Etablissement eta1 = new Etablissement("hf8ui91az", "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
        etablissementDao.create(eta1);




        // Le programme devra initialiser la base avec un jeu de donnée



        //    Etablissements etablissements = new Etablissements();
      //  etablissements.setEtablissements(etablissementDao.findAll());

        Etablissements etablissements = new Etablissements(etablissementDao.findAll());

        Utils.generateXml(etablissements);








    }




}
