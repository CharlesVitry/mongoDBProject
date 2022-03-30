package dao;

import model.Adresse;
import model.Etudiant;
import model.Formation;
import model.Etablissement;

public class DaoFactory {

	public static Dao<Adresse> getAdresseDAO(){
		return new AdresseDao();
	}

	public static Dao<Etudiant> getEtudiantDAO() { return new EtudiantDao(); }
	
}
