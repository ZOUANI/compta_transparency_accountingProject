package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Societe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String raisonSocial;
    private String ice;
    private String identifiantFiscal;
    
    @OneToMany(mappedBy = "societe")
    private List<Facture> factures;
    @OneToMany(mappedBy = "societe")
    private List<DeclarationTva> declarationTvas;

    public List<DeclarationTva> getDeclarationTvas() {
        return declarationTvas;
    }

    public void setDeclarationTvas(List<DeclarationTva> declarationTvas) {
        this.declarationTvas = declarationTvas;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public void setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Societe)) return false;
        Societe societe = (Societe) o;
        return id.equals(societe.id) &&
                Objects.equals(raisonSocial, societe.raisonSocial) &&
                Objects.equals(ice, societe.ice) &&
                Objects.equals(identifiantFiscal, societe.identifiantFiscal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Societe{" +
                "id=" + id +
                ", raisonSocial='" + raisonSocial + '\'' +
                ", ice='" + ice + '\'' +
                ", identifiantFiscal='" + identifiantFiscal + '\'' +
                '}';
    }
}
