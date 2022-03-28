package main;

import com.mongodb.client.model.InsertOneModel;
import dao.Dao;
import dao.DaoFactory;
import model.Adresse;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * main class
 */
public class App 
{
    public static void main( String[] args )
    {
        Dao<Adresse> adresseDao = DaoFactory.getAdresseDAO();
    }
}
