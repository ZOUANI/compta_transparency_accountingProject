package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comptable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @OneToMany(mappedBy = "comptable")
    private List<Adherant> adherants;
    @OneToOne(mappedBy = "comptable")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "comptable")
    private List<Societe> societes;
    @OneToMany(mappedBy = "comptable")
    private List<Facture> factures;
    @OneToMany(mappedBy = "comptable")
    private List<Cpc> cpcs;
    @OneToMany(mappedBy = "comptable")
    private List<DeclarationTva> declarationTvas;
    @OneToMany(mappedBy = "comptable")
    private List<Client> clients;
    @OneToMany(mappedBy = "comptable")
    private List<Fournisseur> fournisseurs;
    @OneToMany(mappedBy = "comptable")
    private List<CompteBanquaire> compteBanquaires;
    @OneToMany(mappedBy = "comptable")
    private List<OperationComptableGroupe> operationComptableGroupes;


    public List<Societe> getSocietes() {
        return societes;
    }

    public void setSocietes(List<Societe> societes) {
        this.societes = societes;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public List<Cpc> getCpcs() {
        return cpcs;
    }

    public void setCpcs(List<Cpc> cpcs) {
        this.cpcs = cpcs;
    }

    public List<DeclarationTva> getDeclarationTvas() {
        return declarationTvas;
    }

    public void setDeclarationTvas(List<DeclarationTva> declarationTvas) {
        this.declarationTvas = declarationTvas;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public List<CompteBanquaire> getCompteBanquaires() {
        return compteBanquaires;
    }

    public void setCompteBanquaires(List<CompteBanquaire> compteBanquaires) {
        this.compteBanquaires = compteBanquaires;
    }

    public List<OperationComptableGroupe> getOperationComptableGroupes() {
        return operationComptableGroupes;
    }

    public void setOperationComptableGroupes(List<OperationComptableGroupe> operationComptableGroupes) {
        this.operationComptableGroupes = operationComptableGroupes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Adherant> getAdherants() {
        return adherants;
    }

    public void setAdherants(List<Adherant> adherants) {
        this.adherants = adherants;
    }
}
