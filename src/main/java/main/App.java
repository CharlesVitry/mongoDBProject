package main;

import dao.Dao;
import dao.DaoFactory;
import model.Adresse;
import model.Etablissement;
import model.Etudiant;
import model.Formation;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * main class
 *
 *
 *
 */

// TO DO
/*

Demande de renseignements
Elle doit respecter le pattern DAO et le pattern Singleton
Le fichier permettant la transformation XSLT (.xsl)

A FAIRE
Le projet doit comporter un jeu de données afin que l’application puisse être tester
De rajouter/modifier un étudiant
• De rajouter/modifier un établissement
• De rajouter/modifier une formation
• De rechercher un étudiant (par numéro d’étudiant) /un établissement (par numéro
de SIRET ou par commune) / une formation (par intitulé)
• De lister l’ensemble des formations, établissements, étudiants

à voir demain
• De faire une extraction des données des établissements sous format XML (en
utilisant DOM, SAX ou JAXB)
• De lister les cours d’un étudiant

• L’application doit aussi générer une page HTML représentant les différentes
informations des établissements (à l’aide d’une transformation XSLT)


• De réinitialiser la base (en injectant le fichier json concernant les universités, ainsi
que le jeu de données) == Retirer les données, puis re-ajouter les etablissments



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

       Etablissement eta1 = new Etablissement("hf8ui91az", "UCO", "UCO ANGERS", "0245784554", "Université", "public", "Nantes", ad1, etudiants, diplomes,  formations);
        etablissementDao.create(eta1);




        // Le programme devra initialiser la base avec un jeu de donnée
        try{
            BufferedReader br = new BufferedReader(new FileReader("etablissements.json"));
            String line;
            while((line = br.readLine()) != null){
                //System.out.println(lines)
                BsonArray documents = BsonArray.parse(line);
                System.out.println(documents);
                for(BsonValue bson : documents.getValues()){
                    Document document = Document.parse(bson.toString());
                    LectureJSON.LectureJSON_Etablissement(document, etablissementDao, adresseDao);

                    LectureJSON.LectureJSON_Etablissement(document,etablissementDao,adresseDao);


                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
