package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import model.Etablissement;
import model.Etudiant;
import org.bson.Document;

import java.util.ArrayList;



public class EtablissementDao extends Dao<Etablissement>{

    MongoCollection<Document> collection = database.getCollection("etablissement");

    @Override
    public boolean create(Etudiant obj) {
        Document document = new Document();
        document.put("id_Eta", obj.getId_Eta());
        document.put("sigle", obj.getSigle());
        document.put("nom", obj.getNom());
        document.put("telephone", obj.getTelephone());
        document.put("TypeEtablissement", obj.getTypeEtablissement());
        document.put("statut", obj.getStatut());
        document.put("Universite_de_Rattachement", obj.getUniversite_de_Rattachement());
        document.put("adresse", obj.getAdresse());
        document.put("Liste_Etudiant", obj.getListe_Etudiant());
        document.put("Liste_De_Diplome", obj.getListe_De_Diplome());
        document.put("Liste_De_Formations", obj.getListe_De_Formations());

        collection.insertOne(document);
        System.out.println("Etablissement créer avec succès !");
        return true;
    }

    @Override
    public boolean delete(Etablissement obj) {
        return false;
    }

    @Override
    public boolean update(Etablissement obj) {
        return false;
    }

    @Override
    public Etablissement find(Etablissement obj) {
        Document document = collection.find(Filters.eq("numero", obj.getNumero())).first();
        Etablissement etablissement = new Etablissement(
                document.getInteger("id_Eta"),
                document.getString("sigle"),
                document.getString("nom"),
                document.getString("telephone"),
                document.getString("TypeEtablissement"),
                document.getString("statut"),
                document.getString("Universite_de_Rattachement"),
                document.get("adresse"  ),
                ArrayList<String> Liste_Etudiant = (ArrayList<String>) document.get("Liste_Etudiant") ,
                ArrayList<String> Liste_De_Diplome = (ArrayList<String>) document.get("Liste_De_Diplome") ,
                ArrayList<String> Liste_De_Formations = (ArrayList<String>) document.get("Liste_De_Formations") 

                );




        return obj;

    }






    @Override
    public ArrayList<Etablissement> findAll() {
        ArrayList<Etablissement> Etablissements = new ArrayList<Etablissement>();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()){
            Document document = cursor.next();
            Etablissement etablissement = new Etablissement(
                    document.getInteger("id_Eta"),
            document.getString("sigle"),
            document.getString("nom"),
            document.getString("telephone"),
            document.getString("TypeEtablissement"),
            document.getString("statut"),
            document.getString("Universite_de_Rattachement"),
            document.get("adresse"  ),
                    ArrayList<String> Liste_Etudiant = (ArrayList<String>) document.get("Liste_Etudiant") ,
            ArrayList<String> Liste_De_Diplome = (ArrayList<String>) document.get("Liste_De_Diplome") ,
            ArrayList<String> Liste_De_Formations = (ArrayList<String>) document.get("Liste_De_Formations")
            );
            Etablissements.add(etablissement);
        }
        return Etablissements;
    }
}
