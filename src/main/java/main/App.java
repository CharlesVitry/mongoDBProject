package main;

import com.mongodb.client.model.InsertOneModel;
import dao.Dao;
import dao.DaoFactory;
import dao.EtablissementDao;
import model.Adresse;
import model.Etablissement;
import model.Etudiant;
import model.Formation;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * main class
 */
public class App<ad1>
{
    public static void main( String[] args ) {
        Dao<Adresse> adresseDao = DaoFactory.getAdresseDAO();
        Dao<Etudiant> etudiantDao = DaoFactory.getEtudiantDAO();
        Dao<Formation> formationDao = DaoFactory.getFormationDAO();
        Dao<Etablissement> etablissementDao = DaoFactory.getEtablissementDAO();

        Adresse ad1 = new Adresse(45, "Rue rabelaie", "Angers", 49000, "49", 49.3, 49.7);
        adresseDao.create(ad1);
        System.out.println(adresseDao.find(ad1));

        Formation form1 = new Formation(145, "MathInfo", new ArrayList<String>());
        formationDao.create(form1);
        System.out.println(formationDao.find(form1));

        Etudiant et1 = new Etudiant(235472, "Paul", "Paul", ad1, form1, "present");
        etudiantDao.create(et1);
        System.out.println(etudiantDao.find(et1));



        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        etudiants.add(et1);

        ArrayList<String> diplomes = new ArrayList<String>();
        diplomes.add("Licences");
        diplomes.add("Master");

        ArrayList<Formation> formations = new ArrayList<Formation>();
        formations.add(form1);

        Etablissement eta1 = new Etablissement(45784, "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
        etablissementDao.create(eta1);
        System.out.println(etablissementDao.find(eta1));
    }
}
