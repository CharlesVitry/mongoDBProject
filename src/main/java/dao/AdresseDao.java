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

	MongoCollection<Document> collection = database.getCollection("etablissement");
	
	@Override
	public boolean create(Adresse obj) {
		Document document = new Document();
		document.put("numero", obj.getNumero());
		document.put("voie", obj.getVoie());
		document.put("ville", obj.getVille());
		document.put("codePostal", obj.getCodePostal());
		document.put("departement", obj.getDepartement());
		document.put("longitude", obj.getLongitude());
		document.put("latitude", obj.getLatitude());

		collection.insertOne(document);
		System.out.println("Adresse créé avec succès !");
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
				document.getDouble("latitude")
		);

		return adr;
	}

	@Override
	public ArrayList<Adresse> findAll() {
		ArrayList<Adresse> adresses = new ArrayList<Adresse>();
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator();
		while(cursor.hasNext()){
			Document document = cursor.next();
			Adresse adresse = new Adresse(
					document.getInteger("numero"),
					document.getString("voie"),
					document.getString("ville"),
					document.getInteger("codePostal"),
					document.getString("departement"),
					document.getDouble("longitude"),
					document.getDouble("latitude")
			);
			adresses.add(adresse);
		}
		return adresses;
	}
}
