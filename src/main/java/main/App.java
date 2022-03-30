package main;

import com.mongodb.client.model.InsertOneModel;
import dao.Dao;
import dao.DaoFactory;
import model.Adresse;
import model.Etudiant;
import model.Formation;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
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

        Adresse ad1 = new Adresse(45, "Rue rabelaie", "Angers", 49000, "49", 49.3, 49.7);
        //adresseDao.create(ad1);

        Adresse add2 = adresseDao.find(ad1);
        System.out.println(add2);


        ArrayList<String> listDiscip = new ArrayList<>(){{
            add("maths");
            add("info");
        }};
        Formation form1 = new Formation(145, "MathInfo", listDiscip);
        Etudiant et1 = new Etudiant(235472, "Paul", "Paul", ad1, form1, "present");
        etudiantDao.create(et1);
        etudiantDao.find(et1);
    }
}
