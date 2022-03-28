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
		document.put("Voie", obj.getVoie());
		document.put("Ville", obj.getVille());
		document.put("CodePostal", obj.getCodePostal());
		document.put("Departement", obj.getCodePostal());
		document.put("Longitude", obj.getLongitude());
		document.put("Latitude", obj.getLatitude());

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
		Document document = collection.find(Filters.eq("numero", adr.getNumero())).first();
		Adresse adresse = new Adresse(
				document.getInteger("numero"),
				document.getString("voie"),
				document.getString("ville"),
				document.getInteger("codePostal"),
				document.getString("departement"),
				document.getDouble("longitude"),
				document.getDouble("latitutde")
		);

		return adr;
	}

	@Override
	public ArrayList<Adresse> findAll() {
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		return adresses;
	}
}
