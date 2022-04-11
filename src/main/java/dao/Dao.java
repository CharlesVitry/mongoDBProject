package dao;

import com.mongodb.client.MongoDatabase;
import main.MongoDBConnection;
import model.Adresse;
import model.Etablissement;
import model.Etudiant;
import org.bson.Document;

import java.util.ArrayList;


public abstract class Dao<T> {

	// ici je récupère ma database qui est initialisée à l'aide de la classe que l'on a créé MongoDBConnection
	protected MongoDatabase database = MongoDBConnection.getDatabase();

    public abstract boolean create(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean update(T obj);
	public abstract T find(T obj);
	public abstract ArrayList<T> findAll();
	public abstract boolean DropCollection();
}
