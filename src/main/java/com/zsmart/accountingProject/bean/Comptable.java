package com.zsmart.accountingProject.bean;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Comptable extends Utilisateur implements Serializable {


    @OneToMany(mappedBy = "comptable")
    private List<Adherant> adherants;
    @OneToMany(mappedBy = "comptable")
    private List<Societe> societes;


    public List<Societe> getSocietes() {
        return societes;
    }

    public void setSocietes(List<Societe> societes) {
        this.societes = societes;
    }



    public List<Adherant> getAdherants() {
        return adherants;
    }

    public void setAdherants(List<Adherant> adherants) {
        this.adherants = adherants;
    }
}
