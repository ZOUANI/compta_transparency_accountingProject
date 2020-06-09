package com.zsmart.accountingProject.ws.rest.vo;


public class UtilisateurVo {
    private String id ;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String adresse;
    private String ville;
    private String role;
    private String datenaissance;
    private AdherantVo adherantVo;
    private ComptableVo comptableVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
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
}
