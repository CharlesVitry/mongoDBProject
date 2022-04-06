package main;

import dao.Dao;
import model.*;
import org.bson.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class LectureJSON {

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


    public static void LectureJSON_Etablissement(Document document, Dao<Etablissement> etablissementDao, Dao<Adresse> adresseDao){
        try{
        //AJOUT ADRESSE

        //récup des fields pour l'adresse
        Document fields = (Document) document.get("fields");

        // décomposition adresse
        String initial = (String) fields.get("adresse");
        String[] parties = initial.split("\\ ");

        int numero;
        String voie;
        int cp;

        if (isNumeric(parties[0])){
            numero = Integer.valueOf(parties[0]);
          voie = initial.substring(parties[1].length() -1);
        }
        else{

             numero = 0;
             voie = initial;
        }

        if(isNumeric((String) fields.get("cp"))){
             cp = Integer.parseInt(String.valueOf((String) fields.get("cp")));
        }
        else{
             cp = 0;
        }
//System.out.println(fields.get("cp"));
            Adresse ad1 = new Adresse( numero,
                voie,
                (String) fields.get("commune"),
               cp,
                (String) fields.get("departement"),
                (Double) fields.get("longitude_x"),
                (Double) fields.get("latitude_y"));
       // adresseDao.create(ad1);


        //AJOUT ETABLISSEMENT

            ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
            ArrayList<String> diplomes = new ArrayList<String>();
            ArrayList<Formation> formations = new ArrayList<Formation>();

            Etablissement eta1 = new Etablissement((String) fields.get("n_siret"),
                    "",
                    (String) fields.get("nom"),
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
        transformer.setOutputProperty("http://xml.apache.org/xslt}indent-amount","2");
        transformer.transform(new DOMSource(domResult.getNode()), new StreamResult(cut));

    }
    
}
