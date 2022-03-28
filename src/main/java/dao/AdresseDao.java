package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import model.Adresse;
import org.bson.Document;

import java.util.ArrayList;


public class AdresseDao extends Dao<Adresse>{

	MongoCollection<Document> collection = database.getCollection("restaurants");
	
	@Override
	public boolean create(Adresse obj) {
		Document document = new Document();
		document.put("numero", obj.getNumero());
		document.put("CodePostal", obj.getCodePostal());
		document.put("Ville", obj.getVille());
		document.put("Voie", obj.getVoie());
		collection.insertOne(document);

		return true;
	}

	@Override
	public boolean delete(Adresse obj) {
		return true;
	}

	@Override
	public boolean update(Adresse obj) {
		return true;
	}

	@Override
	public Adresse find(Adresse adr) {
		return adr;
	}

	@Override
	public ArrayList<Adresse> findAll() {
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		return adresses;
	}
}
