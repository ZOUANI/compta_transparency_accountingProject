package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class TauxTva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private Integer taux;
    @ManyToOne
    private Adherant adherant;
    @OneToMany(mappedBy = "tauxTva")
    private List<Facture> factures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TauxTva)) return false;
        TauxTva tauxTva = (TauxTva) o;
        return taux == tauxTva.taux &&
                Objects.equals(id, tauxTva.id) &&
                Objects.equals(libelle, tauxTva.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, taux);
    }

    @Override
    public String toString() {
        return "TauxTva{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", taux=" + taux +
                '}';
    }
}
