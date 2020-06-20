package com.zsmart.accountingProject.bean;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Admin extends Utilisateur {
    @OneToMany
    private List<Adherant> adherants;
    @OneToMany
    private List<Comptable> comptables;

    public List<Adherant> getAdherants() {
        return adherants;
    }

    public void setAdherants(List<Adherant> adherants) {
        this.adherants = adherants;
    }

    public List<Comptable> getComptables() {
        return comptables;
    }

    public void setComptables(List<Comptable> comptables) {
        this.comptables = comptables;
    }
}
