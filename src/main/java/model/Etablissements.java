package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement
public class Etablissements {



    @XmlElement
    private ArrayList<Etablissement> etablissements;

    public void setEtablissements(ArrayList<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }
    public ArrayList<Etablissement> getEtablissements() {
        return etablissements;
    }




}




