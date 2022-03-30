package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import model.Adresse;
import  model.Formation;
import model.Etablissement;
import org.bson.Document;

import java.util.ArrayList;



public class FormationDAO extends Dao<Formation>{

    MongoCollection<Document> collection = database.getCollection("etablissement");

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



    @Override
    public Formation find(Formation obj) {
        Document document = collection.find(Filters.eq("id_F", obj.getid_f())).first();
        Formation formation = new Formation(
                document.getInteger("id_F"),
                document.getString("Intitule"),
        ArrayList<String> ListeDisciplines = (ArrayList<String>) document.get("ListeDisciplines")
                );




        return obj;

    }






    @Override
    public ArrayList<Formation> findAll() {
        ArrayList<Formation> Formations = new ArrayList<Formation>();
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> cursor = documents.iterator();
        while(cursor.hasNext()){
            Document document = cursor.next();
            Formation formation = new Formation(
                     document.getInteger("id_F"),
                    document.getString("Intitule"),
                    ArrayList<String> ListeDisciplines = (ArrayList<String>) document.get("ListeDisciplines")
            );
            Formations.add(formation);
        }
        return Formations;
    }
}
