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
        Dao<Etablissement> EtablissementDao = DaoFactory.getEtablissementDAO();

        Adresse ad1 = new Adresse(45, "Rue rabelaie", "Angers", 49000, "49", 49.3, 49.7);
        adresseDao.create(ad1);
        System.out.println(adresseDao.find(ad1));

        Formation form1 = new Formation(145, "MathInfo", new ArrayList<String>());
        formationDao.create(form1);
        System.out.println(formationDao.find(form1));

        Etudiant et1 = new Etudiant(235472, "Paul", "Paul", ad1, form1, "present");
        etudiantDao.create(et1);
        System.out.println(etudiantDao.find(et1));

        //Etablissement eta1 = new Etablissement(1,"sigle","JASA","+33(0)6 87 14 89","secondaire","privé","UCO",ad1,
        //        new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>() );
        //EtablissementDao.create(eta1);


    }
}
