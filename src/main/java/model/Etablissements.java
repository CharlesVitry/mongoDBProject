package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Etablissements {



    @XmlElement
    private ArrayList<Etablissement> etablissements;

    public Etablissements(ArrayList<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public Etablissements() {

    }

    public void setEtablissements(ArrayList<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }
    public ArrayList<Etablissement> getEtablissements() {
        return etablissements;
    }




}




