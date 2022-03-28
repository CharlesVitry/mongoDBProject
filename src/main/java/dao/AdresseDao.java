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
