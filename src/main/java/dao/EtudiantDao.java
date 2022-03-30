package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import model.Etudiant;
import model.Adresse;
import model.Formation;
import org.bson.Document;

import java.util.ArrayList;

public class EtudiantDao extends Dao<Etudiant> {

    MongoCollection<Document> collection = database.getCollection("etablissement");

    @Override
    public boolean create(Etudiant obj) {
        Document document = new Document();
        document.put("id_E", obj.getId_E());
        document.put("nom", obj.getNom());
        document.put("prenom", obj.getPrenom());
        document.put("adresse", obj.getAdresse());
        document.put("formation", obj.getFormation());
        document.put("present", obj.getPresent());

        collection.insertOne(document);
        System.out.println("Etudiant créé avec succès !");
        return true;
    }

    @Override
    public boolean delete(Etudiant obj) {

        return false;
    }

    @Override
    public boolean update(Etudiant obj) {
        return false;
    }

    @Override
    public Etudiant find(Etudiant et) {
        Document document = collection.find(Filters.eq("id_E", et.getId_E())).first();
        Adresse  adresseDoc = new Adresse (
                adresseDoc.getInteger("numero"),
                adresseDoc.getString("voie"),
                adresseDoc.getString("ville"),
                adresseDoc.getInteger("codePostal"),
                adresseDoc.getString("departement"),
                adresseDoc.getDouble("longitude"),
                adresseDoc.getDouble("latitude"));

        Etudiant etu = new Etudiant(
            document.getInteger("id_E"),
            document.getString("nom"),
            document.getString("prenom"),
            (Adresse) document.get("adresse"),
            (Formation) document.get("formation"),
            document.getString("present")

        );

        return et;
    }

    @Override
    public ArrayList<Etudiant> findAll() {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()){
            Document document = cursor.next();

            Etudiant etudiant = new Etudiant(
                    document.getInteger("id_E"),
                    document.getString("nom"),
                    document.getString("prenom"),

                    (Adresse) document.get("adresse"),
                    (Formation) document.get("formation"),
                    document.getString("present")

            );
            etudiants.add(etudiant);
        }
        return etudiants;
    }
}
