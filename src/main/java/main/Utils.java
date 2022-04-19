package main;


import dao.AdresseDao;
import dao.Dao;
import model.*;
import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class Utils {



    public static ArrayList<String> Liste_de_Cours_Etudiant(Etudiant obj){
        ArrayList<String> formations_Etudiant  = obj.getFormation().getListeDisciplines();
        return formations_Etudiant;
    }

    public static void Affichage_liste_Etudiants(Dao<Etudiant> etudiantDao){
        for (Etudiant e:etudiantDao.findAll()){
            System.out.println(e.toString());
        }

    }
    public static void Affichage_liste_Adresses(Dao<Adresse> adresseDao ){
        for (Adresse a:adresseDao.findAll()){
            System.out.println(a);
        }
    }

    public static void Affichage_liste_Etablissements(   Etablissements etablissements ){
        for (Etablissement e:etablissements.getEtablissements()){
            System.out.println(e.toString());
        }

    }

    public static void Affichage_liste_Formations(Dao<Formation> formationDao ){
        for (Formation f:formationDao .findAll()){
            System.out.println(f);
        }
    }

    public static void Affichage_liste_etudiants(Dao<Etudiant> etudiantDao){
        for (Etudiant e:etudiantDao.findAll()){
            System.out.println(e);
        }
    }



    public static boolean Jeux_De_Donnees_Ajouts(Dao<Adresse> adresseDao,Dao<Etudiant> etudiantDao,Dao<Formation> formationDao){




        // 10 formations
        Formation form1 = new Formation(1, "MathInfo",new ArrayList<String>());
        Formation form2 = new Formation(2, "Génie civil", new ArrayList<String>());
        Formation form3 = new Formation(3, "Physique", new ArrayList<String>());
        Formation form4 = new Formation(4, "Drosophile", new ArrayList<String>());
        Formation form5 = new Formation(5, "Droit", new ArrayList<String>());
        Formation form6 = new Formation(6, "Economie & Gestion", new ArrayList<String>());
        Formation form7 = new Formation(7, "Arts et Lettres", new ArrayList<String>());
        Formation form8 = new Formation(8, "Musicologie", new ArrayList<String>());
        Formation form9 = new Formation(9, "Anglais", new ArrayList<String>());
        Formation form10 = new Formation(10, "Arts du spectacle", new ArrayList<String>());
        formationDao.create(form1);
        formationDao.create(form2);
        formationDao.create(form3);
        formationDao.create(form4);
        formationDao.create(form5);
        formationDao.create(form6);
        formationDao.create(form7);
        formationDao.create(form8);
        formationDao.create(form9);
        formationDao.create(form10);

        Adresse ad1 = new Adresse(4, "Rue Rabelaie", "Angers", 49000, "49", 49.3, 49.7);
        //adresseDao.create(ad1);


        //10 étudiants
        Etudiant et1 = new Etudiant(1, "Paul", "LaMousse", ad1, form1, "present");
        Etudiant et2 = new Etudiant(2, "Mathieu", "Sommet", ad1, form2, "present");
        Etudiant et3 = new Etudiant(3, "Arthur", "Ronde", ad1, form3, "present");
        Etudiant et4 = new Etudiant(4, "Loic", "Breton", ad1, form4, "present");
        Etudiant et5 = new Etudiant(5, "Florian", "DuJardin", ad1, form1, "present");
        Etudiant et6 = new Etudiant(6, "Eleonore", "Clai", ad1, form6, "present");
        Etudiant et7 = new Etudiant(7, "Esteban", "Citaidor", ad1, form7, "present");
        Etudiant et8 = new Etudiant(8, "Sofia", "Desmond", ad1, form8, "present");
        Etudiant et9 = new Etudiant(9, "Theodore", "Louve", ad1, form8, "present");
        Etudiant et10 = new Etudiant(10, "Fanny", "Acab", ad1, form8, "present");

        etudiantDao.create(et1);
        etudiantDao.create(et2);
        etudiantDao.create(et3);
        etudiantDao.create(et4);
        etudiantDao.create(et5);
        etudiantDao.create(et6);
        etudiantDao.create(et7);
        etudiantDao.create(et8);
        etudiantDao.create(et9);
        etudiantDao.create(et10);
        return true;
    }

    public static boolean JsonAdd(Dao<Etablissement> etablissementDao){
        try{
            BufferedReader br = new BufferedReader(new FileReader("etablissements.json"));
            String line;
            while((line = br.readLine()) != null){
                BsonArray documents = BsonArray.parse(line);
                System.out.println(documents);
                for(BsonValue bson : documents.getValues()){
                    Document document = Document.parse(bson.toString());
                    Utils.LectureJSON_Etablissement(document, etablissementDao);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }




    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isStringUpperCase(String str){
        char[] charArray = str.toCharArray();

        for(int i=0; i < charArray.length; i++){
            if( !Character.isUpperCase( charArray[i] ))
                return false;
        }

        return true;
    }



    public static void LectureJSON_Etablissement(Document document, Dao<Etablissement> etablissementDao){
        try{
        //AJOUT ADRESSE

        //récup des fields pour l'adresse
        Document fields = (Document) document.get("fields");

        //Décomposition adresse
           int numero = 0,cp = 0;
           String voie = null,commune = null,departement = null;
           double longitude = 0,latitude = 0;



            if ((String) fields.get("adresse") != null){
                 commune = (String) fields.get("commune");
                departement = (String) fields.get("departement");
                longitude = (double) fields.get("longitude_x");
                latitude = (double) fields.get("latitude_y");


                String Adresse_base = (String) fields.get("adresse");
                String[] parties_adresse = Adresse_base.split("\\ ");


                //condition pour détecter si l'adresse possède un numéro de rue
                if (isNumeric(parties_adresse[0])) {
                    numero = Integer.parseInt(parties_adresse[0]);
                    voie = Adresse_base.substring(parties_adresse[0].length() - 1);
                } else {

                    numero = 0;
                    voie = Adresse_base;
                }

                //Certains Code postale sont non valides, on les instancie à 0.
                if (isNumeric((String) fields.get("cp"))) {
                    cp = Integer.parseInt(String.valueOf(fields.get("cp")));
                } else {
                    cp = 0;
                }
            }
            else{
            }

        //Décompostion du nom pour obtenir le SIGLE
            String nom_initial = (String) fields.get("nom");
            String[] nom_parties = nom_initial.split("\\ ");

            String sigle;
            String nom;

            //condition pour détecter si l'adresse possède un numéro de rue
            if (isStringUpperCase(nom_parties[0])){
                sigle = (nom_parties[0]);
                //nom = nom_initial.substring(nom_parties[0].length());
                nom = nom_initial;
            }
            else{

                sigle = "";
                nom = nom_initial;
            }




            //Déclaration de l'adresse
            Adresse ad1 = new Adresse( numero,
                voie,
                    commune,
               cp,
                    departement ,
                    longitude,
                latitude);
       // adresseDao.create(ad1);


        //AJOUT ETABLISSEMENT

            ArrayList<Etudiant> etudiants = new ArrayList<>();
            ArrayList<String> diplomes = new ArrayList<>();
            ArrayList<Formation> formations = new ArrayList<>();

            Etablissement eta1 = new Etablissement((String) fields.get("n_siret"),
                    sigle,
                    nom,
                    (String) fields.get("telephone"),
                    (String) fields.get("type_detablissement"),
                    (String) fields.get("statut"),
                    (String) fields.get("academie"),
                    ad1,
                    etudiants,
                    diplomes,
                    formations);
            etablissementDao.create(eta1);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }










    }
    //créé classe etab
    public static void generateXml(Etablissements etablissements) throws JAXBException, FileNotFoundException, TransformerException {
        JAXBContext context = JAXBContext.newInstance(etablissements.getClass());
        Marshaller marshaller = context.createMarshaller();
        OutputStream cut = new FileOutputStream("src/etablissement.xml");
        DOMResult domResult = new DOMResult();
        marshaller.marshal(etablissements,domResult);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


        transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(cut));

    }

}
