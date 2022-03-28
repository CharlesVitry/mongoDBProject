package model;

import java.util.ArrayList;

public class Formation {

    private String Intitule;
    private ArrayList<String> ListeDisciplines;


    public Formation(String Intitule,ArrayList<String> ListeDisciplines) {
        super();
        this.Intitule = Intitule;
        this.ListeDisciplines = ListeDisciplines;
    }

    public String getIntitule() {
        return Intitule;
    }
    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }
    public ArrayList<String> getListeDisciplines() {
        return ListeDisciplines;
    }
    public void setVoie(ArrayList<String> ListeDisciplines) {
        this.ListeDisciplines = ListeDisciplines;
    }


}
