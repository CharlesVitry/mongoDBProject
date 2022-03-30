package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import model.Etudiant;
import  model.Formation;
import model.Etablissement;
import org.bson.Document;

import java.util.ArrayList;



public class FormationDAO extends Dao<Formation>{

    MongoCollection<Document> collection = database.getCollection("formation");

    @Override
    public boolean create(Formation obj) {
        Document document = new Document();

        document.put("id_F", obj.getid_f());
        document.put("Intitule", obj.getIntitule());
        document.put("ListeDisciplines", obj.getListeDisciplines());



        collection.insertOne(document);
        System.out.println("Formation créer avec succès !");
        return true;
    }

    public boolean delete(Formation obj){return true; }

    public boolean update(Formation obj){return true; }

    @Override
    public Formation find(Formation obj) {
        Document document = collection.find(Filters.eq("id_F", obj.getid_f())).first();
        Formation formation = new Formation(
                document.getInteger("id_F"),
                document.getString("Intitule"),
                (ArrayList<String>) document.get("ListeDisciplines")
        );




        return formation;

    }






    @Override
    public ArrayList<Formation> findAll() {

        return None;
    }
}
