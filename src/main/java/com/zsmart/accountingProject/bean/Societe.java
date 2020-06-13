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
    private String nom;
    private String prenom;
    private String registreComerce;
    private String authentificationCnss;
    private String juridiction;
    private String statuejuridique;
    private String adresse;
    private String  contratBail;
    private String certificatNegatif;
    private String registreCommerceimage;
    private String patente;
    private String statuet;
    private String releverBanquaire;
    private String publicationCreationBo;
    @OneToMany(mappedBy = "societe")
    private List<Facture> factures;
    @OneToMany(mappedBy = "societe")
    private List<DeclarationTva> declarationTvas;
    @ManyToOne
    private Adherant adherant;
    @ManyToOne
    private Comptable comptable;

    public String getContratBail() {
        return contratBail;
    }

    public void setContratBail(String contratBail) {
        this.contratBail = contratBail;
    }

    public String getCertificatNegatif() {
        return certificatNegatif;
    }

    public void setCertificatNegatif(String certificatNegatif) {
        this.certificatNegatif = certificatNegatif;
    }

    public String getRegistreCommerceimage() {
        return registreCommerceimage;
    }

    public void setRegistreCommerceimage(String registreCommerceimage) {
        this.registreCommerceimage = registreCommerceimage;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getStatuet() {
        return statuet;
    }

    public void setStatuet(String statuet) {
        this.statuet = statuet;
    }

    public String getReleverBanquaire() {
        return releverBanquaire;
    }

    public void setReleverBanquaire(String releverBanquaire) {
        this.releverBanquaire = releverBanquaire;
    }

    public String getPublicationCreationBo() {
        return publicationCreationBo;
    }

    public void setPublicationCreationBo(String publicationCreationBo) {
        this.publicationCreationBo = publicationCreationBo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Comptable getComptable() {
        return comptable;
    }

    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRegistreComerce() {
        return registreComerce;
    }

    public void setRegistreComerce(String registreComerce) {
        this.registreComerce = registreComerce;
    }

    public String getAuthentificationCnss() {
        return authentificationCnss;
    }

    public void setAuthentificationCnss(String authentificationCnss) {
        this.authentificationCnss = authentificationCnss;
    }

    public String getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(String juridiction) {
        this.juridiction = juridiction;
    }

    public String getStatuejuridique() {
        return statuejuridique;
    }

    public void setStatuejuridique(String statuejuridique) {
        this.statuejuridique = statuejuridique;
    }

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
