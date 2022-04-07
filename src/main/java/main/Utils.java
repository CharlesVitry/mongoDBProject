package main;


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

    public static boolean Jeux_De_Donnees_Ajouts(){





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

                    Utils.LectureJSON_Etablissement(document,etablissementDao);
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
