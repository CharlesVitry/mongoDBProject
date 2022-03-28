package dao;

import model.Adresse;

public class DaoFactory {

	public static Dao<Adresse> getAdresseDAO(){
		return new AdresseDao();
	}

	
	
}
