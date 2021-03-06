package com.zsmart.accountingProject.ws.rest.vo;


import java.util.List;

public class SocieteVo {
    private String id;
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
    private List<FactureVo> facturesVo;
    private AdherantVo adherantVo;
    private ComptableVo comptableVo;
    private String  contratBail;
    private String certificatNegatif;
    private String registreCommerceimage;
    private String patente;
    private String statuet;
    private String releverBanquaire;
    private String publicationCreationBo;

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

    public AdherantVo getAdherantVo() {
        return adherantVo;
    }

    public void setAdherantVo(AdherantVo adherantVo) {
        this.adherantVo = adherantVo;
    }

    public ComptableVo getComptableVo() {
        return comptableVo;
    }

    public void setComptableVo(ComptableVo comptableVo) {
        this.comptableVo = comptableVo;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
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

    public List<FactureVo> getFacturesVo() {
        return facturesVo;
    }

    public void setFacturesVo(List<FactureVo> facturesVo) {
        this.facturesVo = facturesVo;
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
}
